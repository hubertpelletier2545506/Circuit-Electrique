package composants;

import enums.TypeEnergie;
import enums.Voltage;

public class Charge extends Resistance {
    private Voltage voltage;
    private double resistance;
    private TypeEnergie typeEnergie1;
    private TypeEnergie typeEnergie2;
    private String nomCharge;
    private int chargeID;
    private static int numCharge = 0;


    public Charge(Voltage voltage, double resistance, TypeEnergie typeEnergie1, TypeEnergie typeEnergie2, String nomCharge){
        super(voltage, resistance, typeEnergie1);
        setNomCharge(nomCharge);
        setTypeEnergie2(typeEnergie2);

        Charge.numCharge += 1;
        this.chargeID = numCharge;
    }

    private void setNomCharge(String nomCharge){
        this.nomCharge = nomCharge;
    }

    private void setTypeEnergie2(TypeEnergie typeEnergie2){
        this.typeEnergie2 = typeEnergie2;
    }


    public Voltage getVoltage() {
        return voltage;
    }

    public double getResistance() {
        return resistance;
    }

    public TypeEnergie getTypeEnergie1() {
        return typeEnergie1;
    }

    public TypeEnergie getTypeEnergie2() {
        return typeEnergie2;
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
        return this.chargeID + " | " + getNomCharge() + " | " + getResistance() + " Ω | " + getTypeEnergie1() + " | " + getTypeEnergie2() + " | " + getVoltage() + "V";
    }
}
