package composants;

import enums.TypeEnergie;
import enums.Voltage;

public class Charge extends Resistance {
    private TypeEnergie autreTypeEnergie;
    private String nomCharge;
    private int chargeID;
    private static int numCharge = 0;


    public Charge(Voltage voltage, double resistance, TypeEnergie autreTypeEnergie, String nomCharge){
        super(voltage, resistance);
        setNomCharge(nomCharge);
        setAutreTypeEnergie(autreTypeEnergie);

        Charge.numCharge += 1;
        this.chargeID = numCharge;
    }

    private void setNomCharge(String nomCharge){
        this.nomCharge = nomCharge;
    }

    private void setAutreTypeEnergie(TypeEnergie autreTypeEnergie){
        this.autreTypeEnergie = autreTypeEnergie;
    }



    public TypeEnergie getAutreTypeEnergie() {
        return autreTypeEnergie;
    }


    public String getNomCharge() {
        return nomCharge;
    }

    public int getChargeID() {
        return chargeID;
    }

    @Override
    public String toString() {
        return "Numéro de charge: " + this.chargeID + " | Nom: " + getNomCharge() + " | Résistance: " + getResistance() + " Ω | Types d'énergie: " + TYPE_ENERGIE_RESISTANCE + " - " + getAutreTypeEnergie() + " | Différence de potentiel: " + getVoltage().getValeurVoltage() + "V\n";
    }
}
