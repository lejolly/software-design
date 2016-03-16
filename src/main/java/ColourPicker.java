import gui.*;
import tools.ColourBean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

@SuppressWarnings("FieldCanBeLocal")
public class ColourPicker extends JPanel {

    private final int WINDOW_HEIGHT = 250;
    private final int WINDOW_WIDTH = 300;

    private final ColourBean currentColour;
    private JMenuBar menuBar;

    public ColourPicker() {
        currentColour = new ColourBean();
        setBackground(Color.white);
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setLayout(new BorderLayout());
        initializeComponents();
        initializeMenuBar();

        // Toggle colour
        currentColour.setColour(Color.WHITE);
        currentColour.setColour(Color.BLACK);
    }

    private void initializeMenuBar() {
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
        createRadioButtons(attributesMenu);

        menuBar.add(fileMenu);
        menuBar.add(attributesMenu);
    }

    private void initializeComponents() {
        JPanel colourSelectionPanels = new JPanel();
        colourSelectionPanels.setLayout(new BoxLayout(colourSelectionPanels, BoxLayout.Y_AXIS));
        colourSelectionPanels.add(createColourSelectionPanel(Color.RED));
        colourSelectionPanels.add(createColourSelectionPanel(Color.GREEN));
        colourSelectionPanels.add(createColourSelectionPanel(Color.BLUE));
        this.add(colourSelectionPanels, BorderLayout.PAGE_START);
        this.add(new ColourPanel(currentColour), BorderLayout.LINE_START);

        JPanel radioButtons = new JPanel();
        radioButtons.setLayout(new BoxLayout(radioButtons, BoxLayout.Y_AXIS));
        createRadioButtons(radioButtons);
        this.add(radioButtons, BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        buttons.add(new ColourButton(true, currentColour));
        buttons.add(new ColourButton(false, currentColour));
        this.add(buttons, BorderLayout.LINE_END);
    }

    private void createRadioButtons(JComponent component) {
        component.add(new ColourRadioButton("red", Color.RED, currentColour));
        component.add(new ColourRadioButton("blue", Color.BLUE, currentColour));
        component.add(new ColourRadioButton("green", Color.GREEN, currentColour));
        component.add(new ColourRadioButton("yellow", Color.YELLOW, currentColour));
        component.add(new ColourRadioButton("cyan", Color.CYAN, currentColour));
        component.add(new ColourRadioButton("orange", Color.ORANGE, currentColour));
        component.add(new ColourRadioButton("black", Color.BLACK, currentColour));
    }

    private JPanel createColourSelectionPanel(Color colour) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2, 0, 0));
        JPanel innerPanel = new JPanel();
        ColourScrollBar scrollBar = new ColourScrollBar(colour, currentColour);
        ColourTextField textField = new ColourTextField(colour, currentColour);
        ColourLabel label = new ColourLabel(colour, currentColour);
        innerPanel.add(textField);
        innerPanel.add(label);
        panel.add(scrollBar);
        panel.add(innerPanel);
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

