package tests;

import composants.*;
import enums.TypeProtection;
import enums.Voltage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Classe de tests unitaires pour la classe CircuitParallele.
 * <p>
 * Cette classe vérifie le bon fonctionnement des méthodes
 * de la classe CircuitParallele.
 *
 * @author code par Maxime et Eric
 * @author Javadoc ChatGPT par Hubert
 */
class CircuitParalleleTest {
    /**
     * Vérifie le calcul de la résistance totale
     * lorsque l'interrupteur est allumé.
     */
    @Test
    void calculerResistanceInterrupteurAllume() {
        Resistance r = new Resistance(Voltage.VOLTAGE_STANDARD, 10);
        Resistance r2 = new Resistance(Voltage.VOLTAGE_STANDARD, 20);
        List<Composant> composants = new ArrayList<>();
        composants.add(r);
        composants.add(r2);
        Protection p1 = new Protection(10000, TypeProtection.FUSIBLE);

        CircuitParallele c1 = new CircuitParallele(composants, Voltage.VOLTAGE_STANDARD, p1, true);
        double resistanceC1 = c1.calculerResistance();
        double reponseAttendue = 6.67;

        assertEquals(reponseAttendue, resistanceC1);
    }

    /**
     * Vérifie que la résistance totale est nulle
     * lorsque l'interrupteur est éteint.
     */
    @Test
    void calculerResistanceInterrupteurEteint() {
        List<Composant> composants = new ArrayList<>();
        composants.add(new Resistance(Voltage.VOLTAGE_STANDARD, 10));

        CircuitParallele circuit = new CircuitParallele(composants, Voltage.VOLTAGE_STANDARD, false);

        assertEquals(0, circuit.calculerResistance());
    }

    /**
     * Vérifie le calcul de la résistance totale d'un circuit parallele
     * lorsqu'un composant possède une résistance de zéro ohm.
     */
    @Test
    void calculerResistanceAvecComposantZeroOhm() {
        List<Composant> composants = new ArrayList<>();
        composants.add(new Resistance(Voltage.VOLTAGE_STANDARD, 10));
        composants.add(new Resistance(Voltage.VOLTAGE_STANDARD, 0));

        CircuitParallele circuit = new CircuitParallele(composants, Voltage.VOLTAGE_STANDARD, true);


        assertEquals(10.0, circuit.calculerResistance());
    }
}