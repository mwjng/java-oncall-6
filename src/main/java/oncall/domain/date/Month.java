package oncall.domain.date;

import java.util.Arrays;

public enum Month {

    JANUARY(1, 31),
    FEBRUARY(2, 28),
    MARCH(3, 31),
    APRIL(4, 30),
    MAY(5, 31),
    JUNE(6, 30),
    JULY(7, 31),
    AUGUST(8, 31),
    SEPTEMBER(9, 30),
    OCTOBER(10, 31),
    NOVEMBER(11, 30),
    DECEMBER(12, 31);

    private final int monthNumber;
    private final int daysInMonth;

    Month(int monthNumber, int daysInMonth) {
        this.monthNumber = monthNumber;
        this.daysInMonth = daysInMonth;
    }

    public static Month of(int inputMonth) {
        return Arrays.stream(values())
                .filter(month -> month.monthNumber == inputMonth)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 월입니다."));
    }

    public boolean isOutOfRange(int day) {
        return day < 1 || day > daysInMonth;
    }

    public int getMonthNumber() {
        return monthNumber;
    }

    public int getDaysInMonth() {
        return daysInMonth;
    }
}
