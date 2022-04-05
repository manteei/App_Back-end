package WorkClass;

import CommandWorker.Command;
import Reader.CommandLine;

/**
 * Класс очищающий коллекцию.
 */
public class Clear implements Command {
    private CommandLine commandLine;
    public Clear(CommandLine commandLine){
        this.commandLine = commandLine;
    }

    @Override
    public void execute() {
        commandLine.clear();
        System.out.println("Коллекция очищена!");
    }
}
