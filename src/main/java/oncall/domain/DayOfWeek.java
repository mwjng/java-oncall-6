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

    private static final DayOfWeek[] DAYS = DayOfWeek.values();

    private final String displayName;

    DayOfWeek(String displayName) {
        this.displayName = displayName;
    }

    public static DayOfWeek of(String input) {
        return Arrays.stream(DAYS)
                .filter(day -> day.displayName.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 요일입니다."));
    }

    public boolean isWeekend() {
        return this == SATURDAY || this == SUNDAY;
    }

    public DayOfWeek next() {
        return DAYS[(ordinal() + 1) % DAYS.length];
    }

    public String getDisplayName() {
        return displayName;
    }
}
