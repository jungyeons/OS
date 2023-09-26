package textview;

import javax.swing.*;

import guiview.Monitor;

public class Main {

    public static void main(String[] args) throws Exception {
        CPU cpu = new CPU();
        cpu.setSlowMode(true);
        ProcessManager processManager = new ProcessManager();
        cpu.associate(processManager);
        processManager.associate(cpu);
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        new Monitor(cpu, processManager);
    }
}
