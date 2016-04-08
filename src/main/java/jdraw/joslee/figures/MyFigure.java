package jdraw.joslee.figures;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureListener;

import java.util.ArrayList;
import java.util.List;

public abstract class MyFigure implements Figure {

    private List<FigureListener> listeners;

    MyFigure() {
        listeners = new ArrayList<>();
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
    public Figure clone() {
        return null;
    }

    void notifyListeners() {
        FigureEvent event = new FigureEvent(this);
        for (FigureListener listener : new ArrayList<>(listeners)) {
            listener.figureChanged(event);
        }
    }

}
