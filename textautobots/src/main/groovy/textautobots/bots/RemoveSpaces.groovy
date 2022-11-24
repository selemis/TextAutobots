package textautobots.bots

class RemoveSpaces extends TextAutobot{

    @Override
    String name() {
        'Remove Spaces'
    }

    @Override
    String transform(String input) {
        input.replaceAll("\\s+", "")
    }
}
