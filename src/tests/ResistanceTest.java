package tests;

import composants.Resistance;
import enums.Voltage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de tests unitaires pour la classe Resistance.
 *
 * Cette classe vérifie le bon fonctionnement des méthodes
 * de la classe Resistance.
 *
 * @author code Maxime Filion
 * @author javadoc chatGPT par Maxime Filion
 */
class ResistanceTest {

    /**
     * Vérifie que la méthode calculerResistance retourne
     * la bonne valeur de résistance.
     */
    @Test
    void calculerResistance() {
        Resistance resistance = new Resistance(Voltage.VOLTAGE_STANDARD, 15);
        assertEquals(15, resistance.calculerResistance());
    }

    /**
     * Vérifie que la méthode getVoltage retourne
     * le bon type de voltage.
     */
    @Test
    void getVoltage() {
        Resistance resistance2 = new Resistance(Voltage.VOLTAGE_ELEVE, 10);
        assertEquals(Voltage.VOLTAGE_ELEVE, resistance2.getVoltage());
    }

    /**
     * Vérifie que la méthode getResistance retourne
     * la bonne valeur de résistance.
     */
    @Test
    void getResistance() {
        Resistance resistance3 = new Resistance(Voltage.VOLTAGE_BAS, 20);
        assertEquals(20, resistance3.getResistance());
    }

    /**
     * Vérifie que la méthode getResistance retourne
     * la valeur de resistance de base, car la valeur entré n'est pas valide
     */
    @Test
    void getResistanceNegative(){
        Resistance resistance3 = new Resistance(Voltage.VOLTAGE_BAS, -5);
        assertEquals(0, resistance3.getResistance());
    }

    /**
     * Vérifie que la méthode toString retourne
     * la chaîne de caractères attendue.
     */
    @Test
    void testToString() {
        Resistance resistance4 = new Resistance(Voltage.VOLTAGE_STANDARD, 10);
        assertEquals("Numéro de résistance: 2 | Résistance: 10.0 Ω | Type d'énergie: THERMIQUE | Différence de potentiel 120V\n", resistance4.toString());
    }

}