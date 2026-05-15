package tests;

import composants.*;
import enums.TypeEnergie;
import enums.TypeProtection;
import enums.Voltage;
import exception.CircuitSauteException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CircuitTest {

    @org.junit.jupiter.api.Test
    void setComposants() {
        Charge charge = new Charge(Voltage.VOLTAGE_STANDARD, 10000,TypeEnergie.MECANIQUE, "Moteur");

        List<Composant> composantsInitiale = new ArrayList<>();
        composantsInitiale.add(charge);

        Charge charge2 = new Charge(Voltage.VOLTAGE_STANDARD, 10000,TypeEnergie.RAYONNANTE, "Lumière");

        List<Composant> composantsApresSet = new ArrayList<>();
        composantsApresSet.add(charge2);

        Protection disjoncteurTest = new Protection(100, TypeProtection.DISJONCTEUR);

        CircuitSerie serie = new CircuitSerie(composantsInitiale, Voltage.VOLTAGE_STANDARD, disjoncteurTest, true );

        assertNotEquals(composantsApresSet, serie.getComposants());

        serie.setComposants(composantsApresSet);

        assertEquals(composantsApresSet, serie.getComposants());
    }

    @org.junit.jupiter.api.Test
    void setVoltage() {
        Charge charge = new Charge(Voltage.VOLTAGE_STANDARD, 10000,TypeEnergie.MECANIQUE, "Moteur");
        List<Composant> composants = new ArrayList<>();
        composants.add(charge);
        Protection disjoncteurTest = new Protection(100, TypeProtection.DISJONCTEUR);

        Voltage  voltageInitiale = Voltage.VOLTAGE_STANDARD;

        CircuitSerie serie = new CircuitSerie(composants, voltageInitiale, disjoncteurTest, true );

        Voltage  voltageApresSet = Voltage.VOLTAGE_ELEVE;


        assertNotEquals(voltageApresSet, serie.getVoltage());

        serie.setVoltage(voltageApresSet);

        assertEquals(voltageApresSet, serie.getVoltage());
    }

    @org.junit.jupiter.api.Test
    void getVoltage() {
        Charge charge = new Charge(Voltage.VOLTAGE_STANDARD, 10000,TypeEnergie.MECANIQUE, "Moteur");
        List<Composant> composants = new ArrayList<>();
        composants.add(charge);
        Protection disjoncteurTest = new Protection(100, TypeProtection.DISJONCTEUR);

        CircuitSerie serie = new CircuitSerie(composants, Voltage.VOLTAGE_STANDARD, disjoncteurTest, true );



        assertEquals(Voltage.VOLTAGE_STANDARD, serie.getVoltage());
    }

    @org.junit.jupiter.api.Test
    void setProtection() {
        Charge charge = new Charge(Voltage.VOLTAGE_STANDARD, 10000,TypeEnergie.MECANIQUE, "Moteur");
        List<Composant> composants = new ArrayList<>();
        composants.add(charge);
        Protection disjoncteurInitiale = new Protection(100, TypeProtection.DISJONCTEUR);

        Voltage  voltageInitiale = Voltage.VOLTAGE_STANDARD;

        CircuitSerie serie = new CircuitSerie(composants, voltageInitiale, disjoncteurInitiale, true );

        Protection disjoncteurApresSet = new Protection(150, TypeProtection.DISJONCTEUR);


        assertNotEquals(disjoncteurApresSet, serie.getProtection());

        serie.setProtection(disjoncteurApresSet);

        assertEquals(disjoncteurApresSet, serie.getProtection());
    }

    @org.junit.jupiter.api.Test
    void setInterrupteurAllume() {
        Charge charge = new Charge(Voltage.VOLTAGE_STANDARD, 10000,TypeEnergie.MECANIQUE, "Moteur");
        List<Composant> composants = new ArrayList<>();
        composants.add(charge);
        Protection disjoncteurInitiale = new Protection(100, TypeProtection.DISJONCTEUR);
        Voltage  voltageInitiale = Voltage.VOLTAGE_STANDARD;
        CircuitSerie serie = new CircuitSerie(composants, voltageInitiale, disjoncteurInitiale, false );

        assertFalse(serie.getinterrupteurAllume());
        serie.setInterrupteurAllume(true);
        assertTrue(serie.getinterrupteurAllume());

    }

    @org.junit.jupiter.api.Test
    void calculerAmperage() {
        Charge charge = new Charge(Voltage.VOLTAGE_STANDARD, 10000,TypeEnergie.MECANIQUE, "Moteur");
        List<Composant> composants = new ArrayList<>();
        composants.add(charge);
        Protection disjoncteurInitiale = new Protection(100, TypeProtection.DISJONCTEUR);
        Voltage  voltageInitiale = Voltage.VOLTAGE_STANDARD;
        CircuitSerie serie = new CircuitSerie(composants, voltageInitiale, disjoncteurInitiale, true );

        assertEquals(0.012,serie.calculerAmperage());
     }

    @org.junit.jupiter.api.Test
    void calculerWattage() {
        Charge charge = new Charge(Voltage.VOLTAGE_STANDARD, 10000,TypeEnergie.MECANIQUE, "Moteur");
        List<Composant> composants = new ArrayList<>();
        composants.add(charge);
        Protection disjoncteurInitiale = new Protection(100, TypeProtection.DISJONCTEUR);
        Voltage  voltageInitiale = Voltage.VOLTAGE_STANDARD;
        CircuitSerie serie = new CircuitSerie(composants, voltageInitiale, disjoncteurInitiale, true );

        assertEquals(1.44, serie.calculerWattage());
    }

    @Test
    void constructeurSansProtection(){
        Charge charge = new Charge(Voltage.VOLTAGE_STANDARD, 10000,TypeEnergie.MECANIQUE, "Moteur");
        List<Composant> composants = new ArrayList<>();
        composants.add(charge);
        CircuitSerie circuitSerie=new CircuitSerie(composants,Voltage.VOLTAGE_STANDARD,true);

        Protection actual=circuitSerie.getProtection();
        Protection expected= null;

        assertEquals(expected,actual);
    }
    @Test
    void mauvaisVoltageComposantTest(){
        Charge charge = new Charge(Voltage.VOLTAGE_STANDARD, 10000,TypeEnergie.MECANIQUE, "Moteur");
        List<Composant> composants = new ArrayList<>();
        composants.add(charge);

        assertThrows(ArithmeticException.class, () ->new CircuitSerie(composants,Voltage.VOLTAGE_ELEVE,true));

    }
    @Test
    void circuitSautePasResistance(){
        List<Composant> composants=new ArrayList<>();

        assertThrows(CircuitSauteException.class, () -> new CircuitSerie(composants,Voltage.VOLTAGE_ELEVE,true));

    }
    @Test
    void disjoncteurSauteTest() {
        Resistance petiteResistance = new Resistance(Voltage.VOLTAGE_STANDARD, 1);
        List<Composant> composants = new ArrayList<>();
        composants.add(petiteResistance);

        CircuitSerie serie = new CircuitSerie(composants, Voltage.VOLTAGE_STANDARD, true);

        Protection petitDisjoncteur = new Protection(10, TypeProtection.DISJONCTEUR);
        serie.setProtection(petitDisjoncteur);

        assertFalse(serie.getinterrupteurAllume());
    }

    @Test
    void fusibleBruleTest() {
        Resistance petiteResistance = new Resistance(Voltage.VOLTAGE_STANDARD, 1);
        List<Composant> composants = new ArrayList<>();
        composants.add(petiteResistance);

        CircuitSerie serie = new CircuitSerie(composants, Voltage.VOLTAGE_STANDARD, true);

        Protection petitFusible = new Protection(10, TypeProtection.FUSIBLE);

        assertThrows(CircuitSauteException.class, () -> serie.setProtection(petitFusible));
    }

    @Test
    void calculerAmperageInterrupteurEteintTest() {
        Resistance r = new Resistance(Voltage.VOLTAGE_STANDARD, 100);
        List<Composant> composants = new ArrayList<>();
        composants.add(r);
        Protection disjoncteur = new Protection(100, TypeProtection.DISJONCTEUR);

        CircuitSerie serie = new CircuitSerie(composants, Voltage.VOLTAGE_STANDARD, disjoncteur, false);

        assertEquals(0.0, serie.calculerAmperage());
    }

}