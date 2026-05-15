package tests;

import composants.*;
import enums.TypeProtection;
import enums.Voltage;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de tests unitaires pour la classe CircuitSerie.
 * <p>
 * Cette classe vérifie le bon fonctionnement des méthodes
 * de la classe CircuitSerie.
 *
 * @author code Hubert et Eric
 * @author Javadoc ChatGPT par Hubert
 */
class CircuitSerieTest {

    /**
     * Vérifie le calcul de la résistance totale
     * lorsque l'interrupteur est allumé.
     */
    @org.junit.jupiter.api.Test
    void calculerResistanceInterrupteurAllume() {
        Resistance r = new Resistance(Voltage.VOLTAGE_STANDARD, 10);
        Resistance r2 = new Resistance(Voltage.VOLTAGE_STANDARD, 20);
        List<Composant> composants = new ArrayList<>();
        composants.add(r);
        composants.add(r2);
        Protection p1 = new Protection(10000, TypeProtection.FUSIBLE);

        CircuitSerie c1 = new CircuitSerie(composants, Voltage.VOLTAGE_STANDARD, p1, true);
        double resistanceC1 = c1.calculerResistance();
        double reponseAttendue = 30;

        assertEquals(reponseAttendue, resistanceC1);


    }

    /**
     * Vérifie que la résistance totale est nulle
     * lorsque l'interrupteur est éteint.
     */
    @org.junit.jupiter.api.Test
    void calculerResistanceInterrupteurEteint() {
        List<Composant> composants = new ArrayList<>();
        composants.add(new Resistance(Voltage.VOLTAGE_STANDARD, 100));


        CircuitSerie c1 = new CircuitSerie(composants, Voltage.VOLTAGE_STANDARD, false);

        assertEquals(0.0, c1.calculerResistance());
    }
}