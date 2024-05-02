package compiler.frontend.lexical;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Lexer
 */
public class Lexer {

  public static boolean errors = false;
  public static List<Token> tokenize(String input) {
    List<Token> tokens = new ArrayList<>();
    StringTokenizer tokenizer = new StringTokenizer(input, " \n+-*/();,=", true);

    while (tokenizer.hasMoreTokens()) {
      String tokenValue = tokenizer.nextToken().trim();

      if (!tokenValue.isEmpty()) {
        TokenType type = getTokenType(tokenValue);
        if (!type.equals(TokenType.IGNORE)) {
          tokens.add(new Token(type, tokenValue));
        }
      }
    }
    return tokens;
  }

  private static TokenType getTokenType(String tokenValue) {
    if (tokenValue.matches("[A-Z]+") && tokenValue.length() > 1) {
      if (isValidKeyword(tokenValue)) {
        return TokenType.KEYWORD;
      } else {
        System.out.println("Lexical Error: Invalid keyword '" + tokenValue + "'.");
        errors = true;
        return TokenType.MISSPELLING;
      }

    } else if (tokenValue.matches("[1-9]")) {
      return TokenType.NUMBER;
    } else if (tokenValue.matches("[a-zA-Z]")) {
      return TokenType.IDENTIFIER;
    } else if (tokenValue.matches("[+\\-*/|]")) {
      return TokenType.OPERATOR;
    } else if (tokenValue.equals("=") || tokenValue.equals(";")) {
      return TokenType.SYMBOL;
    } else {
      return TokenType.IGNORE;
    }
  }

  public static boolean isValidKeyword(String keyword) {
    return List.of("INTEGER", "ASSIGN", "READ", "WRITE", "START", "STOP").contains(keyword);
  }

  public static void test(String input) {
    System.out.println("Lexical Analysis passed successfully.....\n");
    List<Token> tokens = tokenize(input);
    for (Token token : tokens) {
      System.out.println(token.getTypeValue());
    }
  }

  public static String[] getTokenTypes(String program) {
    List<Token> tokens = Lexer.tokenize(program);
    List<String> tokenTypeList = new ArrayList<>();

    for (Token token : tokens) {
      tokenTypeList.add(token.getType().toString());
    }
    return tokenTypeList.toArray(new String[0]);
  }

  public static String[] getTokenValues(String program) {
    List<Token> tokens = Lexer.tokenize(program);
    List<String> tokenTypeList = new ArrayList<>();

    for (Token token : tokens) {
      tokenTypeList.add(token.getValue().toString());
    }
    return tokenTypeList.toArray(new String[0]);
  }

}
