package textautobots

import org.apache.commons.text.CaseUtils

class CamelCase {

    String name() {
        "Camel Case"
    }

    String transform(String input) {
        if (input == null)
            return ""

        CaseUtils.toCamelCase(
                input,
                false,
                (char) ' ',
                (char) '_',
                (char) '.'
        )
    }
}
