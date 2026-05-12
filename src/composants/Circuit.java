package composants;

import enums.TypeProtection;
import enums.Voltage;
import exception.CircuitSauteException;

import java.util.List;

public abstract class Circuit extends Composant {

    protected List<Composant> composants;
    protected Voltage voltage;
    protected Protection protection;
    protected boolean interrupteurAllume;

    public Circuit(List<Composant> composants, Voltage voltage, Protection protection, boolean interrupteurAllume) {
        setVoltage(voltage);
        setComposants(composants);
        setInterrupteurAllume(interrupteurAllume);
        setProtection(protection);
    }

    public Circuit(List<Composant> composants, Voltage voltage, boolean interrupteurAllume) {
        setVoltage(voltage);
        setComposants(composants);
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

    public boolean getinterrupteurAllume(){return interrupteurAllume;
    }

    public void setProtection(Protection protection){

        if(calculerAmperage() < protection.getAmperageMax()) {

            this.protection = protection;
        } else{
            if(protection.getTypeProtection() == TypeProtection.DISJONCTEUR){
                System.out.println("Disjoncteur sauté! L'ampérage maximal de " + protection.getAmperageMax() + "A a été dépassé.\nLe circuit est maintenant fermé.");
                setInterrupteurAllume(false);

            }
            if(protection.getTypeProtection() == TypeProtection.FUSIBLE){
                throw new CircuitSauteException("Fusible brûlée! L'ampérage maximal de " + protection.getAmperageMax() + "A a été dépassé.");

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
            throw new CircuitSauteException("Le circuit a sauté, il n'y a aucune résistance!");



        }
    }

    public double calculerWattage(){

        return voltage.getValeurVoltage()*calculerAmperage();
    }

    public double calculerCout(double coutElectricite, double nbHeures){
        double cout = coutElectricite*nbHeures*(calculerWattage()/1000);
        return Math.round(cout * 100.0) / 100.0;
    }
}
