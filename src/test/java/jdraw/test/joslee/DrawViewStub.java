package jdraw.test.joslee;

import jdraw.framework.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;

public class DrawViewStub implements DrawView {

    @Override
    public DrawModel getModel() {
        return null;
    }

    @Override
    public void setDrawContext(DrawContext controller) {

    }

    @Override
    public DrawContext getDrawContext() {
        return new DrawContextStub();
    }

    @Override
    public void setConstrainer(PointConstrainer p) {

    }

    @Override
    public PointConstrainer getConstrainer() {
        return null;
    }

    @Override
    public FigureHandle getHandle(int x, int y, MouseEvent e) {
        return null;
    }

    @Override
    public List<Figure> getSelection() {
        return null;
    }

    @Override
    public void clearSelection() {

    }

    @Override
    public void addToSelection(Figure f) {

    }

    @Override
    public void removeFromSelection(Figure f) {

    }

    @Override
    public void setSelectionRubberBand(Rectangle rect) {

    }

    @Override
    public void paint(Graphics g) {

    }

    @Override
    public void repaint() {

    }

    @Override
    public void setCursor(Cursor c) {

    }

    @Override
    public void close() {

    }

}
