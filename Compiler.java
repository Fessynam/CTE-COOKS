import compiler.frontend.lexical.Lexer;
import compiler.frontend.semantic.ErrorChecker;
import compiler.frontend.semantic.SemanticAnalyzer;
import compiler.intermediate.IntermediateCode;
import compiler.intermediate.IntermediateCodeGenerator;
import compiler.backend.optimization.Optimizer;
import compiler.backend.codegen.MachineCodeGenerator;

public class Compiler {

    public static void main(String[] args) {
        String program = """
                START
                INTEGER M, N, K, P, R, H, |, g, k, m
                READ M N K
                ASSIGN N = M - K
                READ g, H, |, m
                P = g/H-l+m+N/k
                R= M+N/K
                WRITE P
                STOP
                """;

        Lexer.tokenize(program);

        if (!Lexer.errors) {
            Lexer.test(program);
            if (!compiler.frontend.syntax.ErrorChecker.errors(Lexer.getTokenTypes(program))) {
                if (!compiler.frontend.semantic.ErrorChecker.checkSemanticErrors(program)) {
                    SemanticAnalyzer.analyze(program);

                    // Generate intermediate code
                    IntermediateCode code = IntermediateCodeGenerator
                            .generateIntermediateCode(Lexer.getTokenTypes(program), Lexer.getTokenValues(program));

                    // Print intermediate code before optimization
                    System.out.println("Intermediate Code (Before Optimization):");
                    code.printCode();

                    // Optimize the intermediate code
                    IntermediateCode optimizedCode = Optimizer.optimize(code);

                    // Print optimized intermediate code
                    System.out.println("\nOptimized Intermediate Code:");
                    optimizedCode.printCode();

                    // Generate machine code from the optimized intermediate code
                    String machineCode = MachineCodeGenerator.generateMachineCode(optimizedCode);

                    // Print the generated machine code
                    System.out.println("\nMachine Code:");
                    System.out.println(machineCode);
                }
            }
        }
    }
}
