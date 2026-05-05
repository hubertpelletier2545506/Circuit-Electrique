package composants;

import enums.Voltage;

public abstract class Composant {
    public Composant(){}

    public abstract double calculerResistance();

    public abstract Voltage getVoltage();
}
