package SetItems;

import Reader.FillerFields;
import Reader.CommandLine;
import java.time.LocalDateTime;

/**
 * Класс формирует объекты коллекции
 */
public class CreationLabwork {
    static int id = 1;
    private int newId = 1;
    private FillerFields fillerFields = new FillerFields();

    /**
     * Метод создает объект лабораторная работа
     * @return
     */
    public LabWork creationLabwork(){
        return new LabWork(getId(), fillerFields.readName(), fillerFields.readCoordinates(), LocalDateTime.now(), fillerFields.readMinimalPoint(), fillerFields.readDifficulty(), fillerFields.readPerson());
    }

    /**
     * Вспомогательный метод, позволяет при обновлении элемента не менять его id
     * @param oldId
     * @return
     */
    public LabWork updateLabwork(int oldId){
        return new LabWork(oldId, fillerFields.readName(), fillerFields.readCoordinates(), LocalDateTime.now(), fillerFields.readMinimalPoint(), fillerFields.readDifficulty(), fillerFields.readPerson());
    }

    /**
     * Метод возвращает id увеличенный на 1;
     * @return
     */
    public static int getId() {
        return id++;
    }

    /**
     * Сеттер для id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Метод позволяет выбрать свободный id
     * @param commandLine
     * @return
     */

    /**
     * Метод позволяет получить уникальный id
     * @param commandLine
     * @return
     */
    public int getNewId(CommandLine commandLine){
        int newId = 1;
        for(LabWork lab: commandLine.getLabworks()){
            if (newId != lab.getId()){
                return newId;
            } newId++;
        }
        return newId;
    }
}
