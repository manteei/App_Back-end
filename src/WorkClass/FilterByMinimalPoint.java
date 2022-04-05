package WorkClass;

import CommandWorker.CommandWithArgument;
import Reader.CommandLine;
import Reader.LineReader;

/**
 * Класс выводит элементы, чье поле минимального балла равно указанному.
 */
public class FilterByMinimalPoint implements CommandWithArgument {
    private String[] arguments;
    private CommandLine commandLine;
    private LineReader lineReader;

    public FilterByMinimalPoint(CommandLine commandLine, LineReader lineReader){
        this.commandLine = commandLine;
        this.lineReader = lineReader;
    }

    @Override
    public void execute() {
        try{
        Double minPoint = Double.parseDouble(arguments[0]);
        commandLine.filterMinPoint(minPoint);
        } catch (ArrayIndexOutOfBoundsException e){
            System.err.println("Не указан аргумент команды!\n");
        }
    }

    @Override
    public void getArgument(String[] arguments) {
        this.arguments = arguments;
    }
}
