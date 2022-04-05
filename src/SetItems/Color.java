package SetItems;

/**
 * Класс с данными о цвете волос автора.
 */
public enum Color {
    GREEN("green"),
    YELLOW("yellow"),
    WHITE("white"),
    BROWN("brown");
    String color;
    Color(String color){
        this.color = color;
    }
}
