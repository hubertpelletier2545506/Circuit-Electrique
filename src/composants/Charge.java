package composants;

import enums.TypeEnergie;
import enums.Voltage;

/**
 * Classe représentant une charge électrique.
 * <p>
 * Une charge est une résistance spécialisée qui possède
 * un nom et un second type d'énergie associé.
 *
 * @author code
 * @author Javadoc ChatGPT par Hubert
 */
public class Charge extends Resistance {
    private TypeEnergie autreTypeEnergie;
    private String nomCharge;
    private int chargeID;
    private static int numCharge = 0;

    /**
     * Construit une charge avec un voltage, une résistance,
     * un type d'énergie secondaire et un nom.
     *
     * @param voltage          tension électrique de la charge
     * @param resistance       valeur de la résistance en ohms
     * @param autreTypeEnergie second type d'énergie associé
     * @param nomCharge        nom de la charge
     */
    public Charge(Voltage voltage, double resistance, TypeEnergie autreTypeEnergie, String nomCharge) {
        super(voltage, resistance);
        setNomCharge(nomCharge);
        setAutreTypeEnergie(autreTypeEnergie);

        Charge.numCharge += 1;
        this.chargeID = numCharge;
    }

    /**
     * Définit le nom de la charge.
     *
     * @param nomCharge nom de la charge
     */
    private void setNomCharge(String nomCharge) {
        this.nomCharge = nomCharge;
    }

    /**
     * Définit le type d'énergie secondaire de la charge.
     *
     * @param autreTypeEnergie type d'énergie associé
     */
    private void setAutreTypeEnergie(TypeEnergie autreTypeEnergie) {
        this.autreTypeEnergie = autreTypeEnergie;
    }


    /**
     * Retourne le type d'énergie secondaire de la charge.
     *
     * @return type d'énergie secondaire
     */
    public TypeEnergie getAutreTypeEnergie() {
        return autreTypeEnergie;
    }

    /**
     * Retourne le nom de la charge.
     *
     * @return nom de la charge
     */
    public String getNomCharge() {
        return nomCharge;
    }

    /**
     * Retourne l'identifiant unique de la charge.
     *
     * @return identifiant de la charge
     */
    public int getChargeID() {
        return chargeID;
    }

    /**
     * Réinitialise le compteur d’identifiants des charges.
     * <p>
     * Utilisé principalement pour les tests unitaires.
     */
    public static void resetCompteur() {
        numCharge = 0;
    }

    /**
     * Retourne une représentation textuelle de la charge.
     *
     * @return chaîne décrivant la charge
     */

    @Override
    public String toString() {
        return "Numéro de charge: " + this.chargeID + " | Nom: " + getNomCharge() + " | Résistance: " + getResistance() + " Ω | Types d'énergie: " + TYPE_ENERGIE_RESISTANCE + " - " + getAutreTypeEnergie() + " | Différence de potentiel: " + getVoltage().getValeurVoltage() + "V\n";
    }
}
