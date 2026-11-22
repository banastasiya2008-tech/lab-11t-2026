package model;

import exceptions.DailyLimitExceededException;
import exceptions.InvalidAmountException;

public class CheckingAccount extends BankAccount {

    public static final double DEFAULT_DAILY_WITHDRAWAL_LIMIT = 300;

    private double dailyWithdrawalLimit = DEFAULT_DAILY_WITHDRAWAL_LIMIT;

    public CheckingAccount(String customerName, String userId, String password) {
        super(customerName, userId, password);
    }

    @Override
    public void withdraw(double amount) {
        validateAmount(amount);
        if (amount > dailyWithdrawalLimit) {
            throw new DailyLimitExceededException(
                    "Withdrawal amount " + amount + " exceeds daily limit " + dailyWithdrawalLimit);
        }
        super.withdraw(amount);
    }

    public double getDailyWithdrawalLimit() {
        return dailyWithdrawalLimit;
    }

    public void setDailyWithdrawalLimit(double dailyWithdrawalLimit) {
        if (Double.isNaN(dailyWithdrawalLimit) || Double.isInfinite(dailyWithdrawalLimit)
                || dailyWithdrawalLimit <= 0) {
            throw new InvalidAmountException("Daily withdrawal limit must be greater than 0");
        }
        this.dailyWithdrawalLimit = dailyWithdrawalLimit;
    }
}
