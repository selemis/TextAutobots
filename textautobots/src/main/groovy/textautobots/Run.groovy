package textautobots

import textautobots.gui.GUI

class Run {

    static void main(String[] args) {
        def textAutobots = [
                new CamelCase(),
                new KebabCase(),
                new SnakeCase(),
        ]
        new GUI(textAutobots)
    }
}
