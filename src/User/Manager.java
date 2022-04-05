package User;

import CommandWorker.Commander;
import Reader.CommandLine;
import Reader.FillerFields;
import Reader.LineReader;
import Reader.XmlParser;
import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * Класс автоматически загружает коллекцию из xml-файла при запуске программы.
 */
public class Manager {
    CommandLine commandLine;
    XmlParser xmlParser;
    LineReader lineReader;
    Commander commander;
    FillerFields fillerFields;

    /**
     * Метод загрузки коллекции.
     * @param xmlfile
     */
    public void start(String xmlfile){
        commandLine = new CommandLine();
        xmlParser = new XmlParser(commandLine , commandLine.getCreationLabwork());
        lineReader = new LineReader();
        this.commander = new Commander(commandLine, lineReader, xmlfile, fillerFields);

        try {
            File file = new File(xmlfile);
            if (!file.canWrite() || !file.isFile() || file.isDirectory()) throw new IOException();
            xmlParser.fromXmlToObject();
            if (commandLine.getSize()==0){
                System.out.println("Коллекция пуста, добавьте элементы!");
            } else System.out.println("Объекты из файла загружены!");

        } catch (IOException e) {
            System.out.println("Такого файла нет!");
            System.exit(0);
        }

        try{
            process();
        } catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }

    }

    /**
     * Метод работающий в процессе работы программы, считывающий команду.
     */
    public void process(){
        System.out.println("Программа запущена");
        while (true){
            System.out.println("\nВведите название команды");
            String command = lineReader.getLine();
            commander.execute(command);
        }
    }
}
