package textautobots.bots

import spock.lang.Specification
import spock.lang.Unroll

class RemoveSpacesSpec extends Specification {

    RemoveSpaces removeSpaces

    def setup() {
        removeSpaces = new RemoveSpaces()
    }

    def "has a name"() {
        expect:
        removeSpaces.name() == "Remove Spaces"
    }

    @Unroll
    def "remove spaces"() {
        expect:
        removeSpaces.transform(input) == output

        where:
        input         || output
        '123 123'     || '123123'
        ' 123 123   ' || '123123'
        'RF04 9077 3800 0300 0124 0215 6' || 'RF04907738000300012402156'
    }
}
