package Reader;

import SetItems.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;


/**
 * Выделение корневого элемента
 */
@XmlRootElement(name = "labworks")

/**
 * Класс управления коллекцией
 */
public class CommandLine {
    private Scanner scanner;
    private ArrayDeque<LabWork> collection;
    private LocalDateTime date;
    private CreationLabwork creationLabwork;

    private ArrayList<LabWork> collectionArr;


    public CommandLine(){
        collection = new ArrayDeque<LabWork>();
        date = LocalDateTime.now();
        creationLabwork = new CreationLabwork();
    }

    @XmlElement(name = "labwork")
    public void setLabworks(ArrayDeque<LabWork> list) {
        collection = list;
    }

    public ArrayDeque<LabWork> getLabworks(){
        return collection;
    }


    /**
     * Метод вывода справки о доступных методах
     */
    public void help(){
        System.out.println("help : вывести справку по доступным командам");
        System.out.println("info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        System.out.println("show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        System.out.println("add {element} : добавить новый элемент в коллекцию");
        System.out.println("update id {element} : обновить значение элемента коллекции, id которого равен заданному");
        System.out.println("remove_by_id id : удалить элемент из коллекции по его id");
        System.out.println("clear : очистить коллекцию");
        System.out.println("save : сохранить коллекцию в файл");
        System.out.println("execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        System.out.println("exit : завершить программу (без сохранения в файл)");
        System.out.println("add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        System.out.println("add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
        System.out.println("remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный");
        System.out.println("min_by_minimal_point : вывести любой объект из коллекции, значение поля minimalPoint которого является минимальным");
        System.out.println("filter_by_minimal_point minimalPoint : вывести элементы, значение поля minimalPoint которых равно заданному");
        System.out.println("filter_greater_than_minimal_point minimalPoint : вывести элементы, значение поля minimalPoint которых больше заданного");

    }
    /**
     * Метод вывода информации о коллекции
     */
    public void info(){
        System.out.println("Тип коллекции: ArrayDeque");
        System.out.println("Дата и время инициализации: " + date);
        System.out.println("Kоличество элементов: " + collection.size());
    }
    /**
     * Метод выводит элементы коллекции в стороковом представлении
     */
    public void show(){
        for(LabWork lab: collection){
            System.out.println(lab);
        }
    }

    /**
    * Метод добавляет элемент в коллекцию
     */
    public void insert(LabWork labWork){
        int id2 = labWork.getId();

        for (LabWork lab: getLabworks()) {
            if (containsCollection(id2)) {
                ++id2;
            }
        }
        labWork.setId(id2);
        collection.add(labWork);
        creationLabwork.setId(id2++);
    }

    /**
     * Метод очищает коллекцию
     */
    public void clear(){
        this.collectionArr = new ArrayList<>(this.collection);
        collectionArr.removeAll(collectionArr);
        collection = new ArrayDeque<>(collectionArr);
    }

    /**
     * Метод сохраняет коллекцию
     */
    public void save(){
        XmlParser xmlParser = new XmlParser(this, this.getCreationLabwork());
        xmlParser.saveToXml();
    }

    /**
     * Метод удаляет элементы коллекции, чьи значения координаты Y меньше
     * @param labWork
     */
    public void removeLower(LabWork labWork){
        collection.removeIf(lab -> labWork.getCoordinates().getY() > lab.getCoordinates().getY());
    }

    /**
     * Метод проверяет есть ли элемент с данным id в коллекции
     * @param id
     * @return
     */
    public boolean containsCollection(int id){
        for (LabWork lab: this.getLabworks()){
            if(lab.getId() == id){
                return true;
            }
        }
        return false;
    }

    /**
     * Метод позволяет получить элемент коллекции по id
     * @param id
     * @return
     */
    public LabWork getLabworkById(int id){
        if (id<=0){
            System.out.println("Некорректный id!");
        } else {
            for (LabWork lab: collection){
                if (lab.getId() == id){
                    return lab;
                }
            }
        } return null;
    }

    /**
     * Метод удаляет элемент коллекции по id
     * @param id
     * @return
     */
    public boolean removeId(int id){
        for (LabWork lab: collection){
            if(lab.getId() == id){
                collection.remove(lab);
                creationLabwork.setId(creationLabwork.getNewId(this));
                return true;
            }
        }
        return false;
    }

    public CreationLabwork getCreationLabwork(){
        return creationLabwork;
    }
    public int getSize(){
        return collection.size();
    }

    /**
     * Метод сортировки коллекции
     */
    public void sortCollection(){
        this.collectionArr = new ArrayList<>(this.collection);
        collectionArr.sort(new Comparator<LabWork>() {
            @Override
            public int compare(LabWork o1, LabWork o2) {
                if (o1.getId() == o2.getId()) return 0;
                else if (o1.getId()> o2.getId()) return 1;
                else return -1;
            }
        });
        collection = new ArrayDeque<>(collectionArr);


    }

    /**
     * Метод нахождения максимального по минимальному баллу элемента
     * @return
     */
    public Double findMaxMinimalPoint(){
        Double maxPoint = 0.0;
        for (LabWork lab: collection){
            if (lab.getMinimalPoint() > maxPoint){
                maxPoint = lab.getMinimalPoint();
            }
        }
        return maxPoint;
    }
    /**
     * Метод нахождения минимального по весу элемента
     * @return
     */
    public long findMinimalX(){
        long minX = 9223372036854775807L;
        for (LabWork lab: collection){
            if ((lab.getCoordinates().getX() < minX)){
                minX = lab.getCoordinates().getX();
            }
        }
        return minX;
    }

    /**
     * Метод нахождения минимального минимального значения
     */
    public void labMinimalPoint(){
        Double minPoint = 9223372036854775807.0;
        LabWork minlabWork = new LabWork();
        for (LabWork lab: collection){
            if (lab.getMinimalPoint() < minPoint){
                minPoint = lab.getMinimalPoint();
                minlabWork = lab;
            }
        } System.out.println(minlabWork);
    }

    /**
     * Метод вывода элементов с заданным полем минимального значения
     * @param minPoint
     */
    public void filterMinPoint(Double minPoint){
        for (LabWork lab: collection){
            if (lab.getMinimalPoint().equals(minPoint)){
                System.out.println(lab);
            }
        }
    }

    /**
     * Метод вывода элементов, чье поле минимального значения больше заданного
     * @param minPoint
     */
    public void greaterLab(Double minPoint){
        for (LabWork lab: collection){
            if (lab.getMinimalPoint() > minPoint){
                System.out.println(lab);
            }
        }
    }

}
