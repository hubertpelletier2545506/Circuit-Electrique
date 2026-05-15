package composants;

import enums.TypeEnergie;
import enums.Voltage;

/**
 * Classe représentant une résistance dans un circuit électrique.
 * <p>
 * Une résistance est un composant électrique caractérisé par une valeur
 * en ohms et un type d'énergie associé (thermique dans ce cas).
 *
 * @author code
 * @author Javadoc ChatGPT par Hubert
 */
public class Resistance extends Composant {
    private double resistance;
    private TypeEnergie typeEnergie;
    private Voltage voltage;
    private int resistanceID;
    private static int numResistance = 0;
    public final static int RESISTANCE_DEFAUT = 0;

    //CONSTANTES
    public static final TypeEnergie TYPE_ENERGIE_RESISTANCE = TypeEnergie.THERMIQUE;

    /**
     * Construit une résistance avec un voltage et une valeur de résistance.
     *
     * @param voltage    tension électrique du composant
     * @param resistance valeur de la résistance en ohms
     */
    public Resistance(Voltage voltage, double resistance) {
        setVoltage(voltage);
        setResistance(resistance);
        setTypeEnergie(TYPE_ENERGIE_RESISTANCE);

        Resistance.numResistance += 1;
        this.resistanceID = numResistance;
    }

    /**
     * Calcule la résistance du composant.
     *
     * @return la valeur de la résistance en ohms
     */
    @Override
    public double calculerResistance() {
        return this.resistance;
    }

    /**
     * Retourne la tension (voltage) associée au composant.
     *
     * @return le voltage du composant
     */
    @Override
    public Voltage getVoltage() {
        return voltage;
    }

    /**
     * Retourne la valeur de la résistance.
     *
     * @return la résistance en ohms
     */
    public double getResistance() {
        return resistance;
    }

    /**
     * Définit le voltage du composant.
     *
     * @param voltage le voltage à assigner
     */
    private void setVoltage(Voltage voltage) {
        this.voltage = voltage;
    }

    /**
     * Définit la valeur de la résistance.
     * <p>
     * Si la valeur est négative, elle est remplacée par la valeur par défaut.
     *
     * @param resistance valeur de résistance en ohms
     */
    private void setResistance(double resistance) {
        if (resistance >= 0) {
            this.resistance = resistance;
        } else {
            System.out.println("Une résistance ne peut pas avoir une valeur de résistance nulle. Nouvelle valeur de résistance: " + RESISTANCE_DEFAUT + "Ω");
            setResistance(RESISTANCE_DEFAUT);
        }
    }

    /**
     * Définit le type d'énergie associé à la résistance.
     *
     * @param typeEnergie type d'énergie
     */
    private void setTypeEnergie(TypeEnergie typeEnergie) {
        this.typeEnergie = typeEnergie;
    }

    /**
     * Retourne une représentation textuelle de la résistance.
     *
     * @return chaîne décrivant la résistance
     */
    @Override
    public String toString() {
        return "Numéro de résistance: " + this.resistanceID + " | Résistance: " + getResistance() + " Ω | Type d'énergie: " + TYPE_ENERGIE_RESISTANCE + " | Différence de potentiel: " + getVoltage().getValeurVoltage() + "V\n";
    }
}
