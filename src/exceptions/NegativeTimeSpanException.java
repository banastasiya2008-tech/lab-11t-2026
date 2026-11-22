package exceptions;

/** Викидається, коли значення TimeSpan стає від'ємним. */
public class NegativeTimeSpanException extends RuntimeException {

    public NegativeTimeSpanException(String message) {
        super(message);
    }
}
