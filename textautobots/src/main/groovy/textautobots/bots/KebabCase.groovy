package textautobots.bots

class KebabCase extends TextAutobot{
    @Override
    String name() {
        return "Kebab Case"
    }

    @Override
    String transform(String input) {
        input.toLowerCase().trim().replaceAll(" +", " ")
        .replaceAll(" ", "-")
        .replaceAll("\\.", "-")
        .replaceAll("_", "-")
    }

}
