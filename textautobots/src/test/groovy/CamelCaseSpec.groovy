import spock.lang.Specification
import spock.lang.Unroll
import textautobots.CamelCase

class CamelCaseSpec extends Specification {

    CamelCase camelCase

    def setup() {
        camelCase = new CamelCase()
    }

    def "has a name"() {
        expect:
        camelCase.name() == "Camel Case"
    }

    @Unroll
    def "to camel case"() {
        expect:
        camelCase.transform(input) == output

        where:
        input             || output
        "hello world"     || "helloWorld"
        "Rock your World" || "rockYourWorld"
        "hello  world"    || "helloWorld"
        null              || ""
        "hello_world"     || "helloWorld"
        " "               || ""
        "Rock.Your_World" || "rockYourWorld"
    }

}
