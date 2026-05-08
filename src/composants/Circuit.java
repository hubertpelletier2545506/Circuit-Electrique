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
        setVoltage(voltage);
        setComposants(composants);
        setProtection(protection);
        setInterrupteurAllume(interrupteurAllume);
    }

    public Circuit(List<Composant> composants, Voltage voltage, boolean interrupteurAllume) {

        setComposants(composants);
        setVoltage(voltage);
        setInterrupteurAllume(interrupteurAllume);
    }

    private boolean verifierVoltageComposants(){

        for (Composant composant : composants){

            if(composant.getVoltage() != voltage){
                return false;
            }
        }

        return true;
    }

    public void setComposants(List<Composant> composants) {
        this.composants = composants;
        if(verifierVoltageComposants()){
            this.composants = composants;
        } else{
            throw new ArithmeticException("Le voltage d'un des composants est incompatible avec le voltage du circuit");
        }

    }

    public void setVoltage(Voltage voltage){

        this.voltage = voltage;
    }

    public Voltage getVoltage(){
        return voltage;
    }

    public Protection getProtection(){return protection;}

    public List<Composant> getComposants(){return composants;}

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
            if(interrupteurAllume == false){
                return 0;
            } else {

                return voltage.getValeurVoltage() / calculerResistance();
            }
        } catch (ArithmeticException exception){
            return Double.POSITIVE_INFINITY;

        }
    }

    public double calculerWattage(){

        return voltage.getValeurVoltage()*calculerAmperage();
    }

    public double calculerCout(double coutElectricite, double nbHeures){

        return coutElectricite*nbHeures*(calculerWattage()/1000);
    }
}
