package jdraw.joslee.figures.handles;

import jdraw.joslee.figures.MyOval;

import java.awt.*;

/**
 * Handles for MyOval
 */
public class MyOvalHandle extends MyHandle {

    public enum Type {N, E, S, W, CENTER}

    private Type type;

    public MyOvalHandle(MyOval owner, Type type) {
        super(owner);
        this.type = type;
        setLocation();
        changeNameAndCursor();
    }

    @Override
    public boolean contains(int x, int y) {
        return type != Type.CENTER && handleBox.contains(x, y);
    }

    void setLocation() {
        switch(this.type) {
            case N:
                handleBox.setLocation(getOwnerLocation().x + (getWidth() / 2) - (HANDLE_SIZE / 2),
                        getOwnerLocation().y - (HANDLE_SIZE / 2));
                break;
            case E:
                handleBox.setLocation(getOwnerLocation().x + getWidth() - (HANDLE_SIZE / 2),
                        getOwnerLocation().y + (getHeight() / 2) - (HANDLE_SIZE / 2));
                break;
            case S:
                handleBox.setLocation(getOwnerLocation().x + (getWidth() / 2) - (HANDLE_SIZE / 2),
                        getOwnerLocation().y + getHeight() - (HANDLE_SIZE / 2));
                break;
            case W:
                handleBox.setLocation(getOwnerLocation().x - (HANDLE_SIZE / 2),
                        getOwnerLocation().y + (getHeight() / 2) - (HANDLE_SIZE / 2));
                break;
            case CENTER:
                handleBox.setLocation(getOwnerLocation().x + (getWidth() / 2) - (HANDLE_SIZE / 2),
                        getOwnerLocation().y + (getHeight() / 2) - (HANDLE_SIZE / 2));
                break;
        }
    }

    void changeNameAndCursor() {
        switch(this.type) {
            case N:
                cursor = Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
                name = "North";
                break;
            case E:
                cursor = Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR);
                name = "East";
                break;
            case S:
                cursor = Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);
                name = "South";
                break;
            case W:
                cursor = Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR);
                name = "West";
                break;
            case CENTER:
                break;
        }
    }

    void resize(int x, int y) {
        switch(this.type) {
            case N:
                if (y < endPoint.y) {
                    getOwner().setBounds(new Point(startPoint.x, y), endPoint);
                } else {
                    getOwner().setBounds(new Point(startPoint.x, endPoint.y), new Point(endPoint.x, y));
                }
                break;
            case E:
                if (x > startPoint.x) {
                    getOwner().setBounds(startPoint, new Point(x, endPoint.y));
                } else {
                    getOwner().setBounds(new Point(x, startPoint.y), new Point(startPoint.x, endPoint.y));
                }
                break;
            case S:
                if (y > startPoint.y) {
                    getOwner().setBounds(startPoint, new Point(endPoint.x, y));
                } else {
                    getOwner().setBounds(new Point(startPoint.x, y), new Point(endPoint.x, startPoint.y));
                }
                break;
            case W:
                if (x < endPoint.x) {
                    getOwner().setBounds(new Point(x, startPoint.y), endPoint);
                } else {
                    getOwner().setBounds(new Point(endPoint.x, startPoint.y), new Point(x, endPoint.y));
                }
                break;
            case CENTER:
                break;
        }
    }

    public Type getType() {
        return type;
    }

}
