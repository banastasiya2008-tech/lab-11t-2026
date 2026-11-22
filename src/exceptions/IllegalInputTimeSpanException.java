package exceptions;

/** Викидається, коли вхідні значення години/хвилини некоректні. */
public class IllegalInputTimeSpanException extends RuntimeException {

    public IllegalInputTimeSpanException(String message) {
        super(message);
    }
}
