import tools.ComplexNumber;

import javax.swing.*;
import java.awt.*;

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
                ComplexNumber complexNumber = new ComplexNumber(((col - (WINDOW_WIDTH / 2)) * 4.0) / WINDOW_WIDTH,
                        ((row - (WINDOW_HEIGHT / 2)) * 4.0) / WINDOW_WIDTH);
//                ComplexNumber complexNumber = new ComplexNumber(-2.25, -1.5);
//                ComplexNumber complexNumber = new ComplexNumber(0.75, 1.5);
                double x = 0;
                double y = 0;
                int iteration = 0;
                while (((x * x) + (y * y)) < 4 && iteration < max) {
                    double newX = (x * x) - (y * y) + complexNumber.getReal();
                    y = 2 * x * y + complexNumber.getImaginary();
                    x = newX;
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
