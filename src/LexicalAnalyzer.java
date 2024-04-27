import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexicalAnalyzer {
    // Define regular expressions for tokens
    private static final String KEYWORDS_REGEX = "\\b(INTEGER|ASSIGN|READ|WRITE|START|STOP)\\b";
    private static final String IDENTIFIER_REGEX = "\\b[a-zA-Z]\\b";
    private static final String OPERATORS_REGEX = "[+\\-/*]";
    private static final String SYMBOLS_REGEX = "[=,;]";
    private static final String LITERALS_REGEX = "\\b\\d+\\b";

    // Token types
    public enum TokenType {
        KEYWORD, IDENTIFIER, OPERATOR, SYMBOL, LITERAL, UNKNOWN
    }

    // Token class
    public static class Token {
        private final TokenType type;
        private final String value;

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

        @Override
        public String toString() {
            return "(" + type + ", " + value + ")";
        }
    }

    // Method to tokenize input program
    public static ArrayList<Token> tokenize(String program) {
        ArrayList<Token> tokens = new ArrayList<>();
        String regex = String.join("|", KEYWORDS_REGEX, IDENTIFIER_REGEX, OPERATORS_REGEX, SYMBOLS_REGEX, LITERALS_REGEX);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(program);

        while (matcher.find()) {
            String match = matcher.group().trim();
            TokenType type;
            if (match.matches(KEYWORDS_REGEX)) {
                type = TokenType.KEYWORD;
            } else if (match.matches(IDENTIFIER_REGEX)) {
                type = TokenType.IDENTIFIER;
            } else if (match.matches(OPERATORS_REGEX)) {
                type = TokenType.OPERATOR;
            } else if (match.matches(SYMBOLS_REGEX)) {
                type = TokenType.SYMBOL;
            } else if (match.matches(LITERALS_REGEX)) {
                type = TokenType.LITERAL;
            } else {
                type = TokenType.UNKNOWN;
            }
            tokens.add(new Token(type, match));
        }

        return tokens;
    }

    // Main method for testing
    public static void main(String[] args) {
        String program = "START\nINTEGER M, N, K, P, R, H, |, g, k, m\nREAD M, N, K\nASSIGN N = M -/ K\nREAD g, H, |, m\nP = g/H-l+m*N/k\nR= M+N/K\nWRITE P\nSTOP";
        ArrayList<Token> tokens = tokenize(program);
        for (Token token : tokens) {
            System.out.println(token);
        }
    }
}
