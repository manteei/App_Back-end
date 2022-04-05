package Reader;


import SetItems.CreationLabwork;
import SetItems.LabWork;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

/**
 * Класс, выполняющий парсинг данных из xml-файла в коллекцию
 */
public class XmlParser {
    private FileChecker fileChecker;
    private CommandLine commandLine;

    private String filepath = System.getenv("FILE_PATH");

    private CreationLabwork creationLabwork;

    public XmlParser(CommandLine commandLine,CreationLabwork creationLabwork) {
        this.commandLine = commandLine;
        fileChecker = new FileChecker(commandLine);
        this.creationLabwork = creationLabwork;
    }


    public CommandLine getCommandLine() {
        return commandLine;
    }

    /**
     * Метод преобразовывает xml-файл в объекты коллекции
     */
    public void fromXmlToObject() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath));
            JAXBContext jaxbContext = JAXBContext.newInstance(CommandLine.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            CommandLine elements = (CommandLine) unmarshaller.unmarshal(bufferedReader);

            for (LabWork val : elements.getLabworks()) {
                if (fileChecker.chekId(val) && fileChecker.chekName(val) && fileChecker.chekCoordinates(val) && fileChecker.checkMinimalPoint(val)
                        && fileChecker.checkDifficulty(val)&& fileChecker.checkDate(val) && fileChecker.checkPerson(val)) {
                    fileChecker.IdStock(val);
                    this.commandLine.insert(val);
                    System.out.println("Лабораторная работа из XML-файла с названием: " + val.getName() + " добавлена!");
                } else
                    System.out.println("Из XML-файла лабораторная работа с названием: " + val.getName() + " не добавлена в коллекцию!");
            }
            commandLine.sortCollection();
            creationLabwork.setId(creationLabwork.getNewId(commandLine));
        } catch (JAXBException | FileNotFoundException e) {
            System.out.println(e.getMessage());

        } catch (NullPointerException e2) {
            System.err.println("Xml-файл некорректен!");

        }
    }

    /**
     * Метод преобразовывает элементы коллекции в xml-файл.
     */
    public void saveToXml() {
        try {
            File outFile = new File(filepath);
            PrintWriter outputStream = new PrintWriter(outFile);
            JAXBContext jaxbContext = JAXBContext.newInstance(CommandLine.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(commandLine, outputStream);

        } catch (FileNotFoundException e) {
            System.out.println("Такой файл не найден!");
        } catch (JAXBException e2) {
            System.out.println(e2.getMessage());
        }
    }


}
