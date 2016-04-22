package jdraw.joslee.pointConstrainers;

import com.sun.tools.javac.util.Pair;
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

    private static final int SNAP_RANGE = 5;

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

    @SuppressWarnings("unchecked")
    private Point getClosestPoint(Point point, Figure sourceFigure) {
        ArrayList<FigureHandle> handles = new ArrayList<>();
        ArrayList<Figure> figures = new ArrayList<>();
        drawView.getModel().getFigures().forEach(figures::add);
        figures.remove(sourceFigure);
        figures.forEach(figure -> figure.getHandles().forEach(handles::add));
        java.util.List<FigureHandle> closeHandles = handles.stream()
                .filter(figureHandle -> isHandleCloseToFigure(figureHandle.getLocation(), sourceFigure))
                .collect(Collectors.toList());
        if (closeHandles.isEmpty()) {
            return point;
        } else {
            ArrayList<Pair<FigureHandle, FigureHandle>> handlePairs = new ArrayList<>();
            for (FigureHandle sourceHandle : sourceFigure.getHandles()) {
                handlePairs.addAll(closeHandles.stream()
                        .map(handle -> new Pair<>(handle, sourceHandle)).collect(Collectors.toList()));
            }
            Collections.sort(handlePairs, (o1, o2) ->
                    getDistance(o1.fst.getLocation(), o1.snd.getLocation())
                            .compareTo(getDistance(o2.fst.getLocation(), o2.snd.getLocation())));
            return handlePairs.get(0).fst.getLocation();
        }
    }

    private boolean isHandleCloseToFigure(Point handlePoint, Figure figure) {
        return figure.getHandles().stream()
                .anyMatch(handle -> arePointsClose(handle.getLocation(), handlePoint));
    }

    private boolean arePointsClose(Point a, Point b) {
        return Math.abs(a.x - b.x) <= SNAP_RANGE || Math.abs(a.y - b.y) <= SNAP_RANGE;
    }

    private Double getDistance(Point a, Point b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }

}
