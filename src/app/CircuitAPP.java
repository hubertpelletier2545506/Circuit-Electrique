package app;

import composants.Composant;

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
    private static void afficherResultat(String nomFichier, double resultat){
        System.out.println("===============================================");
        System.out.println(" Résultat pour : " + nomFichier);
        System.out.println(" Résistance équivalente : " + String.format("%.2f",resultat) + "Ω");
        System.out.println("===============================================");
    }


}
