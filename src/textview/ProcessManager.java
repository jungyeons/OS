package textview;

import java.util.LinkedList;
import java.util.Queue;

public class ProcessManager extends Thread {

    private CPU cpu;
    private Scheduler scheduler;
    private InterruptHandler interruptHandler;

    public ProcessManager() {
        this.scheduler = new Scheduler();
        this.interruptHandler = new InterruptHandler();
        interruptHandler.associate(scheduler);
    }

    public void associate(CPU cpu) {
        this.cpu = cpu;
        scheduler.associate(cpu);
    }

    public Process getRunningProcess() {
        return scheduler.getRunningProcess();
    }


    public void interrupt() {
        if (scheduler.blockQueue.isEmpty()) return;
        scheduler.readyQueue.add(scheduler.blockQueue.poll());
    }

    @Override
    public void run() {
        scheduler.start();
    }

    public String getStatus() {
        return scheduler.getStatus();
    }

    public int getProcessCount() {
        return scheduler.getProcessCount();
    }


    public void load() {
        if (getProcessCount() >= 6) {
            System.out.println("Low Memory! (Max : 6)");
            return;
        }
        scheduler.readyQueue.add(new Process("PROCESS_" + (char) ('A' + getProcessCount())));
    }

    public void interruptServiceRoutine(int i) {
        interruptHandler.process(i);
    }
}
