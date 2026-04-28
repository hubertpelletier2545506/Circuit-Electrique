package enums;

public enum Voltage {

    VOLTAGE_BAS(12),
    VOLTAGE_STANDARD(120),
    VOLTAGE_ELEVE(240);

    private final int volts;


    Voltage(int volts) {
        this.volts = volts;
    }

    // Getter pour récupérer le code
    public int getVoltage() {
        return volts;
    }
}
