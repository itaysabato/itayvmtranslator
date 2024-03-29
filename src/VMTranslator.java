import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Names: Itay Sabato, Rotem Barzilay <br/>
 * Logins: itays04, rotmus <br/>
 * IDs: 036910008, 300618592 <br/>
 * Date: 27/11/2010 <br/>
 * Time: 19:42:17 <br/>
 */
public class VMTranslator {

    public static void main(String[] arguments) {
        File input = new File(arguments[0]);
        File output;

        if(input.isDirectory()){
            String child = new File(arguments[0].replaceAll(".vm", "")+".asm").getName();
            output = new File(input, child);
        }
        else output = new File(arguments[0].replaceAll(".vm", "")+".asm");

        CodeWriter codeWriter = null;

        try {
            codeWriter = new CodeWriter(output);
//            codeWriter.writeInit();

            if(input.isDirectory()){
                File[] files = input.listFiles();
                for(File file: files){
                    if(file.isFile() && file.getName().endsWith(".vm")) translate(file, codeWriter);
                }
            }
            else if(input.isFile()) translate(input, codeWriter);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found: "+e.getMessage());
        }
        catch (IOException e) {
            System.out.println("Failed writing to output stream: "+e.getMessage());
        }
        finally {
            if(codeWriter != null){
                try {
                    codeWriter.close();
                }
                catch (IOException e) {
                    System.out.println("Failed closing output stream: "+e.getMessage());
                }
            }
        }
    }

    private static void translate(File file, CodeWriter codeWriter) throws IOException {
        Parser parser = new Parser(file);
        codeWriter.setFileName(file.getName().replaceAll(".vm",""));

        try {
            while(parser.advance()){
                CommandType command = parser.commandType();

                if(command == CommandType.RETURN){
                    codeWriter.writeReturn();
                }
                else if(command == CommandType.ARITHMETIC){
                    codeWriter.writeArithmetic(parser.first());
                }
                else if(command == CommandType.LABEL){
                    codeWriter.writeLabel(parser.first());
                }
                else if(command == CommandType.GOTO){
                    codeWriter.writeGoto(parser.first());
                }
                else if(command == CommandType.IF){
                    codeWriter.writeIf(parser.first());
                }
                else {
                    String first =  parser.first();
                    int second =  parser.second();

                    if(command == CommandType.FUNCTION){
                        codeWriter.writeFunction(first, second);
                    }

                    if(command == CommandType.CALL){
                        codeWriter.writeCall(first, second);
                    }

                    if(command == CommandType.PUSH){
                        codeWriter.writePush(first, second);
                    }
                    else if(command == CommandType.POP){
                        codeWriter.writePop(first, second);
                    }
                }
            }
        }
        catch(IOException e) {
            System.out.println("Failed writing to file");
            throw  e;
        }
        finally {
            parser.close();
        }
    }
}
