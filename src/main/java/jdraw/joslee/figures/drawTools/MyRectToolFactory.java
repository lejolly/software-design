package jdraw.joslee.figures.drawTools;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;

public class MyRectToolFactory extends MyToolFactory {

    @Override
    public DrawTool createTool(DrawContext context) {
        return new MyRectTool(context, getName(), getIconName());
    }

}
