package composants;

import enums.Voltage;

import java.util.List;

/**
 * Classe représentant un circuit en parallèle.
 * <p>
 * Dans un circuit en parallèle, l'inverse des résistances des composants
 * s'additionne lorsque l'interrupteur est allumé.
 *
 * @author code Maxime, hubert, ludo
 * @author Javadoc ChatGPT par Hubert
 */
public class CircuitParallele extends Circuit {
    /**
     * Construit un circuit parallèle avec protection et interrupteur.
     *
     * @param composants         liste des composants du circuit
     * @param voltage            tension du circuit
     * @param protection         protection associée au circuit
     * @param interrupteurAllume état de l'interrupteur
     */
    public CircuitParallele(List<Composant> composants, Voltage voltage, Protection protection, boolean interrupteurAllume) {
        super(composants, voltage, protection, interrupteurAllume);
    }

    /**
     * Construit un circuit parallèle sans protection.
     *
     * @param composants         liste des composants du circuit
     * @param voltage            tension du circuit
     * @param interrupteurAllume état de l'interrupteur
     */
    public CircuitParallele(List<Composant> composants, Voltage voltage, boolean interrupteurAllume) {
        super(composants, voltage, interrupteurAllume);
    }

    /**
     * Calcule la résistance équivalente du circuit en parallèle.
     * <p>
     * Si l'interrupteur est fermé, la résistance équivalente est calculée
     * à partir de la somme des inverses des résistances des composants.
     * Sinon, elle est nulle.
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
                if (composant.calculerResistance() != 0)
                    resistance += 1.0 / composant.calculerResistance();
            }
            return Math.round((1.0 / resistance) * 100.0) / 100.0;
        }
    }
}
