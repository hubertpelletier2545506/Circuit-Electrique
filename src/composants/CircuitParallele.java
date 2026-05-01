package composants;

import enums.Voltage;

import java.util.List;

public class CircuitParallele extends Circuit{

    CircuitParallele(List<Composant> composants, Voltage voltage, Protection protection, boolean interrupteurAllume){
        super(composants, voltage, protection,  interrupteurAllume);
    }

    @Override
    public double calculerResistance() {
        double resistance = 0;
        if (this.interrupteurAllume == false){
            return  resistance = 0;
        }
        else {
            for(Composant composant : composants){
                resistance += 1.0/ composant.calculerResistance();
            }
            return 1.0/resistance;
        }
    }
}
