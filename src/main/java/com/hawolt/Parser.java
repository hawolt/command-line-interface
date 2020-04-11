package com.hawolt;

import java.util.Arrays;

/**
 * Created by: Niklas
 * Date: 27.03.2020
 * Time: 10:44
 */
public class Parser extends BaseParser {

    public BaseParser check(String[] args) throws ParserException {
        BaseParser result = new BaseParser();
        for (Argument argument : this) {
            boolean found = false;
            for (int i = 0; i < args.length; i++) {
                String current = args[i];
                if (isArgument(current)) {
                    int index = current.lastIndexOf("-") + 1;
                    String raw = current.substring(index);
                    if ((argument.getMin().equals(raw) && index == 1) || (argument.getMax().equals(raw) && index == 2)) {
                        found = true;
                        if (argument.isArgument()) {
                            if (argument.isUntilNext()) {
                                String[] values = new String[0];
                                boolean valid;
                                do {
                                    if (i + 1 >= args.length) break;
                                    String next = args[++i];
                                    if (valid = !isArgument(next)) {
                                        values = Arrays.copyOf(values, values.length + 1);
                                        values[values.length - 1] = next;
                                    }
                                } while (valid);
                                argument.setValues(values);
                                --i;
                            } else {
                                int next = i + argument.getTotal();
                                if (next < args.length) {
                                    String[] values = new String[argument.getTotal()];
                                    for (int j = 0; j < values.length; j++) {
                                        String additional = args[++i];
                                        if (isArgument(additional)) {
                                            throw new ParserException(String.format("Could not find value for argument %s", argument.getMax()));
                                        } else {
                                            values[j] = additional;
                                        }
                                    }
                                    argument.setValues(values);
                                } else {
                                    throw new ParserException(String.format("Could not find value for argument %s", argument.getMax()));
                                }
                            }
                        }
                        result.put(argument);
                    }
                }
            }
            if (!found && argument.isRequired()) {
                throw new ParserException(String.format("Could not find required argument %s", argument.getMax()));
            }
        }
        return result;
    }
}
