package hexlet.code;

import lombok.Getter;

@Getter
public class DiffElement {
    public static final int DIFFERENCE_TYPE_ADDED = 0;
    public static final int DIFFERENCE_TYPE_DELETED = 1;
    public static final int DIFFERENCE_TYPE_CHANGED = 2;
    public static final int DIFFERENCE_TYPE_UNCHANGED = 3;

    private String key;
    private Object value1;
    private Object value2;
    private Integer differenceType;

    DiffElement(String k, Object val1, Object val2, Integer diffType) {
        key = k;
        value1 = val1;
        value2 = val2;

        if ((diffType != DIFFERENCE_TYPE_ADDED) &&
                (diffType != DIFFERENCE_TYPE_DELETED) &&
                (diffType != DIFFERENCE_TYPE_CHANGED) &&
                (diffType != DIFFERENCE_TYPE_UNCHANGED)) {
            throw new IllegalArgumentException("Invalid value of differenceType = " + diffType);
        }
        differenceType = diffType;
    }

    @Override
    public String toString() {
        return "<" + key + ", " + (value1 == null ? "null" : value1.toString()) + ", " + (value2 == null ? "null" : value2.toString()) + ", " + differenceType + ">";
    }
}
