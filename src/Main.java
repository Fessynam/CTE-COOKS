import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Call methods of other classes here
        // For example:

        // Step 1: Lexical Analysis
        String program = "YOUR_V_LANGUAGE_PROGRAM_HERE";
        ArrayList<LexicalAnalyzer.Token> tokens = LexicalAnalyzer.tokenize(program);

        // Step 2: Syntax Analysis
        Parser.parse(tokens);

        // Step 3: Semantic Analysis
        SemanticAnalyzer.analyze(tokens);

        // Step 4: Code Generation
        CodeGenerator.generate(tokens);

        // Step 5: Code Optimization
        String generatedCode = "YOUR_GENERATED_CODE_HERE";
        String optimizedCode = CodeOptimizer.optimize(generatedCode);

        // Step 6: Output or execute the optimized code
        System.out.println("Optimized code:");
        System.out.println(optimizedCode);

        // Or, if you want to execute the code, you can do that here
        // For example, if it's Java bytecode:
        // executeBytecode(optimizedCode);
    }
}
