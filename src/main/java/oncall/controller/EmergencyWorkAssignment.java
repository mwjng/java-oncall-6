package oncall.controller;

import java.util.function.Supplier;
import oncall.controller.dto.MonthAndStartDayOfWeek;
import oncall.controller.parser.EmergencyWorkInputParser;
import oncall.domain.EmergencyWorkers;
import oncall.domain.Workers;
import oncall.view.InputView;
import oncall.view.OutputView;

public class EmergencyWorkAssignment {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final EmergencyWorkInputParser parser = new EmergencyWorkInputParser();

    public void run() {
        MonthAndStartDayOfWeek monthAndStartDayOfWeek = requestInputMonthAndStartDayOfWeek();
        EmergencyWorkers emergencyWorkers = getEmergencyWorkers();
    }

    private MonthAndStartDayOfWeek requestInputMonthAndStartDayOfWeek() {
        return repeatUntilSuccess(() -> {
            outputView.askMonthAndStartDayOfWeek();
            String input = inputView.read();
            return parser.parseMonthAndStartDayOfWeek(input);
        });
    }

    private EmergencyWorkers getEmergencyWorkers() {
        return repeatUntilSuccess(() -> {
            Workers weekdaysWorkers = requestInputWeekdaysWorkers();
            Workers weekendsWorkers = requestInputWeekendsWorkers();
            return EmergencyWorkers.of(weekdaysWorkers, weekendsWorkers);
        });
    }

    private Workers requestInputWeekdaysWorkers() {
        return repeatUntilSuccess(() -> {
            outputView.askWeekdaysWorkers();
            String input = inputView.read();
            return parser.parseWorkers(input);
        });
    }

    private Workers requestInputWeekendsWorkers() {
        return repeatUntilSuccess(() -> {
            outputView.askWeekendsWorkers();
            String input = inputView.read();
            return parser.parseWorkers(input);
        });
    }

    private <T> T repeatUntilSuccess(Supplier<T> action) {
        while (true) {
            try {
                return action.get();
            } catch (IllegalArgumentException e) {
                outputView.showErrorMessage(e.getMessage());
            }
        }
    }
}
