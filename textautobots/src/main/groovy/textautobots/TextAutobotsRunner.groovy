package textautobots

import textautobots.bots.CamelCase
import textautobots.bots.KebabCase
import textautobots.bots.SnakeCase
import textautobots.gui.GUI

class TextAutobotsRunner {

    static void main(String[] args) {
        def textAutobots = [
                new CamelCase(),
                new KebabCase(),
                new SnakeCase(),
        ]
        new GUI(textAutobots)
    }
}
