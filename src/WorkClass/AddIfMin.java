package WorkClass;

import CommandWorker.ScriptCommand;
import Reader.CommandLine;
import SetItems.LabWork;

/**
 * Класс добавляет объект в коллекцию, если его вес меньше минимального веса в коллекции
 */
public class AddIfMin implements ScriptCommand {
    private LabWork newLab;
    private CommandLine commandLine;
    public AddIfMin(CommandLine commandLine){
        this.commandLine = commandLine;
    }

    /**
     * Метод добавления с консоли.
     */
    @Override
    public void execute() {
        LabWork newLab = commandLine.getCreationLabwork().creationLabwork();
        if (commandLine.findMinimalX()> newLab.getCoordinates().getX()){
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
        LabWork newLab = lab;
        if (commandLine.findMinimalX()> newLab.getCoordinates().getX()){
            commandLine.insert(newLab);
            commandLine.sortCollection();
            System.out.println("Лабораторная работа " + newLab.getName() + " добавлена! ");
        } else System.out.println("Лабораторная работа " + newLab.getName() + " не добавлена!");
    }
}
