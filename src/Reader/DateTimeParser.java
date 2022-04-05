package Reader;

import SetItems.LabWork;

/**
 * Класс парсинга даты и времени
 */
public class DateTimeParser {

    public int getYear(LabWork labWork) {
        String datetime = labWork.getCreationDate().toString();
        String[] date = datetime.split("T");
        String year = date[0].split("-")[0];
        return Integer.parseInt(year);
    }

    public int getMonth(LabWork labWork) {
        String datetime = labWork.getCreationDate().toString();
        String[] date = datetime.split("T");
        String month = date[0].split("-")[1];
        return Integer.parseInt(month);
    }

    public int getDay(LabWork labWork) {
        String datetime = labWork.getCreationDate().toString();
        String[] date = datetime.split("T");
        String day = date[0].split("-")[2];
        return Integer.parseInt(day);
    }
    public int getHour(LabWork labWork) {
        String datetime = labWork.getCreationDate().toString();
        String[] date = datetime.split("T");
        String hour = date[1].split(":")[0];
        return Integer.parseInt(hour);
    }
    public int getMinute(LabWork labWork) {
        String datetime = labWork.getCreationDate().toString();
        String[] date = datetime.split("T");
        String minute = date[1].split(":")[1];
        return Integer.parseInt(minute);
    }
    public double getSecond(LabWork labWork) {
        String datetime = labWork.getCreationDate().toString();
        String[] date = datetime.split("T");
        String second = date[1].split(":")[2];
        return Double.parseDouble(second);
    }
}
