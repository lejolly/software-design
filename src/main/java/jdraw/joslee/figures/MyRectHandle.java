package jdraw.joslee.figures;

import java.awt.*;

/**
 * Handles for MyRect
 */
public class MyRectHandle extends MyHandle {

    public enum Type {NW, N, NE, E, SE, S, SW, W, CENTER}

    private Type type;

    MyRectHandle(MyRect owner, Type type) {
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
            case NW:
                handleBox.setLocation(getOwnerLocation().x - (HANDLE_SIZE / 2), getOwnerLocation().y - (HANDLE_SIZE / 2));
                break;
            case N:
                handleBox.setLocation(getOwnerLocation().x + (getWidth() / 2) - (HANDLE_SIZE / 2),
                        getOwnerLocation().y - (HANDLE_SIZE / 2));
                break;
            case NE:
                handleBox.setLocation(getOwnerLocation().x + getWidth() - (HANDLE_SIZE / 2),
                        getOwnerLocation().y - (HANDLE_SIZE / 2));
                break;
            case E:
                handleBox.setLocation(getOwnerLocation().x + getWidth() - (HANDLE_SIZE / 2),
                        getOwnerLocation().y + (getHeight() / 2) - (HANDLE_SIZE / 2));
                break;
            case SE:
                handleBox.setLocation(getOwnerLocation().x + getWidth() - (HANDLE_SIZE / 2),
                        getOwnerLocation().y + getHeight() - (HANDLE_SIZE / 2));
                break;
            case S:
                handleBox.setLocation(getOwnerLocation().x + (getWidth() / 2) - (HANDLE_SIZE / 2),
                        getOwnerLocation().y + getHeight() - (HANDLE_SIZE / 2));
                break;
            case SW:
                handleBox.setLocation(getOwnerLocation().x - (HANDLE_SIZE / 2),
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

    void resize(int x, int y) {
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

    public Type getType() {
        return type;
    }

}
