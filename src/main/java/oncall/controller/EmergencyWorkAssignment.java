package oncall.controller;

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
        while (true) {
            try {
                outputView.askMonthAndStartDayOfWeek();
                String input = inputView.read();
                return parser.parseMonthAndStartDayOfWeek(input);
            } catch (Exception e) {
                outputView.showErrorMessage();
            }
        }
    }

    private Workers requestInputWeekdaysWorkers() {
        while (true) {
            try {
                outputView.askWeekdaysWorkers();
                String input = inputView.read();
                return parser.parseWorkers(input);
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
                return parser.parseWorkers(input);
            } catch (Exception e) {
                outputView.showErrorMessage();
            }
        }
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
}
