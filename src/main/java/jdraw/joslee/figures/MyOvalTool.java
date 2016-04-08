/*
 * Copyright (c) 2000-2016 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package jdraw.joslee.figures;

import jdraw.framework.DrawContext;

import javax.swing.*;

/**
 * This tool defines a mode for drawing ovals.
 *
 * @see jdraw.framework.Figure
 *
 * @author  Christoph Denzler, Joshua Lee
 */
public class MyOvalTool extends MyDrawTool {

    public MyOvalTool(DrawContext context) {
        super(context);
    }

    @Override
    MyFigure getNewFigure(int x, int y) {
        return new MyOval(x, y, 0, 0);
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
