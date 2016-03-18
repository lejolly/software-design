/*
 * Copyright (c) 2000-2016 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package jdraw.joslee;

import jdraw.framework.*;
import jdraw.std.EmptyDrawCommandHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Provide a standard behavior for the drawing model. This class initially does not implement the methods
 * in a proper way.
 * It is part of the course assignments to do so.
 * @author Joshua Lee
 *
 */
public class MyDrawModel implements DrawModel, FigureListener {

    private List<Figure> figures;
    private List<DrawModelListener> listeners;

    public MyDrawModel() {
        figures = new ArrayList<>();
        listeners = new ArrayList<>();
    }

    @Override
    public void addFigure(Figure f) {
        if (!figures.contains(f)) {
            f.addFigureListener(this);
            figures.add(f);
            notifyListeners(new DrawModelEvent(this, f, DrawModelEvent.Type.FIGURE_ADDED));
        }
    }

    @Override
    public Iterable<Figure> getFigures() {
        return new ArrayList<>(figures);
    }

    @Override
    public void removeFigure(Figure f) {
        if (figures.contains(f)) {
            f.removeFigureListener(this);
            figures.remove(f);
            notifyListeners(new DrawModelEvent(this, f, DrawModelEvent.Type.FIGURE_REMOVED));
        }
    }

    @Override
    public void addModelChangeListener(DrawModelListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    @Override
    public void removeModelChangeListener(DrawModelListener listener) {
        if (listeners.contains(listener)) {
            listeners.remove(listener);
        }
    }

    /** The draw command handler. Initialized here with a dummy implementation. */
    // TODO initialize with your implementation of the undo/redo-assignment.
    private DrawCommandHandler handler = new EmptyDrawCommandHandler();

    /**
     * Retrieve the draw command handler in use.
     * @return the draw command handler.
     */
    public DrawCommandHandler getDrawCommandHandler() {
        return handler;
    }

    @Override
    public void setFigureIndex(Figure f, int index) {
        if (figures.contains(f)) {
            if (index < figures.size()) {
                figures.remove(f);
                figures.add(index, f);
                notifyListeners(new DrawModelEvent(this, f, DrawModelEvent.Type.DRAWING_CHANGED));
            } else {
                throw new IndexOutOfBoundsException();
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void removeAllFigures() {
        for (Figure figure : figures) {
            figure.removeFigureListener(this);
        }
        figures = new ArrayList<>();
        notifyListeners(new DrawModelEvent(this, null, DrawModelEvent.Type.DRAWING_CLEARED));
    }

    @Override
    public void figureChanged(FigureEvent e) {

    }

    private void notifyListeners(DrawModelEvent event) {
        for (DrawModelListener listener : new ArrayList<>(listeners)) {
            listener.modelChanged(event);
        }
    }

}
