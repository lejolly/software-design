package jdraw.joslee.figures.decorators;

import jdraw.framework.*;
import jdraw.joslee.figures.MyFigure;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class DecoratorFigure extends MyFigure {

    private final MyFigure innerFigure;
    private final FigureListener figureListener;

    public DecoratorFigure(DrawModel drawModel, MyFigure figure, FigureListener figureListener) {
        super(drawModel);
        this.innerFigure = figure;
        this.figureListener = figureListener;
        for (FigureHandle handle : new ArrayList<>(this.innerFigure.getHandles())) {
            handle.setOwner(this);
        }
    }

    @Override
    public void move(int dx, int dy) {
        innerFigure.move(dx, dy);
        figureListener.figureChanged(new FigureEvent(this));
    }

    @Override
    public boolean contains(int x, int y) {
        return innerFigure.contains(x, y);
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        innerFigure.setBounds(origin, corner);
        figureListener.figureChanged(new FigureEvent(this));
    }

    @Override
    public Rectangle getBounds(Object caller) {
        return innerFigure.getBounds(caller);
    }

    @Override
    public List<FigureHandle> getHandles() {
        return innerFigure.getHandles();
    }

    @Override
    public void addFigureListener(FigureListener listener) {
        innerFigure.addFigureListener(listener);
    }

    @Override
    public void removeFigureListener(FigureListener listener) {
        innerFigure.removeFigureListener(listener);
    }

    @Override
    public Figure clone() {
        return innerFigure.clone();
    }

    @Override
    public void draw(Graphics g) {
        innerFigure.draw(g);
    }

    @Override
    public void notifyListeners() {
        innerFigure.notifyListeners();
    }

    public Figure getInnerFigure() {
        // the only time we use this is to get the inner figure
        // out to remove the decorator, so we set the owner of the
        // handles back to the original figure
        for (FigureHandle handle : new ArrayList<>(innerFigure.getHandles())) {
            handle.setOwner(innerFigure);
        }
        return innerFigure;
    }

    @Override
    public void createHandles() {}

}
