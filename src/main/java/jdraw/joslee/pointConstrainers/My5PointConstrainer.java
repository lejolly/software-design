package jdraw.joslee.pointConstrainers;

import jdraw.framework.PointConstrainer;

import java.awt.*;

/**
 * Rounds off to a 5x5 point grid.
 */
public class My5PointConstrainer implements PointConstrainer {

    @Override
    public Point constrainPoint(Point p) {
        return new Point(5 * (Math.round(p.x / 5)), 5 * (Math.round(p.y / 5)));
    }

    @Override
    public int getStepX(boolean right) {
        return 5;
    }

    @Override
    public int getStepY(boolean down) {
        return 5;
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
