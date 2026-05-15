package composants;

import enums.Voltage;

/**
 * Classe abstraite représentant un composant d’un circuit électrique.
 * <p>
 * Tous les composants du système doivent pouvoir calculer une résistance
 * et fournir leur tension (voltage).
 *
 * @author code
 * @author Javadoc ChatGPT par
 */
public abstract class Composant {
    /**
     * Construit un composant de base.
     */
    public Composant() {
    }

    /**
     * Calcule la résistance équivalente du composant.
     *
     * @return la résistance en ohms
     */
    public abstract double calculerResistance();

    /**
     * Retourne le voltage associé au composant.
     *
     * @return le voltage du composant
     */
    public abstract Voltage getVoltage();
}
