/*
 * Copyright (c) 2000-2016 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package jdraw.joslee.figures.drawTools;

import jdraw.framework.DrawContext;
import jdraw.joslee.figures.MyFigure;
import jdraw.joslee.figures.MyOval;

import javax.swing.*;

/**
 * This tool defines a mode for drawing ovals.
 *
 * @see jdraw.framework.MyFigure
 *
 * @author  Christoph Denzler, Joshua Lee
 */
public class MyOvalTool extends MyDrawTool {

    public MyOvalTool(DrawContext context) {
        super(context);
    }

    @Override
    MyFigure getNewFigure(int x, int y) {
        return new MyOval(super.getModel(), x, y, 0, 0);
    }

    @Override
    public Icon getIcon() {
        return new ImageIcon(getClass().getResource(IMAGES + "oval.png"));
    }

    @Override
    public String getName() {
        return "Oval";
    }

}