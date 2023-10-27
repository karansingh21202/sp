import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String [] args) throws Exception{

        String srcFilePath = "D:\\Visual studio\\java_programs\\OS\\macroprocessor\\macro.txt";
        String outFilePath = "D:\\Visual studio\\java_programs\\OS\\macroprocessor\\out.txt";

        SrcCode asmCode = new SrcCode(srcFilePath);

        //get formatted instructions
        ArrayList<String> instructions = asmCode.getInstructions();
        System.out.println("\nSource Code: ");
        for(String instruction: instructions) System.out.println(instruction);

        //macro processor
        instructions = MacroProcessor.expandMacros(instructions);
        System.out.println("\nExpanded Code: ");
        for(String instruction: instructions) System.out.println(instruction);

        //assembler
//        Assembler.generateObjectCode(instructions, outFilePath);

        return ;
    }
}
