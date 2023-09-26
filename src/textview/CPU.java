package textview;

public class CPU extends Thread {

    public int PC;
    public Instruction IR;
    public boolean timeOut;
    public boolean eventWait;
    private Timer timer;
    private boolean slowMode;

    private ProcessManager processManager;

    public CPU() {
        PC = 0;
        IR = null;
        timeOut = false;
        eventWait = false;
        timer = new Timer();
        processManager = null;
        slowMode = false;
    }

    public void associate(ProcessManager processManager) {
        this.processManager = processManager;
    }

    public void setSlowMode(boolean b) {
        slowMode = b;
    }

    @Override
    public void run() {
        timer.start();
        try {
            while (true) {
                if (processManager.getRunningProcess() == null) continue; // Idle
                fetch();
                ++PC;
                decode();
                execute();
                checkStatus();
            }
        } catch (InterruptedException e) {
            System.err.println("InterruptedException has occurred.");
        }
    }

    private void fetch() throws InterruptedException {
        IR = processManager.getRunningProcess().instructions.get(PC);
        if (slowMode) Thread.sleep(300);
    }

    private void decode() throws InterruptedException {
        if (slowMode) Thread.sleep(300);
    }

    private void execute() throws InterruptedException {
        switch (IR) {
            case DO -> DO();
            case JUMP -> JUMP();
            case INT -> INT();
        }
    }

    private void DO() throws InterruptedException {
        if (slowMode) Thread.sleep(300);
    }

    private void JUMP() {
        PC = 0;
    }

    private void INT() {
        eventWait = true;
    }

    private void checkStatus() throws InterruptedException {
        Thread.sleep(300);
        if (eventWait) {
            processManager.interruptServiceRoutine(0);
            eventWait = false;
        } else if (timeOut) {
            processManager.interruptServiceRoutine(1);
            timer.init();
        }
    }

    public void setContext(Process nextProcess) {
        PC = nextProcess.PC;
    }

    public String getStatus() {
        String text = "[CPU Status]\n";
        text += "PC        : " + PC + "\n";
        text += "IR        : " + IR + "\n";
        text += "TimeOut   : " + timeOut + "\n";
        text += "EventWait : " + eventWait + "\n";
        return text;
    }

    class Timer extends Thread {

        private int sec = 0;

        public void init() {
            sec = 0;
            timeOut = false;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(slowMode ? 100 : 500);
                    ++this.sec;
                    if (this.sec > 3) timeOut = true;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
