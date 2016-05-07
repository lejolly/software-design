package jdraw.joslee.figures;

import java.awt.*;

/**
 * Handles for MyLine
 */
public class MyLineHandle extends MyHandle {

    public enum Type {FIRST, SECOND}

    private Type type;

    MyLineHandle(MyLine owner, Type type) {
        super(owner);
        this.type = type;
        setLocation();
        changeNameAndCursor();
    }

    @Override
    public boolean contains(int x, int y) {
        return handleBox.contains(x, y);
    }

    void setLocation() {
        switch(this.type) {
            case FIRST:
                handleBox.setLocation(((MyLine) getOwner()).getP1().x - (HANDLE_SIZE / 2),
                        ((MyLine) getOwner()).getP1().y - (HANDLE_SIZE / 2));
                break;
            case SECOND:
                handleBox.setLocation(((MyLine) getOwner()).getP2().x - (HANDLE_SIZE / 2),
                        ((MyLine) getOwner()).getP2().y - (HANDLE_SIZE / 2));
                break;
        }
    }

    void changeNameAndCursor() {
        cursor = Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
        switch(this.type) {
            case FIRST:
                name = "First";
                break;
            case SECOND:
                name = "Second";
                break;
        }
    }

    void resize(int x, int y) {
        switch(this.type) {
            case FIRST:
                if (x > endPoint.x || y > endPoint.y) {
                    getOwner().setBounds(endPoint, new Point(x, y));
                } else {
                    getOwner().setBounds(new Point(x, y), endPoint);
                }
                break;
            case SECOND:
                if (x < startPoint.x || y < startPoint.y) {
                    getOwner().setBounds(new Point(x, y), startPoint);
                } else {
                    getOwner().setBounds(startPoint, new Point(x, y));
                }
                break;
        }
    }

    public Type getType() {
        return type;
    }

}
