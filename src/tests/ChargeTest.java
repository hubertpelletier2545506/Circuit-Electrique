package tests;

import composants.Charge;
import enums.TypeEnergie;
import enums.Voltage;

import static org.junit.jupiter.api.Assertions.*;

class ChargeTest {

    @org.junit.jupiter.api.Test
    void getAutreTypeEnergie() {
        Charge charge = new Charge(Voltage.VOLTAGE_STANDARD, 10000,TypeEnergie.MECANIQUE, "Moteur");
        assertEquals(TypeEnergie.MECANIQUE, charge.getAutreTypeEnergie());
    }

    @org.junit.jupiter.api.Test
    void getNomCharge() {
        Charge charge2 = new Charge(Voltage.VOLTAGE_STANDARD, 10000,TypeEnergie.MECANIQUE, "Moteur");
        assertEquals("Moteur", charge2.getNomCharge());
    }

    @org.junit.jupiter.api.Test
    void getChargeID() {
        Charge charge3 = new Charge(Voltage.VOLTAGE_STANDARD, 10000,TypeEnergie.MECANIQUE, "Moteur");
        Charge charge4 = new Charge(Voltage.VOLTAGE_STANDARD, 10000,TypeEnergie.RAYONNANTE, "Lumière");

        assertEquals(3, charge3.getChargeID());
        assertEquals(4, charge4.getChargeID());

    }

    @org.junit.jupiter.api.Test
    void testToString() {
        Charge charge = new Charge(Voltage.VOLTAGE_STANDARD, 10000,TypeEnergie.MECANIQUE, "Moteur");
        assertEquals("Numéro de charge: 2 | Moteur | 10000.0 Ω | THERMIQUE | MECANIQUE | 120V", charge.toString());
    }
}