package compiler.backend.codegen;

import compiler.intermediate.IntermediateCode;
import compiler.intermediate.IntermediateInstruction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MachineCodeGenerator {
    private static final Map<String, String> instructionMapping;

    static {
        instructionMapping = new HashMap<>();
        instructionMapping.put("START", "1010");
        instructionMapping.put("READ", "1100");
        instructionMapping.put("WRITE", "1110");
        instructionMapping.put("STOP", "0000");
        instructionMapping.put("ASSIGN", "100");
        instructionMapping.put("+", "101");
        instructionMapping.put("-", "110");
        instructionMapping.put("*", "111");
        instructionMapping.put("/", "1000");
    }

    private static final Map<String, String> tempMapping = new HashMap<>();
    private static int tempCount = 0;

    public static String generateMachineCode(IntermediateCode intermediateCode) {
        StringBuilder machineCode = new StringBuilder();

        List<IntermediateInstruction> instructions = intermediateCode.getInstructions();
        for (IntermediateInstruction instruction : instructions) {
            String type = instruction.getType();
            String machineInstruction = instructionMapping.get(type);

            if (machineInstruction != null) {
                switch (type) {
                    case "START":
                    case "READ":
                    case "WRITE":
                    case "STOP":
                        machineCode.append(machineInstruction).append("\n");
                        break;
                    case "ASSIGN":
                    case "+":
                    case "-":
                    case "*":
                    case "/":
                        appendOperand(machineCode, instruction);
                        break;
                }
            }
        }

        return machineCode.toString();
    }

    private static void appendOperand(StringBuilder machineCode, IntermediateInstruction instruction) {
        machineCode.append(instructionMapping.get(instruction.getType())).append(" ");
        machineCode.append(toBinary(instruction.getOperand1())).append(" ");
        machineCode.append(toBinary(instruction.getOperator())).append(" ");
        machineCode.append(toBinary(instruction.getOperand2())).append(" ");
        machineCode.append(getTemp(instruction.getResult())).append("\n");
    }

    private static String toBinary(String input) {
        StringBuilder binaryString = new StringBuilder();
        for (char c : input.toCharArray()) {
            binaryString.append(Integer.toBinaryString(c)).append(" ");
        }
        return binaryString.toString().trim(); // Trim to remove extra space at the end
    }

    private static String getTemp(String temp) {
        if (!tempMapping.containsKey(temp)) {
            tempMapping.put(temp, "temp" + tempCount++);
        }
        return tempMapping.get(temp);
    }
}
