package app;

import composants.Circuit;
import composants.Composant;
import composants.Protection;
import enums.TypeEnergie;
import enums.TypeProtection;
import enums.Voltage;

import java.io.File;
import java.util.Scanner;

public class CircuitAPP {
    private static final Scanner scanner = new Scanner(System.in);
    private static final char fSep = File.separatorChar;
    private static final String pathIn = System.getProperty("user.dir") + fSep + "src" + fSep + "donnees" + fSep;

    private static File selectionnerFichier() {
        File dossier = new File(pathIn);

        File[] fichiers = dossier.listFiles(((dir, name) -> name.endsWith(".json")));

        if (fichiers == null || fichiers.length == 0) {
            return null;
        }
        while (true) {
            System.out.println("--- LISTE DES CIRCUITS ---");
            for (int i = 0; i < fichiers.length; i++) {
                System.out.println("[" + (i + 1) + "] " + fichiers[i].getName());

            }
            System.out.println("Entrez le numéro du fichier à tester :");
            if (scanner.hasNextInt()) {
                int fichierALire = scanner.nextInt();
                scanner.nextLine();

                if (fichierALire >= 1 && fichierALire <= fichiers.length) {
                    return fichiers[fichierALire - 1];
                }
            } else {
                scanner.nextLine();
            }
            System.out.println("Choix incorrect. Veuillez entrer un chiffre entre 1 et " + fichiers.length + ".");
        }
    }
    private static void afficherResultat(String nomFichier, double resistance, double amperage, double wattage, Voltage voltage){
        System.out.println("===============================================");
        System.out.println(" Résultat pour : " + nomFichier);
        System.out.println(" Différence de potentiel: " + voltage.getValeurVoltage() + "V");
        System.out.println(" Résistance équivalente: " + String.format("%.2f",resistance) + "Ω");
        System.out.println(" Ampérage du circuit: " + String.format("%.2f", amperage) + "A");
        System.out.println(" Puissance du circuit: " + String.format("%.2f", wattage) + "W");
        System.out.println("===============================================");
    }

    private static void afficherDebutProgramme(){
        System.out.println("===============================================");
        System.out.println("Menu principal");
        System.out.println("===============================================");
        System.out.println("Veuillez choisir parmi les deux options suivantes: \n[1] Tester un fichier existant\n[2] Créer un nouveau circuit en série\n[3] Quitter");
    }

    private static boolean demanderTesterOuRetourner(){
        while(true){
            System.out.println("\n[1] Tester un autre fichier | [2] Retourner au menu principal ");
            int continuer = scanner.nextInt();

            if(continuer == 1){
                return true;
            }
            else if (continuer == 2){
                return false;
            }
            System.out.println("Option non reconnue. Utilisez 1 ou 2.");
        }
    }

    private static boolean validerIntervalle(int nombreOptions, int numeroSelectionne){

        if(numeroSelectionne > 0 && numeroSelectionne <= nombreOptions){
            return true;
        }

        System.out.println("Option non reconnue. Veuillez entrer un nombre entre 1 et " + nombreOptions);

        return false;

    }

    private static void afficherVoltage(){

        int i = 1;

        for (Voltage voltage : Voltage.values()){

            System.out.println("[" + i + "] " + voltage);
            i++;
        }

    }

    private static Voltage retournerVoltage(int numeroSelectionne){

        return Voltage.values()[numeroSelectionne -1];

    }

    private static void afficherTypesEnergie (){

        int i = 1;

        for(TypeEnergie typeEnergie : TypeEnergie.values()){

            System.out.println("[" + i + "] " + typeEnergie);
            i++;
        }
    }  private static TypeEnergie retournerTypeEnergie (int numeroSelectionne){

        return TypeEnergie.values()[numeroSelectionne -1];

    }

    private static void afficherProtections(){

        int i = 1;

        for (TypeProtection typeProtection : TypeProtection.values()){

            System.out.println("[" + i + "] " + typeProtection);
            i++;
        }

    }

    private static TypeProtection retournerProtection (int numeroSelectionne){

        return TypeProtection.values()[numeroSelectionne -1];

    }

    public static void main(String[] args) {

        CircuitBuilder builder = new CircuitBuilder();
        boolean testerOuCreer = true;
        boolean testerOuRetourner = true;
        boolean choisirProtection = true;

        while(testerOuCreer) {
            afficherDebutProgramme();

            int option = scanner.nextInt();

            if (option == 1) {

                while (testerOuRetourner) {
                    File fichier = selectionnerFichier();

                    if (fichier != null) {
                        Composant circuit = builder.construireCircuit(fichier.getName());

                        if (circuit != null) {
                            double resistance = circuit.calculerResistance();
                            double amperage = ((Circuit) circuit).calculerAmperage();
                            double wattage = ((Circuit) circuit).calculerWattage();
                            Voltage voltage = circuit.getVoltage();
                            afficherResultat(fichier.getName(), resistance, amperage, wattage, voltage);
                        }
                    }
                    testerOuRetourner = demanderTesterOuRetourner();
                }
                testerOuCreer = true;

            } else if (option == 2) {

                int numeroSelectionne = 0;
                boolean validerNumeroSelectionne = false;

                System.out.println("--- CRÉATION D'UN NOUVEAU CIRCUIT EN SÉRIE ---");
                System.out.println("Veuillez choisir la différence de potentiel du circuit: ");

                while(!validerNumeroSelectionne) {

                    afficherVoltage();
                    numeroSelectionne = scanner.nextInt();
                    validerNumeroSelectionne = validerIntervalle(3, numeroSelectionne);
                }

                Voltage voltage = retournerVoltage(numeroSelectionne);

                validerNumeroSelectionne = false;

                System.out.println("Voulez-vous ajouter une protection au circuit?");

                while(choisirProtection) {

                    System.out.println("[1] Oui\n[2] Non");
                    int choixProtection = scanner.nextInt();

                    if (choixProtection == 1) {

                        System.out.println("Veuillez choisir le type de protection");

                        while (!validerNumeroSelectionne) {
                            afficherProtections();
                            numeroSelectionne = scanner.nextInt();
                            validerNumeroSelectionne = validerIntervalle(2, numeroSelectionne);

                        }

                        TypeProtection typeProtection = retournerProtection(numeroSelectionne);

                        System.out.println("Veuillez choisir l'ampérage maximal de la protection");
                        int amperageMax = scanner.nextInt();

                        Protection protection = new Protection(amperageMax,typeProtection);

                        choisirProtection = false;

                    } else if (choixProtection == 2) {


                        choisirProtection = false;

                    } else {
                        System.out.println("Option non reconnue. Utilisez 1 ou 2.");

                    }
                }

            }

            else if (option == 3){
                System.out.println("===============================================");
                System.out.println("Fin du programme");
                System.out.println("===============================================");
                testerOuCreer = false;

            } else {
                System.out.println("Option non reconnue. Utilisez 1, 2 ou 3.");
                testerOuCreer = true;
            }

        }


    }


}
