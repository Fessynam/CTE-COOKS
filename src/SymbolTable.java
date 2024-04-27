import java.util.ArrayList;

public class SymbolTable {
    private static ArrayList<String> symbolTable = new ArrayList<>();

    public static void addIdentifier(String identifier) {
        symbolTable.add(identifier);
    }

    public static boolean containsIdentifier(String identifier) {
        return symbolTable.contains(identifier);
    }
}
