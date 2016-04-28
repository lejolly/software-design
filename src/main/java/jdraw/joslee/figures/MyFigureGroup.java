package jdraw.joslee.figures;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureGroup;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a group of MyFigures
 */
public class MyFigureGroup extends MyFigure implements FigureGroup {

    DrawView drawView;
    List<Figure> figures;

    public MyFigureGroup(DrawView drawView) {
        this.drawView = drawView;
        figures = new ArrayList<>(drawView.getSelection());
        for (Figure figure : figures) {
            drawView.getModel().removeFigure(figure);
            drawView.removeFromSelection(figure);
        }
        drawView.getModel().addFigure(this);
        drawView.addToSelection(this);
    }

    @Override
    public void draw(Graphics g) {
        for (Figure figure : figures) {
            figure.draw(g);
        }
    }

    @Override
    public void move(int dx, int dy) {
        if (dx != 0 || dy != 0) {
            for (Figure figure : figures) {
                figure.move(dx, dy);
            }
            notifyListeners();
        }
    }

    @Override
    public boolean contains(int x, int y) {
        boolean result = false;
        for (Figure figure : figures) {
            if (figure.contains(x, y)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        // TODO
//        Rectangle originalBounds = new Rectangle(getBounds());
//        if (!getBounds().equals(originalBounds)) {
//            notifyListeners();
//        }
    }

    @Override
    public Rectangle getBounds() {
        Rectangle bounds = null;
        for (Figure figure : figures) {
            if (bounds == null) {
                bounds = figure.getBounds();
            } else {
                bounds = bounds.union(figure.getBounds());
            }
        }
        return bounds;
    }

    @Override
    public Iterable<Figure> getFigureParts() {
        return figures;
    }

    public void ungroup() {
        for (Figure figure : figures) {
            drawView.getModel().addFigure(figure);
        }
    }

    /**
     * Returns a list of 8 handles for this Rectangle.
     * @return all handles that are attached to the targeted figure.
     * @see jdraw.framework.Figure#getHandles()
     */
    @Override
    public List<FigureHandle> getHandles() {
        List<FigureHandle> handles = new ArrayList<>();
        for (MyFigureGroupHandle.Type direction : MyFigureGroupHandle.Type.values()) {
            MyFigureGroupHandle myFigureGroupHandle = new MyFigureGroupHandle(this, direction);
            handles.add(myFigureGroupHandle);
            addFigureListener(myFigureGroupHandle);
        }
        return handles;
    }

}
