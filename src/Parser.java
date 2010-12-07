import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Names: Itay Sabato, Rotem Barzilay <br/>
 * Logins: itays04, rotmus <br/>
 * IDs: 036910008, 300618592 <br/>
 * Date: 27/11/2010 <br/>
 * Time: 19:42:46 <br/>
 */
public class Parser {
    private Scanner source;
    private String token = "";

    public Parser(File source) throws FileNotFoundException {
        this.source = new Scanner(source);
        this.source.useDelimiter("(\\s)|(//(.*))");
    }

    /**
     *   Advances to the next line.
     */
    public boolean advance() {
        while(source.hasNext()) {
            token = source.next();
            if(!token.isEmpty()){
                return true;
            }
        }
        return false;
    }

    /**
     *   returns the command type.
     * @return the command type
     */
    public CommandType commandType()  {
        CommandType result;
        boolean readNext = true;

        if(token.equals("push")){
            result =  CommandType.PUSH;
        }
        else if(token.equals("pop")){
            result = CommandType.POP;
        }
        else if(token.equals("function")){
            result = CommandType.FUNCTION;
        }
        else if(token.equals("call")){
            result = CommandType.CALL;
        }
        else if(token.equals("label")){
            result = CommandType.LABEL;
        }
        else if(token.equals("goto")){
            result = CommandType.GOTO;
        }
        else if(token.equals("if-goto")){
            result = CommandType.IF;
        }
        else {
            readNext = false;
            if(token.equals("return")){
                result = CommandType.RETURN;
            }
            else result = CommandType.ARITHMETIC;
        }

        if(readNext) token = source.next();

        return result;
    }

    public String  first() {
        return token;
    }

    public int  second() {
        return source.nextInt();
    }

    /**
     *    closes the file.
     */
    public void close() {
        source.close();
    }
}
