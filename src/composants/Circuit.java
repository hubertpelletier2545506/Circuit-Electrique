package composants;

import enums.TypeProtection;
import enums.Voltage;
import exception.CircuitSauteException;

import java.util.List;

/**
 * Classe abstraite représentant un circuit électrique.
 * <p>
 * Un circuit est composé de plusieurs composants et peut être protégé
 * par un système de protection (disjoncteur ou fusible).
 *
 * @author code
 * @author Javadoc ChatGPT par Hubert
 */
public abstract class Circuit extends Composant {

    protected List<Composant> composants;
    protected Voltage voltage;
    protected Protection protection;
    protected boolean interrupteurAllume;

    /**
     * Construit un circuit avec protection.
     *
     * @param composants         liste des composants du circuit
     * @param voltage            tension du circuit
     * @param protection         protection électrique du circuit
     * @param interrupteurAllume état de l’interrupteur
     */
    public Circuit(List<Composant> composants, Voltage voltage, Protection protection, boolean interrupteurAllume) {
        this(composants, voltage, interrupteurAllume);
        setProtection(protection);

    }

    /**
     * Construit un circuit sans protection.
     *
     * @param composants         liste des composants du circuit
     * @param voltage            tension du circuit
     * @param interrupteurAllume état de l’interrupteur
     */
    public Circuit(List<Composant> composants, Voltage voltage, boolean interrupteurAllume) {
        setVoltage(voltage);
        setComposants(composants);
        setInterrupteurAllume(interrupteurAllume);
        this.calculerAmperage();
    }

    /**
     * Vérifie que tous les composants ont le même voltage que le circuit.
     *
     * @param composantsAVerifier liste des composants à vérifier
     * @return true si tous les composants sont compatibles, false sinon
     */
    private boolean verifierVoltageComposants(List<Composant> composantsAVerifier) {

        for (Composant composant : composantsAVerifier) {
            if (composant.getVoltage() != voltage) {
                return false;
            }
        }
        return true;
    }

    /**
     * Définit les composants du circuit.
     *
     * @param composants liste des composants
     * @throws ArithmeticException si un composant n'est pas compatible en voltage
     */
    public void setComposants(List<Composant> composants) {
        if (verifierVoltageComposants(composants)) {
            this.composants = composants;
        } else {
            throw new ArithmeticException("Le voltage d'un des composants est incompatible avec le voltage du circuit");
        }

    }

    /**
     * Définit le voltage du circuit.
     *
     * @param voltage tension du circuit
     */
    public void setVoltage(Voltage voltage) {

        this.voltage = voltage;
    }

    /**
     * Retourne le voltage du circuit.
     *
     * @return voltage du circuit
     */
    public Voltage getVoltage() {
        return voltage;
    }

    /**
     * Retourne la protection du circuit.
     *
     * @return protection du circuit
     */
    public Protection getProtection() {
        return protection;
    }

    /**
     * Retourne la liste des composants du circuit.
     *
     * @return liste des composants
     */
    public List<Composant> getComposants() {
        return composants;
    }

    /**
     * Retourne l'état de l’interrupteur du circuit.
     *
     * @return true si allumé, false sinon
     */
    public boolean getinterrupteurAllume() {
        return interrupteurAllume;
    }

    /**
     * Définit la protection du circuit et vérifie si elle saute.
     *
     * @param protection protection à appliquer
     */
    public void setProtection(Protection protection) {

        if (calculerAmperage() < protection.getAmperageMax()) {

            this.protection = protection;
        } else {
            if (protection.getTypeProtection() == TypeProtection.DISJONCTEUR) {
                System.out.println("Disjoncteur sauté! L'ampérage maximal de " + protection.getAmperageMax() + "A a été dépassé.\nLe circuit est maintenant fermé.");
                setInterrupteurAllume(false);

            }
            if (protection.getTypeProtection() == TypeProtection.FUSIBLE) {
                throw new CircuitSauteException("Fusible brûlée! L'ampérage maximal de " + protection.getAmperageMax() + "A a été dépassé.");

            }
        }
    }

    /**
     * Définit l'état de l’interrupteur.
     *
     * @param interrupteurAllume état de l’interrupteur
     */
    public void setInterrupteurAllume(boolean interrupteurAllume) {

        this.interrupteurAllume = interrupteurAllume;
    }

    /**
     * Calcule l’intensité du courant (ampérage) du circuit.
     *
     * @return intensité en ampères
     * @throws CircuitSauteException si le circuit est ouvert ou invalide
     */
    public double calculerAmperage() {
        if (!interrupteurAllume) {
            return 0;
        }
        double resistanceEquivalente = calculerResistance();
        if (resistanceEquivalente == 0) {
            throw new CircuitSauteException("Le circuit a sauté, il n'y a aucune résistance!");
        }
        return voltage.getValeurVoltage() / resistanceEquivalente;
    }

    /**
     * Calcule la puissance électrique du circuit en watts.
     *
     * @return puissance en watts
     */
    public double calculerWattage() {

        return voltage.getValeurVoltage() * calculerAmperage();
    }

    /**
     * Calcule le coût électrique du circuit.
     *
     * @param coutElectricite coût par kWh
     * @param nbHeures        nombre d’heures d’utilisation
     * @return coût total arrondi à 2 décimales
     */
    public double calculerCout(double coutElectricite, double nbHeures) {
        double cout = coutElectricite * nbHeures * (calculerWattage() / 1000.0);
        return Math.round(cout * 100.0) / 100.0;
    }
}
