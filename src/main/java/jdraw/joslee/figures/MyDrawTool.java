package jdraw.joslee.figures;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.DrawView;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * This tool defines a mode for drawing figures.
 *
 * @see jdraw.framework.Figure
 *
 * @author  Christoph Denzler, Joshua Lee
 */
abstract class MyDrawTool implements DrawTool {

    /**
     * the image resource path.
     */
    static final String IMAGES = "/images/";

    /**
     * The context we use for drawing.
     */
    private DrawContext context;

    /**
     * The context's view. This variable can be used as a shortcut, i.e.
     * instead of calling context.getView().
     */
    private DrawView view;

    /**
     * Temporary variable. During line creation (during a
     * mouse down - mouse drag - mouse up cycle) this variable refers
     * to the new figure that is inserted.
     */
    private MyFigure newFigure = null;

    /**
     * Temporary variable.
     * During rectangle creation this variable refers to the point the
     * mouse was first pressed.
     */
    private Point anchor = null;

    /**
     * Create a new tool for the given context.
     * @param context a context to use this tool in.
     */
    MyDrawTool(DrawContext context) {
        this.context = context;
        this.view = context.getView();
    }

    /**
    * Creates a new figure
    * */
    abstract MyFigure getNewFigure(int x, int y);

    /**
     * Activates the Figure Mode. There will be a
     * specific menu added to the menu bar that provides settings for
     * Figure attributes
     */
    public void activate() {
        this.context.showStatusText(getName() + " Mode");
    }

    /**
     * Deactivates the current mode by resetting the cursor
     * and clearing the status bar.
     * @see jdraw.framework.DrawTool#deactivate()
     */
    public void deactivate() {
        this.context.showStatusText("");
    }

    /**
     * Initializes a new Line object by setting an anchor
     * point where the mouse was pressed. A new Line is then
     * added to the model.
     * @param x x-coordinate of mouse
     * @param y y-coordinate of mouse
     * @param e event containing additional information about which keys were pressed.
     *
     * @see jdraw.framework.DrawTool#mouseDown(int, int, MouseEvent)
     */
    public void mouseDown(int x, int y, MouseEvent e) {
        if (newFigure != null) {
            throw new IllegalStateException();
        }
        anchor = new Point(x, y);
        newFigure = getNewFigure(x, y);
        view.getModel().addFigure(newFigure);
    }

    /**
     * During a mouse drag, the Figure will be resized according to the mouse
     * position. The status bar shows the current size.
     *
     * @param x   x-coordinate of mouse
     * @param y   y-coordinate of mouse
     * @param e   event containing additional information about which keys were
     *            pressed.
     *
     * @see jdraw.framework.DrawTool#mouseDrag(int, int, MouseEvent)
     */
    public void mouseDrag(int x, int y, MouseEvent e) {
        newFigure.setBounds(anchor, new Point(x, y));
        java.awt.Rectangle r = newFigure.getBounds();
        this.context.showStatusText("w: " + r.width + ", h: " + r.height);
    }

    /**
     * When the user releases the mouse, the Line object is updated
     * according to the color and fill status settings.
     *
     * @param x   x-coordinate of mouse
     * @param y   y-coordinate of mouse
     * @param e   event containing additional information about which keys were
     *            pressed.
     *
     * @see jdraw.framework.DrawTool#mouseUp(int, int, MouseEvent)
     */
    public void mouseUp(int x, int y, MouseEvent e) {
        newFigure = null;
        anchor = null;
        this.context.showStatusText(getName() + " Mode");
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
    }

}
