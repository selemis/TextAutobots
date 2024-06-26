package textautobots.gui

import javax.swing.*
import java.awt.*

class SettingsFrame extends JFrame{

    SettingsProperties settingsProperties
    GUI mainFrame
    JComboBox<String> fontTypeComboBox
    JComboBox<Integer> fontSizeComboBox
    JTextField mainWindowWidthField
    JTextField mainWindowHeightField
    JButton applyButton

    SettingsFrame(GUI mainFrame, SettingsProperties settingsProperties) {
        this.settingsProperties = settingsProperties
        this.mainFrame = mainFrame
        setTitle("Settings");
        setSize(300, 200);
        setLocationRelativeTo(mainFrame);
        setLayout(new BorderLayout(10, 10));

        createFontComboBox()
        createFontSizeComboBox()
        createMainWindowWidthTextField()
        createMainWindowHeightTextField()
        createApplyButton()
        add(createMainPanel(), BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(createRow(createFontLabel(), fontTypeComboBox));
        mainPanel.add(createRow(createFontSizeLabel(), fontSizeComboBox));
        mainPanel.add(createRow(createMainWindowWidthLabel(), mainWindowWidthField));
        mainPanel.add(createRow(createMainWindowHeightLabel(), mainWindowHeightField))
        mainPanel.add(applyButton);
        mainPanel
    }

    private JButton createApplyButton() {
        applyButton = new JButton("Apply Font");
        applyButton.addActionListener(e -> {
            String fontName = (String) fontTypeComboBox.getSelectedItem();
            int fontSize = (Integer) fontSizeComboBox.getSelectedItem();
            applyFont(fontName, fontSize)

            try {
                applyMainWindowSize();
                settingsProperties.setProperty("fontName", fontName);
                settingsProperties.setProperty("fontSize", String.valueOf(fontSize));
                settingsProperties.setProperty("windowWidth", mainWindowWidthField.getText());
                settingsProperties.setProperty("windowHeight", mainWindowHeightField.getText());
                settingsProperties.saveSettingsProperties();
                dispose(); // Close the settings frame
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number for width.", "Invalid Width", JOptionPane.ERROR_MESSAGE);
            }
        });
        applyButton
    }

    private applyFont(String fontName, int fontSize) {
        mainFrame.font(fontName, fontSize)
    }

    private applyMainWindowSize() {
        int width = Integer.parseInt(mainWindowWidthField.getText());
        int height = Integer.parseInt(mainWindowHeightField.getText());
        mainFrame.windowSize(width, height)
    }

    private void createMainWindowWidthTextField() {
        mainWindowWidthField = new JTextField(5);
        mainWindowWidthField.setText(settingsProperties.getProperty("windowWidth", "800"));
    }

    private void createMainWindowHeightTextField(){
        mainWindowHeightField = new JTextField(5);
        mainWindowHeightField.setText(settingsProperties.getProperty("windowHeight", "600"));
    }

    private JLabel createMainWindowWidthLabel() {
        new JLabel("Main Window Width:")
    }

    private JLabel createMainWindowHeightLabel()  {
        new JLabel("Main Window Height:")
    }

    private JLabel createFontSizeLabel() {
        new JLabel("Size:")
    }

    private void createFontComboBox() {
        fontTypeComboBox = new JComboBox<>(new String[]{"Serif", "SansSerif", "Monospaced"});
        fontTypeComboBox.setSelectedItem(settingsProperties.getProperty("fontName", "Serif"));
    }

    private void createFontSizeComboBox() {
        fontSizeComboBox = new JComboBox<>(new Integer[]{12, 14, 16, 18, 20, 22, 24, 26, 28, 30});
        fontSizeComboBox.setSelectedItem(Integer.valueOf(settingsProperties.getProperty("fontSize", "14")));
    }

    private JLabel createFontLabel() {
        new JLabel("Font:");
    }

    private JPanel createRow(JLabel label, JComponent field) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        row.add(label);
        row.add(field);
        return row;
    }

}
