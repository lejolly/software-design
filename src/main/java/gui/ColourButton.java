package gui;

import tools.ColourBean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ColourButton extends JButton implements PropertyChangeListener {

    private final boolean isDarkerButton;

    public ColourButton(Boolean isDarkerButton, final ColourBean colourBean) {
        super(isDarkerButton ? "Darker" : "Brighter");
        this.isDarkerButton = isDarkerButton;
        colourBean.addPropertyChangeListener(this);
        if (this.isDarkerButton) {
            this.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    colourBean.setColour(colourBean.getColour().darker());
                }
            });
        } else {
            this.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    colourBean.setColour(colourBean.getColour().brighter());
                }
            });
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ((this.isDarkerButton && evt.getNewValue().equals(Color.BLACK)) ||
                (!this.isDarkerButton && evt.getNewValue().equals(Color.WHITE))) {
            this.setEnabled(false);
        } else {
            this.setEnabled(true);
        }
    }

}
