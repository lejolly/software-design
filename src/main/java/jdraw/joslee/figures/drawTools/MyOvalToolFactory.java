package jdraw.joslee.figures.drawTools;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;

public class MyOvalToolFactory extends MyToolFactory {

    @Override
    public DrawTool createTool(DrawContext context) {
        return new MyOvalTool(context, getName(), getIconName());
    }

}
