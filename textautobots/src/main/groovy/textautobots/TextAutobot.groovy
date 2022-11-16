package textautobots

abstract class TextAutobot {

    abstract String name();

    abstract String transform(String input);

    @Override
    boolean equals(Object obj) {
        if (obj instanceof TextAutobot)
            if (obj.name() == name())
                return true;

        return false;
    }

    int hashCode() {
        return (name() != null ? name().hashCode() : 0)
    }
}