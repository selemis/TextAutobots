package textautobots.gui

import javax.swing.JFrame
import java.awt.BorderLayout
import java.awt.HeadlessException

class GUI extends JFrame{

    GUI() throws HeadlessException {
        setDefaultCloseOperation(EXIT_ON_CLOSE)
        setTitle("Text Autobots")
        getContentPane().setLayout(new BorderLayout())
//        getContentPane().add(mainPanel(), BorderLayout.CENTER)
//        getContentPane().add(createFilterListPanel(), BorderLayout.EAST)
        setSize(800, 600)
        setVisible(true)
    }

}
