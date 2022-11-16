package textautobots

class SnakeCase extends TextAutobot {
    @Override
    String name() {
        return "Snake Case"
    }

    @Override
    String transform(String input) {
        String camelCase = new CamelCase().transform(input)
        camelToSnake(camelCase)
    }

    private String camelToSnake(String str) {
        String result = ""
        char c = str.charAt(0)
        result += Character.toLowerCase(c)

        for (int i = 1; i < str.length(); i++) {
            char ch = str.charAt(i)
            if (Character.isUpperCase(ch)) {
                result +=  '_' + Character.toLowerCase(ch)
            }
            else {
                result += ch
            }
        }

        return result
    }

}
