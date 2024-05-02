package compiler.frontend.lexical;

import java.util.List;

public class Token {
  private TokenType type;
  private String value;

  public Token(TokenType type, String value) {
    this.type = type;
    this.value = value;
  }

  public TokenType getType() {
    return type;
  }

  public String getValue() {
    return value;
  }

  public List<String> getTypeValue() {
    return List.of(type.toString(), value);
  }
}

enum TokenType {
  KEYWORD,
  IDENTIFIER,
  OPERATOR,
  SYMBOL,
  MISSPELLING,
  INVALID,
  UNKNOWN,
  IGNORE,
  NUMBER
}