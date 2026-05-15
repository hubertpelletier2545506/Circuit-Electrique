package tests;

import composants.*;
import enums.TypeProtection;
import enums.Voltage;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CircuitSerieTest {


    @org.junit.jupiter.api.Test
    void calculerResistance_InterrupteurAllume() {
        Resistance r =new Resistance(Voltage.VOLTAGE_STANDARD,10);
        Resistance r2= new Resistance(Voltage.VOLTAGE_STANDARD,20);
        List<Composant> composants=new ArrayList<>();
        composants.add(r);
        composants.add(r2);
        Protection p1=new Protection(10000, TypeProtection.FUSIBLE);

        CircuitSerie c1=new CircuitSerie(composants,Voltage.VOLTAGE_STANDARD,p1,true);
        double resistanceC1=c1.calculerResistance();
        double reponseAttendue= 30;

        assertEquals(reponseAttendue,resistanceC1);


    }
    @org.junit.jupiter.api.Test
    void calculerResistance_InterrupteurEteint() {
        List<Composant> composants = new ArrayList<>();
        composants.add(new Resistance(Voltage.VOLTAGE_STANDARD, 100));


        CircuitSerie c1 = new CircuitSerie(composants, Voltage.VOLTAGE_STANDARD, false);

        assertEquals(0.0, c1.calculerResistance());
    }
}