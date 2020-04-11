package com.hawolt;

import java.util.*;

/**
 * Created by: Niklas
 * Date: 25.03.2020
 * Time: 19:51
 */
public class BaseParser extends HashSet<Argument> {

    public void put(Argument arg) throws ParserException {
        for (Argument argument : this) {
            if (arg.isOnce() && (argument.getMin().equals(arg.getMin()) || argument.getMax().equals(arg.getMax()))) {
                throw new ParserException("Argument already defined");
            }
        }
        add(arg);
    }

    public boolean has(String key) {
        for (Argument argument : this) {
            if (argument.getMax().equals(key) || argument.getMin().equals(key)) return true;
        }
        return false;
    }

    public Argument get(String key) {
        for (Argument argument : this) {
            if (argument.getMax().equals(key) || argument.getMin().equals(key)) return argument;
        }
        return null;
    }

    protected boolean isArgument(String s) {
        return s.startsWith("--") || s.startsWith("-");
    }

    public void print() {
        System.out.format("%-16s | %-9s | %-9s | %-9s | %-5s | %-5s | %s\n", "Command", "Shortcut", "Required", "Argument", "Once", "Args", "Description");
        for (Argument argument : this) {
            System.out.format("%-16s | %-9s | %-9s | %-9s | %-5s | %-5s | %s\n", argument.getMax(), argument.getMin(), argument.isRequired(), argument.isArgument(), argument.isOnce(), argument.getTotal(), argument.getDescription());
        }
    }
}
