package textview;

import java.util.LinkedList;
import java.util.Queue;

public class Scheduler extends Thread {

    private CPU cpu;
    private Process runningProcess;
    public Queue<Process> readyQueue;
    public Queue<Process> blockQueue;

    public Scheduler() {
        runningProcess = null;
        readyQueue = new LinkedList<>();
        blockQueue = new LinkedList<>();
    }

    public Process getRunningProcess() {
        return runningProcess;
    }

    public void associate(CPU cpu) {
        this.cpu = cpu;
    }

    public void switchContext() {
        runningProcess.PC = cpu.PC;
        readyQueue.add(runningProcess);
        runningProcess = readyQueue.poll();
        cpu.setContext(runningProcess);
    }

    public void addBlockQueue() {
        runningProcess.PC = cpu.PC;
        blockQueue.add(runningProcess);
        runningProcess = readyQueue.poll();
        cpu.setContext(runningProcess);
    }

    public String getStatus() {
        String text = "[Process Manager Status]\n";
        text += "Running Process : " + (getRunningProcess() == null ? "Empty" : getRunningProcess().name) + "\n";
        text += "Ready Queue     : ";
        if (readyQueue.isEmpty()) text += "Empty";
        else for (Process process : readyQueue) text += process.name + "  ";
        text += "\nBlock Queue     : ";
        if (blockQueue.isEmpty()) text += "Empty";
        else for (Process process : blockQueue) text += process.name + "  ";
        return text;
    }


    @Override
    public void run() {
        while (readyQueue.isEmpty()) ;
        runningProcess = readyQueue.poll();
        cpu.setContext(runningProcess);
    }

    public int getProcessCount() {
        return (runningProcess == null ? 0 : 1) + readyQueue.size() + blockQueue.size();
    }
}
