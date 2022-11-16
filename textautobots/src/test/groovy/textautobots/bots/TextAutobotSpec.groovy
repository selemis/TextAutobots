package textautobots.bots

import textautobots.bots.TextAutobot

class TextAutobotSpec extends spock.lang.Specification {

    def "they are equal if they have the same name - case sensitive"() {
        given:
        TextAutobot bot1 = bot1()
        TextAutobot bot2 = bot2()
        TextAutobot bot3 = bot3()

        expect:
        bot1 == bot2
        bot1 != bot3
        bot2 != bot3

    }

    private TextAutobot bot1() {
        new TextAutobot() {
            @Override
            String name() {
                return "bot"
            }

            @Override
            String transform(String input) {
                return null
            }
        }
    }

    private TextAutobot bot2() {
        new TextAutobot() {
            @Override
            String name() {
                return "bot"
            }

            @Override
            String transform(String input) {
                return null
            }
        }
    }

    private TextAutobot bot3() {
        new TextAutobot() {
            @Override
            String name() {
                return "Bot"
            }

            @Override
            String transform(String input) {
                return null
            }
        }
    }

}





