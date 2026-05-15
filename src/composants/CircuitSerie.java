package composants;

import enums.Voltage;

import java.util.List;

/**
 * Classe représentant un circuit en série.
 * <p>
 * Dans un circuit en série, les résistances des composants
 * s'additionnent lorsque l'interrupteur est allumé.
 *
 * @author code Hubert, ludo, eric
 * @author Javadoc ChatGPT par Hubert
 */
public class CircuitSerie extends Circuit {
    /**
     * Construit un circuit série avec protection et interrupteur.
     *
     * @param composants         liste des composants du circuit
     * @param voltage            tension du circuit
     * @param protection         protection associée au circuit
     * @param interrupteurAllume état de l'interrupteur
     */
    public CircuitSerie(List<Composant> composants, Voltage voltage, Protection protection, boolean interrupteurAllume) {
        super(composants, voltage, protection, interrupteurAllume);
    }

    /**
     * Construit un circuit série sans protection.
     *
     * @param composants         liste des composants du circuit
     * @param voltage            tension du circuit
     * @param interrupteurAllume état de l'interrupteur
     */
    public CircuitSerie(List<Composant> composants, Voltage voltage, boolean interrupteurAllume) {
        super(composants, voltage, interrupteurAllume);
    }

    /**
     * Calcule la résistance équivalente du circuit en série.
     * <p>
     * Si l'interrupteur est fermé, la résistance totale est la somme
     * des résistances des composants. Sinon, elle est nulle.
     *
     * @return la résistance équivalente du circuit
     */
    @Override
    public double calculerResistance() {
        double resistance = 0;
        if (!this.interrupteurAllume) {
            return 0;
        } else {
            for (Composant composant : composants) {
                resistance += composant.calculerResistance();
            }

            return resistance;
        }

    }
}
