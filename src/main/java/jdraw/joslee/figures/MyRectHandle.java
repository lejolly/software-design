package jdraw.joslee.figures;

import jdraw.framework.*;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Handles for MyRect
 */
public class MyRectHandle implements FigureHandle, FigureListener {

    public enum Type {NW, N, NE, E, SE, S, SW, W, CENTER}
    private static final int HANDLE_SIZE = 6;

    private Figure owner;
    private Type type;
    private String name;
    private Rectangle handleBox;
    private Point startPoint;
    private Point endPoint;
    private Cursor cursor;

    MyRectHandle(Figure owner, Type type) {
        this.owner = owner;
        this.type = type;
        handleBox = new Rectangle(0, 0, HANDLE_SIZE, HANDLE_SIZE);
        setLocation();
        changeNameAndCursor();
    }

    @Override
    public Figure getOwner() {
        return owner;
    }

    @Override
    public Point getLocation() {
        return owner.getBounds().getLocation();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(handleBox.x, handleBox.y, handleBox.width, handleBox.height);
        g.setColor(Color.BLACK);
        g.drawRect(handleBox.x, handleBox.y, handleBox.width, handleBox.height);
    }

    @Override
    public Cursor getCursor() {
        return cursor;
    }

    @Override
    public boolean contains(int x, int y) {
        return type != Type.CENTER && handleBox.contains(x, y);
    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle bounds = owner.getBounds();
        startPoint = new Point(bounds.x, bounds.y);
        endPoint = new Point(bounds.x + bounds.width, bounds.y + bounds.height);
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
    }

    @Override
    public void figureChanged(FigureEvent e) {
        setLocation();
    }

    private void setLocation() {
        switch(this.type) {
            case NW:
                handleBox.setLocation(getLocation().x - (HANDLE_SIZE / 2), getLocation().y - (HANDLE_SIZE / 2));
                break;
            case N:
                handleBox.setLocation(getLocation().x + (getWidth() / 2) - (HANDLE_SIZE / 2),
                        getLocation().y - (HANDLE_SIZE / 2));
                break;
            case NE:
                handleBox.setLocation(getLocation().x + getWidth() - (HANDLE_SIZE / 2),
                        getLocation().y - (HANDLE_SIZE / 2));
                break;
            case E:
                handleBox.setLocation(getLocation().x + getWidth() - (HANDLE_SIZE / 2),
                        getLocation().y + (getHeight() / 2) - (HANDLE_SIZE / 2));
                break;
            case SE:
                handleBox.setLocation(getLocation().x + getWidth() - (HANDLE_SIZE / 2),
                        getLocation().y + getHeight() - (HANDLE_SIZE / 2));
                break;
            case S:
                handleBox.setLocation(getLocation().x + (getWidth() / 2) - (HANDLE_SIZE / 2),
                        getLocation().y + getHeight() - (HANDLE_SIZE / 2));
                break;
            case SW:
                handleBox.setLocation(getLocation().x - (HANDLE_SIZE / 2),
                        getLocation().y + getHeight() - (HANDLE_SIZE / 2));
                break;
            case W:
                handleBox.setLocation(getLocation().x - (HANDLE_SIZE / 2),
                        getLocation().y + (getHeight() / 2) - (HANDLE_SIZE / 2));
                break;
            case CENTER:
                handleBox.setLocation(getLocation().x + (getWidth() / 2) - (HANDLE_SIZE / 2),
                        getLocation().y + (getHeight() / 2) - (HANDLE_SIZE / 2));
                break;
        }
    }

    private void changeNameAndCursor() {
        switch(this.type) {
            case NW:
                cursor = Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
                name = "North West";
                break;
            case N:
                cursor = Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
                name = "North";
                break;
            case NE:
                cursor = Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR);
                name = "North East";
                break;
            case E:
                cursor = Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR);
                name = "East";
                break;
            case SE:
                cursor = Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR);
                name = "South East";
                break;
            case S:
                cursor = Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);
                name = "South";
                break;
            case SW:
                cursor = Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR);
                name = "South West";
                break;
            case W:
                cursor = Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR);
                name = "West";
                break;
            case CENTER:
                break;
        }
    }

    private void resize(int x, int y) {
        switch(this.type) {
            case NW:
                owner.setBounds(new Point(x, y), endPoint);
                break;
            case N:
                owner.setBounds(new Point(startPoint.x, y), endPoint);
                break;
            case NE:
                owner.setBounds(new Point(x, endPoint.y), new Point(startPoint.x, y));
                break;
            case E:
                owner.setBounds(startPoint, new Point(x, endPoint.y));
                break;
            case SE:
                owner.setBounds(startPoint, new Point(x, y));
                break;
            case S:
                owner.setBounds(startPoint, new Point(endPoint.x, y));
                break;
            case SW:
                owner.setBounds(new Point(x, startPoint.y), new Point(endPoint.x, y));
                break;
            case W:
                owner.setBounds(new Point(x, startPoint.y), endPoint);
                break;
            case CENTER:
                break;
        }
    }

    private int getWidth() {
        return owner.getBounds().width;
    }

    private int getHeight() {
        return owner.getBounds().height;
    }

}
