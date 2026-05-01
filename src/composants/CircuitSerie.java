package composants;

import enums.Voltage;

import java.util.List;

public class CircuitSerie extends Circuit {
    CircuitSerie(List<Composant> composants, Voltage voltage, Protection protection,boolean interrupteurAllume) {
        super(composants, voltage, protection,interrupteurAllume);
    }

    @Override
    public double calculerResistance() {
        double resistance = 0;
        if (this.interrupteurAllume == false) {
            return 0;
        } else {
            for (Composant composant : composants) {
                resistance += composant.calculerResistance();
            }

            return resistance;
        }

    }
}
