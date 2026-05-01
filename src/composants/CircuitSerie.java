package composants;

import enums.Voltage;

import java.util.List;

public class CircuitSerie extends Circuit {
    CircuitSerie(List<Composant> composants, Voltage voltage,Protection protection) {
        super(composants,voltage,protection);
    }

    @Override
    public double calculerResistance() {
        double resistance = 0;
        for(Composant composant : composants){
            resistance += composant.calculerResistance();
        }

        return resistance;
    }
}
