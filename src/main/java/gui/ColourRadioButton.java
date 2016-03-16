package gui;

import tools.ColourBean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ColourRadioButton extends JRadioButton implements PropertyChangeListener {

    private final ColourRadioButton that = this;
    private final Color colour;

    public ColourRadioButton(String name, final Color colour, final ColourBean colourBean) {
        super(name);
        this.colour = colour;
        colourBean.addPropertyChangeListener(this);
        this.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colourBean.setColour(that.colour);
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue().equals(colour)) {
            this.setSelected(true);
        } else {
            this.setSelected(false);
        }
    }

}
