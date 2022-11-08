package textautobots.gui

import javax.swing.*
import java.awt.*
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS

class GUI extends JFrame {

    GUI() throws HeadlessException {
        setDefaultCloseOperation(EXIT_ON_CLOSE)
        setTitle("Text Autobots")
        getContentPane().setLayout(new BorderLayout())
        getContentPane().add(textAreasPanel(), BorderLayout.CENTER)
        getContentPane().add(createFilterListPanel(), BorderLayout.EAST)
        setSize(800, 600)
        setVisible(true)
    }

    private JPanel textAreasPanel() {
        JPanel panel = new JPanel()
        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxLayout)
        panel.add(sourceTextAreaPanel())
        panel.add(resultsTextAreaPanel())
        panel
    }

    private JPanel sourceTextAreaPanel() {
        JPanel panel = new JPanel()
        panel.setLayout(new BorderLayout())
        JLabel label = new JLabel("Source Text")
        JTextArea textArea = new JTextArea(5, 80)
        panel.add(label, BorderLayout.NORTH)
        panel.add(textArea, BorderLayout.CENTER)
        panel
    }

    private JPanel resultsTextAreaPanel() {
        JPanel panel = new JPanel()
        panel.setLayout(new BorderLayout())
        JLabel label = new JLabel("Results")
        JTextArea textArea = new JTextArea(5, 80)
        panel.add(label, BorderLayout.NORTH)
        panel.add(textArea, BorderLayout.CENTER)
        panel
    }

    private JPanel createFilterListPanel() {
        JPanel panel = new JPanel()
        panel.setLayout(new BorderLayout())
        FilteredJList filteredList = createFilteredJList()
        filteredList.addMouseListener(new MouseAdapter() {
            @Override
            void mouseClicked(MouseEvent e) {
                JList<String> source = (JList<String>) e.getSource()
                if (e.getClickCount() == 2) {
                    int index = source.locationToIndex(e.getPoint());
                    ListModel<String> model = source.getModel()
                    String elementAt = model.getElementAt(index)
                    System.out.println(elementAt)
//                    doSomething(index)
                }

            }
        })
        JScrollPane pane = new JScrollPane(filteredList, VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(pane, BorderLayout.CENTER)
        panel.add(filteredList.getFilterField(), BorderLayout.NORTH);
        return panel
    }

    private FilteredJList createFilteredJList() {
        def listItems = [
            "Chris" , "Joshua" , "Daniel" , "Michael"
        ]

        FilteredJList list = new FilteredJList();
        listItems.each {
            list.addItem(it)
        }


        return list;
    }


}