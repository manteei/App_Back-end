package WorkClass;

import CommandWorker.Command;
import Reader.CommandLine;

/**
 * Класс выводит информацию о коллекции.
 */
public class Show implements Command {
    private CommandLine commandLine;
    public Show(CommandLine commandLine){
        this.commandLine = commandLine;
    }

    /**
     * Метод вывода информации
     */
    @Override
    public void execute() {
        commandLine.show();
    }


}
