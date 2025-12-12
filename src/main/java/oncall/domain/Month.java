package oncall.domain;

import java.util.Arrays;

public enum Month {

    JANUARY("1월", 31),
    FEBRUARY("2월", 28),
    MARCH("3월", 31),
    APRIL("4월", 30),
    MAY("5월", 31),
    JUNE("6월", 30),
    JULY("7월", 31),
    AUGUST("8월", 31),
    SEPTEMBER("9월", 30),
    OCTOBER("10월", 31),
    NOVEMBER("11월", 30),
    DECEMBER("12월", 31);

    private final String name;
    private final int daysInMonth;

    Month(String name, int daysInMonth) {
        this.name = name;
        this.daysInMonth = daysInMonth;
    }

    public static Month of(String inputMonth) {
        return Arrays.stream(values())
                .filter(month -> month.name.equals(inputMonth + "월"))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 월 입니다."));
    }

    public boolean isOutOfRange(int day) {
        return day < 1 || day > daysInMonth;
    }
}
