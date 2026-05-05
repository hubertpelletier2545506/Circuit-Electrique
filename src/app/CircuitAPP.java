package app;

import composants.Circuit;
import composants.Composant;
import enums.TypeEnergie;
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
        System.out.println(" Différence de potentiel: " + voltage.getVoltage() + "V");
        System.out.println(" Résistance équivalente: " + String.format("%.2f",resistance) + "Ω");
        System.out.println(" Ampérage du circuit: " + String.format("%.2f", amperage) + "A");
        System.out.println(" Puissance du circuit: " + String.format("%.2f", wattage) + "W");
        System.out.println("===============================================");
    }

    private static void afficherDebutProgramme(){
        System.out.println("===============================================");
        System.out.println("Début du programme");
        System.out.println("===============================================");
        System.out.println("Veuillez choisir parmi les deux options suivantes: \n[1] Tester un fichier existant\n[2] Créer un nouveau circuit");
    }

    private static boolean demanderSiContinuer(){
        while(true){
            System.out.println("\n[1] Tester un autre fichier | [2] Quitter : ");
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

    private static void afficherTypesEnergie (){

        int i = 1;

        for(TypeEnergie typeEnergie : TypeEnergie.values()){

            System.out.println("[" + i + "] " + typeEnergie);
            i++;
        }
    }  private static void retournerTypeEnergie (int numeroSelectionne){


    }

    public static void main(String[] args) {

        CircuitBuilder builder = new CircuitBuilder();
        boolean continuer = true;

        while(true) {
            afficherDebutProgramme();

            int option = scanner.nextInt();

            if (option == 1) {

                while (continuer) {
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
                    continuer = demanderSiContinuer();
                }
                System.out.println("Programme arrêté");

            } else if (option == 2) {

            } else {
                System.out.println("Option non reconnue. Utilisez 1 ou 2.");
            }

        }


    }


}
