package jdraw.joslee.figures.decorators;

import jdraw.framework.DrawModel;
import jdraw.framework.FigureListener;
import jdraw.joslee.figures.MyFigure;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AnimationDecorator extends DecoratorFigure {

    private Executor executor = Executors.newSingleThreadExecutor();
    private boolean animate = false;

    public AnimationDecorator(DrawModel drawModel, MyFigure figure, FigureListener figureListener) {
        super(drawModel, figure, figureListener);
        animate = true;
        startAnimation();
    }

    private void startAnimation() {
        executor.execute(() -> {
            int counter1 = 1;
            int counter2 = 20;
            while (animate) {
                if (counter2 <= 0) {
                    counter2 = 20;
                    if (counter1 < 4) {
                        counter1++;
                    } else {
                        counter1 = 1;
                    }
                }
                counter2--;
                if (counter1 == 1) {
                    move(1, 1);
                } else if (counter1 == 2) {
                    move(-1, 1);
                } else if (counter1 == 3) {
                    move(-1, -1);
                } else {
                    move(1, -1);
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void stopAnimation() {
        animate = false;
    }

}
