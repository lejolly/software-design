import tools.ColourBean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ColourPicker extends JPanel implements PropertyChangeListener {

    private final int WINDOW_WIDTH = 400;
    private final int WINDOW_HEIGHT = 400;

    private ColourBean currentColour;
    private JMenuBar menuBar;

    private JScrollBar redScrollBar;
    private JTextField redTextField;
    private JLabel redLabel;

    private JScrollBar greenScrollBar;
    private JTextField greenTextField;
    private JLabel greenLabel;

    private JScrollBar blueScrollBar;
    private JTextField blueTextField;
    private JLabel blueLabel;

    private JPanel colourPanel;

    public ColourPicker() {
        currentColour = new ColourBean(this);
        setBackground(Color.white);
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        initializeMenuBar();
        initializeComponents();
//        currentColour.setColour(Color.WHITE);
        currentColour.setColour(Color.BLACK);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Color colour = currentColour.getColour();
        if (evt.getSource().equals(currentColour)) {
            // ScrollBars
            redScrollBar.setValue(colour.getRed());
            greenScrollBar.setValue(colour.getGreen());
            blueScrollBar.setValue(colour.getBlue());
            // TextFields
            redTextField.setText(Integer.toString(colour.getRed()));
            greenTextField.setText(Integer.toString(colour.getGreen()));
            blueTextField.setText(Integer.toString(colour.getBlue()));
            // Labels
            redLabel.setText(Integer.toString(colour.getRed(), 16).toUpperCase());
            greenLabel.setText(Integer.toString(colour.getGreen(), 16).toUpperCase());
            blueLabel.setText(Integer.toString(colour.getBlue(), 16).toUpperCase());
            // ColourPanel
            colourPanel.setBackground(colour);
        } else if (evt.getSource().equals(redScrollBar)) {
            // ScrollBars
            currentColour.setColour(new Color(redScrollBar.getValue(), colour.getGreen(), colour.getBlue()));
        } else if (evt.getSource().equals(greenScrollBar)) {
            currentColour.setColour(new Color(colour.getRed(), greenScrollBar.getValue(), colour.getBlue()));
        } else if (evt.getSource().equals(blueScrollBar)) {
            currentColour.setColour(new Color(colour.getRed(), colour.getGreen(), blueScrollBar.getValue()));
        } else if (evt.getSource().equals(redTextField)) {
            // TextFields
            currentColour.setColour(new Color((Integer) evt.getNewValue(), colour.getGreen(), colour.getBlue()));
        } else if (evt.getSource().equals(greenTextField)) {
            currentColour.setColour(new Color(colour.getRed(), (Integer) evt.getNewValue(), colour.getBlue()));
        } else if (evt.getSource().equals(blueTextField)) {
            currentColour.setColour(new Color(colour.getRed(), colour.getGreen(), (Integer) evt.getNewValue()));
        }
    }

    public void initializeMenuBar() {
        menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem(new AbstractAction("Exit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitItem);

        JMenu attributesMenu = new JMenu("Attributes");

        menuBar.add(fileMenu);
        menuBar.add(attributesMenu);
    }

    public void initializeComponents() {
        JPanel redPanel = new JPanel();
        redScrollBar = createScrollBar(Color.RED);
        redPanel.add(redScrollBar);
        redTextField = createTextField();
        redPanel.add(redTextField);
        redLabel = createLabel();
        redPanel.add(redLabel);

        JPanel greenPanel = new JPanel();
        greenScrollBar = createScrollBar(Color.GREEN);
        greenPanel.add(greenScrollBar);
        greenTextField = createTextField();
        greenPanel.add(greenTextField);
        greenLabel = createLabel();
        greenPanel.add(greenLabel);

        JPanel bluePanel = new JPanel();
        blueScrollBar = createScrollBar(Color.BLUE);
        bluePanel.add(blueScrollBar);
        blueTextField = createTextField();
        bluePanel.add(blueTextField);
        blueLabel = createLabel();
        bluePanel.add(blueLabel);

        JPanel bottom = new JPanel();
        colourPanel = new JPanel();
        colourPanel.setPreferredSize(new Dimension(100, 100));

        JPanel radioButtons = new JPanel();
        JRadioButton redRadioButton = createRadioButton("red", Color.RED);
        radioButtons.add(redRadioButton);

        bottom.add(colourPanel);
        bottom.add(radioButtons);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(redPanel);
        this.add(greenPanel);
        this.add(bluePanel);
        this.add(bottom);
    }

    public JRadioButton createRadioButton(String name, final Color colour) {
        JRadioButton radioButton = new JRadioButton(name);

        return radioButton;
    }

    public JScrollBar createScrollBar(Color color) {
        final JScrollBar scrollBar = new JScrollBar(Adjustable.HORIZONTAL, 0, 1, 0, 256);
        scrollBar.setBackground(color);
        scrollBar.setPreferredSize(new Dimension(200, 20));
        scrollBar.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                scrollBar.firePropertyChange("value", 0, scrollBar.getValue());
            }
        });
        scrollBar.addPropertyChangeListener("value", this);
        return scrollBar;
    }

    public JTextField createTextField() {
        final JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(80, 20));
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int value = Integer.parseInt(textField.getText());
                    if (value >= 0 && value <= 255) {
                        textField.firePropertyChange("value", 0, value);
                    }
                } catch (Exception ex) {
                    System.out.println("Invalid value");
                }
            }
        });
        textField.addPropertyChangeListener("value", this);
        return textField;
    }

    public JLabel createLabel() {
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(80, 20));
        return label;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFrame jFrame = new JFrame("Colour Picker");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        ColourPicker colourPicker = new ColourPicker();
        jFrame.add(colourPicker);
        jFrame.setJMenuBar(colourPicker.getMenuBar());
        jFrame.pack();
        jFrame.setVisible(true);
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

}

