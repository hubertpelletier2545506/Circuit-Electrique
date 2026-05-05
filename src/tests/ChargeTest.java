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
        Charge charge = new Charge(Voltage.VOLTAGE_STANDARD, 10000,TypeEnergie.MECANIQUE, "Moteur");
        assertEquals("Moteur", charge.getNomCharge());
    }

    @org.junit.jupiter.api.Test
    void getChargeID() {
        Charge charge = new Charge(Voltage.VOLTAGE_STANDARD, 10000,TypeEnergie.MECANIQUE, "Moteur");
        Charge charge2 = new Charge(Voltage.VOLTAGE_STANDARD, 10000,TypeEnergie.RAYONNANTE, "Lumière");

        assertEquals(1, charge.getChargeID());
        assertEquals(2, charge2.getChargeID());

    }

    @org.junit.jupiter.api.Test
    void testToString() {
    }
}