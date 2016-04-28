package jdraw.joslee.pointConstrainers;

import javafx.util.Pair;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;
import jdraw.framework.PointConstrainer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Snaps to figures
 */
public class MySnapPointConstrainer implements PointConstrainer {

    private static final int SNAP_RANGE = 10;

    private DrawView drawView;

    public MySnapPointConstrainer(DrawView drawView) {
        this.drawView = drawView;
    }

    @Override
    public Point constrainPoint(Point p) {
        if (drawView.getSelection().size() == 1) {
            return getClosestPoint(p, drawView.getSelection().get(0));
        } else {
            return p;
        }
    }

    @SuppressWarnings("Duplicates")
    @Override
    public int getStepX(boolean right) {
        if (drawView.getSelection().size() == 1) {
            Figure sourceFigure = drawView.getSelection().get(0);
            ArrayList<FigureHandle> handles = getHandles(sourceFigure);
            if (!handles.isEmpty()) {
                // take out handles which are not within y boundaries
                // and take out handles that are far from x boundaries
                java.util.List<FigureHandle> closeHandles = handles.stream()
                        .filter(handle -> isWithinYBoundaries(handle, sourceFigure))
                        .filter(handle -> isCloseToXBoundaries(handle, sourceFigure))
                        .collect(Collectors.toList());
                if (!closeHandles.isEmpty()) {
                    int x1 = sourceFigure.getBounds().x;
                    int x2 = sourceFigure.getBounds().x + sourceFigure.getBounds().width;
                    java.util.List<Pair<Integer, FigureHandle>> leftHandles;
                    java.util.List<Pair<Integer, FigureHandle>> rightHandles;
                    if (!right) {
                        leftHandles = closeHandles.stream()
                                .filter(handle -> x1 - handle.getLocation().x <= SNAP_RANGE
                                        && x1 - handle.getLocation().x > 0)
                                .map(handle -> new Pair<>(x1 - handle.getLocation().x, handle))
                                .collect(Collectors.toList());
                        rightHandles = closeHandles.stream()
                                .filter(handle -> x2 - handle.getLocation().x <= SNAP_RANGE
                                        && x2 - handle.getLocation().x > 0)
                                .map(handle -> new Pair<>(x2 - handle.getLocation().x, handle))
                                .collect(Collectors.toList());
                    } else {
                        leftHandles = closeHandles.stream()
                                .filter(handle -> handle.getLocation().x - x1 <= SNAP_RANGE
                                        && handle.getLocation().x - x1 > 0)
                                .map(handle -> new Pair<>(handle.getLocation().x - x1, handle))
                                .collect(Collectors.toList());
                        rightHandles = closeHandles.stream()
                                .filter(handle -> handle.getLocation().x - x2 <= SNAP_RANGE
                                        && handle.getLocation().x - x2 > 0)
                                .map(handle -> new Pair<>(handle.getLocation().x - x2, handle))
                                .collect(Collectors.toList());
                    }
                    ArrayList<Pair<Integer, FigureHandle>> potentialHandles = new ArrayList<>();
                    potentialHandles.addAll(leftHandles);
                    potentialHandles.addAll(rightHandles);
                    if (!potentialHandles.isEmpty()) {
                        Collections.sort(potentialHandles, (o1, o2) -> Integer.compare(o1.getKey(), o2.getKey()));
                        return potentialHandles.get(0).getKey();
                    }
                }
            }
        }
        return 1;
    }

    private boolean isCloseToXBoundaries(FigureHandle handle, Figure figure) {
        return (Math.abs(handle.getLocation().x - figure.getBounds().x) <= SNAP_RANGE
                && Math.abs(handle.getLocation().x - figure.getBounds().x) > 0)||
                (Math.abs(handle.getLocation().x - figure.getBounds().x - figure.getBounds().width) <= SNAP_RANGE
                && Math.abs(handle.getLocation().x - figure.getBounds().x - figure.getBounds().width) > 0);
    }

    private boolean isWithinYBoundaries(FigureHandle handle, Figure figure) {
        return handle.getLocation().y >= figure.getBounds().y &&
                handle.getLocation().y <= (figure.getBounds().y + figure.getBounds().height);
    }

    @SuppressWarnings("Duplicates")
    @Override
    public int getStepY(boolean down) {
        if (drawView.getSelection().size() == 1) {
            Figure sourceFigure = drawView.getSelection().get(0);
            ArrayList<FigureHandle> handles = getHandles(sourceFigure);
            if (!handles.isEmpty()) {
                // take out handles which are not within x boundaries
                // and take out handles that are far from y boundaries
                java.util.List<FigureHandle> closeHandles = handles.stream()
                        .filter(handle -> isWithinXBoundaries(handle, sourceFigure))
                        .filter(handle -> isCloseToYBoundaries(handle, sourceFigure))
                        .collect(Collectors.toList());
                if (!closeHandles.isEmpty()) {
                    int y1 = sourceFigure.getBounds().y;
                    int y2 = sourceFigure.getBounds().y + sourceFigure.getBounds().height;
                    java.util.List<Pair<Integer, FigureHandle>> topHandles;
                    java.util.List<Pair<Integer, FigureHandle>> bottomHandles;
                    if (!down) {
                        topHandles = closeHandles.stream()
                                .filter(handle -> y1 - handle.getLocation().y <= SNAP_RANGE
                                        && y1 - handle.getLocation().y > 0)
                                .map(handle -> new Pair<>(y1 - handle.getLocation().y, handle))
                                .collect(Collectors.toList());
                        bottomHandles = closeHandles.stream()
                                .filter(handle -> y2 - handle.getLocation().y <= SNAP_RANGE
                                        && y2 - handle.getLocation().y > 0)
                                .map(handle -> new Pair<>(y2 - handle.getLocation().y, handle))
                                .collect(Collectors.toList());
                    } else {
                        topHandles = closeHandles.stream()
                                .filter(handle -> handle.getLocation().y - y1 <= SNAP_RANGE
                                        && handle.getLocation().y - y1 > 0)
                                .map(handle -> new Pair<>(handle.getLocation().y - y1, handle))
                                .collect(Collectors.toList());
                        bottomHandles = closeHandles.stream()
                                .filter(handle -> handle.getLocation().y - y2 <= SNAP_RANGE
                                        && handle.getLocation().y - y2 > 0)
                                .map(handle -> new Pair<>(handle.getLocation().y - y2, handle))
                                .collect(Collectors.toList());
                    }
                    ArrayList<Pair<Integer, FigureHandle>> potentialHandles = new ArrayList<>();
                    potentialHandles.addAll(topHandles);
                    potentialHandles.addAll(bottomHandles);
                    if (!potentialHandles.isEmpty()) {
                        Collections.sort(potentialHandles, (o1, o2) -> Integer.compare(o1.getKey(), o2.getKey()));
                        return potentialHandles.get(0).getKey();
                    }
                }
            }
        }
        return 1;
    }

    private boolean isCloseToYBoundaries(FigureHandle handle, Figure figure) {
        return (Math.abs(handle.getLocation().y - figure.getBounds().y) <= SNAP_RANGE
                && Math.abs(handle.getLocation().y - figure.getBounds().y) > 0)||
                (Math.abs(handle.getLocation().y - figure.getBounds().y - figure.getBounds().height) <= SNAP_RANGE
                        && Math.abs(handle.getLocation().y - figure.getBounds().y - figure.getBounds().height) > 0);
    }

    private boolean isWithinXBoundaries(FigureHandle handle, Figure figure) {
        return handle.getLocation().x >= figure.getBounds().x &&
                handle.getLocation().x <= (figure.getBounds().x + figure.getBounds().width);
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

    // TODO fix snap to handle when using mouse, currently it snaps to the mouse cursor and not the handle
    @SuppressWarnings("unchecked")
    private Point getClosestPoint(Point mouseLocation, Figure sourceFigure) {
        ArrayList<FigureHandle> handles = getHandles(sourceFigure);
        java.util.List<FigureHandle> closeHandles = handles.stream()
                .filter(handle -> getDistance(handle.getLocation(), mouseLocation) > 0)
                .collect(Collectors.toList());
        Collections.sort(closeHandles, (o1, o2) ->
                getDistance(o1.getLocation(), mouseLocation).compareTo(getDistance(o2.getLocation(), mouseLocation)));
        if (closeHandles.isEmpty() || getDistance(closeHandles.get(0).getLocation(), mouseLocation) > SNAP_RANGE) {
            return mouseLocation;
        } else {
            return closeHandles.get(0).getLocation();
        }
    }

    private Double getDistance(Point a, Point b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }

    private ArrayList<FigureHandle> getHandles(Figure sourceFigure) {
        // returns all handles except from the source figure
        ArrayList<FigureHandle> handles = new ArrayList<>();
        ArrayList<Figure> figures = new ArrayList<>();
        drawView.getModel().getFigures().forEach(figures::add);
        figures.remove(sourceFigure);
        figures.forEach(figure -> figure.getHandles().forEach(handles::add));
        return handles;
    }

}
