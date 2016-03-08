import tools.ComplexNumber;

import javax.swing.*;
import java.awt.*;

/*
* Code adapted from: https://github.com/joni/fractals/blob/master/mandelbrot/MandelbrotColor.java
* */
public class GraphicViewer extends JPanel {

    private final int WINDOW_WIDTH = 1280;
    private final int WINDOW_HEIGHT = 800;

    public GraphicViewer() {
        setBackground(Color.white);
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        int max = 100;
        int[] colors = new int[max];
        for (int i = 0; i < max; i++) {
            colors[i] = Color.HSBtoRGB(i / 256f, 1, i / (i + 8f));
        }

        for (int row = 0; row < WINDOW_HEIGHT; row++) {
            for (int col = 0; col < WINDOW_WIDTH; col++) {
                ComplexNumber baseNumber = new ComplexNumber(((col - (WINDOW_WIDTH / 2)) * 4.0) / WINDOW_WIDTH,
                        ((row - (WINDOW_HEIGHT / 2)) * 4.0) / WINDOW_WIDTH);
                ComplexNumber tempNumber = new ComplexNumber(0, 0);
                int iteration = 0;

                while (tempNumber.getAbsolute() < 2 && iteration < max) {
                    tempNumber = new ComplexNumber(
                            (tempNumber.getReal() * tempNumber.getReal()) -
                                    (tempNumber.getImaginary() * tempNumber.getImaginary())
                                    + baseNumber.getReal(),
                            2 * tempNumber.getReal() * tempNumber.getImaginary() + baseNumber.getImaginary());
                    iteration++;
                }

                if (iteration < max) {
                    graphics.setColor(new Color(colors[iteration]));
                    graphics.drawRect(col, row, 1, 1);
                } else {
                    graphics.setColor(Color.BLACK);
                    graphics.drawRect(col, row, 1, 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Graphic Viewer");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.add(new GraphicViewer());
        jFrame.pack();
        jFrame.setVisible(true);
    }

}
