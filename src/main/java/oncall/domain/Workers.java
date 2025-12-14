package oncall.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Workers {
    private static final String WORKER_COUNT_ERROR_MESSAGE = "근무자는 최소 %d명 이상 입력해야 합니다.";
    private static final String DUPLICATE_ERROR_MESSAGE = "중복된 닉네임이 존재할 수 없습니다.";

    private static final int MINIMUM_COUNT = 5;
    private static final int MAXIMUM_COUNT = 35;

    private final List<Worker> workers;

    private Workers(List<Worker> workers) {
        validateCount(workers);
        validateNoDuplicate(workers);
        this.workers = workers;
    }

    public static Workers from(String[] inputWorkers) {
        List<Worker> workers = Arrays.stream(inputWorkers)
                .map(Worker::of)
                .toList();

        return new Workers(workers);
    }

    public List<Worker> getWorkers() {
        return new ArrayList<>(workers);
    }

    private void validateCount(List<Worker> workers) {
        if (isInvalidCount(workers.size())) {
            throw new IllegalArgumentException(WORKER_COUNT_ERROR_MESSAGE.formatted(MINIMUM_COUNT));
        }
    }

    private boolean isInvalidCount(int count) {
        return count < MINIMUM_COUNT || count > MAXIMUM_COUNT;
    }

    private void validateNoDuplicate(List<Worker> workers) {
        if (hasDuplicate(workers)) {
            throw new IllegalArgumentException(DUPLICATE_ERROR_MESSAGE);
        }
    }

    private boolean hasDuplicate(List<Worker> workers) {
        return workers.stream()
                .distinct()
                .count() != workers.size();
    }
}
