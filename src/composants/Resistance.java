package composants;

import enums.TypeEnergie;
import enums.Voltage;

public class Resistance extends Composant {
    private double resistance;
    private TypeEnergie typeEnergie;
    private Voltage voltage;

    //CONSTANTES
    public static final TypeEnergie TYPE_ENERGIE_RESISTANCE = TypeEnergie.THERMIQUE;

    public Resistance(Voltage voltage ,double resistance){
        setVoltage(voltage);
        setResistance(resistance);
        setTypeEnergie(TYPE_ENERGIE_RESISTANCE);
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
