package gui;

import tools.ColourBean;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ColourPanel extends JPanel implements PropertyChangeListener {

    public ColourPanel(ColourBean colourBean) {
        this.setPreferredSize(new Dimension(100, 100));
        colourBean.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.setBackground((Color) evt.getNewValue());
    }

}
