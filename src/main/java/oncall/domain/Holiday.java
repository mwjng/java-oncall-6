package oncall.domain;

import java.util.Arrays;
import java.util.Optional;

public enum Holiday {

    SHINJUNG(Month.JANUARY, 1, "신정"),
    SAMIL(Month.MARCH, 1, "삼일절"),
    CHILDREN(Month.MAY, 5, "어린이날"),
    HYUNCHUNG(Month.JUNE, 6, "현충일"),
    GWANGBOK(Month.AUGUST, 15, "광복절"),
    GAECHUN(Month.OCTOBER, 3, "개천절"),
    HANGUL(Month.OCTOBER, 9, "한글날"),
    CHRISTMAS(Month.DECEMBER, 25, "성탄절");

    private final Month month;
    private final int day;
    private final String description;

    Holiday(Month month, int day, String description) {
        this.month = month;
        this.day = day;
        this.description = description;
    }

    public static Optional<Holiday> findBy(Month month, int day) {
        return Arrays.stream(values())
                .filter(holiday -> holiday.month == month)
                .filter(holiday -> holiday.day == day)
                .findFirst();
    }
}
