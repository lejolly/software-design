/*
 * Copyright (c) 2000-2016 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package jdraw.joslee.figures.drawTools;

import jdraw.framework.DrawContext;
import jdraw.joslee.figures.MyFigure;
import jdraw.joslee.figures.MyLine;

/**
 * This tool defines a mode for drawing lines.
 *
 * @see jdraw.framework.Figure
 *
 * @author  Christoph Denzler, Joshua Lee
 */
public class MyLineTool extends MyDrawTool {

    public MyLineTool(DrawContext context, String name, String icon) {
        super(context, name, icon);
    }

    @Override
    MyFigure getNewFigure(int x, int y) {
        return new MyLine(super.getModel(), x, y, x, y);
    }

}
