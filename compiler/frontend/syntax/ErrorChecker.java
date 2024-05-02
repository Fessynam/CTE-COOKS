package compiler.frontend.syntax;

public class ErrorChecker {
  public static Boolean errors(String[] tokens) {
    boolean syntaxError = false;

    // Error 1: Invalid Keyword at the Beginning (Index 0)
    if (!isStart(tokens[0])) {
      System.err.println("\nSyntax Error: Invalid Keyword: " + tokens[0] + "\nSTART keyword required" + "\nindex: 0");
      syntaxError = true;
    }

    // Error 2: Invalid Keyword at the End (Index tokens.length - 1)
    if (!isEnd(tokens[tokens.length - 1])) {
      System.err.println("\nSyntax Error: Invalid Keyword: " + tokens[tokens.length - 1] + "\nSTOP keyword required"+ "\nindex: " + (tokens.length - 1));
      syntaxError = true;
    }

    // Error 3: Semicolon at the End (Index tokens.length - 2)
    if (isSemicolon(tokens[tokens.length - 2])) {
      System.err.println("\nSyntax Error: Semicolon not allowed at the end of a line: " + tokens[tokens.length - 2]+ "\nindex: " + (tokens.length - 2));
      syntaxError = true;
    }

    if (isStart(tokens[0]) && isEnd(tokens[tokens.length - 1])) {
      for (int i = 1; i < tokens.length - 1; i++) {
        String currentToken = tokens[i];
        String nextToken = tokens[i + 1];

        // Error 4: Combined Operators (Indices 1 to tokens.length - 2)
        if (isCombinedOperator(currentToken, nextToken)) {
          System.err.println("\nSyntax Error: Combined operators not allowed: " + currentToken + " " + nextToken + "\nindex: " + i);
          syntaxError = true;
        }

        // Error 5: Numbers Not Allowed (Indices 1 to tokens.length - 2)
        if (isNumber(currentToken)) {
          System.err.println("\nSyntax Error: Numbers not allowed: " + currentToken  + "\nindex: " + (i-1));
          syntaxError = true;
        }
      }
    }

    if (!syntaxError) {
      System.out.println("\n\nSyntax Analysis passed successfully: No Errors\n");
    }
    return syntaxError;
  }

  private static boolean isStart(String token) {
    return token.equals("KEYWORD");
  }

  private static boolean isEnd(String token) {
    return token.equals("KEYWORD");
  }

  private static boolean isCombinedOperator(String tokenType1, String tokenType2) {
    return tokenType1.equals("OPERATOR") && tokenType2.equals("OPERATOR");
  }

  private static boolean isSemicolon(String tokenType) {
    return tokenType.equals("SYMBOL");
  }

  private static boolean isNumber(String tokenType) {
    return tokenType.equals("NUMBER");
  }

}
