package Reader;

import SetItems.Color;
import SetItems.Country;
import SetItems.Difficulty;
import SetItems.LabWork;

/**
 * Класс для проверки корректности данных в файле
 */
public class FileChecker {
    private CommandLine commandLine;

    public FileChecker(CommandLine commandLine){
        this.commandLine = commandLine;
    }

    public boolean chekId(LabWork labWork){
        if (labWork.getId() <= 0 ){
            System.out.println("Ошибка: id должен быть больше 0!");
            return false;
        }return true;
    }
    public boolean chekName(LabWork labWork){
        if (labWork.getName().equals("null")){
            System.out.println("Ошибка: имя не может быть null!");
            return false;
        } if(labWork.getName().equals("")){
            System.out.println("Ошибка: имя не может быть пустым!");
            return false;
        } return true;

    }
    public boolean chekCoordinates(LabWork labWork){
        try {
            if (String.valueOf(labWork.getCoordinates().getX()).equals("null")) {
                System.out.println("Ошибка: координата Х не может быть null!");
                return false;
            }
            if (String.valueOf(labWork.getCoordinates().getY()).equals("null")) {
                System.out.println("Ошибка: координата Y не может быть null!");
                return false;
            } else if (labWork.getCoordinates().getY() <= -732) {
                System.out.println("Ошибка: координата Y должна быть больше -732!");
                return false;
            }
        } catch (NullPointerException e){
            System.out.println("Ошибка: координаты заданы некорректно!");
            return false;
        }
        return true;
    }
    public boolean checkMinimalPoint(LabWork labWork){
            if (String.valueOf(labWork.getMinimalPoint()).equals("null")){
                System.out.println("Ошибка: минимальная оценка не может быть null! ");
                return false;
            } else if(labWork.getMinimalPoint()<=0){
                System.out.println("Ошибка: минимальная оценка должна быть больше 0! ");
                return false;
            }
            return true;
    }
    public boolean checkDifficulty(LabWork labWork){
        try {
             Difficulty.valueOf(String.valueOf(labWork.getDifficulty()).toUpperCase());
        } catch (IllegalArgumentException e){
            System.out.println("Ошибка: некорректная сложность лабораторной работы!");
            return false;
        }
        return true;
    }
    public boolean checkDate(LabWork labWork){
        DateTimeParser dateTimeParser = new DateTimeParser();
        try {
            if (dateTimeParser.getYear(labWork) > 2022) {
                System.out.println("Ошибка: неверно указан год!");
                return false;
            }
            if (dateTimeParser.getMonth(labWork) > 12 | dateTimeParser.getMonth(labWork) < 1) {
                System.out.println("Ошибка: неверно указан месяц!");
                return false;
            }
            if (dateTimeParser.getDay(labWork) > 31 | dateTimeParser.getDay(labWork) < 1) {
                System.out.println("Ошибка: неверно указан день!");
                return false;
            }
            if (dateTimeParser.getHour(labWork) > 23 | dateTimeParser.getHour(labWork) < 0) {
                System.out.println("Ошибка: неверно указан час!");
                return false;
            }
            if (dateTimeParser.getMinute(labWork) > 60 | dateTimeParser.getMinute(labWork) < 0) {
                System.out.println("Ошибка: неверно указаны минуты!");
                return false;
            }
            if (dateTimeParser.getSecond(labWork) > 60 | dateTimeParser.getSecond(labWork) < 0) {
                System.out.println("Ошибка: неверно указаны секунды!");
                return false;
            }
            if (String.valueOf(labWork.getCreationDate()).equals("null")) {
                System.out.println("Ошибка: дата и время не может быть null!");
                return false;
            }
        }catch (NullPointerException e) {
            System.out.println("Некорректно задано поле creation_Date!");
            return false;
        }
            return true;
    }
    public boolean checkPerson(LabWork labWork) {
        if (!LabWork.isNull(labWork)) {
            if (String.valueOf(labWork.getAuthor().getName()).equals("null")) {
                System.out.println("Ошибка: имя автора не может быть null! ");
                return false;
            }
            if (String.valueOf(labWork.getAuthor().getName()).equals("")) {
                System.out.println("Ошибка: имя автора не может быть пустой строкой! ");
                return false;
            }
            if (String.valueOf(labWork.getAuthor().getWeight()).equals("null")) {
                System.out.println("Ошибка: вес не может быть null! ");
                return false;
            } else if (labWork.getAuthor().getWeight() <= 0) {
                System.out.println("Ошибка: вес должен быть больше 0! ");
                return false;
            }
            if (String.valueOf(labWork.getAuthor().getPassportID()).equals("null")) {
                System.out.println("Ошибка: номер паспорта не может быть null! ");
                return false;
            }
            if (String.valueOf(labWork.getAuthor().getPassportID()).equals("")) {
                System.out.println("Ошибка: номер паспорта не может быть пустой строкой! ");
                return false;
            }
            if (String.valueOf(labWork.getAuthor().getPassportID()).length() > 30) {
                System.out.println("Ошибка: длина номера паспорта не должна быть больше 30! ");
                return false;
            }
            try {
                Color.valueOf(String.valueOf(labWork.getAuthor().getHairColor()).toUpperCase());
                if (String.valueOf(labWork.getAuthor().getHairColor()).equals("null")) {
                    System.out.println("Ошибка: цвет волос автора не может быть null! ");
                    return false;
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: некорректный цвет волос автора!");
                return false;
            }
            try {
                Country.valueOf(String.valueOf(labWork.getAuthor().getNationality()).toUpperCase());
                if (String.valueOf(labWork.getAuthor().getNationality()).equals("null")) {
                    System.out.println("Ошибка: национальность автора не может быть null! ");
                    return false;
                }
            } catch (IllegalArgumentException e1) {
                System.out.println("Ошибка: некорректная национальность  автора!");
                return false;
            }
            return true;
        } return true;
    }
    public boolean IdStock(LabWork labWork){
        for(LabWork lab : commandLine.getLabworks()){
            if(lab.getId() == labWork.getId()){
                commandLine.removeId(lab.getId());
                System.out.println("В xml-файле несколько элементов с одинаковым id, элементы заменены!");
                return true;
            }
        } return false;
    }

}
