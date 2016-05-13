/*
 * Copyright (c) 2000-2016 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package jdraw.joslee.figures;

import jdraw.framework.DrawModel;
import jdraw.joslee.figures.handles.MyRectHandle;

import java.awt.*;

/**
 * Represents rectangles in JDraw.
 *
 * @author Christoph Denzler, Joshua Lee
 *
 */
public class MyRect extends MyFigure {

    /**
     * Use the java.awt.Rectangle in order to save/reuse code.
     */
    private Rectangle rectangle;

    /**
     * Create a new rectangle of the given dimension.
     * @param x the x-coordinate of the upper left corner of the rectangle
     * @param y the y-coordinate of the upper left corner of the rectangle
     * @param w the rectangle's width
     * @param h the rectangle's height
     */
    public MyRect(DrawModel drawModel, int x, int y, int w, int h) {
        super(drawModel);
        rectangle = new Rectangle(x, y, w, h);
        createHandles();
    }

    private MyRect(MyRect myRect) {
        super(myRect.getDrawModel());
        Rectangle bounds = myRect.getBounds(this);
        rectangle = new Rectangle(bounds.x, bounds.y, bounds.width, bounds.height);
        createHandles();
    }

    /**
     * Draw the rectangle to the given graphics context.
     * @param g the graphics context to use for drawing.
     */
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        g.setColor(Color.BLACK);
        g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        Rectangle originalRectangle = new Rectangle(rectangle);
        rectangle.setFrameFromDiagonal(origin, corner);
        if (!rectangle.equals(originalRectangle)) {
            notifyListeners();
        }
    }

    @Override
    public void move(int dx, int dy) {
        if (dx != 0 || dy != 0) {
            rectangle.setLocation(rectangle.x + dx, rectangle.y + dy);
            notifyListeners();
        }
    }

    @Override
    public boolean contains(int x, int y) {
        return rectangle.contains(x, y);
    }

    @Override
    public Rectangle getBounds(Object caller) {
        return rectangle.getBounds();
    }

    @Override
    public MyFigure clone() {
        return new MyRect(this);
    }

    @Override
    public void createHandles() {
        for (MyRectHandle.Type direction : MyRectHandle.Type.values()) {
            MyRectHandle myRectHandle = new MyRectHandle(this, direction);
            handles.add(myRectHandle);
            addFigureListener(myRectHandle);
        }
    }

}
