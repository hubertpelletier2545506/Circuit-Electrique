package exception;
/**
 * Exception levée lorsqu’un circuit saute (surcharge ou défaillance).
 *
 * Cette exception est de type non vérifiée (RuntimeException),
 * donc elle n’a pas besoin d’être déclarée dans les signatures de méthodes.
 *
 * @author code Hubert
 * @author Javadoc ChatGPT par Hubert
 */
public class CircuitSauteException extends RuntimeException {
    public CircuitSauteException(String message) {
        super(message);
    }
}
