package jdraw.test.joslee;

import jdraw.framework.DrawView;
import jdraw.framework.FigureHandle;
import jdraw.joslee.figures.*;
import jdraw.joslee.figures.handles.MyLineHandle;
import jdraw.joslee.figures.handles.MyOvalHandle;
import jdraw.joslee.figures.handles.MyRectHandle;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.event.MouseEvent;

import static org.junit.Assert.assertEquals;

/**
 * Test for Figure Handles
 */
public class HandlesTest {

    private MouseEvent mouseEvent;
    private DrawView drawView;

    @Before
    public void setUp() {
        mouseEvent = new MouseEvent(new Component() {
            @Override
            public String getName() {
                return null;
            }
        }, 0, 0, 0, 0, 0, 0, false, 0);
        drawView = new DrawViewStub();
    }

    @Test
    public void rectangleHandleTest() {
        // creates a 10x10 rectangle/square at (0,0)
        MyRect myRect = new MyRect(0, 0, 10, 10);
        checkMyRectHandles(myRect);

        // resize using north handle beyond south handle
        for (FigureHandle handle : myRect.getHandles()) {
            MyRectHandle myRectHandle = (MyRectHandle) handle;
            if (myRectHandle.getType() == MyRectHandle.Type.N) {
                myRectHandle.startInteraction(5, 0, mouseEvent, drawView);
                myRectHandle.dragInteraction(5, 20, mouseEvent, drawView);
            }
        }
        myRect.move(0, -10);
        checkMyRectHandles(myRect);
    }

    private void checkMyRectHandles(MyRect myRect) {
        for (FigureHandle handle : myRect.getHandles()) {
            MyRectHandle myRectHandle = (MyRectHandle) handle;
            if (myRectHandle.getType() == MyRectHandle.Type.CENTER) {
                assertEquals(new Point(5, 5), myRectHandle.getLocation());
            } else if (myRectHandle.getType() == MyRectHandle.Type.NW) {
                assertEquals(new Point(0, 0), myRectHandle.getLocation());
            } else if (myRectHandle.getType() == MyRectHandle.Type.N) {
                assertEquals(new Point(5, 0), myRectHandle.getLocation());
            } else if (myRectHandle.getType() == MyRectHandle.Type.NE) {
                assertEquals(new Point(10, 0), myRectHandle.getLocation());
            } else if (myRectHandle.getType() == MyRectHandle.Type.E) {
                assertEquals(new Point(10, 5), myRectHandle.getLocation());
            } else if (myRectHandle.getType() == MyRectHandle.Type.SE) {
                assertEquals(new Point(10, 10), myRectHandle.getLocation());
            } else if (myRectHandle.getType() == MyRectHandle.Type.S) {
                assertEquals(new Point(5, 10), myRectHandle.getLocation());
            } else if (myRectHandle.getType() == MyRectHandle.Type.SW) {
                assertEquals(new Point(0, 10), myRectHandle.getLocation());
            } else if (myRectHandle.getType() == MyRectHandle.Type.W) {
                assertEquals(new Point(0, 5), myRectHandle.getLocation());
            }
        }
    }

    @Test
    public void ovalHandleTest() {
        // creates a 10x10 oval/circle at (0,0)
        MyOval myOval = new MyOval(0, 0, 10, 10);
        checkMyOvalHandles(myOval);

        // resize using north handle beyond south handle
        for (FigureHandle handle : myOval.getHandles()) {
            MyOvalHandle myOvalHandle = (MyOvalHandle) handle;
            if (myOvalHandle.getType() == MyOvalHandle.Type.N) {
                myOvalHandle.startInteraction(5, 0, mouseEvent, drawView);
                myOvalHandle.dragInteraction(5, 20, mouseEvent, drawView);
            }
        }
        myOval.move(0, -10);
        checkMyOvalHandles(myOval);
    }

    private void checkMyOvalHandles(MyOval myOval) {
        for (FigureHandle handle : myOval.getHandles()) {
            MyOvalHandle myOvalHandle = (MyOvalHandle) handle;
            if (myOvalHandle.getType() == MyOvalHandle.Type.CENTER) {
                assertEquals(new Point(5, 5), myOvalHandle.getLocation());
            } else if (myOvalHandle.getType() == MyOvalHandle.Type.N) {
                assertEquals(new Point(5, 0), myOvalHandle.getLocation());
            } else if (myOvalHandle.getType() == MyOvalHandle.Type.E) {
                assertEquals(new Point(10, 5), myOvalHandle.getLocation());
            } else if (myOvalHandle.getType() == MyOvalHandle.Type.S) {
                assertEquals(new Point(5, 10), myOvalHandle.getLocation());
            } else if (myOvalHandle.getType() == MyOvalHandle.Type.W) {
                assertEquals(new Point(0, 5), myOvalHandle.getLocation());
            }
        }
    }

    @Test
    public void lineHandleTest() {
        // creates a 1x10 line at (0,0)
        MyLine myLine = new MyLine(0, 0, 0, 10);
        checkMyLineHandles(myLine);

        // resize using first handle beyond second handle
        for (FigureHandle handle : myLine.getHandles()) {
            MyLineHandle myLineHandle = (MyLineHandle) handle;
            if (myLineHandle.getType() == MyLineHandle.Type.FIRST) {
                myLineHandle.startInteraction(0, 0, mouseEvent, drawView);
                myLineHandle.dragInteraction(0, 20, mouseEvent, drawView);
            }
        }
        myLine.move(0, -10);
        checkMyLineHandles(myLine);
    }

    private void checkMyLineHandles(MyLine myLine) {
        for (FigureHandle handle : myLine.getHandles()) {
            MyLineHandle myLineHandle = (MyLineHandle) handle;
            if (myLineHandle.getType() == MyLineHandle.Type.FIRST) {
                assertEquals(new Point(0, 0), myLineHandle.getLocation());
            } else if (myLineHandle.getType() == MyLineHandle.Type.SECOND) {
                assertEquals(new Point(0, 10), myLineHandle.getLocation());
            }
        }
    }

}
