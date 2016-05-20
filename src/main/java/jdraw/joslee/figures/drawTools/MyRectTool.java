/*
 * Copyright (c) 2000-2016 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package jdraw.joslee.figures.drawTools;

import jdraw.framework.DrawContext;
import jdraw.joslee.figures.MyFigure;
import jdraw.joslee.figures.MyRect;

/**
 * This tool defines a mode for drawing rectangles.
 *
 * @see jdraw.framework.Figure
 *
 * @author  Christoph Denzler, Joshua Lee
 */
public class MyRectTool extends MyDrawTool {

    public MyRectTool(DrawContext context, String name, String icon) {
        super(context, name, icon);
    }

    @Override
    MyFigure getNewFigure(int x, int y) {
        return new MyRect(super.getModel(), x, y, 0, 0);
    }

}
