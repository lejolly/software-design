package jdraw.joslee.figures.handles;

import jdraw.framework.*;
import jdraw.joslee.commands.ResizeFigureCommand;
import jdraw.joslee.figures.MyFigure;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class MyHandle implements FigureHandle, FigureListener {

    static final int HANDLE_SIZE = 6;

    private MyFigure owner;
    private Rectangle originalBounds;
    String name;
    Cursor cursor;
    Point startPoint;
    Point endPoint;
    Rectangle handleBox;

    MyHandle(MyFigure owner) {
        this.owner = owner;
        handleBox = new Rectangle(0, 0, HANDLE_SIZE, HANDLE_SIZE);
    }

    @Override
    public MyFigure getOwner() {
        return owner;
    }

    public Point getOwnerLocation() {
        return owner.getBounds().getLocation();
    }

    @Override
    public Point getLocation() {
        return new Point(handleBox.getLocation().x + (HANDLE_SIZE / 2), handleBox.getLocation().y + (HANDLE_SIZE / 2));
    }

    @Override
    public Cursor getCursor() {
        return cursor;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(handleBox.x, handleBox.y, handleBox.width, handleBox.height);
        g.setColor(Color.BLACK);
        g.drawRect(handleBox.x, handleBox.y, handleBox.width, handleBox.height);
    }

    @Override
    public void figureChanged(FigureEvent e) {
        setLocation();
    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        originalBounds = owner.getBounds();
        startPoint = new Point(originalBounds.x, originalBounds.y);
        endPoint = new Point(originalBounds.x + originalBounds.width, originalBounds.y + originalBounds.height);
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        changeNameAndCursor();
        resize(x, y);
        v.getDrawContext().showStatusText("Dragging " + name + " Handle");
    }

    @Override
    public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
        v.getDrawContext().showStatusText("Selection mode");
        owner.getDrawModel().getDrawCommandHandler().addCommand(
                new ResizeFigureCommand(owner, originalBounds, owner.getBounds()));
    }

    int getWidth() {
        return owner.getBounds().width;
    }

    int getHeight() {
        return owner.getBounds().height;
    }

    abstract void setLocation();

    abstract void changeNameAndCursor();

    abstract void resize(int x, int y);

    @Override
    public void setOwner(MyFigure figure) {
        this.owner = figure;
    }

}
