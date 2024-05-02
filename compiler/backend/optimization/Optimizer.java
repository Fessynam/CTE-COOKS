package compiler.backend.optimization;

import compiler.intermediate.IntermediateCode;
import compiler.intermediate.IntermediateInstruction;

import java.util.*;

public class Optimizer {
    public static IntermediateCode optimize(IntermediateCode code) {
        IntermediateCode optimizedCode = new IntermediateCode();
        List<IntermediateInstruction> instructions = code.getInstructions();
        Set<String> usedVariables = new HashSet<>();
        Set<String> assignedVariables = new HashSet<>();
        Map<String, String> variableMap = new HashMap<>();

        for (int i = instructions.size() - 1; i >= 0; i--) {
            IntermediateInstruction instruction = instructions.get(i);

            if (instruction == null) continue; // Skip null instructions

            String type = instruction.getType();

            if ("ASSIGN".equals(type)) {
                String result = instruction.getResult();
                String operand1 = instruction.getOperand1();
                String operand2 = instruction.getOperand2();

                if (result == null || operand1 == null || operand2 == null) continue; // Skip null values

                if (usedVariables.contains(result) || (assignedVariables.contains(result) && operand1.equals(operand2))) {
                    instructions.remove(i); // Remove redundant assignment instruction
                    continue;
                }

                if (operand1.startsWith("temp") && variableMap.containsKey(operand1)) {
                    operand1 = variableMap.get(operand1);
                }
                if (operand2.startsWith("temp") && variableMap.containsKey(operand2)) {
                    operand2 = variableMap.get(operand2);
                }

                variableMap.put(result, operand1 + " " + instruction.getOperator() + " " + operand2);
                assignedVariables.add(result);
            } else if ("READ".equals(type) || "WRITE".equals(type)) {
                usedVariables.add(instruction.getOperand1());
            }

            optimizedCode.addInstruction(instruction);
        }

        for (IntermediateInstruction instruction : optimizedCode.getInstructions()) {
            if ("ASSIGN".equals(instruction.getType()) && variableMap.containsKey(instruction.getResult())) {
                String optimizedAssignment = variableMap.get(instruction.getResult());
                instruction.setOperand1(optimizedAssignment.split(" ")[0]);
                instruction.setOperator(optimizedAssignment.split(" ")[1]);
                instruction.setOperand2(optimizedAssignment.split(" ")[2]);
            }
        }

        return optimizedCode;
    }
}
