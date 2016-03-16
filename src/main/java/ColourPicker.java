import gui.ColourLabel;
import gui.ColourPanel;
import gui.ColourScrollBar;
import gui.ColourTextField;
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
//        attributesMenu.add(redRadioButton);

        menuBar.add(fileMenu);
        menuBar.add(attributesMenu);
    }

    public void initializeComponents() {
        JPanel bottom = new JPanel();
        ColourPanel colourPanel = new ColourPanel();
        currentColour.addPropertyChangeListener(colourPanel);

        this.add(createColourSelectionPanel(Color.RED));
        this.add(createColourSelectionPanel(Color.GREEN));
        this.add(createColourSelectionPanel(Color.BLUE));

        JPanel radioButtons = new JPanel();
//        redRadioButton = createRadioButton("red", Color.RED);
//        radioButtons.add(redRadioButton);

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
        currentColour.addPropertyChangeListener(scrollBar);
        currentColour.addPropertyChangeListener(textField);
        currentColour.addPropertyChangeListener(label);
        return panel;
    }

//    public JRadioButton createRadioButton(String name, final Color colour) {
//        final JRadioButton radioButton = new JRadioButton(name);
//        radioButton.addActionListener(new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                radioButton.firePropertyChange("colour", 0, colour.getRGB());
//            }
//        });
//        return radioButton;
//    }

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

