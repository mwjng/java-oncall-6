package oncall.controller.dto;

import oncall.domain.date.DayOfWeek;
import oncall.domain.date.Month;

public record MonthAndStartDayOfWeek(
        Month month,
        DayOfWeek startDayOfWeek
) {
}