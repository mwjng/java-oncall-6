package oncall.controller;

import oncall.domain.DayOfWeek;
import oncall.domain.EmergencyWorkers;
import oncall.domain.Month;
import oncall.domain.Workers;
import oncall.view.InputView;
import oncall.view.OutputView;

public class EmergencyWorkAssignment {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        MonthAndStartDayOfWeek monthAndStartDayOfWeek = requestInputMonthAndStartDayOfWeek();
        EmergencyWorkers emergencyWorkers = getEmergencyWorkers();
    }

    private MonthAndStartDayOfWeek requestInputMonthAndStartDayOfWeek() {
        while (true) {
            try {
                outputView.askMonthAndStartDayOfWeek();
                String input = inputView.read();
                return parseMonthAndStartDayOfWeek(input);
            } catch (Exception e) {
                outputView.showErrorMessage();
            }
        }
    }

    private MonthAndStartDayOfWeek parseMonthAndStartDayOfWeek(String input) {
        String[] parts = input.split(",");
        Month month = Month.of(parts[0]);
        DayOfWeek dayOfWeek = DayOfWeek.of(parts[1]);

        return new MonthAndStartDayOfWeek(month, dayOfWeek);
    }

    private Workers requestInputWeekdaysWorkers() {
        while (true) {
            try {
                outputView.askWeekdaysWorkers();
                String input = inputView.read();
                return parseWorkers(input);
            } catch (Exception e) {
                outputView.showErrorMessage();
            }
        }
    }

    private Workers requestInputWeekendsWorkers() {
        while (true) {
            try {
                outputView.askWeekendsWorkers();
                String input = inputView.read();
                return parseWorkers(input);
            } catch (Exception e) {
                outputView.showErrorMessage();
            }
        }
    }

    private Workers parseWorkers(String input) {
        String[] inputWorkers = input.split(",");
        return Workers.from(inputWorkers);
    }

    private EmergencyWorkers getEmergencyWorkers() {
        while (true) {
            try {
                Workers weekdaysWorkers = requestInputWeekdaysWorkers();
                Workers weekendsWorkers = requestInputWeekendsWorkers();
                return EmergencyWorkers.of(weekdaysWorkers, weekendsWorkers);
            } catch (Exception e) {
                outputView.showErrorMessage();
            }
        }
    }

    private record MonthAndStartDayOfWeek(
            Month month,
            DayOfWeek startDayOfWeek
    ) {
    }
}
