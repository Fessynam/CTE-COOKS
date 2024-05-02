package compiler.intermediate;

public class IntermediateInstruction {
    private String type;
    private String operand1;
    private String operator;
    private String operand2;
    private String result;

    public IntermediateInstruction(String type, String operand1, String operator, String operand2, String result) {
        this.type = type;
        this.operand1 = operand1;
        this.operator = operator;
        this.operand2 = operand2;
        this.result = result;
    }

    public String getType() {
        return type;
    }

    public String getOperand1() {
        return operand1;
    }

    public void setOperand1(String operand1) {
        this.operand1 = operand1;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperand2() {
        return operand2;
    }

    public void setOperand2(String operand2) {
        this.operand2 = operand2;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        if ("ASSIGN".equals(type)) {
            return result + " = " + operand1 + " " + operator + " " + operand2;
        } else {
            return type + " " + operand1;
        }
    }
}
