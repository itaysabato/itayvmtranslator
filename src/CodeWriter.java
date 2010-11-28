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
    FileWriter writer;
    int jumpCounter = 0;

    public CodeWriter(File output) throws IOException {
        writer = new FileWriter(output);
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
        else {
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
        // once the correct value is in D we can push it to the top of the stack:
        writer.write("@SP\n");
        writer.write("A=M\n");
        writer.write("M=D\n");
        writer.write("D=A+1\n");
        writer.write("@SP\n");
        writer.write("M=D\n");
        writer.write("\n");
    }

    public void close() throws IOException {
        writer.close();
    }

    public void writePop(String segment, int index) throws IOException {
        writer.write("\n");
    }
}
