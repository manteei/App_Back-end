package CommandWorker;

import SetItems.LabWork;

/**
 * Интерфейс, описывающий поведение команд без аргумента, с которыми должен подаваться объект класса Labwork.
 */
public interface ScriptCommand extends Command{
    void execute(LabWork lab);
}
