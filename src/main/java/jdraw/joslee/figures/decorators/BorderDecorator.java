package jdraw.joslee.figures.decorators;

import jdraw.framework.Figure;
import jdraw.framework.FigureListener;
import jdraw.joslee.figures.MyHandle;

import java.awt.*;

public class BorderDecorator extends DecoratorFigure {

    private static final int BORDER_GAP = 5;

    public BorderDecorator(Figure figure, FigureListener figureListener) {
        super(figure, figureListener);
    }

    @Override
    public Rectangle getBounds(Object caller) {
        Rectangle bounds = super.getBounds(caller);
        if (caller instanceof MyHandle) {
            return bounds;
        } else {
            return new Rectangle(bounds.x - BORDER_GAP, bounds.y - BORDER_GAP,
                    bounds.width + 2 * BORDER_GAP, bounds.height + 2 * BORDER_GAP);
        }
    }

    @Override
    public void draw(Graphics g) {
        Rectangle bounds = getBounds(this);
        int x1 = bounds.x ;
        int y1 = bounds.y;
        int x2 = bounds.x + bounds.width;
        int y2 = bounds.y + bounds.height;
        g.setColor(Color.BLUE);
        g.drawLine(x1, y1, x2, y1);
        g.drawLine(x1, y1, x1, y2);
        g.setColor(Color.darkGray);
        g.drawLine(x2, y1, x2, y2);
        g.drawLine(x1, y2, x2, y2);
        super.draw(g);
    }

}
