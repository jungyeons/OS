package guiview;

import javax.swing.*;

import textview.CPU;
import textview.ProcessManager;

import java.awt.*;

public class Monitor extends JFrame {

    private CPU cpu;
    private ProcessManager processManager;
    private CustomPanel processManagerPanel;
    private CustomPanel cpuPanel;
    private boolean isRunning = false;

    public Monitor(CPU cpu, ProcessManager processManager) {
        this.cpu = cpu;
        this.processManager = processManager;
        setTitle("Process Manager Simulator");
        setSize(900, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        add(processManagerPanel = new CustomPanel());
        add(cpuPanel = new CustomPanel());
        add(new CustomButtonPanel());
        new MonitorUpdater().start();
        setVisible(true);
    }

    private void run() {
        if (isRunning || processManager.getProcessCount() == 0) return;
        cpu.start();
        processManager.start();
        isRunning = true;
    }

    private void load() {
        processManager.load();
    }

    private void interrupt() {
        processManager.interrupt();
    }

    private class CustomButtonPanel extends JPanel {
        public CustomButtonPanel() {
            add(new CustomButton("Run", (e) -> run()));
            add(new CustomButton("Load", (e) -> load()));
            add(new CustomButton("Interrupt", (e) -> interrupt()));
        }
    }

    private class MonitorUpdater extends Thread {
        @Override
        public void run() {
            while (true) {
                processManagerPanel.update(processManager.getStatus());
                cpuPanel.update(cpu.getStatus());
            }
        }
    }

}
