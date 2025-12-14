package oncall.domain.assignment;

import java.util.ArrayList;
import java.util.List;
import oncall.domain.date.Date;
import oncall.domain.date.DayOfWeek;
import oncall.domain.date.Month;
import oncall.domain.worker.Worker;

public class AssignmentService {

    public List<Workday> assignment(
            Month month,
            DayOfWeek startDayOfWeek,
            List<Worker> weekdaysWorkers,
            List<Worker> weekendsWorkers
    ) {
        List<Workday> workdays = new ArrayList<>();
        int weekdaysCursor = 0;
        int weekendsCursor = 0;
        Worker currentWorker;
        DayOfWeek dayOfWeek = startDayOfWeek;
        Date date = Date.of(month, 1, dayOfWeek);

        if (date.isHoliday()) {
            currentWorker = weekendsWorkers.get(weekendsCursor++);
        } else {
            currentWorker = weekdaysWorkers.get(weekdaysCursor++);
        }
        Workday workday = Workday.of(currentWorker, date);
        workdays.add(workday);
        Worker previousWorker = currentWorker;
        dayOfWeek = dayOfWeek.next();

        for (int day = 2; day <= month.getDaysInMonth(); day++) {
            if (weekdaysCursor == weekdaysWorkers.size()) {
                weekdaysCursor = 0;
            }
            if (weekendsCursor == weekendsWorkers.size()) {
                weekendsCursor = 0;
            }

            date = Date.of(month, day, dayOfWeek);
            if (date.isHoliday()) {
                currentWorker = weekendsWorkers.get(weekendsCursor);
                if (previousWorker.equals(currentWorker)) {
                    if (weekendsCursor == weekendsWorkers.size() - 1) {
                        Worker nextWorker = weekendsWorkers.remove(0);
                        weekendsWorkers.remove(currentWorker);
                        weekendsWorkers.add(0, currentWorker);
                        weekendsWorkers.add(nextWorker);
                    } else {
                        Worker nextWorker = weekendsWorkers.remove(weekendsCursor + 1);
                        weekendsWorkers.add(weekendsCursor, nextWorker);
                        currentWorker = nextWorker;
                    }
                }
                workday = Workday.of(currentWorker, date);
                workdays.add(workday);
                weekendsCursor++;
                dayOfWeek = dayOfWeek.next();
                previousWorker = currentWorker;
                continue;
            }

            currentWorker = weekdaysWorkers.get(weekdaysCursor);
            if (previousWorker.equals(currentWorker)) {
                if (weekdaysCursor == weekdaysWorkers.size() - 1) {
                    Worker nextWorker = weekdaysWorkers.remove(0);
                    weekdaysWorkers.remove(currentWorker);
                    weekdaysWorkers.add(0, currentWorker);
                    weekdaysWorkers.add(nextWorker);
                } else {
                    Worker nextWorker = weekdaysWorkers.remove(weekdaysCursor + 1);
                    weekdaysWorkers.add(weekdaysCursor, nextWorker);
                    currentWorker = nextWorker;
                }
            }
            workday = Workday.of(currentWorker, date);
            workdays.add(workday);
            weekdaysCursor++;
            dayOfWeek = dayOfWeek.next();
            previousWorker = currentWorker;
        }

        return workdays;
    }
}
