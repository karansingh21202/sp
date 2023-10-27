import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MacroProcessor {

    static HashMap<String, Integer> MNT;
    static ArrayList<String> MDT;
    static HashMap<Integer, ArrayList<String>>ALAMap;
    static void pass1(ArrayList<String> instructions){

        //create MNT and MDT and remove macro definitions from the code
        for(int i=0;i<instructions.size();i++){
            if(instructions.get(i).equals("MACRO")){
                String macro = instructions.get(i+1);
                String macroName = macro.split(" ")[0];
                String[] params = macro.split(" ")[1].split(",");
                for(int j=0;j<params.length;j++){
                    if(params[j].contains("=")) params[j] = params[j].split("=")[0];
                }
                //create entry in mnt
                MNT.put(macroName,MDT.size());
                //enter definition in mdt
                MDT.add(macro);
                i+=2;
                while(!instructions.get(i).equals("MEND")){
                    String instruction = instructions.get(i);
                    String newInstruction = instruction.substring(0,instruction.indexOf(" ")+1);
                    String[] operands = instruction.substring(instruction.indexOf(" ")+1).split(",");
                    for(int j=0;j<operands.length;j++){
                        for(int k=0;k< params.length;k++) {
                            if (params[k].equals(operands[j])) operands[j] = "#"+(k+1);
                        }
                    }
                    newInstruction+=String.join(",",operands);
                    MDT.add(newInstruction);
                    i++;
                }
                MDT.add("MEND");
            }
            else if(instructions.get(i).startsWith("START")){
                instructions.subList(0,i).clear();
                break;
            }
        }

        //create ALAs for macro calls
        for(int i=0;i<instructions.size();i++){
            String instruction = instructions.get(i);
            if(instruction.contains(":")) instruction = instruction.split(":")[1];
            String opcode = instruction.split(" ")[0];
            if(MNT.containsKey(opcode)){
                String[] formalParams = MDT.get(MNT.get(opcode)).split(" ")[1].split(",");
                String[] actualParams = instruction.split(" ")[1].split(",");
                HashMap<String,String>value = new HashMap<String, String>();
                for(String param:formalParams){
                    if(!param.contains("=")) continue;
                    String[] temp = param.split("=");
                    value.put(temp[0], temp[1]);
                }
                ArrayList<String>ALA = new ArrayList<>();
                int startIndexOfKwargs = 0;
                for(int j=0;j<actualParams.length;j++) {
                    if (!actualParams[j].contains("=")) {
                        ALA.add(actualParams[j]);
                        startIndexOfKwargs = j;
                    }
                    else{
                        String temp[] = actualParams[j].split("=");
                        value.put("&"+temp[0], temp[1]);
                    }
                }
                startIndexOfKwargs++;
                for(int j=startIndexOfKwargs;j<formalParams.length;j++){
                    ALA.add(value.get(formalParams[j].split("=")[0]));
                }
                ALAMap.put(i,ALA);
            }
        }
    }

    static ArrayList<String> pass2(ArrayList<String> instructions){
        ArrayList<String> expandedInstructions = new ArrayList<String>();
        for(int i=0;i<instructions.size();i++){
            String instruction = instructions.get(i);
            if(instruction.contains(":")) instruction = instruction.split(":")[1];
            String opcode = instruction.split(" ")[0];
            if(MNT.containsKey(opcode)){
//                instructions.remove(i);
//                int j = i;
                int ind = MNT.get(opcode)+1;
                while(!MDT.get(ind).equals("MEND")){
                    int paramCount = ALAMap.get(i).size();
                    String actualInstruction = MDT.get(ind);
                    for(int k=1;k<=paramCount;k++) {
                        actualInstruction = actualInstruction.replace("#"+k, ALAMap.get(i).get(k-1));
                    }
                    expandedInstructions.add(actualInstruction);
                    ind++;
                }
            }
            else{
                expandedInstructions.add(instructions.get(i));
            }
        }

        return expandedInstructions;
    }
    static void showmnt(){
        System.out.println("\nMNT:");
        for(String key: MNT.keySet()){
            System.out.println(key+"\t"+MNT.get(key));
        }
    }
    static void showmdt(){
        System.out.println("\nMDT:");
        for(String str:MDT) System.out.println(str);
    }
    static void showalas(){
        System.out.println("\nALAs: ");
        for(Integer key: ALAMap.keySet()){
            System.out.print(key+": ");
            for(String str:ALAMap.get(key)){
                System.out.print(str+" ");
            }
            System.out.println();
        }
    }
    public static ArrayList<String> expandMacros(ArrayList<String> instructions){

        MNT = new HashMap<String, Integer>();
        MDT = new ArrayList<String>();
        ALAMap = new HashMap<Integer, ArrayList<String>>();

        pass1(instructions);
        instructions = pass2(instructions);

        showmnt();
        showmdt();
        showalas();

        return instructions;
    }

}

/*
find macros
create mnt
create mdt
for each instance, create ala
expand

approach 1 =>
make MacroProcessor constructor
create mnt and mdt as members
mnt: name, index in mdt;
hashmap?
make: printmnt;
mdt: desc
arraylist of strings?
ala:
probably create for each line
hashmap<int,arraylist<string>>
how to expand?
get line no->replace mdt with ala->replace line with mdt
 */
