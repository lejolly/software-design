/*
 * Copyright (c) 2000-2016 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package jdraw.joslee.figures.drawTools;

import jdraw.framework.DrawContext;
import jdraw.joslee.figures.MyFigure;
import jdraw.joslee.figures.MyOval;

/**
 * This tool defines a mode for drawing ovals.
 *
 * @see jdraw.framework.Figure
 *
 * @author  Christoph Denzler, Joshua Lee
 */
public class MyOvalTool extends MyDrawTool {

    public MyOvalTool(DrawContext context, String name, String icon) {
        super(context, name, icon);
    }

    @Override
    MyFigure getNewFigure(int x, int y) {
        return new MyOval(super.getModel(), x, y, 0, 0);
    }

}
