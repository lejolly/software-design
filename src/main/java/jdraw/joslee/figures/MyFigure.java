package jdraw.joslee.figures;

import jdraw.framework.*;

import java.util.ArrayList;
import java.util.List;

public abstract class MyFigure implements Figure {

    private final DrawModel drawModel;
    private List<FigureListener> listeners;
    protected List<FigureHandle> handles;

    public MyFigure(DrawModel drawModel) {
        this.drawModel = drawModel;
        listeners = new ArrayList<>();
        handles = new ArrayList<>();
    }

    @Override
    public void addFigureListener(FigureListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    @Override
    public void removeFigureListener(FigureListener listener) {
        if (listeners.contains(listener)) {
            listeners.remove(listener);
        }
    }

    @Override
    public void notifyListeners() {
        FigureEvent event = new FigureEvent(this);
        for (FigureListener listener : new ArrayList<>(listeners)) {
            listener.figureChanged(event);
        }
    }

    public abstract Figure clone();

    public abstract void createHandles();

    /**
     * Returns a list of handles for this figure.
     * @return all handles that are attached to the targeted figure.
     * @see jdraw.framework.Figure#getHandles()
     */
    @Override
    public List<FigureHandle> getHandles() {
        return handles;
    }

    public DrawModel getDrawModel() {
        return drawModel;
    }

}
