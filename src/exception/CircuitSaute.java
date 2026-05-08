package exception;

public class CircuitSaute extends RuntimeException {
    public CircuitSaute(String message) {
        super(message);
    }
}
