package oncall.domain.worker;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EmergencyWorkers {
    private final Workers weekdaysWorkers;
    private final Workers weekendsWorkers;

    private EmergencyWorkers(Workers weekdaysWorkers, Workers weekendsWorkers) {
        validateIncludeBothSides(weekdaysWorkers, weekendsWorkers);
        this.weekdaysWorkers = weekdaysWorkers;
        this.weekendsWorkers = weekendsWorkers;
    }

    public static EmergencyWorkers of(Workers weekdaysWorkers, Workers weekendsWorkers) {
        return new EmergencyWorkers(weekdaysWorkers, weekendsWorkers);
    }

    public Workers getWeekdaysWorkers() {
        return weekdaysWorkers;
    }

    public Workers getWeekendsWorkers() {
        return weekendsWorkers;
    }

    private void validateIncludeBothSides(Workers weekdaysWorkers, Workers weekendsWorkers) {
        Set<Worker> weekdays = new HashSet<>(weekdaysWorkers.getWorkers());
        Set<Worker> weekends = new HashSet<>(weekendsWorkers.getWorkers());

        if (!weekdays.equals(weekends)) {
            throw new IllegalArgumentException("평일과 주말 근무자는 동일한 구성원이어야 합니다.");
        }
    }
}
