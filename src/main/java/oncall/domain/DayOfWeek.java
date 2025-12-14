package oncall.domain;

import java.util.Arrays;

public enum DayOfWeek {

    MONDAY("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목"),
    FRIDAY("금"),
    SATURDAY("토"),
    SUNDAY("일");

    private final String name;

    DayOfWeek(String name) {
        this.name = name;
    }

    public static DayOfWeek of(String inputDayOfWeek) {
        return Arrays.stream(values())
                .filter(dayOfWeek -> dayOfWeek.name.equals(inputDayOfWeek))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 요일 입니다."));
    }

    public boolean isWeekend() {
        return this == SATURDAY || this == SUNDAY;
    }

    public DayOfWeek next() {
        if (this == SUNDAY) {
            return MONDAY;
        }
        return DayOfWeek.values()[ordinal() + 1];
    }

    public String getName() {
        return name;
    }
}
