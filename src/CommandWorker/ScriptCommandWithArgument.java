package CommandWorker;

import SetItems.LabWork;
/**
 * Интерфейс, описывающий поведение команд с аргументом, с которыми подается объект класса Labwork.
 */
public interface ScriptCommandWithArgument extends CommandWithArgument{
    void execute(LabWork lab);
}
