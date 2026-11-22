package exceptions;

/** Викидається, коли множник у методі scale некоректний. */
public class IllegalScaleFactorException extends RuntimeException {

    public IllegalScaleFactorException(String message) {
        super(message);
    }
}
