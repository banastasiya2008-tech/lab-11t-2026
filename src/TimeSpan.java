import exceptions.IllegalInputTimeSpanException;
import exceptions.IllegalScaleFactorException;
import exceptions.NegativeTimeSpanException;

/**
 * Інтервал часу в годинах та хвилинах. Об'єкт незмінний (immutable) і
 * ніколи не може мати від'ємне значення.
 */
public class TimeSpan {

    private final long totalMinutes;

    /** Створює інтервал за годинами та хвилинами (0 <= minutes <= 59). */
    public TimeSpan(int hours, int minutes) {
        if (hours < 0 || minutes < 0) {
            throw new IllegalInputTimeSpanException(
                    "Hours and minutes must not be negative: " + hours + "h " + minutes + "m");
        }
        if (minutes > 59) {
            throw new IllegalInputTimeSpanException(
                    "Minutes must be in range 0..59 when hours are given: " + minutes);
        }
        this.totalMinutes = hours * 60L + minutes;
    }

    /** Створює інтервал із кількості хвилин (може бути понад 59). */
    public TimeSpan(int minutes) {
        if (minutes < 0) {
            throw new IllegalInputTimeSpanException("Minutes must not be negative: " + minutes);
        }
        this.totalMinutes = minutes;
    }

    private TimeSpan(long totalMinutes) {
        if (totalMinutes < 0) {
            throw new NegativeTimeSpanException("Time span can not be negative: " + totalMinutes + " minutes");
        }
        this.totalMinutes = totalMinutes;
    }

    public int getHours() {
        return (int) (totalMinutes / 60);
    }

    public int getMinutes() {
        return (int) (totalMinutes % 60);
    }

    public long getTotalMinutes() {
        return totalMinutes;
    }

    public double getTotalHours() {
        return totalMinutes / 60.0;
    }

    public TimeSpan add(int hours, int minutes) {
        return add(new TimeSpan(hours, minutes));
    }

    public TimeSpan add(int minutes) {
        return add(new TimeSpan(minutes));
    }

    public TimeSpan add(TimeSpan other) {
        requireNonNull(other);
        return new TimeSpan(totalMinutes + other.totalMinutes);
    }

    public TimeSpan subtract(int hours, int minutes) {
        return subtract(new TimeSpan(hours, minutes));
    }

    public TimeSpan subtract(int minutes) {
        return subtract(new TimeSpan(minutes));
    }

    /** @throws NegativeTimeSpanException якщо результат був би від'ємним */
    public TimeSpan subtract(TimeSpan other) {
        requireNonNull(other);
        return new TimeSpan(totalMinutes - other.totalMinutes);
    }

    /** Множить інтервал на додатний множник. */
    public TimeSpan scale(int factor) {
        if (factor <= 0) {
            throw new IllegalScaleFactorException("Scale factor must be positive: " + factor);
        }
        return new TimeSpan(totalMinutes * factor);
    }

    private static void requireNonNull(TimeSpan other) {
        if (other == null) {
            throw new IllegalInputTimeSpanException("Time span must not be null");
        }
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof TimeSpan t && t.totalMinutes == totalMinutes;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(totalMinutes);
    }

    @Override
    public String toString() {
        return getHours() + "h " + getMinutes() + "m";
    }
}
