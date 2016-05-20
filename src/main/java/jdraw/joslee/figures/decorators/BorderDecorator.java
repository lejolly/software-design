package jdraw.joslee.figures.decorators;

import jdraw.framework.DrawModel;
import jdraw.framework.FigureListener;
import jdraw.joslee.figures.MyFigure;

import java.awt.*;

public class BorderDecorator extends DecoratorFigure {

    private static final int BORDER_GAP = 5;

    public BorderDecorator(DrawModel drawModel, MyFigure figure, FigureListener figureListener) {
        super(drawModel, figure, figureListener);
    }

    @Override
    public void draw(Graphics g) {
        Rectangle bounds;
        if (getInnerFigure(false) instanceof BorderDecorator) {
            bounds = getBorderBounds();
        } else {
            bounds = new Rectangle(getBounds().x - BORDER_GAP, getBounds().y - BORDER_GAP,
                    getBounds().width + 2 * BORDER_GAP, getBounds().height + 2 * BORDER_GAP);
        }
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

    @Override
    public Rectangle getBounds() {
        return getInnerFigure(false).getBounds();
    }

    public Rectangle getBorderBounds() {
        if (getInnerFigure(false) instanceof BorderDecorator) {
            Rectangle bounds = ((BorderDecorator) getInnerFigure(false)).getBorderBounds();
            return new Rectangle(bounds.x - BORDER_GAP, bounds.y - BORDER_GAP,
                    bounds.width + 2 * BORDER_GAP, bounds.height + 2 * BORDER_GAP);
        } else {
            return new Rectangle(getBounds().x - BORDER_GAP, getBounds().y - BORDER_GAP,
                    getBounds().width + 2 * BORDER_GAP, getBounds().height + 2 * BORDER_GAP);
        }
    }

}
