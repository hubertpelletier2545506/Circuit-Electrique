package app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import composants.*;
import enums.TypeEnergie;
import enums.TypeProtection;
import enums.Voltage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CircuitBuilder {
    private static final char fSep = File.separatorChar;
    public static final String pathIn = System.getProperty("user.dir") + fSep +"src" + fSep + "donnees";

    public Composant construireCircuit (String nomFichier) {

        ObjectMapper mapper = new ObjectMapper();

        try{
            JsonNode donneesCircuits = mapper.readTree(new File(pathIn + fSep + nomFichier));
            return lireComposant(donneesCircuits.get("circuit"));
        } catch (IOException e) {
            System.out.println("Erreur de lecture du fichier : " + e.getMessage());
            return null;
        } catch (ArithmeticException | IllegalArgumentException e) {
            System.out.println("Erreur lors de la construction du circuit : " + e.getMessage());
            System.out.println("Veuillez vérifier l'intégrité du fichier JSON.");
            return null;
        }
    }


    private Composant lireComposant (JsonNode node) {

        String type = node.get("type").asText();

        if ("charge".equals(type)) {
            return new Charge(Voltage.valueOf(node.get("voltage").asText()), node.get("valeur").asDouble(), TypeEnergie.valueOf(node.get("typeEnergie").asText()), node.get("nom").asText());

        } else if ("resistance".equals((type))){
            return new Resistance(Voltage.valueOf(node.get("voltage").asText()), node.get("valeur").asDouble());
        }
        else if ("serie".equals(type) || "parallele".equals(type)) {

            // 1. Lire la liste des composants
            List<Composant> composants = new ArrayList<>();
            for(JsonNode composantNode : node.get("composants")) {
                composants.add(lireComposant(composantNode));
            }

            // 2. Lire le voltage
            Voltage voltage = Voltage.valueOf(node.get("voltage").asText());

            // 3. Vérifier et lire l'interrupteur (true par défaut si absent)
            boolean interrupteurAllume = true;
            if (node.has("interrupteurAllume")) {
                interrupteurAllume = node.get("interrupteurAllume").asBoolean();
            }

            // 4. Vérifier et lire la protection si elle existe
            Protection protection = null;
            if (node.has("protection")) {
                JsonNode protNode = node.get("protection");
                protection = new Protection(protNode.get("valeur").asInt(), TypeProtection.valueOf(protNode.get("type").asText()));
            }

            // 5. Instancier le bon type de circuit avec le bon constructeur
            if ("serie".equals(type)) {
                if (protection != null) {
                    return new CircuitSerie(composants, voltage, protection, interrupteurAllume);
                } else {
                    // Utilise le constructeur à 3 paramètres
                    return new CircuitSerie(composants, voltage, interrupteurAllume);
                }
            } else { // C'est un circuit parallèle
                if (protection != null) {
                    return new CircuitParallele(composants, voltage, protection, interrupteurAllume);
                } else {
                    return new CircuitParallele(composants, voltage, interrupteurAllume);
                }
            }
        }

        throw new IllegalArgumentException("Type de composant inconnu:" + type);
    }

    // récursivité ?
    public void ecritureComposantCSV(Composant composant, PrintWriter writer) {
        String type = composant.getClass().getSimpleName();
        double resistance = composant.calculerResistance();
        String voltage = composant.getVoltage().toString();

        writer.println(type + ";" + resistance + ";" + voltage);
    }
    public void exporterCSV(Composant composant, String nomFichier) {
        try (PrintWriter writer = new PrintWriter(new File(pathIn + fSep + nomFichier))){
            writer.println("Type;Resistance;Voltage");
            ecritureComposantCSV(composant, writer);
            System.out.println("Exportation CSV réussi : " + nomFichier);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}