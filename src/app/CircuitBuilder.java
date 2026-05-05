package app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import composants.*;
import enums.TypeEnergie;
import enums.TypeProtection;
import enums.Voltage;

import java.io.File;
import java.io.IOException;
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
            System.err.println("Erreur de lecture : " + e.getMessage());
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

        else if ("serie".equals(type)) {
            List<Composant> composants = new ArrayList<>();
            for(JsonNode composantNode : node.get("composants")) {
                composants.add(lireComposant(composantNode));
            }
            Protection protection = new Protection(node.get("protection").get("valeur").asInt(), TypeProtection.valueOf(node.get("protection").get("type").asText()));
            return new CircuitSerie(composants,Voltage.valueOf(node.get("voltage").asText()), protection, node.get("interrupteurAllume").asBoolean());
        } else if ("parallele".equals(type)) {
            List<Composant> composants = new ArrayList<>();
            for(JsonNode composantNode : node.get("composants")) {
                composants.add(lireComposant(composantNode));
            }
            Protection protection = new Protection(node.get("protection").get("valeur").asInt(), TypeProtection.valueOf(node.get("protection").get("type").asText()));
            return new CircuitSerie(composants,Voltage.valueOf(node.get("voltage").asText()), protection, node.get("interrupteurAllume").asBoolean());
        }
        throw new IllegalArgumentException("Type de composant inconnu:" + type);
    }
}
