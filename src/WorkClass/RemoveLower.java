package WorkClass;

import CommandWorker.Command;
import CommandWorker.ScriptCommand;
import Reader.CommandLine;
import SetItems.LabWork;

/**
 * Метод удаляет объекты коллекции, чья координата Y меньше чем у введенного объекта
 */
public class RemoveLower implements ScriptCommand {
    private LabWork newLab;
    private CommandLine commandLine;
    public RemoveLower(CommandLine commandLine){
        this.commandLine = commandLine;
    }

    /**
     * Метод реализует удаления для консоли
     */
    @Override
    public void execute(){
        newLab = commandLine.getCreationLabwork().creationLabwork();
        commandLine.removeLower(newLab);
    }

    /**
     * Метод реализует удаления для скрипта.
     * @param lab
     */
    @Override
    public void execute(LabWork lab) {
        newLab = lab;
        commandLine.removeLower(newLab);
    }
}
