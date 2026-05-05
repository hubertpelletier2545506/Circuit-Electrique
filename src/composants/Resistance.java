package composants;

import enums.TypeEnergie;
import enums.Voltage;

/**
 * Représente une résistance électrique, un composant passif qui s'oppose au passage du courant.
 * <p>
 * Une résistance est caractérisée par :
 * <ul>
 *     <li>sa valeur de résistance (en ohms)</li>
 *     <li>son type d'énergie (thermique)</li>
 *     <li>son voltage</li>
 * </ul>
 * </p>
 *
 * @author code Éric Burton-Labrecque
 * @author javadoc ChatGPT par Maxime Filion
 */
public class Resistance extends Composant {

    /** Valeur de la résistance en ohms (Ω). */
    private double resistance;

    /** Type d'énergie associé à la résistance. */
    private TypeEnergie typeEnergie;

    /** Voltage appliqué à la résistance. */
    private Voltage voltage;

    //CONSTANTES

    /** Type d'énergie constant pour une résistance (thermique). */
    public static final TypeEnergie TYPE_ENERGIE_RESISTANCE = TypeEnergie.THERMIQUE;

    /**
     * Constructeur de la classe Resistance.
     *
     * @param voltage le voltage appliqué à la résistance
     * @param resistance la valeur de la résistance en ohms (Ω)
     */
    public Resistance(Voltage voltage ,double resistance){
        setVoltage(voltage);
        setResistance(resistance);
        setTypeEnergie(TYPE_ENERGIE_RESISTANCE);
    }

    /**
     * Retourne la valeur de la résistance.
     *
     * @return la résistance en ohms (Ω)
     */
    @Override
    public double calculerResistance() {
        return this.resistance;
    }

    /**
     * Définit le voltage de la résistance.
     *
     * @param voltage le voltage à associer
     */
    private void setVoltage(Voltage voltage){
        this.voltage = voltage;
    }

    /**
     * Définit la valeur de la résistance.
     *
     * @param resistance la résistance en ohms (Ω)
     */
    private void setResistance(double resistance){
        this.resistance = resistance;
    }

    /**
     * Définit le type d'énergie de la résistance.
     *
     * @param typeEnergie le type d'énergie
     */
    private void setTypeEnergie(TypeEnergie typeEnergie){
        this.typeEnergie = typeEnergie;
    }

    /**
     * Retourne le voltage de la résistance.
     *
     * @return le voltage
     */
    public Voltage getVoltage() {
        return voltage;
    }

    /**
     * Retourne la valeur de la résistance.
     *
     * @return la résistance en ohms (Ω)
     */
    public double getResistance() {
        return resistance;
    }

    /**
     * Retourne une représentation textuelle de la résistance.
     *
     * @return une chaîne de caractères contenant la valeur de la résistance,
     * le type d'énergie et le voltage
     */
    @Override
    public String toString() {
        return getResistance() + " Ω | " + TYPE_ENERGIE_RESISTANCE  + " " + getVoltage().getValeurVoltage() + "V";
    }
}