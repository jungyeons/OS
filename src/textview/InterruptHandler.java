package textview;

public class InterruptHandler {

    private Scheduler scheduler;

    public void associate(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void process(int index) {
        switch (index) {
            case 0 -> addBlockQueue();
            case 1 -> switchContext();
        }
    }

    public void switchContext() {
        scheduler.switchContext();
    }

    public void addBlockQueue() {
        scheduler.addBlockQueue();
    }

}
