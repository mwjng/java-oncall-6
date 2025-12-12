package oncall.controller;

import oncall.domain.DayOfWeek;
import oncall.domain.Month;
import oncall.view.InputView;
import oncall.view.OutputView;

public class EmergencyWorkAssignment {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        outputView.askMonthAndStartDayOfWeek();
        String input = inputView.read();
        String[] parts = input.split(",");
        String inputMonth = parts[0];
        String inputDayOfWeek = parts[1];
        Month month = Month.of(inputMonth);
        DayOfWeek dayOfWeek = DayOfWeek.of(inputDayOfWeek);
    }
}
