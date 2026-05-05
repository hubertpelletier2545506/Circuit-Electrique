package tests;

import composants.*;
import enums.TypeProtection;
import enums.Voltage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CircuitParalleleTest {

    @Test
    void calculerResistance() {
        Resistance r =new Resistance(Voltage.VOLTAGE_STANDARD,10);
        Resistance r2= new Resistance(Voltage.VOLTAGE_STANDARD,20);
        List<Composant> composants=new ArrayList<>();
        composants.add(r);
        composants.add(r2);
        Protection p1=new Protection(10000, TypeProtection.FUSIBLE);

        CircuitParallele c1=new CircuitParallele(composants, Voltage.VOLTAGE_STANDARD, p1, true);
        double resistanceC1= c1.calculerResistance();
        double reponseAttendue= 6.67;

        assertEquals(reponseAttendue,resistanceC1);
    }
}