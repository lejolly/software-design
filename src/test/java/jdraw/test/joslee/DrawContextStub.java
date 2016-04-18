package jdraw.test.joslee;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawModel;
import jdraw.framework.DrawTool;
import jdraw.framework.DrawView;

import javax.swing.*;

public class DrawContextStub implements DrawContext {

    @Override
    public DrawView getView() {
        return null;
    }

    @Override
    public DrawModel getModel() {
        return null;
    }

    @Override
    public void showStatusText(String msg) {

    }

    @Override
    public void addMenu(JMenu menu) {

    }

    @Override
    public void removeMenu(JMenu menu) {

    }

    @Override
    public void addTool(DrawTool tool) {

    }

    @Override
    public DrawTool getTool() {
        return null;
    }

    @Override
    public void setTool(DrawTool tool) {

    }

    @Override
    public void setDefaultTool() {

    }

    @Override
    public void showView() {

    }

}
