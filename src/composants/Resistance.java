package composants;

import enums.TypeEnergie;
import enums.Voltage;

public class Resistance extends Composant {
    private double resistance;
    private TypeEnergie typeEnergie;
    private Voltage voltage;

    public Resistance(Voltage voltage ,double resistance, TypeEnergie typeEnergie){
        setVoltage(voltage);
        setResistance(resistance);
        setTypeEnergie(typeEnergie);
    }

    @Override
    public double calculerResistance() {
        return this.resistance;
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
}
