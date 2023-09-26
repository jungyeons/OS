package guiview;

import javax.swing.*;
import java.awt.*;

public class CustomPanel extends JPanel {

    protected JTextArea textArea;

    public CustomPanel() {
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setEnabled(false);
        textArea.setDisabledTextColor(textArea.getForeground());
        textArea.setPreferredSize(new Dimension(800, 140));
        add(textArea);
    }

    public void update(String text) {
        textArea.setText(text);
    }
}