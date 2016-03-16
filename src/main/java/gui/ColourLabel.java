package gui;

import tools.ColourBean;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ColourLabel extends JLabel implements PropertyChangeListener {

    private Color colour;

    public ColourLabel(final Color colour, final ColourBean colourBean) {
        this.colour = colour;
        this.setPreferredSize(new Dimension(80, 20));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (!evt.getSource().equals(this)) {
            Color newColour = (Color) evt.getNewValue();
            if (this.colour.equals(Color.RED)) {
                this.setText(Integer.toString(newColour.getRed(), 16).toUpperCase());
            } else if (this.colour.equals(Color.GREEN)) {
                this.setText(Integer.toString(newColour.getGreen(), 16).toUpperCase());
            } else if (this.colour.equals(Color.BLUE)) {
                this.setText(Integer.toString(newColour.getBlue(), 16).toUpperCase());
            }
        }
    }

}
