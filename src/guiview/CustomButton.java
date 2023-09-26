package guiview;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CustomButton extends JButton {

    public CustomButton(String text, ActionListener al) {
        super(text);
        setPreferredSize(new Dimension(100, 30));
        setFocusable(false);
        addActionListener(al);
    }

}