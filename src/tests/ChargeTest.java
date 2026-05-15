package tests;

import composants.Charge;
import enums.TypeEnergie;
import enums.Voltage;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de tests unitaires pour la classe Charge.
 * <p>
 * Cette classe vérifie le bon fonctionnement des méthodes
 * de la classe Charge.
 *
 * @author code Eric Burton-Labrecque
 * @author Javadoc ChatGPT par Hubert Pelletier
 */

class ChargeTest {
    /**
     * Réinitialise le compteur des charges avant chaque test.
     */
    @BeforeEach
    void setUp() {
        Charge.resetCompteur();
    }

    /**
     * Vérifie que le type d'énergie secondaire
     * d'une charge est correctement retourné.
     */
    @org.junit.jupiter.api.Test
    void getAutreTypeEnergie() {
        Charge charge = new Charge(Voltage.VOLTAGE_STANDARD, 10000, TypeEnergie.MECANIQUE, "Moteur");
        assertEquals(TypeEnergie.MECANIQUE, charge.getAutreTypeEnergie());
    }

    /**
     * Vérifie que le nom de la charge
     * est correctement retourné.
     */
    @org.junit.jupiter.api.Test
    void getNomCharge() {
        Charge charge2 = new Charge(Voltage.VOLTAGE_STANDARD, 10000, TypeEnergie.MECANIQUE, "Moteur");
        assertEquals("Moteur", charge2.getNomCharge());
    }

    /**
     * Vérifie que les identifiants des charges
     * sont générés correctement.
     */
    @org.junit.jupiter.api.Test
    void getChargeID() {
        Charge charge3 = new Charge(Voltage.VOLTAGE_STANDARD, 10000, TypeEnergie.MECANIQUE, "Moteur");
        Charge charge4 = new Charge(Voltage.VOLTAGE_STANDARD, 10000, TypeEnergie.RAYONNANTE, "Lumière");

        assertEquals(1, charge3.getChargeID());
        assertEquals(2, charge4.getChargeID());

    }

    /**
     * Vérifie que la représentation textuelle
     * d'une charge est correcte.
     */
    @org.junit.jupiter.api.Test
    void testToString() {
        Charge charge = new Charge(Voltage.VOLTAGE_STANDARD, 10000, TypeEnergie.MECANIQUE, "Moteur");
        assertEquals("Numéro de charge: 1 | Nom: Moteur | Résistance: 10000.0 Ω | Types d'énergie: THERMIQUE - MECANIQUE | Différence de potentiel 120V\n", charge.toString());
    }
}