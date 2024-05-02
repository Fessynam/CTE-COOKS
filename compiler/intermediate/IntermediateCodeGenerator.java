package compiler.intermediate;


public class IntermediateCodeGenerator {
    public static IntermediateCode generateIntermediateCode(String[] types, String[] values) {
        IntermediateCode code = new IntermediateCode();

        // Generate intermediate code based on types and values
        for (int i = 0; i < types.length; i++) {
            String type = types[i];
            String value = values[i];

            if (type.equals("KEYWORD")) {
                if (value.equals("START")) {
                    code.addInstruction("START", "", "", "", "");
                } else if (value.equals("READ")) {
                    code.addInstruction("READ", values[i + 1], "", "", "");
                } else if (value.equals("WRITE")) {
                    code.addInstruction("WRITE", values[i + 1], "", "", "");
                } else if (value.equals("STOP")) {
                    code.addInstruction("STOP", "", "", "", "");
                }
            } else if (type.equals("IDENTIFIER")) {
                if (i + 1 < types.length && types[i + 1].equals("OPERATOR") && values[i + 1].equals("=")) {
                    String temp = "temp" + code.getTempCountAndIncrement();
                    code.addInstruction("ASSIGN", values[i + 2], values[i + 1], "", temp);
                    i += 2;
                }
            } else if (type.equals("OPERATOR")) {
                if (value.equals("+") || value.equals("-") || value.equals("*") || value.equals("/")) {
                    String temp = "temp" + code.getTempCountAndIncrement();
                    code.addInstruction("ASSIGN", values[i - 1], value, values[i + 1], temp);
                    values[i] = temp;
                }
            }
        }

        return code;
    }
}