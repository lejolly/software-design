package jdraw.joslee.figures.decorators;

import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

import java.awt.*;

public class LogDecorator extends DecoratorFigure {

    public LogDecorator(Figure figure, FigureListener figureListener) {
        super(figure, figureListener);
        System.out.println("Creating Log Decorated Figure");
    }

    @Override
    public void move(int dx, int dy) {
        System.out.println("LogDecorator: Moving figure");
        super.move(dx, dy);
    }

    @Override
    public boolean contains(int x, int y) {
        System.out.println("LogDecorator: Checking if figure contains point");
        return super.contains(x, y);
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        System.out.println("LogDecorator: Setting bounds of figure");
        super.setBounds(origin, corner);
    }

    @Override
    public Rectangle getBounds(Object caller) {
        System.out.println("LogDecorator: Getting bounds of figure");
        return super.getBounds(caller);
    }

    @Override
    public java.util.List<FigureHandle> getHandles() {
        System.out.println("LogDecorator: Getting handles of figure");
        return super.getHandles();
    }

    @Override
    public void addFigureListener(FigureListener listener) {
        System.out.println("LogDecorator: Adding figure listener");
        super.addFigureListener(listener);
    }

    @Override
    public void removeFigureListener(FigureListener listener) {
        System.out.println("LogDecorator: Removing figure listener");
        super.removeFigureListener(listener);
    }

    @Override
    public Figure clone() {
        System.out.println("LogDecorator: Cloning figure");
        return super.clone();
    }

    @Override
    public void draw(Graphics g) {
        System.out.println("LogDecorator: Drawing figure");
        super.draw(g);
    }

    @Override
    public Figure getInnerFigure() {
        System.out.println("LogDecorator: Getting inner figure");
        return super.getInnerFigure();
    }

}
