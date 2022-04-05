package WorkClass;

import CommandWorker.Command;
import Reader.CommandLine;

/**
 * Класс выводит информацию о коллекции.
 */
public class Info implements Command {
    private CommandLine commandLine;
    public Info(CommandLine commandLine){
        this.commandLine = commandLine;
    }
    @Override
    public void execute() {
        commandLine.info();
    }


}
