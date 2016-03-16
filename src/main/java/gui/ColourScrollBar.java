package gui;

import tools.ColourBean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ColourScrollBar extends JScrollBar implements PropertyChangeListener {

    private final ColourScrollBar that = this;
    private final Color colour;

    public ColourScrollBar(final Color colour, final ColourBean colourBean) {
        super(Adjustable.HORIZONTAL, 0, 1, 0, 256);
        this.colour = colour;
        this.setBackground(colour);
        this.setPreferredSize(new Dimension(200, 20));
        colourBean.addPropertyChangeListener(this);
        if (this.colour.equals(Color.RED)) {
            this.addAdjustmentListener(new AdjustmentListener() {
                @Override
                public void adjustmentValueChanged(AdjustmentEvent e) {
                    colourBean.setColour(new Color(that.getValue(),
                            colourBean.getColour().getGreen(),
                            colourBean.getColour().getBlue()));
                }
            });
        } else if (this.colour.equals(Color.GREEN)) {
            this.addAdjustmentListener(new AdjustmentListener() {
                @Override
                public void adjustmentValueChanged(AdjustmentEvent e) {
                    colourBean.setColour(new Color(colourBean.getColour().getRed(),
                            that.getValue(),
                            colourBean.getColour().getBlue()));
                }
            });
        } else if (this.colour.equals(Color.BLUE)) {
            this.addAdjustmentListener(new AdjustmentListener() {
                @Override
                public void adjustmentValueChanged(AdjustmentEvent e) {
                    colourBean.setColour(new Color(colourBean.getColour().getRed(),
                            colourBean.getColour().getGreen(),
                            that.getValue()));
                }
            });
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (!evt.getSource().equals(this)) {
            Color newColour = (Color) evt.getNewValue();
            if (this.colour.equals(Color.RED)) {
                this.setValue(newColour.getRed());
            } else if (this.colour.equals(Color.GREEN)) {
                this.setValue(newColour.getGreen());
            } else if (this.colour.equals(Color.BLUE)) {
                this.setValue(newColour.getBlue());
            }
        }
    }

}
