package app;

import composants.Composant;

import java.util.Scanner;

public class CircuitAPP {
    static void main() {

        System.out.println("====== Information sur les circuits ======");

        Scanner sc = new Scanner(System.in);
        boolean continuer = true;

        String[] fichier = {
//                "Circuit1.csv",
//                "Circuit2.csv",
        };

        while (continuer) {
            System.out.println("\n ==== CHOIX DU CIRCUIT ====");

            for (int i = 0; i < fichier.length; i++) {
                System.out.println("[" + (i + 1) + "] " + fichier[i]);
            }

            System.out.println("Choix : ");

            try {
                int choix = Integer.parseInt(sc.nextLine());

                if (choix < 1 || choix > fichier.length) {
                    System.out.println("Choix invalide !");
                }

                //Composant circuit = CircuitBuilder.construireCircuit(fichiers[choix - 1]);

        }

    }
}
