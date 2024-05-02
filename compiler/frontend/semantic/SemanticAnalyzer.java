package compiler.frontend.semantic;

/**
 * SemanticAnalyzer
 */
public class SemanticAnalyzer {
    public static void analyze(String program) {
        boolean errors = ErrorChecker.checkSemanticErrors(program);
        if (!errors) {
            System.err.println("Semantic analysis passed successfully: No Errors\n\n");
        }
    }
}