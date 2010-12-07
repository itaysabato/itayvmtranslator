import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Names: Itay Sabato, Rotem Barzilay <br/>
 * Logins: itays04, rotmus <br/>
 * IDs: 036910008, 300618592 <br/>
 * Date: 27/11/2010 <br/>
 * Time: 19:43:02 <br/>
 */
public class CodeWriter {

    private static final int TEMP = 5;
    private static final int THIS = 3;

    private FileWriter writer;
    private String fileName;
    private String functionName = "";
    private int jumpCounter = 0;
    private int returnCounter = 0;
    private static final int INIT_SP = 256;

    public CodeWriter(File output) throws IOException {
        writer = new FileWriter(output);
    }

    public void writeInit() throws IOException {
        writer.write("@"+INIT_SP+"\n");
        writer.write("D=A\n");
        writer.write("@SP\n");
        writer.write("M=D\n");
        writeCall("Sys.init", 0);
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void writeArithmetic(String operator) throws IOException {
        writer.write("@SP\n");
        writer.write("A=M\n");
        writer.write("A=A-1\n");

        if(operator.equals("neg")){
            writer.write("M=-M\n");
        }
        else if(operator.equals("not")){
            writer.write("M=!M\n");
        }
        else {
            // binary operator
            writer.write("D=M\n");  // D = y
            writer.write("A=A-1\n");   // M = x

            if(operator.equals("and")){
                writer.write("M=D&M\n");
            }
            else if(operator.equals("or")){
                writer.write("M=D|M\n");
            }
            else if(operator.equals("add")){
                writer.write("M=D+M\n");
            }
            else if(operator.equals("sub")){
                writer.write("M=M-D\n");
            }
            else writeCompare(operator);
            writer.write("D=A+1\n");
            writer.write("@SP\n");
            writer.write("M=D\n");
        }
        writer.write("\n");
    }

    /**
     * replace x with the boolean result of the comparison operator.
     * when the method returns A is assumed to hold the address of the result.
     * @param operator lt, gt or eq
     * @throws IOException
     */
    private void writeCompare(String operator) throws IOException {
        writer.write("D=M-D\n");
        writer.write("@R13\n");
        writer.write("M=-1\n");
        writer.write("@TRUE"+jumpCounter+"\n");

        if(operator.equals("lt")){
            writer.write("D;JLT\n");
        }
        else if(operator.equals("gt")){
            writer.write("D;JGT\n");
        }
        else if(operator.equals("eq")){
            writer.write("D;JEQ\n");
        }
        // if the condition holds we skip this part:
        writer.write("@R13\n");
        writer.write("M=0\n");

        writer.write("(TRUE"+jumpCounter+")\n");
        jumpCounter++;
        // now update the result:
        writer.write("@R13\n");
        writer.write("D=M\n");
        writer.write("@SP\n");
        writer.write("A=M-1\n");
        writer.write("A=A-1\n");
        writer.write("M=D\n");
        writer.write("\n");
    }

    public void writePush(String segment, int index) throws IOException {
        if(segment.equals("constant")){
            writer.write("@"+index+"\n");
            writer.write("D=A\n");
        }
        else{
            writeLoadA(segment, index);
            writer.write("D=M\n");
        }
        // once the correct value is in D we can push it to the top of the stack:
        writer.write("@SP\n");
        writer.write("A=M\n");
        writer.write("M=D\n");
        writer.write("D=A+1\n");
        writer.write("@SP\n");
        writer.write("M=D\n");
        writer.write("\n");
    }

    /**
     * writes the commands for loading the
     * correct (final)  address into the A-Register.
     * @param segment
     * @param index
     * @throws IOException
     */
    private void writeLoadA(String segment, int index) throws IOException {
        boolean direct;
        String address = "";

        if(direct = segment.equals("temp")){
            address = "R"+(TEMP+index);
        }
        else if(direct = segment.equals("pointer")){
            address = "R"+(THIS+index);
        }
        else if(direct = segment.equals("static")){
            address = fileName+"."+index;
        }
        else if(segment.equals("local")){
            address = "LCL";
        }
        else if(segment.equals("argument")){
            address = "ARG";
        }
        else if(segment.equals("this")){
            address = "THIS";
        }
        else if(segment.equals("that")){
            address = "THAT";
        }

        writer.write("@"+address+"\n");
        if(!direct){
            writer.write("D=M\n");
            writer.write("@"+index+"\n");
            writer.write("A=D+A\n");
        }
    }

    public void writePop(String segment, int index) throws IOException {
        writeLoadA(segment, index);

        // save the address in the temporary register:
        writer.write("D=A\n");
        writer.write("@R13\n");
        writer.write("M=D\n");

        // save the value of the top of the stack in D:
        writer.write("@SP\n");
        writer.write("A=M\n");
        writer.write("A=A-1\n");
        writer.write("D=M\n");

        // store the value in the correct address:
        writer.write("@R13\n");
        writer.write("A=M\n");
        writer.write("M=D\n");

        // update stack pointer:
        writer.write("@SP\n");
        writer.write("M=M-1\n");
        writer.write("\n");
    }

    public void writeLabel(String label) throws IOException {
        writer.write("("+functionName+"$"+label+")\n");
    }

    public void writeGoto(String label) throws IOException {
        writer.write("@"+functionName+"$"+label+"\n");
        writer.write("0;JMP\n");
        writer.write("\n");
    }

    public void writeIf(String label) throws IOException {
        // pop  to D
        writer.write("@SP\n");
        writer.write("A=M\n");
        writer.write("A=A-1\n");
        writer.write("D=M\n");
        // update SP
        writer.write("@SP\n");
        writer.write("M=M-1\n");
        // jump
        writer.write("@"+functionName+"$"+label+"\n");
        writer.write("D;JNE\n");
        writer.write("\n");
    }

    public void writeCall(String name, int numArguments) throws IOException {
        writeMyPush("RETURN"+returnCounter, "A");
        writeMyPush("LCL", "M");
        writeMyPush("ARG", "M");
        writeMyPush("THIS", "M");
        writeMyPush("THAT", "M");

        // update ARG:
        writer.write("@"+(numArguments + 5)+"\n");
        writer.write("D=A\n");
        writer.write("@SP\n");
        writer.write("A=M\n");       
        writer.write("D=A-D\n");
        writer.write("@ARG\n");
        writer.write("M=D\n");

        //update LCL:
        writer.write("@SP\n");
        writer.write("A=M\n");
        writer.write("D=A\n");
        writer.write("@LCL\n");
        writer.write("M=D\n");

        //jump:
        writer.write("@"+name+"\n");
        writer.write("0;JMP\n");
        writer.write("(RETURN"+returnCounter+")\n");
        returnCounter++;
        writer.write("\n");
    }

    private void writeMyPush(String address, String MOrA) throws IOException {
        writer.write("@"+address+"\n");
        writer.write("D="+MOrA+"\n");
        writer.write("@SP\n");
        writer.write("A=M\n");
        writer.write("M=D\n");
        writer.write("D=A+1\n");
        writer.write("@SP\n");
        writer.write("M=D\n");
    }

    public void writeFunction(String name, int numLocals) throws IOException {
        functionName = name;
        writer.write("("+functionName+")\n");
        for(int i = 0; i < numLocals; i++){
            writePush("constant", 0);
        }
    }

    public void writeReturn() throws IOException {
        writer.write("// restore RET\n");          //
        writeRestore(5,"R14");

        writer.write("// pop ARG\n");                    //
        writePop("argument", 0);

        //update SP:
        writer.write("// SP = ARG +1\n");                 //
        writer.write("@ARG\n");
        writer.write("D=M+1\n");
        writer.write("@SP\n");
        writer.write("M=D\n");

        writer.write("// restore ALL\n");           //
        for(int i = 1; i < 5; i++){
             writeRestore(i,"R"+(5-i));
        }

        writer.write("// jump\n");                   //
        writer.write("@R14\n");
        writer.write("A=M\n");        
        writer.write("0;JMP\n");
        writer.write("\n");        
    }

    private void writeRestore(int i, String register) throws IOException {
        writer.write("@LCL\n");
        writer.write("A=M\n");
        for(int j = 0; j < i; j++){
            writer.write("A=A-1\n");
        }
        writer.write("D=M\n");
        writer.write("@"+register+"\n");
        writer.write("M=D\n");
        writer.write("\n");
    }

    public void close() throws IOException {
        writer.close();
    }
}
