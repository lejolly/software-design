/*
 * Copyright (c) 2000-2016 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package jdraw.joslee.figures;

import jdraw.framework.FigureHandle;

import java.awt.*;
import java.util.List;

/**
 * Represents lines in JDraw.
 *
 * @author Christoph Denzler, Joshua Lee
 *
 */
public class MyOval extends MyFigure {
    /**
     * Use the java.awt.geom.Ellipse2D in order to save/reuse code.
     */
    private java.awt.geom.Ellipse2D.Double oval;

    /**
     * Create a new oval of the given dimension.
     * @param x the X coordinate of the upper-left corner
     *        of the framing rectangle
     * @param y the Y coordinate of the upper-left corner
     *        of the framing rectangle
     * @param w the width of the framing rectangle
     * @param h the height of the framing rectangle
     */
    public MyOval(int x, int y, int w, int h) {
        oval = new java.awt.geom.Ellipse2D.Double(x, y, w, h);
    }

    /**
     * Draw the oval to the given graphics context.
     * @param g the graphics context to use for drawing.
     */
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval((int) oval.x, (int) oval.y, (int) oval.width, (int) oval.height);
        g.setColor(Color.BLACK);
        g.drawOval((int) oval.x, (int) oval.y, (int) oval.width, (int) oval.height);
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        oval.setFrame(origin.x, origin.y, corner.x - origin.x, corner.y - origin.y);
        notifyListeners();
    }

    @Override
    public void move(int dx, int dy) {
        if (dx != 0 || dy != 0) {
            oval.setFrame(oval.x + dx, oval.y + dy, oval.width, oval.height);
            notifyListeners();
        }
    }

    @Override
    public boolean contains(int x, int y) {
        return oval.contains(x, y);
    }

    @Override
    public Rectangle getBounds() {
        return oval.getBounds();
    }

    /**
     * Returns a list of 8 handles for this Oval.
     * @return all handles that are attached to the targeted figure.
     * @see jdraw.framework.Figure#getHandles()
     */
    public List<FigureHandle> getHandles() {
        return null;
    }

}
