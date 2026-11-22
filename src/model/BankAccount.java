package model;

import exceptions.AuthenticationException;
import exceptions.InsufficientFundsException;
import exceptions.InvalidAmountException;
import exceptions.InvalidPasswordException;

public class BankAccount {

    public static final int MIN_PASSWORD_LENGTH = 8;

    private final String userId;
    private final String customerName;
    private String password;
    private double balance;

    public BankAccount(String customerName, String userId, String password) {
        validatePassword(password);
        this.customerName = customerName;
        this.userId = userId;
        this.password = password;
        this.balance = 0;
    }

    /** Пароль: щонайменше 8 символів та щонайменше одна цифра. */
    public static void validatePassword(String password) {
        if (password == null || password.length() < MIN_PASSWORD_LENGTH) {
            throw new InvalidPasswordException(
                    "Password must be at least " + MIN_PASSWORD_LENGTH + " characters long");
        }
        if (password.chars().noneMatch(Character::isDigit)) {
            throw new InvalidPasswordException("Password must contain at least one digit");
        }
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    /** @throws AuthenticationException якщо пароль невірний */
    public void authenticate(String password) {
        if (!checkPassword(password)) {
            throw new AuthenticationException("Wrong password for account '" + userId + "'");
        }
    }

    public void deposit(double amount) {
        validateAmount(amount);
        balance += amount;
    }

    public void withdraw(double amount) {
        validateAmount(amount);
        if (amount > balance) {
            throw new InsufficientFundsException(
                    "Insufficient funds: balance is " + balance + ", requested " + amount);
        }
        balance -= amount;
    }

    protected static void validateAmount(double amount) {
        if (Double.isNaN(amount) || Double.isInfinite(amount) || amount <= 0) {
            throw new InvalidAmountException("Amount must be greater than 0: " + amount);
        }
    }

    public String getUserId() {
        return userId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getBalance() {
        return balance;
    }
}
