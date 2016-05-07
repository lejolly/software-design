package jdraw.joslee.figures.decorators;

import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

import java.util.Collections;
import java.util.List;

public class BundleDecorator extends DecoratorFigure {

    public BundleDecorator(Figure figure, FigureListener figureListener) {
        super(figure, figureListener);
    }

    @Override
    public List<FigureHandle> getHandles() {
        return Collections.emptyList();
    }

}
