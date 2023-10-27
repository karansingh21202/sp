import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SrcCode {

    File srcCode;

    SrcCode(String filePath){
        try{
            this.srcCode = new File(filePath);
        }
        catch(Exception e) {
            System.out.println("Failed to read code.");
            System.out.println(e);
        }
    }

    public ArrayList<String> getInstructions(){

        ArrayList<String>instructions = new ArrayList<String>();

        try {
            Scanner sc = new Scanner(srcCode);

            while (sc.hasNext()) {

                String instruction = sc.nextLine().strip();
                if(instruction.equals("")) continue;

                //check for label
                String label = null;
                boolean hasLabel = false;
                if(instruction.contains(":")) {
                    hasLabel = true;

                    String[] labelAndInstruction = instruction.split(":");
                    label = labelAndInstruction[0].strip();
                    instruction = labelAndInstruction[1].strip();

                    while(instruction.equals("")) {
                        if(sc.hasNext()) instruction = sc.nextLine().strip();
                        else throw new Exception("Label does not point to valid instruction");
                    }
                }

                //format instruction properly
                String[] opcodeAndOperands = instruction.split(" ", 2);
                if (opcodeAndOperands.length == 1) {
                    instructions.add(opcodeAndOperands[0]);
                    continue;
                }
                String opcode = opcodeAndOperands[0];
                String[] operands = opcodeAndOperands[1].split(",");
                for (int i = 0; i < operands.length; i++) {
                    operands[i] = operands[i].strip();
                    if (operands[i].contains("=")) {
                        String lhs = operands[i].split("=")[0].strip();
                        String rhs = operands[i].split("=")[1].strip();
                        operands[i] = lhs + "=" + rhs;
                    }
                }

                //add instruction
                instructions.add( (hasLabel?label+":":"") + opcode + " " + String.join(",", operands));
            }
        }
        catch(Exception E){
            System.out.println(E);
        }

        return instructions;
    }
}
