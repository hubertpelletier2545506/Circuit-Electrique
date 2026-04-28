package composants;

import enums.Voltage;

import java.util.List;

public abstract class Circuit extends Composant {

    protected List<Composant> composants;
    protected Voltage voltage;

    public Circuit(List<Composant> composants, Voltage voltage) {

        setComposants(composants);
        setVoltage(voltage);
    }

    public void setComposants(List<Composant> composants) {

        this.composants = composants;
    }

    public void setVoltage(Voltage voltage){

        this.voltage = voltage;
    }

    public Voltage getVoltage(){
        return voltage;
    }
}
