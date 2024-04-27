import java.util.ArrayList;

public class SemanticAnalyzer {
    // Method to perform semantic analysis
    public static void analyze(ArrayList<String> identifiers) {
        ArrayList<String> symbolTable = new ArrayList<>();

        for (String identifier : identifiers) {
            // Check if identifier already exists in symbol table
            if (symbolTable.contains(identifier)) {
                System.out.println("Semantic error: Identifier '" + identifier + "' already declared");
            } else {
                // Add identifier to symbol table
                symbolTable.add(identifier);
            }
            // Additional semantic checks can be added here
        }
    }
}

