package composants;

import enums.TypeEnergie;
import enums.Voltage;

public class Charge extends Resistance {
    private Voltage voltage;
    private double resistance;
    private TypeEnergie typeEnergie1;
    private TypeEnergie typeEnergie2;
    private String nomCharge;

    public Charge(Voltage voltage, double resistance, TypeEnergie typeEnergie1, TypeEnergie typeEnergie2, String nomCharge){
        super(voltage, resistance, typeEnergie1);
        setNomCharge(nomCharge);
        setTypeEnergie2(typeEnergie2);


    }

    private void setNomCharge(String nomCharge){
        this.nomCharge = nomCharge;
    }

    private void setTypeEnergie2(TypeEnergie typeEnergie2){
        this.typeEnergie2 = typeEnergie2;
    }

    public double calculerWattage(){
        return this.voltage.getVoltage() * (this.voltage.getVoltage()/ this.resistance);
    }

}
