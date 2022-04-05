package Reader;
import java.util.Scanner;

/**
 * Класс считывает данные, введенные пользователем с консоли.
 */
public class LineReader implements Reader {
    Scanner scanner;

    public LineReader(Scanner scanner){
        this.scanner = scanner;
    }
    public LineReader(){
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String getLine(){
        return scanner.nextLine();
    }
    @Override
    public void printEscortMessage(String message){
        System.out.print(message);
    }
    @Override
    public void printErrorMessage(String message){
        System.err.print(message);
    }
}
