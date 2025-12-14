package oncall.domain.date;

import java.util.Optional;

public class Date {
    private final Month month;
    private final int day;
    private final DayOfWeek dayOfWeek;

    private Date(Month month, int day, DayOfWeek dayOfWeek) {
        validateRange(month, day);
        this.month = month;
        this.day = day;
        this.dayOfWeek = dayOfWeek;
    }

    public static Date of(Month month, int day, DayOfWeek dayOfWeek) {
        return new Date(month, day, dayOfWeek);
    }

    public static Date firstDayOfMonth(Month month, DayOfWeek dayOfWeek) {
        return of(month, 1, dayOfWeek);
    }

    public boolean isHoliday() {
        if (dayOfWeek.isWeekend()) {
            return true;
        }
        Optional<Holiday> holiday = Holiday.findBy(month, day);
        return holiday.isPresent();
    }

    public Date nextDay() {
        if (month.isLastDayOfMonth(day)) {
            return firstDayOfMonth(month.nextMonth(), dayOfWeek.next());
        }
        return of(month, day + 1, dayOfWeek.next());
    }

    public Month getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    private void validateRange(Month month, int day) {
        if (month.isOutOfRange(day)) {
            throw new IllegalArgumentException("올바르지 않은 날짜입니다.");
        }
    }
}
