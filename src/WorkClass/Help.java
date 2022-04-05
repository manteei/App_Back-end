package WorkClass;

import CommandWorker.Command;
import Reader.CommandLine;

/**
 * Класс выводит список доступных команд.
 */
public class Help implements Command {
    private CommandLine commandLine;
    public Help(CommandLine commandLine){
        this.commandLine = commandLine;
    }
    @Override
    public void execute(){
        commandLine.help();
    }
}
