package jdraw.joslee.figures.drawTools;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;

public class MyLineToolFactory extends MyToolFactory {

    @Override
    public DrawTool createTool(DrawContext context) {
        return new MyLineTool(context, getName(), getIconName());
    }

}
