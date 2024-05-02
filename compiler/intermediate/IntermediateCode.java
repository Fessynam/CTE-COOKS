package compiler.intermediate;

import java.util.ArrayList;
import java.util.List;

public class IntermediateCode {
    private List<IntermediateInstruction> instructions;
    private int tempCount;

    public IntermediateCode() {
        this.instructions = new ArrayList<>();
        this.tempCount = 0;
    }

    public void addInstruction(String type, String operand1, String operator, String operand2, String result) {
        instructions.add(new IntermediateInstruction(type, operand1, operator, operand2, result));
    }

    public void printCode() {
        for (IntermediateInstruction instruction : instructions) {
            System.out.println(instruction);
        }
    }
    public List<IntermediateInstruction> getInstructions() {
        return instructions;
    }

    public void addInstruction(IntermediateInstruction instruction) {
        instructions.add(instruction);
    }
    // public IntermediateCode optimize() {
    //     IntermediateCode optimizedCode = new IntermediateCode();
    //     for (IntermediateInstruction instruction : instructions) {
    //         optimizedCode.addInstruction(instruction.optimizeConstantFolding());
    //     }
    //     return optimizedCode;
    // }

    public int getTempCountAndIncrement() {
        return tempCount++;
    }
}