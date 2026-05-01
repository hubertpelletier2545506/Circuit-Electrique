package composants;

import enums.TypeEnergie;
import enums.Voltage;

public class Charge extends Resistance {
    private Voltage voltage;
    private double resistance;
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

    private void setAutreTypeEnergie(TypeEnergie typeEnergie2){
        this.autreTypeEnergie = typeEnergie2;
    }


    public Voltage getVoltage() {
        return voltage;
    }

    public double getResistance() {
        return resistance;
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

    public static int getNumCharge() {
        return numCharge;
    }

    @Override
    public String toString() {
        return this.chargeID + " | " + getNomCharge() + " | " + getResistance() + " Ω | " + TYPE_ENERGIE_RESISTANCE + " | " + getAutreTypeEnergie() + " | " + getVoltage() + "V";
    }
}
