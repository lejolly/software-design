package jdraw.joslee;

import jdraw.framework.DrawCommand;
import jdraw.framework.DrawCommandHandler;
import jdraw.joslee.commands.MoveFigureCommand;

import java.util.ArrayList;
import java.util.List;

public class MyDrawCommandHandler implements DrawCommandHandler {

    private List<DrawCommand> undo;
    private List<DrawCommand> redo;

    public MyDrawCommandHandler() {
        undo = new ArrayList<>();
        redo = new ArrayList<>();
    }

    @Override
    public void addCommand(DrawCommand cmd) {
        // compress movement commands on the same figure
        if (cmd instanceof MoveFigureCommand
                && !undo.isEmpty()
                && undo.get(0) instanceof MoveFigureCommand
                && ((MoveFigureCommand) cmd).getFigure().equals(((MoveFigureCommand) undo.get(0)).getFigure())) {
            MoveFigureCommand oldCommand = (MoveFigureCommand) undo.get(0);
            undo.remove(0);
            ((MoveFigureCommand) cmd).compress(oldCommand);
            undo.add(0, cmd);
        } else {
            undo.add(0, cmd);
        }
        redo.clear();
    }

    @Override
    public void undo() {
        if (!undo.isEmpty()) {
            DrawCommand command = undo.get(0);
            redo.add(0, command);
            undo.remove(command);
            command.undo();
        }
    }

    @Override
    public void redo() {
        if (!redo.isEmpty()) {
            DrawCommand command = redo.get(0);
            undo.add(0, command);
            redo.remove(command);
            command.redo();
        }
    }

    @Override
    public boolean undoPossible() {
        return !undo.isEmpty();
    }

    @Override
    public boolean redoPossible() {
        return !redo.isEmpty();
    }

    @Override
    public void beginScript() {

    }

    @Override
    public void endScript() {

    }

    @Override
    public void clearHistory() {
        undo.clear();
        redo.clear();
    }

}
