package WorkClass;

import CommandWorker.Command;
import Reader.CommandLine;

/**
 * Класс выводит минимальный элемент по полю минимальный балл.
 */
public class MinByMinimalPoint implements Command {
    private CommandLine commandLine;
    public MinByMinimalPoint(CommandLine commandLine){
        this.commandLine = commandLine;
    }
    @Override
    public void execute() {
        commandLine.labMinimalPoint();
    }
}
