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

    java.util.List<TextAutobot> textAutobots;
    JTextArea sourceTextArea
    JTextArea resultsTextArea
    SettingsProperties settingsProperties = new SettingsProperties();


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
        int width = Integer.valueOf(settingsProperties.getProperty("mainWindowWidth", "800"))
        setSize(width, 600)
        setVisible(true)
    }

    public void font(String fontName, int fontSize) {
        Font font = new Font(fontName, Font.PLAIN, fontSize);
        sourceTextArea.setFont(font);
        resultsTextArea.setFont(font);
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
        JLabel label = new JLabel("Source Text")
        label.setFont(new Font("Serif", Font.BOLD, 28))
        label.setHorizontalAlignment(SwingConstants.CENTER)
        label.setForeground(Color.BLUE)
        sourceTextArea = new JTextArea(5, 80)
        panel.add(label, BorderLayout.NORTH)
        panel.add(sourceTextArea, BorderLayout.CENTER)
        panel
    }

    private JPanel resultsTextAreaPanel() {
        JPanel panel = new JPanel()
        panel.setLayout(new BorderLayout())
        panel.setBorder(new EmptyBorder(10, 10, 10, 10))
        JLabel label = new JLabel("Result Text")
        label.setFont(new Font("Serif", Font.BOLD, 28))
        label.setHorizontalAlignment(SwingConstants.CENTER)
        label.setForeground(Color.RED)
        resultsTextArea = new JTextArea(5, 80)
        panel.add(label, BorderLayout.NORTH)
        panel.add(resultsTextArea, BorderLayout.CENTER)
        panel
    }

    private JPanel createFilterListPanel() {
        JPanel panel = new JPanel()
        panel.setBorder(new EmptyBorder(10, 10, 10, 10))
        panel.setLayout(new BorderLayout())
        FilteredJList filteredList = createFilteredJList()
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
        JButton fontButton = new JButton("Settings");
        fontButton.addActionListener(e -> new SettingsFrame(this, settingsProperties, sourceTextArea, resultsTextArea));
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
        String fontName = settingsProperties.getProperty("fontName", "Serif");
        int fontSize = Integer.parseInt(settingsProperties.getProperty("fontSize", "14"));
        font(fontName, fontSize)
    }


}
