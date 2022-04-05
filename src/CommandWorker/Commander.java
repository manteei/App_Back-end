package CommandWorker;

import Reader.*;
import SetItems.LabWork;
import WorkClass.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

/**
 * Класс вызывает исполнение команд
 */
public class Commander {
    private HashMap<String, Command> commandNoArgument;
    private HashMap<String, CommandWithArgument> commandWithArgument;
    private CommandLine commandLine;
    private ExecuteScript.Script script;
    private LineReader lineReader;
    private String inputFile;
    private FillerFields fillerFields;


    public Commander(CommandLine commandLine, ExecuteScript.Script script, LineReader lineReader, FillerFields fillerFields) {
        this.commandLine = commandLine;
        this.lineReader = lineReader;
        this.fillerFields = fillerFields;
        this.script = script;
        commandNoArgument = new HashMap<>();
        commandWithArgument = new HashMap<>();
        this.putCommand();

    }

    public Commander(CommandLine commandLine, LineReader lineReader, String inputFile, FillerFields fillerFields) {
        this.commandLine = commandLine;
        this.lineReader = lineReader;
        this.inputFile = inputFile;
        this.fillerFields = fillerFields;
        commandWithArgument = new HashMap<>();
        commandNoArgument = new HashMap<>();
        script = new ExecuteScript.Script();
        this.putCommand();
    }

    private void putCommand() {
        commandNoArgument.put("help", new Help(commandLine));
        commandNoArgument.put("info", new Info(commandLine));
        commandNoArgument.put("show", new Show(commandLine));
        commandNoArgument.put("add", new Add(commandLine));
        commandWithArgument.put("update", new Update(commandLine, lineReader));
        commandWithArgument.put("remove_by_id", new Remove(commandLine, lineReader));
        commandNoArgument.put("clear", new Clear(commandLine));
        commandNoArgument.put("save", new Save(commandLine, inputFile));
        commandNoArgument.put("exit", new Exit());
        commandWithArgument.put("execute_script", new ExecuteScript(commandLine, fillerFields, script));
        commandNoArgument.put("add_if_max", new AddIfMax(commandLine));
        commandNoArgument.put("add_if_min", new AddIfMin(commandLine));
        commandNoArgument.put("remove_lower", new RemoveLower(commandLine));
        commandNoArgument.put("min_by_minimal_point", new MinByMinimalPoint(commandLine));
        commandWithArgument.put("filter_by_minimal_point", new FilterByMinimalPoint(commandLine, lineReader));
        commandWithArgument.put("filter_greater_than_minimal_point", new GreaterThanPoint(commandLine, lineReader));

    }

    public void execute(String firstLineCommand) {
        String[] words = firstLineCommand.trim().split("\\s+");
        String[] args = Arrays.copyOfRange(words, 1, words.length);

        if (commandWithArgument.containsKey(words[0].toLowerCase(Locale.ROOT))) {
            CommandWithArgument command;
            command = commandWithArgument.get(words[0].toLowerCase(Locale.ROOT));
            command.getArgument(args);
            command.execute();
        } else if (commandNoArgument.containsKey(words[0].toLowerCase(Locale.ROOT))) {
            Command command;
            command = commandNoArgument.get(words[0].toLowerCase(Locale.ROOT));
            command.execute();
        } else {
            System.out.println("Команда " + words[0] + " не распознана, введите корректную команду!");
        }

    }
    public void execute(String firstLineCommand, LabWork lab) {
        LabWork labwork = lab;
        String[] words = firstLineCommand.trim().split("\\s+");
        String[] args = Arrays.copyOfRange(words, 1, words.length);

        if (commandWithArgument.containsKey(words[0].toLowerCase(Locale.ROOT))) {
            ScriptCommandWithArgument command;
            command = (ScriptCommandWithArgument) commandWithArgument.get(words[0].toLowerCase(Locale.ROOT));
            command.getArgument(args);
            command.execute(labwork);
        } else if (commandNoArgument.containsKey(words[0].toLowerCase(Locale.ROOT))) {
            ScriptCommand command;
            command = (ScriptCommand) commandNoArgument.get(words[0].toLowerCase(Locale.ROOT));
            command.execute(labwork);
        } else {
            System.out.println("Команда " + words[0] + " не распознана, введите корректную команду!");
        }

    }
}
