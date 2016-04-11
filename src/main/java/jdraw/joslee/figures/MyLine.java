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
public class MyLine extends MyFigure {

    private static final double CONTAINS_RANGE_PERCENTAGE = 0.1;

    /**
     * Use the java.awt.geom.Line2D in order to save/reuse code.
     */
    private java.awt.geom.Line2D.Double line;

    /**
     * Create a new line of the given dimension.
     * @param x1 the X coordinate of the start point
     * @param y1 the Y coordinate of the start point
     * @param x2 the X coordinate of the end point
     * @param y2 the Y coordinate of the end point
     */
    public MyLine(int x1, int y1, int x2, int y2) {
        line = new java.awt.geom.Line2D.Double(x1, y1, x2, y2);
    }

    /**
     * Draw the line to the given graphics context.
     * @param g the graphics context to use for drawing.
     */
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine((int) line.x1, (int) line.y1, (int) line.x2, (int) line.y2);
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        line.setLine(origin.x, origin.y, corner.x, corner.y);
        notifyListeners();
    }

    @Override
    public void move(int dx, int dy) {
        if (dx != 0 || dy != 0) {
            line.setLine(line.x1 + dx, line.y1 + dy, line.x2 + dx, line.y2 + dy);
            notifyListeners();
        }
    }

    /**
     * Used for dragging the line around, does so by comparing the gradients
     * but also giving a bit of leeway so that the user does not need to click
     * directly on the line itself. 
     * @param x the X coordinate of the cursor
     * @param y the Y coordinate of the cursor
     */
    @Override
    public boolean contains(int x, int y) {
        double gradient = (line.y2 - line.y1) * (x - line.x1);
        double newGradient = (y - line.y1) * (line.x2 - line.x1);
        return (gradient * (1 - CONTAINS_RANGE_PERCENTAGE)) <= newGradient &&
                newGradient <= (gradient * (1 + CONTAINS_RANGE_PERCENTAGE));
    }

    @Override
    public Rectangle getBounds() {
        return line.getBounds();
    }

    /**
     * Returns a list of 8 handles for this Line.
     * @return all handles that are attached to the targeted figure.
     * @see jdraw.framework.Figure#getHandles()
     */
    public List<FigureHandle> getHandles() {
        return null;
    }

}
