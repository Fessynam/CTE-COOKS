package compiler.frontend.semantic;

import compiler.frontend.lexical.Lexer;

public class ErrorChecker {
    public static boolean checkSemanticErrors(String program) {
        String[] tokenType = Lexer.getTokenTypes(program);
        String[] values = Lexer.getTokenValues(program);
        boolean errors = false;

        // Check for semantic errors related to symbols
        for (int i = 0; i < tokenType.length; i++) {
            String type = tokenType[i];
            String value = values[i];

            if (type.equals("SYMBOL")) {
                if (value.matches("[%$&<>;#]")) {
                    System.out.println("Semantic Error: Symbol '" + value + "' not allowed.\nIndex: " + i);
                    errors = true;
                }
            }
        }

        return errors;
    }
}