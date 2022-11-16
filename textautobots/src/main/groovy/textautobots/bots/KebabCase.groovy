package textautobots.bots

class KebabCase extends TextAutobot{
    @Override
    String name() {
        return "Kebab Case"
    }

    @Override
    String transform(String input) {
        camelToKebab(new CamelCase().transform(input))
    }

    private String camelToKebab(String str) {
        String result = ""
        char c = str.charAt(0)
        result += Character.toLowerCase(c)

        for (int i = 1; i < str.length(); i++) {
            char ch = str.charAt(i)
            if (Character.isUpperCase(ch)) {
                result +=  '-' + Character.toLowerCase(ch)
            }
            else {
                result += ch
            }
        }

        return result
    }
}
