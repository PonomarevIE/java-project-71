package hexlet.code;

import java.util.List;

class Formatter {
    public static String format(List<DiffElement> difference) {
        StringBuilder strBuilder = new StringBuilder();

        strBuilder.append("{\n");

        difference.forEach(d ->
            strBuilder.append(formatDiffElement(d))
        );

        strBuilder.append("}");

        return strBuilder.toString();
    }

    private static String formatDiffElement(DiffElement diffElement) {
        switch (diffElement.getDifferenceType()) {
            case DiffElement.DIFFERENCE_TYPE_ADDED:
                return "  + " + diffElement.getKey() + ": " + diffElement.getValue2() + "\n";
            case DiffElement.DIFFERENCE_TYPE_DELETED:
                return "  - " + diffElement.getKey() + ": " + diffElement.getValue1() + "\n";
            case DiffElement.DIFFERENCE_TYPE_CHANGED:
                return "  - " + diffElement.getKey() + ": " + diffElement.getValue1() + "\n" +
                        "  + " + diffElement.getKey() + ": " + diffElement.getValue2() + "\n";
            case DiffElement.DIFFERENCE_TYPE_UNCHANGED:
                return "    " + diffElement.getKey() + ": " + diffElement.getValue1() + "\n";
            default:
                throw new IllegalStateException("Unexpected value: " + diffElement.getDifferenceType());
        }
    }
}
