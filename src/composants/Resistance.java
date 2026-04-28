package composants;

import enums.TypeEnergie;
import enums.Voltage;

public class Resistance extends Composant {
    private double resistance;
    private TypeEnergie typeEnergie;
    private Voltage voltage;

    public Resistance(Voltage voltage ,double resistance, TypeEnergie typeEnergie){
        this.voltage = voltage;
        this.resistance = resistance;
        this.typeEnergie = typeEnergie;
    }

    @Override
    public double calculerResistance() {
        return this.resistance;
    }
}
