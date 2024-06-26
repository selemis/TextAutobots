package textautobots.gui

import javax.swing.*
import java.awt.*

class SettingsFrame extends JFrame{

    Properties settingsProperties

    SettingsFrame(GUI mainFrame, SettingsProperties settingsProperties, JTextArea sourceTextArea, JTextArea resultsTextArea) {
        this.settingsProperties = settingsProperties
        setTitle("Settings");
        setSize(300, 200);
        setLocationRelativeTo(mainFrame);
        setLayout(new BorderLayout(10, 10));  // Sets the layout manager and the gaps between components

        // Main panel that uses BoxLayout for vertical arrangement
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel fontLabel = new JLabel("Font:");
        JComboBox<String> fontTypeComboBox = new JComboBox<>(new String[]{"Serif", "SansSerif", "Monospaced"});
        fontTypeComboBox.setSelectedItem(settingsProperties.getProperty("fontName", "Serif"));
        JLabel sizeLabel = new JLabel("Size:");
        JComboBox<Integer> fontSizeComboBox = new JComboBox<>(new Integer[]{12, 14, 16, 18, 20, 22, 24, 26, 28, 30});
        fontSizeComboBox.setSelectedItem(Integer.valueOf(settingsProperties.getProperty("fontSize", "14")));
        JLabel mainWindowWidthLabel = new JLabel("Main Window Width:");
        JTextField mainWindowWidthField = new JTextField(5);
        mainWindowWidthField.setText(settingsProperties.getProperty("windowWidth", "800"));

        JButton applyButton = new JButton("Apply Font");
        applyButton.addActionListener(e -> {
            String fontName = (String) fontTypeComboBox.getSelectedItem();
            int fontSize = (Integer) fontSizeComboBox.getSelectedItem();
            mainFrame.font(fontName, fontSize)

            try {
                int width = Integer.parseInt(mainWindowWidthField.getText());
                mainFrame.setSize(width, mainFrame.getHeight());
                settingsProperties.saveSettingsProperties(fontName, fontSize, mainWindowWidthField.getText());
                dispose(); // Close the settings frame
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number for width.", "Invalid Width", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(createRow(fontLabel, fontTypeComboBox));
        panel.add(createRow(sizeLabel, fontSizeComboBox));
        panel.add(createRow(mainWindowWidthLabel, mainWindowWidthField));
        panel.add(applyButton);
        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createRow(JLabel label, JComponent field) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        row.add(label);
        row.add(field);
        return row;
    }

}
