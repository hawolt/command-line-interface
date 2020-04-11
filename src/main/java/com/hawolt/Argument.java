package com.hawolt;

/**
 * Created by: Niklas
 * Date: 25.03.2020
 * Time: 19:53
 */
public class Argument {
    private String description, max, min;
    private boolean required, argument, once, untilNext;
    private int total;

    private String[] values;

    public Argument(String max, String min, String description, boolean required, boolean argument) {
        this(max, min, description, required, true, false, argument, argument ? 1 : 0);
    }

    public Argument(String max, String min, String description, boolean required, int total) {
        this(max, min, description, required, true, false, true, total);
    }

    public Argument(String max, String min, String description, boolean required, boolean once, boolean untilNext) {
        this(max, min, description, required, once, untilNext, true, untilNext ? -1 : 1);
    }

    private Argument(String max, String min, String description, boolean required, boolean once, boolean untilNext, boolean argument, int total) {
        this.description = description;
        this.required = required;
        this.argument = argument;
        this.untilNext = untilNext;
        this.total = total;
        this.once = once;
        this.max = max;
        this.min = min;
    }

    @Override
    public String toString() {
        return String.format("%s[%s]", max, values == null ? "" : String.join(";", values));
    }

    public String getDescription() {
        return description;
    }

    public boolean isArgument() {
        return argument;
    }

    public boolean isUntilNext() {
        return untilNext;
    }

    public boolean isRequired() {
        return required;
    }

    public String getMax() {
        return max;
    }

    public String getMin() {
        return min;
    }

    public boolean isOnce() {
        return once;
    }

    public int getTotal() {
        return total;
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    public String getValue(int i) {
        return values[i];
    }

    public String getValue() {
        return values[0];
    }

    @Override
    public int hashCode() {
        int strings = description.hashCode() + max.hashCode() + min.hashCode();
        if (values != null) for (String string : values) strings += string.hashCode();
        int booleans = (required ? 31 : 1) * (argument ? 37 : 1) * (once ? 41 : 1);
        return 7 * (strings + booleans + total);
    }
}
