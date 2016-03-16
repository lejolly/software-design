package tools;

import javax.swing.event.SwingPropertyChangeSupport;
import java.awt.*;
import java.beans.PropertyChangeListener;

public class ColourBean {

    private SwingPropertyChangeSupport swingPropertyChangeSupport = new SwingPropertyChangeSupport(this);
    private Color colour = Color.BLACK;

    public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        this.swingPropertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
    }

    public void removePropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        this.swingPropertyChangeSupport.removePropertyChangeListener(propertyChangeListener);
    }

    public Color getColour() {
        return colour;
    }

    public void setColour(Color colour, Object source) {
        this.colour = colour;
        this.swingPropertyChangeSupport.firePropertyChange("colour", source, this.colour);
    }

}
