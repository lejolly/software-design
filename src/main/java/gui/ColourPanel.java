package gui;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ColourPanel extends JPanel implements PropertyChangeListener {

    public ColourPanel() {
        this.setPreferredSize(new Dimension(100, 100));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.setBackground((Color) evt.getNewValue());
    }

}
