package WorkClass;

import CommandWorker.Command;

/**
 * Класс выхода из программы.
 */
public class Exit implements Command {
    @Override
    public void execute() {
        System.out.println("Программа завершена!");
        System.exit(0);
    }
}
