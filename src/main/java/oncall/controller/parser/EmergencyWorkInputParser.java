package oncall.controller.parser;

import oncall.controller.dto.MonthAndStartDayOfWeek;
import oncall.domain.DayOfWeek;
import oncall.domain.Month;
import oncall.domain.Workers;

public class EmergencyWorkInputParser {

    public MonthAndStartDayOfWeek parseMonthAndStartDayOfWeek(String input) {
        String[] parts = input.split(",");
        Month month = Month.of(parts[0]);
        DayOfWeek dayOfWeek = DayOfWeek.of(parts[1]);

        return new MonthAndStartDayOfWeek(month, dayOfWeek);
    }

    public Workers parseWorkers(String input) {
        String[] inputWorkers = input.split(",");
        return Workers.from(inputWorkers);
    }
}
