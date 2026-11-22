package exceptions;

/** Базовий клас для всіх виключень, пов'язаних з банківськими рахунками. */
public class AccountException extends RuntimeException {

    public AccountException(String message) {
        super(message);
    }
}
