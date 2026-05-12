package composants;

import enums.TypeEnergie;
import enums.Voltage;

public class Resistance extends Composant {
    private double resistance;
    private TypeEnergie typeEnergie;
    private Voltage voltage;
    private int resistanceID;
    private static int numResistance = 0;

    //CONSTANTES
    public static final TypeEnergie TYPE_ENERGIE_RESISTANCE = TypeEnergie.THERMIQUE;

    public Resistance(Voltage voltage ,double resistance){
        setVoltage(voltage);
        setResistance(resistance);
        setTypeEnergie(TYPE_ENERGIE_RESISTANCE);

        Resistance.numResistance += 1;
        this.resistanceID = numResistance;
    }

    @Override
    public double calculerResistance() {
        return this.resistance;
    }

    @Override
    public Voltage getVoltage() {
        return voltage;
    }
    public double getResistance(){
        return resistance;
    }

    private void setVoltage(Voltage voltage){
        this.voltage = voltage;
    }

    private void setResistance(double resistance){
        this.resistance = resistance;
    }

    private void setTypeEnergie(TypeEnergie typeEnergie){
        this.typeEnergie = typeEnergie;
    }

    @Override
    public String toString() {
        return "Numéro de charge: " + this.resistanceID + " | Résistance: " + getResistance() + " Ω | Type d'énergie: " + TYPE_ENERGIE_RESISTANCE + " | Différence de potentiel" + getVoltage().getValeurVoltage() + "V\n";
    }
}
