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
        File output = new File(arguments[0].replaceAll(".vm", "")+".asm");
        CodeWriter codeWriter = null;
        try {
            codeWriter = new CodeWriter(output);
        }
        catch (IOException e) {
            System.out.println("Failed to open output stream");
            System.exit(0);
        }

        if(input.isDirectory()){
            File[] files = input.listFiles();
            for(File file: files){
                try {
                    if(file.isFile()) translate(file, codeWriter);
                }
                catch (FileNotFoundException e) {
                    System.out.println("File not found: "+file);
                }
            }
        }
    }

    private static void translate(File file, CodeWriter codeWriter) throws FileNotFoundException {
        Parser parser = new Parser(file);
        try {
        while(parser.advance()){
            CommandType command = parser.commandType();
            if(command == CommandType.ARITHMETIC){
                codeWriter.writeArithmetic(parser.first());
            }
            else {
                String segment =  parser.first();
                int index =  parser.second();
                codeWriter.writePushPop(segment, index);
            }
        }
        }
        catch(IOException e) {
            System.out.println("Failed writing to file"); 
        }
        finally {
            parser.close();
            try {
                codeWriter.close();
            }
            catch (IOException e1) {
                System.out.println("Failed closing output stream");
            }
        }
    }
}
