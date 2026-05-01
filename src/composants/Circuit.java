package composants;

import enums.TypeProtection;
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

    public Circuit(List<Composant> composants, Voltage voltage, boolean interrupteurAllume) {

        setComposants(composants);
        setVoltage(voltage);
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

        if(calculerAmperage() < protection.getAmperageMax()) {

            this.protection = protection;
        } else{
            if(protection.getTypeProtection() == TypeProtection.DISJONCTEUR){
                System.out.println("Le disjoncteur a sauté!");

            }
            if(protection.getTypeProtection() == TypeProtection.FUSIBLE){
                throw new ArithmeticException("Fusible brûlée! L'ampérage maximal de " + protection.getAmperageMax() + "amp a été dépassé.");

            }
        }
    }

    public void setInterrupteurAllume(boolean interrupteurAllume){

        this.interrupteurAllume = interrupteurAllume;
    }

    public double calculerAmperage(){

        try {

            return voltage.getVoltage() / calculerResistance();
        } catch (ArithmeticException exception){
            return 0;

        }
    }

    public double calculerWattage(){

        return voltage.getVoltage()*calculerAmperage();
    }

    public double calculerCout(double coutElectricite, double nbHeures){

        return coutElectricite*nbHeures*(calculerWattage()/1000);
    }
}
