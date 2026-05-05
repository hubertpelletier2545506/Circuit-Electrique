package tests;

import composants.*;
import enums.TypeEnergie;
import enums.TypeProtection;
import enums.Voltage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CircuitTest {

    @org.junit.jupiter.api.Test
    void setComposants() {
        Charge charge = new Charge(Voltage.VOLTAGE_STANDARD, 10000,TypeEnergie.MECANIQUE, "Moteur");

        List<Composant> composants = new ArrayList<>();
        composants.add(charge);

        Charge charge2 = new Charge(Voltage.VOLTAGE_STANDARD, 10000,TypeEnergie.RAYONNANTE, "Lumière");

        List<Composant> composants2 = new ArrayList<>();
        composants2.add(charge2);

        Protection disjoncteurTest = new Protection(100, TypeProtection.DISJONCTEUR);

        CircuitSerie serie = new CircuitSerie(composants, Voltage.VOLTAGE_STANDARD, disjoncteurTest, true );

        assertNotEquals(composants2, serie.getComposants());

        serie.setComposants(composants2);

        assertEquals(composants2, serie.getComposants());
    }

    @org.junit.jupiter.api.Test
    void setVoltage() {
    }

    @org.junit.jupiter.api.Test
    void getVoltage() {
    }

    @org.junit.jupiter.api.Test
    void setProtection() {
    }

    @org.junit.jupiter.api.Test
    void setInterrupteurAllume() {
    }

    @org.junit.jupiter.api.Test
    void calculerAmperage() {
    }

    @org.junit.jupiter.api.Test
    void calculerWattage() {
    }

    @org.junit.jupiter.api.Test
    void calculerCout() {
    }
}