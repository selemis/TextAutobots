package textautobots.bots

import spock.lang.Specification
import spock.lang.Unroll
import textautobots.bots.SnakeCase

class SnakeCaseSpec extends Specification {

    SnakeCase snakeCase

    def setup() {
        snakeCase = new SnakeCase()
    }

    def "has a name"() {
        expect:
        snakeCase.name() == "Snake Case"
    }

    //TODO handle empty string and null
    @Unroll
    def "to snake case"() {
        expect:
        snakeCase.transform(input) == output

        where:
        input            || output
        "hello world"    || "hello_world"
        "hello  world"   || "hello_world"
        "Γεια σου κόσμε" || "γεια_σου_κόσμε"

    }


}
