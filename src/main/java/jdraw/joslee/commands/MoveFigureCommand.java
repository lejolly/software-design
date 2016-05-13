package jdraw.joslee.commands;

import jdraw.framework.DrawCommand;
import jdraw.framework.Figure;

public class MoveFigureCommand implements DrawCommand {

    private final Figure figure;
    private int xMovement;
    private int yMovement;

    public MoveFigureCommand(Figure aFigure, int dx, int dy) {
        this.figure = aFigure;
        this.xMovement = dx;
        this.yMovement = dy;
    }

    @Override
    public void redo() {
        figure.move(xMovement, yMovement);
    }

    @Override
    public void undo() {
        figure.move(-xMovement, -yMovement);
    }

    public Figure getFigure() {
        return figure;
    }

    private int getxMovement() {
        return xMovement;
    }

    private int getyMovement() {
        return yMovement;
    }

    public void compress(MoveFigureCommand command) {
        if (this.figure.equals(command.getFigure())) {
            xMovement = xMovement + command.getxMovement();
            yMovement = yMovement + command.getyMovement();
        }
    }

}
