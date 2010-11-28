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
     */
    public CommandType commandType()  {
         CommandType result;

        if(token.equals("push")){
            result =  CommandType.PUSH;
            token = source.next();
        }
        else if(token.equals("pop")){
            result = CommandType.POP;
            token = source.next();
        }
         else result = CommandType.ARITHMETIC;
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
