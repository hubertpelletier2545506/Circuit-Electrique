package composants;

import enums.Voltage;

import java.util.List;

public class CircuitParallele extends Circuit{

    CircuitParallele(List<Composant> composants, Voltage voltage, Protection protection){
        super(composants, voltage, protection);
    }

    @Override
    public double calculerResistance() {
        double resistance = 0;
        for(Composant composant : composants){
            resistance += 1.0/ composant.calculerResistance();
        }

        return 1.0/resistance;
    }
}
