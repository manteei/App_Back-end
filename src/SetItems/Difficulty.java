package SetItems;

/**
 * Класс с данными о сложности лабораторной работы.
 */
public enum Difficulty {
    EASY("easy"),
    HARD("hard"),
    IMPOSSIBLE("impossible"),
    HOPELESS("hopeless");
    String difficulty;
    Difficulty(String difficulty){
        this.difficulty = difficulty;
    }

}
