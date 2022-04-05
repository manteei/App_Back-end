package WorkClass;

import CommandWorker.CommandWithArgument;
import Reader.CommandLine;
import Reader.LineReader;
/**
 * Класс выводит элементы, чье поле минимального балла больше указанного.
 */
public class GreaterThanPoint implements CommandWithArgument {
    private String[] arguments;
    private CommandLine commandLine;
    private LineReader lineReader;


    public GreaterThanPoint(CommandLine commandLine, LineReader lineReader){
        this.commandLine = commandLine;
        this.lineReader = lineReader;
    }

    @Override
    public void execute() {
        try{
        Double minPoint = Double.parseDouble(arguments[0]);
        commandLine.greaterLab(minPoint);
        } catch (ArrayIndexOutOfBoundsException e){
            System.err.println("Не указан аргумент команды!\n");
        }
    }

    @Override
    public void getArgument(String[] arguments) {
        this.arguments = arguments;
    }
}
