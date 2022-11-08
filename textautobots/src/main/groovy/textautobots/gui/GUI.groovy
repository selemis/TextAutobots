package textautobots.gui

import javax.swing.BoxLayout
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextArea
import java.awt.BorderLayout
import java.awt.HeadlessException
import java.awt.Panel

class GUI extends JFrame{

    GUI() throws HeadlessException {
        setDefaultCloseOperation(EXIT_ON_CLOSE)
        setTitle("Text Autobots")
        getContentPane().setLayout(new BorderLayout())
        getContentPane().add(textAreasPanel(), BorderLayout.CENTER)
//        getContentPane().add(createFilterListPanel(), BorderLayout.EAST)
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


}
