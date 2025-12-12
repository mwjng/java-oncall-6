package oncall.controller;

import oncall.domain.DayOfWeek;
import oncall.domain.EmergencyWorkers;
import oncall.domain.Month;
import oncall.domain.Workers;
import oncall.dto.MonthAndStartDayInput;
import oncall.view.InputView;
import oncall.view.OutputView;

public class EmergencyWorkAssignment {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        MonthAndStartDayInput monthAndStartDayInput = requestInputMonthAndStartDayOfWeek();
        Month month = Month.of(monthAndStartDayInput.month());
        DayOfWeek dayOfWeek = DayOfWeek.of(monthAndStartDayInput.startDayOfWeek());

        Workers weekdaysWorkers = requestInputWeekdaysWorkers();
        Workers weekendsWorkers = requestInputWeekendsWorkers();
        EmergencyWorkers emergencyWorkers = EmergencyWorkers.of(weekdaysWorkers, weekendsWorkers);
    }

    private MonthAndStartDayInput requestInputMonthAndStartDayOfWeek() {
        outputView.askMonthAndStartDayOfWeek();
        String input = inputView.read();
        return parseMonthAndStartDayInput(input);
    }

    private MonthAndStartDayInput parseMonthAndStartDayInput(String input) {
        String[] parts = input.split(",");
        return new MonthAndStartDayInput(parts[0], parts[1]);
    }

    private Workers requestInputWeekdaysWorkers() {
        outputView.askWeekdaysWorkers();
        String input = inputView.read();
        return parseWorkers(input);
    }

    private Workers requestInputWeekendsWorkers() {
        outputView.askWeekendsWorkers();
        String input = inputView.read();
        return parseWorkers(input);
    }

    private Workers parseWorkers(String input) {
        String[] inputWorkers = input.split(",");
        return Workers.from(inputWorkers);
    }
}
