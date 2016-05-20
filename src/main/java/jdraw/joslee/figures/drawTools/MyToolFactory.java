package jdraw.joslee.figures.drawTools;

import jdraw.framework.DrawToolFactory;

abstract class MyToolFactory implements DrawToolFactory {

    private String name;
    private String icon;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getIconName() {
        return icon;
    }

    @Override
    public void setIconName(String name) {
        this.icon = name;
    }

}
