package WorkClass;

import CommandWorker.CommandWithArgument;
import Reader.CommandLine;
import Reader.LineReader;

/**
 * Метод удаляет из коллекции элемент с данным id.
 */
public class Remove implements CommandWithArgument {
    private String[] arguments;
    private CommandLine commandLine;
    private LineReader lineReader;

    public Remove(CommandLine commandLine, LineReader lineReader){
        this.commandLine = commandLine;
        this.lineReader = lineReader;
    }

    @Override
    public void execute() {
        try {
            if (commandLine.containsCollection(Integer.parseInt(arguments[0]))) {
                commandLine.removeId(Integer.parseInt(arguments[0]));
            } else {
                System.err.println("Элемента с таким id нет!\n");
            }
        } catch (ArrayIndexOutOfBoundsException e){
            System.err.println("Не указан аргумент команды!\n");
        }

    }

    @Override
    public void getArgument(String[] arguments) {
        this.arguments = arguments;

    }
}
