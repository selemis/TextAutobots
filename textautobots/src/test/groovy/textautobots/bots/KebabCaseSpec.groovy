package textautobots.bots

import spock.lang.Specification
import spock.lang.Unroll
import textautobots.bots.KebabCase

class KebabCaseSpec extends Specification {

    KebabCase kebabCase

    def setup() {
        kebabCase = new KebabCase()
    }

    def "has a name"() {
        expect:
        kebabCase.name() == "Kebab Case"
    }


    //TODO handle empty string and null
    @Unroll
    def "to kebab case"() {
        expect:
        kebabCase.transform(input) == output

        where:
        input             || output
        "hello world"     || "hello-world"
        "Rock your World" || "rock-your-world"

    }
}
