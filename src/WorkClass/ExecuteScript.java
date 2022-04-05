
package WorkClass;

import CommandWorker.CommandWithArgument;
import CommandWorker.Commander;
import Reader.*;
import SetItems.LabWork;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Класс реализует исполнения со скрипта
 */
public class ExecuteScript implements CommandWithArgument {

    private String[] commandArguments;
    private CommandLine commandLine;
    private FillerFields fillerFields;
    private Script script;
    private String scriptPath;
    private LineReader lineReader;

    public ExecuteScript(CommandLine commandLine, FillerFields fillerFields, Script script) {
        this.commandLine = commandLine;
        this.fillerFields = fillerFields;
        this.script = script;
    }


    @Override
    public void execute() {
        try {
            if (commandArguments.length == 1) {
                scriptPath = commandArguments[0];
                if (script.scriptPath.contains(scriptPath)) {
                    System.err.println("Попытка рекурсивного вызова скрипта внутри него же!");
                    return;
                }
                else script.putScript(scriptPath);
            } else throw new IllegalArgumentException();
            File ioFile = new File(scriptPath);
            if (!ioFile.canWrite() || ioFile.isDirectory() || !ioFile.isFile()) throw new IOException();
            FileInputStream fileInputStream = new FileInputStream(scriptPath);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            Scanner scanner = new Scanner(inputStreamReader);
            lineReader = new LineReader(scanner);
            fillerFields = new FillerFields(scanner);
            Commander commander = new Commander(commandLine, script, lineReader , fillerFields);
            while (scanner.hasNext()) {
                String command = scanner.nextLine();
                String[] words = command.trim().split("\\s+");
                if (words[0].equals("add") | words[0].equals( "update") | words[0].equals("add_if_max")| words[0].equals("add_if_min") | words[0].equals("remove_lower" )){
                    FillerScriptFields fillerScriptFields = new FillerScriptFields(scanner);
                    try{
                    LabWork lab = fillerScriptFields.creationsLabwork();
                    commander.execute(command, lab);
                    } catch (NullPointerException e){
                         System.out.println("Объект из скрипта не прочитан!");
                    }

                } else {
                    commander.execute(command);
                    System.out.println("Команда " + command + " выполнена\n");
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден!");
        } catch (IOException e2) {
            System.err.println("Нет доступа к файлу " + e2.getMessage());
        } catch (IllegalArgumentException e3) {
            System.err.println("Скрипт не передан в качестве аргумента команды , либо кол-во аргументов больше 1!");
        } catch (NullPointerException e5) {
            System.err.println("Не выбран файл!");
        } finally {
            script.removeScript(scriptPath);
        }
    }


    @Override
    public void getArgument(String[] arguments) {
        this.commandArguments = arguments;
    }

    public static class Script {

        private ArrayList<String> scriptPath = new ArrayList<>();

        public void putScript(String path) {
            scriptPath.add(path);
        }

        public void removeScript(String path) {
            scriptPath.remove(path);
        }

    }
}