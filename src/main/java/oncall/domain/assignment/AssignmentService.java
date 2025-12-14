package oncall.domain.assignment;

import java.util.ArrayList;
import java.util.List;
import oncall.domain.date.Date;
import oncall.domain.date.DayOfWeek;
import oncall.domain.date.Month;
import oncall.domain.worker.EmergencyWorkers;
import oncall.domain.worker.Worker;

public class AssignmentService {

    public List<Workday> assign(
            Month month,
            DayOfWeek startDayOfWeek,
            EmergencyWorkers emergencyWorkers
    ) {
        List<Workday> workdays = new ArrayList<>();

        WorkerOrder weekdaysWorkerOrder = WorkerOrder.from(emergencyWorkers.getWeekdaysWorkers());
        WorkerOrder weekendsWorkerOrder = WorkerOrder.from(emergencyWorkers.getWeekendsWorkers());
        Date date = Date.firstDayOfMonth(month, startDayOfWeek);

        while (date.getMonth() == month) {
            WorkerOrder selectedWorkerOrder = selectWorkerOrder(date, weekendsWorkerOrder, weekdaysWorkerOrder);
            Worker currentWorker = selectedWorkerOrder.next();

            Workday workday = Workday.of(currentWorker, date);
            workdays.add(workday);
            date = date.nextDay();
        }

        return workdays;
    }

    private WorkerOrder selectWorkerOrder(
            Date date,
            WorkerOrder weekendsWorkerOrder,
            WorkerOrder weekdaysWorkerOrder
    ) {
        if (date.isHoliday()) {
            return weekendsWorkerOrder;
        }
        return weekdaysWorkerOrder;
    }
}
