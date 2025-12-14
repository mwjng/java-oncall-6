package oncall.domain;

public class Workday {
    private final Worker worker;
    private final Date date;

    private Workday(Worker worker, Date date) {
        this.worker = worker;
        this.date = date;
    }

    public static Workday of(Worker worker, Date date) {
        return new Workday(worker, date);
    }

    public Worker getWorker() {
        return worker;
    }

    public Date getDate() {
        return date;
    }
}
