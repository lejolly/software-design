/*
 * Copyright (c) 2000-2016 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package jdraw.joslee.figures.drawTools;

import jdraw.framework.DrawContext;
import jdraw.joslee.figures.MyFigure;
import jdraw.joslee.figures.MyLine;

import javax.swing.*;

/**
 * This tool defines a mode for drawing lines.
 *
 * @see jdraw.framework.MyFigure
 *
 * @author  Christoph Denzler, Joshua Lee
 */
public class MyLineTool extends MyDrawTool {

    public MyLineTool(DrawContext context) {
        super(context);
    }

    @Override
    MyFigure getNewFigure(int x, int y) {
        return new MyLine(super.getModel(), x, y, x, y);
    }

    @Override
    public Icon getIcon() {
        return new ImageIcon(getClass().getResource(IMAGES + "line.png"));
    }

    @Override
    public String getName() {
        return "Line";
    }

}
