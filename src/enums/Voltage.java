package enums;

/**
 * Enum représentant les différents niveaux de tension (voltage)
 * utilisés dans les circuits du projet.
 * <p>
 * Chaque constante est associée à une valeur en volts.
 *
 * @author code Ludovik et Eric
 * @author Javadoc ChatGPT par
 */
public enum Voltage {

    VOLTAGE_BAS(12),
    VOLTAGE_STANDARD(120),
    VOLTAGE_ELEVE(240);

    private final int volts;


    Voltage(int volts) {
        this.volts = volts;
    }

    // Getter pour récupérer le code
    public int getValeurVoltage() {
        return volts;
    }
}
