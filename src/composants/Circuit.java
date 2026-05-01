package composants;

import enums.Voltage;

import java.util.List;

public abstract class Circuit extends Composant {

    protected List<Composant> composants;
    protected Voltage voltage;
    protected Protection protection;
    protected boolean interrupteurAllume;

    public Circuit(List<Composant> composants, Voltage voltage, Protection protection, boolean interrupteurAllume) {

        setComposants(composants);
        setVoltage(voltage);
        setProtection(protection);
        setInterrupteurAllume(interrupteurAllume);
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

    public void setProtection(Protection protection){

        this.protection = protection;
    }

    public void setInterrupteurAllume(boolean interrupteurAllume){

        this.interrupteurAllume = interrupteurAllume;
    }

    public double calculerAmperage(){

        return voltage.getVoltage()/calculerResistance();
    }

    public double calculerWattage(){

        return voltage.getVoltage()*calculerAmperage();
    }
}
