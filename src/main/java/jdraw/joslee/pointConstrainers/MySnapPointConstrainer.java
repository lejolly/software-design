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

    private static final int SNAP_RANGE = 25;

    private DrawView drawView;
    private boolean isDragging;
    private Point startPoint;

    public MySnapPointConstrainer(DrawView drawView) {
        this.drawView = drawView;
        isDragging = false;
    }

    @Override
    public Point constrainPoint(Point p) {
        // currently only limited to dragging a single figure
        if (isDragging && drawView.getSelection().size() == 1) {
            if (startPoint == null) {
                startPoint = getClosestSourceHandle(p, drawView.getSelection().get(0));
                return startPoint;
            }
            return getSnapPoint(p, drawView.getSelection().get(0));
        }
        return p;
    }

    private Point getClosestSourceHandle(Point mouseLocation, Figure sourceFigure) {
        ArrayList<FigureHandle> handles = new ArrayList<>(sourceFigure.getHandles());
        Collections.sort(handles, ((o1, o2) ->
                getDistance(o1.getLocation(), mouseLocation)
                        .compareTo(getDistance(o2.getLocation(), mouseLocation))));
        return new Point(handles.get(0).getLocation().x, handles.get(0).getLocation().y);
    }

    private Point getSnapPoint(Point mouseLocation, Figure sourceFigure) {
        // generate all combinations
        ArrayList<Pair<FigureHandle, FigureHandle>> combinations = new ArrayList<>();
        sourceFigure.getHandles().forEach(sourceHandle -> combinations.addAll(
                getOtherHandles(sourceFigure).stream()
                        .map(targetHandle -> new Pair<>(sourceHandle, targetHandle))
                        .collect(Collectors.toList())));
        // filter combinations
        ArrayList<Pair<FigureHandle, FigureHandle>> filteredCombinations = new ArrayList<>();
        combinations.stream().filter(combination -> pointFilter(
                mouseLocation, combination.getKey().getLocation(), combination.getValue().getLocation()))
                .forEach(filteredCombinations::add);
        // sort combinations
        if (!filteredCombinations.isEmpty()) {
            // sort by closest distance between source point and target point
            Collections.sort(filteredCombinations, ((o1, o2) ->
                    getDistance(o1.getKey().getLocation(), o1.getValue().getLocation())
                            .compareTo(getDistance(o2.getKey().getLocation(), o2.getValue().getLocation()))));
            return filteredCombinations.get(0).getValue().getLocation();
        }
        return mouseLocation;
    }

    private boolean pointFilter(Point mouseLocation, Point sourcePoint, Point targetPoint) {
        // are points close and distance is more than 0
        return getDistance(sourcePoint, targetPoint) <= SNAP_RANGE &&
                getDistance(mouseLocation, targetPoint) <= SNAP_RANGE &&
                getDistance(sourcePoint, targetPoint) > 0 &&
                getDistance(mouseLocation, targetPoint) > 0;
    }

    private Double getDistance(Point a, Point b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }

    private ArrayList<FigureHandle> getOtherHandles(Figure sourceFigure) {
        // returns all handles except from the source figure
        ArrayList<FigureHandle> handles = new ArrayList<>();
        ArrayList<Figure> figures = new ArrayList<>();
        drawView.getModel().getFigures().forEach(figures::add);
        figures.remove(sourceFigure);
        figures.forEach(figure -> figure.getHandles().forEach(handles::add));
        return handles;
    }

    @Override
    public void mouseDown() {
        // start of drag
        isDragging = true;
    }

    @Override
    public void mouseUp() {
        // end of drag, time to reset stuff
        isDragging = false;
        startPoint = null;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public int getStepX(boolean right) {
        if (drawView.getSelection().size() == 1) {
            Figure sourceFigure = drawView.getSelection().get(0);
            ArrayList<FigureHandle> handles = getOtherHandles(sourceFigure);
            if (!handles.isEmpty()) {
                // take out handles which are not within y boundaries
                // and take out handles that are far from x boundaries
                java.util.List<FigureHandle> closeHandles = handles.stream()
                        .filter(handle -> isWithinYBoundaries(handle, sourceFigure))
                        .filter(handle -> isCloseToXBoundaries(handle, sourceFigure))
                        .collect(Collectors.toList());
                if (!closeHandles.isEmpty()) {
                    int x1 = sourceFigure.getBounds(this).x;
                    int x2 = sourceFigure.getBounds(this).x + sourceFigure.getBounds(this).width;
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
                        System.out.println("snap");
                        return potentialHandles.get(0).getKey();
                    }
                }
            }
        }
        return 1;
    }

    private boolean isCloseToXBoundaries(FigureHandle handle, Figure figure) {
        int leftDistance = Math.abs(handle.getLocation().x - figure.getBounds(this).x);
        int rightDistance = Math.abs(handle.getLocation().x - figure.getBounds(this).x - figure.getBounds(this).width);
        return (leftDistance <= SNAP_RANGE && leftDistance > 0) || (rightDistance <= SNAP_RANGE && rightDistance > 0);
    }

    private boolean isWithinYBoundaries(FigureHandle handle, Figure figure) {
        return handle.getLocation().y >= figure.getBounds(this).y &&
                handle.getLocation().y <= (figure.getBounds(this).y + figure.getBounds(this).height);
    }

    @SuppressWarnings("Duplicates")
    @Override
    public int getStepY(boolean down) {
        if (drawView.getSelection().size() == 1) {
            Figure sourceFigure = drawView.getSelection().get(0);
            ArrayList<FigureHandle> handles = getOtherHandles(sourceFigure);
            if (!handles.isEmpty()) {
                // take out handles which are not within x boundaries
                // and take out handles that are far from y boundaries
                java.util.List<FigureHandle> closeHandles = handles.stream()
                        .filter(handle -> isWithinXBoundaries(handle, sourceFigure))
                        .filter(handle -> isCloseToYBoundaries(handle, sourceFigure))
                        .collect(Collectors.toList());
                if (!closeHandles.isEmpty()) {
                    int y1 = sourceFigure.getBounds(this).y;
                    int y2 = sourceFigure.getBounds(this).y + sourceFigure.getBounds(this).height;
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
        int topDistance = Math.abs(handle.getLocation().y - figure.getBounds(this).y);
        int bottomDistance =
                Math.abs(handle.getLocation().y - figure.getBounds(this).y - figure.getBounds(this).height);
        return (topDistance <= SNAP_RANGE && topDistance > 0) || (bottomDistance <= SNAP_RANGE && bottomDistance > 0);
    }

    private boolean isWithinXBoundaries(FigureHandle handle, Figure figure) {
        return handle.getLocation().x >= figure.getBounds(this).x &&
                handle.getLocation().x <= (figure.getBounds(this).x + figure.getBounds(this).width);
    }

    @Override
    public void activate() {}

    @Override
    public void deactivate() {}

}
