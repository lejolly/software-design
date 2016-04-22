package jdraw.joslee.pointConstrainers;

import jdraw.framework.PointConstrainer;

import java.awt.*;

/**
 * No grid.
 */
public class MyPointConstrainerStub implements PointConstrainer {

    @Override
    public Point constrainPoint(Point p) {
        return p;
    }

    @Override
    public int getStepX(boolean right) {
        return 1;
    }

    @Override
    public int getStepY(boolean down) {
        return 1;
    }

    @Override
    public void activate() {

    }

    @Override
    public void deactivate() {

    }

    @Override
    public void mouseDown() {

    }

    @Override
    public void mouseUp() {

    }

}
