package tests;

import composants.Resistance;
import enums.Voltage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResistanceTest {

    @Test
    void calculerResistance() {
        Resistance resistance = new Resistance(Voltage.VOLTAGE_STANDARD, 15);
        assertEquals(15, resistance.calculerResistance());
    }

    @Test
    void getVoltage() {
        Resistance resistance2 = new Resistance(Voltage.VOLTAGE_ELEVE, 10);
        assertEquals(Voltage.VOLTAGE_ELEVE, resistance2.getVoltage());
    }

    @Test
    void getResistance() {
        Resistance resistance3 = new Resistance(Voltage.VOLTAGE_BAS, 20);
        assertEquals(20, resistance3.getResistance());
    }

    @Test
    void testToString() {
        Resistance resistance4 = new Resistance(Voltage.VOLTAGE_STANDARD, 10);
        assertEquals("10.0 Ω | THERMIQUE 120V", resistance4.toString());
    }
}