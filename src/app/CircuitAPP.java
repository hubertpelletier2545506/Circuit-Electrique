package app;

import composants.*;
import enums.TypeEnergie;
import enums.TypeProtection;
import enums.Voltage;

import java.io.File;
import java.util.*;

public class CircuitAPP {
    private static final Scanner scanner = new Scanner(System.in);
    private static final char fSep = File.separatorChar;
    private static final String pathIn = System.getProperty("user.dir") + fSep + "src" + fSep + "donnees" + fSep;

    private static File selectionnerFichier() {
        File dossier = new File(pathIn);

        File[] fichiers = dossier.listFiles(((dir, name) -> name.endsWith(".json")));

        if (fichiers == null || fichiers.length == 0) {
            System.out.println("Aucun fichier .json trouvé dans le dossier.");
            return null;
        }

        Map<Integer, File> fichiersDisponibles = new HashMap<>();
        for (int i = 0; i < fichiers.length; i++) {
            fichiersDisponibles.put(i + 1, fichiers[i]);
        }
        while (true) {
            System.out.println("--- LISTE DES CIRCUITS ---");
            for (Map.Entry<Integer, File> entry : fichiersDisponibles.entrySet()) {
                System.out.println("[" + entry.getKey() + "] " + entry.getValue().getName());
            }
            System.out.println("Entrez le numéro du fichier à tester :");
            if (scanner.hasNextInt()) {
                int choix = scanner.nextInt();
                scanner.nextLine();
                File fichierChoisi = fichiersDisponibles.get(choix);
                if (fichierChoisi != null) {
                    return fichierChoisi;
                }
            } else {
                scanner.nextLine();
            }
            System.out.println("Choix incorrect. Veuillez entrer un chiffre entre 1 et " + fichiersDisponibles.size() + ".");
        }
    }

    private static void afficherResultat(double resistance, double amperage, double wattage, Voltage voltage) {
        System.out.println("===============================================");
        System.out.println(" Différence de potentiel: " + voltage.getValeurVoltage() + "V");
        System.out.println(" Résistance équivalente: " + String.format("%.2f", resistance) + "Ω");
        System.out.println(" Ampérage du circuit: " + String.format("%.2f", amperage) + "A");
        System.out.println(" Puissance du circuit: " + String.format("%.2f", wattage) + "W");
        System.out.println("===============================================");
    }

    private static String afficherDebutProgramme() {

        return "===============================================\nMenu principal\n===============================================\nVeuillez choisir parmi les deux options suivantes: \n[1] Tester un fichier existant\n[2] Créer un nouveau circuit en série\n[3] Exporter un fichier existant\n[4] Quitter";
    }

    public static int lireInt(String message) {
        while (true) {
            System.out.println(message);

            if (scanner.hasNextInt()) {
                int valeur = scanner.nextInt();
                scanner.nextLine();
                return valeur;
            } else {
                System.out.println("Entrée invalide. Veuillez entrer un nombre entier.");
                scanner.nextLine();
            }
        }
    }

    public static double lireDouble(String message) {
        while (true) {
            System.out.println(message);

            if (scanner.hasNextDouble()) {
                double valeur = scanner.nextDouble();
                scanner.nextLine();
                return valeur;
            } else {
                System.out.println("Entrée invalide. Veuillez entrer un nombre décimal.");
                scanner.nextLine();
            }
        }
    }

    public static String lireString(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    public static int lireIntervalle(String message, int nombreOptions) {

        while (true) {

            int valeur = lireInt(message);

            if (valeur > 0 && valeur <= nombreOptions) {
                return valeur;
            }

            System.out.println("Veuillez entrer un nombre entre " + 1 + " et " + nombreOptions);
        }
    }

    //regrouper les méthodes afficher et retourner

    private static String afficherVoltage() {

        StringBuilder chaine = new StringBuilder();

        int i = 1;

        for (Voltage voltage : Voltage.values()) {

            chaine.append("[")
                    .append(i++)
                    .append("] ")
                    .append(voltage)
                    .append(System.lineSeparator());
        }

        return chaine.toString();
    }

    private static Voltage retournerVoltage(int numeroSelectionne) {

        return Voltage.values()[numeroSelectionne - 1];

    }

    private static String afficherTypesEnergie() {

        StringBuilder chaine = new StringBuilder();

        int i = 1;

        for (TypeEnergie typeEnergie : TypeEnergie.values()) {

            chaine.append("[")
                    .append(i++)
                    .append("] ")
                    .append(typeEnergie)
                    .append(System.lineSeparator());
        }

        return chaine.toString();

    }

    private static TypeEnergie retournerTypeEnergie(int numeroSelectionne) {

        return TypeEnergie.values()[numeroSelectionne - 1];

    }

    private static String afficherProtections() {

        StringBuilder chaine = new StringBuilder();

        int i = 1;

        for (TypeProtection protection : TypeProtection.values()) {

            chaine.append("[")
                    .append(i++)
                    .append("] ")
                    .append(protection)
                    .append(System.lineSeparator());
        }

        return chaine.toString();

    }

    private static TypeProtection retournerProtection(int numeroSelectionne) {

        return TypeProtection.values()[numeroSelectionne - 1];

    }

    public static void main(String[] args) {

        CircuitBuilder builder = new CircuitBuilder();
        List<Composant> listeComposants = new ArrayList<>();
        int numeroSelectionne;
        boolean recommencer = false;
        boolean presenceProtection = false;

        Protection protection = new Protection(0, null);
        while (!recommencer) {

            int option = lireIntervalle(afficherDebutProgramme(), 3);

            if (option == 1) {

                recommencer = true;

                while (recommencer) {
                    File fichier = selectionnerFichier();

                    if (fichier != null) {
                        Composant circuit = builder.construireCircuit(fichier.getName());
                        if (circuit != null) {
                            double resistance = circuit.calculerResistance();
                            double amperage = ((Circuit) circuit).calculerAmperage();
                            double wattage = ((Circuit) circuit).calculerWattage();
                            Voltage voltage = circuit.getVoltage(); //à optimiser, méthode qui retourne un tableau?
                            System.out.println("\nRésultat pour : " + fichier.getName() + "\n");
                            afficherResultat(resistance, amperage, wattage, voltage);
                        }
                    }

                    numeroSelectionne = lireIntervalle("\n[1] Tester un autre fichier | [2] Retourner au menu principal", 2);
                    if (numeroSelectionne == 2) {
                        recommencer = false;
                    }
                }

            } else if (option == 2) {

                recommencer = true;

                while (recommencer) {

                    System.out.println("\n--- CRÉATION D'UN NOUVEAU CIRCUIT EN SÉRIE ---");
                    System.out.println("\nVeuillez choisir la différence de potentiel du circuit: ");

                    afficherVoltage();
                    numeroSelectionne = lireIntervalle(afficherVoltage(), 3);
                    Voltage voltage = retournerVoltage(numeroSelectionne);

                    int choixProtection = lireIntervalle("\nVoulez-vous ajouter une protection au circuit?\n[1] Oui\n[2] Non", 2);

                    if (choixProtection == 1) {

                        System.out.println("\nVeuillez choisir le type de protection");

                        numeroSelectionne = lireIntervalle(afficherProtections(), 2);

                        TypeProtection typeProtection = retournerProtection(numeroSelectionne);

                        int amperageMax = lireInt("\nVeuillez choisir l'ampérage maximal de la protection");

                        protection.setTypeProtection(typeProtection);
                        protection.setAmperageMax(amperageMax);

                        presenceProtection = true;
                    }


                    numeroSelectionne = lireIntervalle("\nVoulez-vous ajouter de nouveaux éléments au circuit?\n[1] Oui, ajouter une nouvelle charge ou résistance\n[2] Non, créer le circuit", 2);

                    while (numeroSelectionne == 1) {

                        numeroSelectionne = lireIntervalle("\n[1] Ajouter une résistance\n[2] Ajouter une charge", 2);

                        if (numeroSelectionne == 1) {
                            System.out.println("\nQuelle est la valeur de résistance de la résistance?");
                            double valeurResistance = scanner.nextDouble();

                            Resistance resistance = new Resistance(voltage, valeurResistance);

                            listeComposants.add(resistance);

                        } else if (numeroSelectionne == 2) {

                            String nom = lireString("\nQuel est le nom de la charge?");

                            Double valeurResistance = lireDouble("\nQuelle est la valeur de résistance de la charge?");

                            System.out.println("\nEn quel type d'énergie, autre que thermique, la charge transforme-t-elle l'énergie électrique?");
                            numeroSelectionne = lireIntervalle(afficherTypesEnergie(), 6); //thermique??

                            Charge charge = new Charge(voltage, valeurResistance, retournerTypeEnergie(numeroSelectionne), nom);

                            listeComposants.add(charge);
                        }

                        numeroSelectionne = lireIntervalle("\n[1] Ajouter un autre élément\n[2] Créer le circuit", 2);
                    }

                    if (numeroSelectionne == 2) {
                        System.out.println("\n--- CRÉATION DU CIRCUIT EN COURS ---\n");
                        if (presenceProtection) {
                            CircuitSerie circuitSerieAvecProtection = new CircuitSerie(listeComposants, voltage, protection, true);
                            afficherResultat(circuitSerieAvecProtection.calculerResistance(), circuitSerieAvecProtection.calculerAmperage(), circuitSerieAvecProtection.calculerWattage(), circuitSerieAvecProtection.getVoltage());
                            System.out.println("\nComposantes du circuit: " + listeComposants);
                        } else {
                            CircuitSerie circuitSerieSansProtection = new CircuitSerie(listeComposants, voltage, true);
                            afficherResultat(circuitSerieSansProtection.calculerResistance(), circuitSerieSansProtection.calculerAmperage(), circuitSerieSansProtection.calculerWattage(), circuitSerieSansProtection.getVoltage());
                            System.out.println("\nComposantes du circuit: " + listeComposants);
                        }

                        numeroSelectionne = lireIntervalle("\n[1] Créer un autre circuit | [2] Retourner au menu principal", 2);
                        if (numeroSelectionne == 2) {
                            recommencer = false;
                        }

                    }
                }

            }
            if (option == 3){
                recommencer = true;

                while(recommencer) {
                    System.out.println("--- EXPORTATION ---");
                    File fichier = selectionnerFichier();
                    if (fichier != null) {
                        Composant circuit = builder.construireCircuit(fichier.getName());
                        builder.exporterCSV(circuit, "resultat.csv");
                    }
                    numeroSelectionne = lireIntervalle("\n[1] Exporter un autre fichier | [2] Retourner au menu principal", 2);
                    if (numeroSelectionne == 2) {
                        recommencer = false;
                    }
                }

            } else if (option == 4) {
                System.out.println("\n===============================================");
                System.out.println("Fin du programme");
                System.out.println("===============================================");
                recommencer = true;
            }
        }

    }


}
