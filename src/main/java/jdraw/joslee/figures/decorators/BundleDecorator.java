package jdraw.joslee.figures.decorators;

import jdraw.framework.DrawModel;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;
import jdraw.joslee.figures.MyFigure;

import java.util.Collections;
import java.util.List;

public class BundleDecorator extends DecoratorFigure {

    public BundleDecorator(DrawModel drawModel, MyFigure figure, FigureListener figureListener) {
        super(drawModel, figure, figureListener);
    }

    @Override
    public List<FigureHandle> getHandles() {
        return Collections.emptyList();
    }

}
