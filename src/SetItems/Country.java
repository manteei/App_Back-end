package SetItems;

/**
 * Класс с данными о национальности автора.
 */
public enum Country {
    GERMANY("germany"),
    SPAIN("spain"),
    VATICAN("vatican"),
    ITALY("italy"),
    NORTH_KOREA("north_korea");
    String country;
    Country(String country){
        this.country = country;
    }
}
