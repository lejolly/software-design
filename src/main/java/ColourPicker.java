import gui.*;
import tools.ColourBean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ColourPicker extends JPanel {

    private final int WINDOW_WIDTH = 400;
    private final int WINDOW_HEIGHT = 400;

    private ColourBean currentColour;
    private JMenuBar menuBar;

    public ColourPicker() {
        currentColour = new ColourBean();
        setBackground(Color.white);
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        initializeComponents();
        initializeMenuBar();
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
        attributesMenu.add(new ColourRadioButton("red", Color.RED, currentColour));
        attributesMenu.add(new ColourRadioButton("blue", Color.BLUE, currentColour));
        attributesMenu.add(new ColourRadioButton("green", Color.GREEN, currentColour));
        attributesMenu.add(new ColourRadioButton("yellow", Color.YELLOW, currentColour));
        attributesMenu.add(new ColourRadioButton("cyan", Color.CYAN, currentColour));
        attributesMenu.add(new ColourRadioButton("orange", Color.ORANGE, currentColour));
        attributesMenu.add(new ColourRadioButton("black", Color.BLACK, currentColour));

        menuBar.add(fileMenu);
        menuBar.add(attributesMenu);
    }

    public void initializeComponents() {
        JPanel bottom = new JPanel();
        ColourPanel colourPanel = new ColourPanel(currentColour);

        this.add(createColourSelectionPanel(Color.RED));
        this.add(createColourSelectionPanel(Color.GREEN));
        this.add(createColourSelectionPanel(Color.BLUE));

        JPanel radioButtons = new JPanel();
        radioButtons.setLayout(new BoxLayout(radioButtons, BoxLayout.Y_AXIS));
        radioButtons.add(new ColourRadioButton("red", Color.RED, currentColour));
        radioButtons.add(new ColourRadioButton("blue", Color.BLUE, currentColour));
        radioButtons.add(new ColourRadioButton("green", Color.GREEN, currentColour));
        radioButtons.add(new ColourRadioButton("yellow", Color.YELLOW, currentColour));
        radioButtons.add(new ColourRadioButton("cyan", Color.CYAN, currentColour));
        radioButtons.add(new ColourRadioButton("orange", Color.ORANGE, currentColour));
        radioButtons.add(new ColourRadioButton("black", Color.BLACK, currentColour));

        bottom.add(colourPanel);
        bottom.add(radioButtons);

        this.add(bottom);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public JPanel createColourSelectionPanel(Color colour) {
        JPanel panel = new JPanel();
        ColourScrollBar scrollBar = new ColourScrollBar(colour, currentColour);
        ColourTextField textField = new ColourTextField(colour, currentColour);
        ColourLabel label = new ColourLabel(colour, currentColour);
        panel.add(scrollBar);
        panel.add(textField);
        panel.add(label);
        return panel;
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

