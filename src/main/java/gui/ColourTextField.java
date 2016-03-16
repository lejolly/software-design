package gui;

import tools.ColourBean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ColourTextField extends JTextField implements PropertyChangeListener {

    private ColourTextField that = this;
    private Color colour;

    public ColourTextField(final Color colour, final ColourBean colourBean) {
        this.colour = colour;
        this.setPreferredSize(new Dimension(80, 20));
        colourBean.addPropertyChangeListener(this);
        if (this.colour.equals(Color.RED)) {
            this.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        int value = Integer.parseInt(that.getText());
                        if (value >= 0 && value <= 255) {
                            colourBean.setColour(new Color(value,
                                    colourBean.getColour().getGreen(),
                                    colourBean.getColour().getBlue()),
                                    this);
                        }
                    } catch (Exception ex) {
                        System.out.println("Invalid value");
                    }
                }
            });
        } else if (this.colour.equals(Color.GREEN)) {
            this.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        int value = Integer.parseInt(that.getText());
                        if (value >= 0 && value <= 255) {
                            colourBean.setColour(new Color(colourBean.getColour().getRed(),
                                    value,
                                    colourBean.getColour().getBlue()),
                                    this);
                        }
                    } catch (Exception ex) {
                        System.out.println("Invalid value");
                    }
                }
            });
        } else if (this.colour.equals(Color.BLUE)) {
            this.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        int value = Integer.parseInt(that.getText());
                        if (value >= 0 && value <= 255) {
                            colourBean.setColour(new Color(colourBean.getColour().getRed(),
                                    colourBean.getColour().getGreen(),
                                    value),
                                    this);
                        }
                    } catch (Exception ex) {
                        System.out.println("Invalid value");
                    }
                }
            });
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (!evt.getSource().equals(this)) {
            Color newColour = (Color) evt.getNewValue();
            if (this.colour.equals(Color.RED)) {
                this.setText(Integer.toString(newColour.getRed()));
            } else if (this.colour.equals(Color.GREEN)) {
                this.setText(Integer.toString(newColour.getGreen()));
            } else if (this.colour.equals(Color.BLUE)) {
                this.setText(Integer.toString(newColour.getBlue()));
            }
        }
    }

}
