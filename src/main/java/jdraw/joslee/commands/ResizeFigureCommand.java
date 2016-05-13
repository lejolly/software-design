package jdraw.joslee.commands;

import jdraw.framework.DrawCommand;
import jdraw.joslee.figures.MyFigure;

import java.awt.*;

public class ResizeFigureCommand implements DrawCommand {

    private final MyFigure figure;
    private final Rectangle originalBounds;
    private final Rectangle newBounds;

    public ResizeFigureCommand(MyFigure figure, Rectangle originalBounds, Rectangle newBounds) {
        this.figure = figure;
        this.originalBounds = originalBounds;
        this.newBounds = newBounds;
    }

    @Override
    public void redo() {

    }

    @Override
    public void undo() {

    }

}
