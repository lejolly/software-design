package jdraw.joslee.commands;

import jdraw.framework.DrawCommand;
import jdraw.framework.DrawModel;
import jdraw.framework.Figure;

public class AddFigureCommand implements DrawCommand {

    private final DrawModel model;
    private final Figure figure;

    public AddFigureCommand(DrawModel model, Figure figure) {
        this.model = model;
        this.figure = figure;
    }

    @Override
    public void redo() {
        model.addFigure(figure);
    }

    @Override
    public void undo() {
        model.removeFigure(figure);
    }

}
