package oncall.domain.assignment;

import java.util.ArrayList;
import java.util.List;
import oncall.domain.worker.Worker;
import oncall.domain.worker.Workers;

public class WorkerOrder {
    private final List<Worker> workers;
    private int cursor = 0;
    private Worker previous;

    private WorkerOrder(List<Worker> workers) {
        this.workers = workers;
    }

    public static WorkerOrder from(Workers workers) {
        List<Worker> orderedWorkers = new ArrayList<>(workers.getWorkers());
        return new WorkerOrder(orderedWorkers);
    }

    public Worker next() {
        Worker candidate = workers.get(cursor);

        if (candidate.equals(previous)) {
            swapWithNext();
            candidate = workers.get(cursor);
        }
        nextCursor();
        previous = candidate;
        return candidate;
    }

    private void swapWithNext() {
        if (cursor == workers.size() - 1) {
            Worker currentWorker = workers.remove(cursor);
            Worker nextWorker = workers.remove(0);
            workers.add(0, currentWorker);
            workers.add(nextWorker);
            return;
        }

        Worker nextWorker = workers.remove(cursor + 1);
        workers.add(cursor, nextWorker);
    }

    private void nextCursor() {
        cursor = (cursor + 1) % workers.size();
    }
}
