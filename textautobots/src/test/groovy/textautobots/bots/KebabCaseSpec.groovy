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
        input                                           || output
        "hello world"                                   || "hello-world"
        "Rock your World"                               || "rock-your-world"
        "statement 220701 220930"                       || "statement-220701-220930"
        "statement 220701   220930"                     || "statement-220701-220930"
        "series.22.12.06.alex.boom.u.part.2.hello.st"   || "series-22-12-06-alex-boom-u-part-2-hello-st"
        "Bat Un Eaux ToUr 2 (1997) [1080p]"             || "bat-un-eaux-tour-2-(1997)-[1080p]"
    }
}
