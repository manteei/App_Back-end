package WorkClass;

import CommandWorker.ScriptCommandWithArgument;
import Reader.CommandLine;
import Reader.LineReader;
import SetItems.LabWork;

/**
 * Класс обновляет значение элемента.
 */
public class Update implements ScriptCommandWithArgument {
    private String[] arguments;
    private CommandLine commandLine;
    private LineReader lineReader;

    public Update(CommandLine commandLine, LineReader lineReader){
        this.commandLine = commandLine;
        this.lineReader = lineReader;
    }

    /**
     * Метод обновляет элемент с консоли.
     */
    @Override
    public void execute() {
       try {
           int oldId = Integer.parseInt(arguments[0]);
           if (commandLine.containsCollection(oldId)) {
               commandLine.removeId(oldId);
               commandLine.insert(commandLine.getCreationLabwork().updateLabwork(oldId));
               commandLine.sortCollection();
           } else {
               System.err.println("Нет элемента с таким id!\n");
           }
       } catch (ArrayIndexOutOfBoundsException e){
           System.err.println("Не указан аргумент команды!\n");
       }
    }

    /**
     * Метод получает аргумент команды
     * @param arguments
     */
    @Override
    public void getArgument(String[] arguments) {
        this.arguments = arguments;
    }

    /**
     * Метод обновляет элемент со скрипта.
     * @param lab
     */
    @Override
    public void execute(LabWork lab) {
        LabWork labwork = lab;
        try {
            int oldId = Integer.parseInt(arguments[0]);
            if (commandLine.containsCollection(oldId)) {
                commandLine.removeId(oldId);
                labwork.setId(oldId);
                commandLine.insert(labwork);
                commandLine.sortCollection();
            } else {
                System.err.println("Нет элемента с таким id!\n");
            }
        } catch (ArrayIndexOutOfBoundsException e){
            System.err.println("Не указан аргумент команды!\n");
        }

    }
}
