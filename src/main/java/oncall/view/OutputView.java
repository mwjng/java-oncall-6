package oncall.view;

import java.util.List;
import oncall.domain.Date;
import oncall.domain.DayOfWeek;
import oncall.domain.Month;
import oncall.domain.Workday;
import oncall.domain.Worker;

public class OutputView {

    public void askMonthAndStartDayOfWeek() {
        System.out.print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
    }

    public void askWeekdaysWorkers() {
        System.out.print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
    }

    public void askWeekendsWorkers() {
        System.out.print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
    }

    public void showErrorMessage(String message) {
        System.out.println("[ERROR] " + message + " 다시 입력해 주세요.");
    }

    public void showEmergencyWorkersResult(List<Workday> workdays) {
        for (Workday workday : workdays) {
            Date date = workday.getDate();
            Worker worker = workday.getWorker();

            Month month = date.getMonth();
            int day = date.getDay();
            DayOfWeek dayOfWeek = date.getDayOfWeek();

            if (date.isHoliday() && !dayOfWeek.isWeekend()) {
                System.out.println(month.getName() + " " + day + "일 " + dayOfWeek.getName() + "(휴일) " + worker.getNickname());
                continue;
            }
            System.out.println(month.getName() + " " + day + "일 " + dayOfWeek.getName() + " " + worker.getNickname());
        }
    }
}
