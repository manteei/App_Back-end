package WorkClass;

import CommandWorker.Command;
import Reader.CommandLine;

/**
 * Класс сохраняет коллекцию в файл.
 */
public class Save implements Command {
    private CommandLine commandLine;
    private String file;

    public Save(CommandLine commandLine, String file){
        this.commandLine = commandLine;
        this.file = file;
    }
    @Override
    public void execute() {
        System.out.println("Коллекция сохранена!");
        commandLine.save();
    }
}
