package WorkClass;

import CommandWorker.ScriptCommand;
import Reader.CommandLine;
import SetItems.LabWork;

/**
 * Класс добавляет объект в коллекцию, если его значение поля "minimal point" больше максимального в коллекции
 */
public class AddIfMax implements ScriptCommand {
    private LabWork newLab;
    private CommandLine commandLine;
    public AddIfMax(CommandLine commandLine){
        this.commandLine = commandLine;
    }

    /**
     * Метод добавления с консоли.
     */
    @Override
    public void execute() {
        newLab = commandLine.getCreationLabwork().creationLabwork();
        if (commandLine.findMaxMinimalPoint() < newLab.getMinimalPoint()){
            commandLine.insert(newLab);
            commandLine.sortCollection();
            System.out.println("Лабораторная работа " + newLab.getName() + " добавлена! ");
        } else System.out.println("Лабораторная работа " + newLab.getName() + " не добавлена!");
    }
    /**
     * Метод добавления со скрипта.
     * @param lab
     */
    @Override
    public void execute(LabWork lab) {
        newLab = lab;
        if (commandLine.findMaxMinimalPoint() < newLab.getMinimalPoint()){
            commandLine.insert(newLab);
            commandLine.sortCollection();
            System.out.println("Лабораторная работа " + newLab.getName() + " добавлена! ");
        } else System.out.println("Лабораторная работа " + newLab.getName() + " не добавлена!");
    }
}
