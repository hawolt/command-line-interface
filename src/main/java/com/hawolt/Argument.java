package com.hawolt;

public class Argument {

    private String shortName, longName, description, value;
    private boolean mandatory, unique, flag;

    public static Argument create(String shortName, String longName, String description, boolean mandatory, boolean unique, boolean flag) {
        return new Argument(shortName, longName, description, mandatory, unique, flag);
    }

    private Argument(String shortName, String longName, String description, boolean mandatory, boolean unique, boolean flag) {
        this.shortName = shortName;
        this.longName = longName;
        this.description = description;
        this.mandatory = mandatory;
        this.unique = unique;
        this.flag = flag;
    }

    public String getShortName() {
        return shortName;
    }

    public String getLongName() {
        return longName;
    }

    public String getDescription() {
        return description;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public boolean isUnique() {
        return unique;
    }

    public boolean isFlag() {
        return flag;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        int strings = shortName.hashCode() + longName.hashCode() + description.hashCode() + (value != null ? value.hashCode() : 0);
        int booleans = (mandatory ? 31 : 1) * (unique ? 37 : 1) * (flag ? 41 : 1);
        return 7 * (strings + booleans);
    }
}
