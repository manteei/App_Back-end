package WorkClass;

import CommandWorker.ScriptCommand;
import Reader.CommandLine;
import SetItems.LabWork;

/**
 * Класс добавляющий элемент в коллекцию
 */
public class Add implements ScriptCommand {
   private CommandLine commandLine;
   public Add(CommandLine commandLine){
       this.commandLine = commandLine;
   }

    /**
     * Метод добавления с консоли.
     */
    @Override
    public void execute() {
            commandLine.insert(commandLine.getCreationLabwork().creationLabwork());
            commandLine.sortCollection();
    }

    /**
     * Метод добавления со скрипта.
     * @param labWork
     */
    @Override
    public void execute(LabWork labWork){
       LabWork lab = labWork;
        commandLine.insert(lab);
        commandLine.sortCollection();
    }
}
