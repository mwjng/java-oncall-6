package oncall.controller.dto;

import oncall.domain.DayOfWeek;
import oncall.domain.Month;

public record MonthAndStartDayOfWeek(
        Month month,
        DayOfWeek startDayOfWeek
) {
}