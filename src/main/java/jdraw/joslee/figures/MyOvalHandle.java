package jdraw.joslee.figures;

import java.awt.*;

/**
 * Handles for MyOval
 */
public class MyOvalHandle extends MyHandle {

    public enum Type {N, E, S, W, CENTER}

    private Type type;

    MyOvalHandle(MyOval owner, Type type) {
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
                handleBox.setLocation(getLocation().x + (getWidth() / 2) - (HANDLE_SIZE / 2),
                        getLocation().y - (HANDLE_SIZE / 2));
                break;
            case E:
                handleBox.setLocation(getLocation().x + getWidth() - (HANDLE_SIZE / 2),
                        getLocation().y + (getHeight() / 2) - (HANDLE_SIZE / 2));
                break;
            case S:
                handleBox.setLocation(getLocation().x + (getWidth() / 2) - (HANDLE_SIZE / 2),
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
                owner.setBounds(new Point(startPoint.x, y), endPoint);
                break;
            case E:
                owner.setBounds(startPoint, new Point(x, endPoint.y));
                break;
            case S:
                owner.setBounds(startPoint, new Point(endPoint.x, y));
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
