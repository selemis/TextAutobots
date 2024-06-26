package textautobots.gui

import textautobots.bots.TextAutobot

import javax.swing.*
import javax.swing.border.EmptyBorder
import java.awt.*
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS

class GUI extends JFrame {

    public static final String DEFAULT_WIDTH = "800"
    public static final String DEFAULT_HEIGHT = "600"
    public static final String DEFAULT_FONT_NAME = "Serif"
    public static final String DEFAULT_FONT_SIZE = "14"

    java.util.List<TextAutobot> textAutobots;
    JTextArea sourceTextArea
    JTextArea resultsTextArea
    SettingsProperties settingsProperties = new SettingsProperties()
    FilteredJList filteredList
    Font font
    JLabel sourceTextLabel
    JLabel resultTextLabel
    JButton fontButton

    GUI(java.util.List<TextAutobot> textAutobots) throws HeadlessException {
        this.textAutobots = textAutobots;
        setDefaultCloseOperation(EXIT_ON_CLOSE)
        setTitle("Text Autobots")
        getContentPane().setLayout(new BorderLayout())
        getContentPane().add(textAreasPanel(), BorderLayout.CENTER)
        getContentPane().add(createFilterListPanel(), BorderLayout.EAST)
        getContentPane().add(createSettingsPanel(), BorderLayout.SOUTH);
        settingsProperties.loadSettingsProperties();
        configureFont()
        int width = Integer.valueOf(settingsProperties.getProperty(SettingsProperties.WINDOW_WIDTH, DEFAULT_WIDTH))
        int height = Integer.valueOf(settingsProperties.getProperty(SettingsProperties.WINDOW_HEIGHT, DEFAULT_HEIGHT))
        windowSize(width, height)
        setVisible(true)
    }

    public void windowSize(int width, int height) {
        setSize(width, height)
    }

    public void font(String fontName, int fontSize) {
        font = new Font(fontName, Font.PLAIN, fontSize);
        Font labelsFont = new Font(font.getFontName(), Font.BOLD, fontSize + 5)
        resultTextLabel.setFont(labelsFont)
        sourceTextLabel.setFont(labelsFont)
        fontButton.setFont(font)
        filteredList.setFont(font)
        sourceTextArea.setFont(font)
        resultsTextArea.setFont(font)
    }

    private JPanel textAreasPanel() {
        JPanel panel = new JPanel()
        panel.setBorder(new EmptyBorder(10, 10, 10, 10))
        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS)
        panel.setLayout(boxLayout)
        panel.add(sourceTextAreaPanel())
        panel.add(resultsTextAreaPanel())
        panel
    }

    private JPanel sourceTextAreaPanel() {
        JPanel panel = new JPanel()
        panel.setBorder(new EmptyBorder(10, 10, 10, 10))
        panel.setLayout(new BorderLayout())
        sourceTextLabel = new JLabel("Source Text")
        sourceTextLabel.setHorizontalAlignment(SwingConstants.CENTER)
        sourceTextLabel.setForeground(Color.BLUE)
        sourceTextArea = new JTextArea(5, 80)
        panel.add(sourceTextLabel, BorderLayout.NORTH)
        panel.add(sourceTextArea, BorderLayout.CENTER)
        panel
    }

    private JPanel resultsTextAreaPanel() {
        JPanel panel = new JPanel()
        panel.setLayout(new BorderLayout())
        panel.setBorder(new EmptyBorder(10, 10, 10, 10))
        resultTextLabel = new JLabel("Result Text")
        resultTextLabel.setHorizontalAlignment(SwingConstants.CENTER)
        resultTextLabel.setForeground(Color.RED)
        resultsTextArea = new JTextArea(5, 80)
        panel.add(resultTextLabel, BorderLayout.NORTH)
        panel.add(resultsTextArea, BorderLayout.CENTER)
        panel
    }

    private JPanel createFilterListPanel() {
        JPanel panel = new JPanel()
        panel.setBorder(new EmptyBorder(10, 10, 10, 10))
        panel.setLayout(new BorderLayout())
        filteredList = createFilteredJList()
        filteredList.addMouseListener(new MouseAdapter() {
            @Override
            void mouseClicked(MouseEvent e) {
                transformText(e)
            }
        })
        JScrollPane pane = new JScrollPane(filteredList, VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(pane, BorderLayout.CENTER)
        panel.add(filteredList.getFilterField(), BorderLayout.NORTH)
        return panel
    }

    private JPanel createSettingsPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        fontButton = new JButton("Settings");
        fontButton.addActionListener(e -> new SettingsFrame(this, settingsProperties));
        panel.add(fontButton);
        return panel;
    }

    private void transformText(MouseEvent e) {
        JList<String> source = (JList<String>) e.getSource()
        if (e.getClickCount() == 2) {
            int index = source.locationToIndex(e.getPoint());
            ListModel<String> model = source.getModel()
            String elementAt = model.getElementAt(index)
            TextAutobot bot = textAutobots.find { it.name() == elementAt }
            String result = bot.transform(sourceTextArea.getText())
            resultsTextArea.setText(result);
        }
    }

    private FilteredJList createFilteredJList() {
        FilteredJList list = new FilteredJList();
        textAutobots
                .collect({ it.name() })
                .each { list.addItem(it) }

        return list;
    }

    private void configureFont() {
        String fontName = settingsProperties.getProperty(SettingsProperties.FONT_NAME, DEFAULT_FONT_NAME);
        int fontSize = Integer.parseInt(settingsProperties.getProperty(SettingsProperties.FONT_SIZE, DEFAULT_FONT_SIZE));
        font(fontName, fontSize)
    }

}
