package User;

/**
 * Главный класс.
 */
public class Main {
    /**
     * Исполнение кода.
     * @param args
     */
    public static void main(String[] args) {
        try{
            String xmlFile = System.getenv("FILE_PATH");
            Manager manager = new Manager();
            manager.start(xmlFile);
        } catch (NullPointerException e){
            System.err.println("Укажите переменную окружения!");
        }
    }
}
