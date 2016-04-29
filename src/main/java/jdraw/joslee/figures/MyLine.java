/*
 * Copyright (c) 2000-2016 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package jdraw.joslee.figures;

import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents lines in JDraw.
 *
 * @author Christoph Denzler, Joshua Lee
 *
 */
public class MyLine extends MyFigure {

    private static final int CONTAINS_RANGE = 5;

    /**
     * Use the java.awt.geom.Line2D in order to save/reuse code.
     */
    private Line2D.Double line;

    /**
     * Create a new line of the given dimension.
     * @param x1 the X coordinate of the start point
     * @param y1 the Y coordinate of the start point
     * @param x2 the X coordinate of the end point
     * @param y2 the Y coordinate of the end point
     */
    public MyLine(int x1, int y1, int x2, int y2) {
        line = new Line2D.Double(x1, y1, x2, y2);
    }

    private MyLine(MyLine myLine) {
        Point p1 = myLine.getP1();
        Point p2 = myLine.getP2();
        line = new Line2D.Double(p1.x, p1.y, p2.x, p2.y);
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
        Line2D.Double originalLine = new Line2D.Double(line.getP1(), line.getP2());
        line.setLine(origin.x, origin.y, corner.x, corner.y);
        if (!line.equals(originalLine)) {
            notifyListeners();
        }
    }

    @Override
    public void move(int dx, int dy) {
        if (dx != 0 || dy != 0) {
            line.setLine(line.x1 + dx, line.y1 + dy, line.x2 + dx, line.y2 + dy);
            notifyListeners();
        }
    }

    @Override
    public boolean contains(int x, int y) {
        return line.ptSegDist(x, y) <= CONTAINS_RANGE;
    }

    @Override
    public Rectangle getBounds() {
        return line.getBounds();
    }

    @Override
    public List<FigureHandle> getHandles() {
        List<FigureHandle> handles = new ArrayList<>();
        for (MyLineHandle.Type direction : MyLineHandle.Type.values()) {
            MyLineHandle myLineHandle = new MyLineHandle(this, direction);
            handles.add(myLineHandle);
            addFigureListener(myLineHandle);
        }
        return handles;
    }

    @Override
    public Figure clone() {
        return new MyLine(this);
    }

    public Point getP1() {
        return new Point((int) line.x1, (int) line.y1);
    }

    public Point getP2() {
        return new Point((int) line.x2, (int) line.y2);
    }

}
