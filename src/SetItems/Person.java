package SetItems;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Класс описывающий автора лабораторной работы.
 */
@XmlRootElement(name = "author")
//@XmlType(propOrder = {"name", "weight", "passportID", "hairColor", "nationality" })
public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Double weight; //Поле не может быть null, Значение поля должно быть больше 0
    private String passportID; //Длина строки не должна быть больше 30, Строка не может быть пустой, Поле не может быть null
    private Color hairColor; //Поле не может быть null
    private Country nationality; //Поле не может быть null
    public Person(){}
    public Person(String name,Double weight,String passportID, Color hairColor, Country nationality){
        this.name = name;
        this.weight = weight;
        this.passportID = passportID;
        this.hairColor = hairColor;
        this.nationality = nationality;
    }
    @XmlElement
    public void setName(String name) {
        this.name = name;
    }
    @XmlElement
    public void setWeight(Double weight) {
        this.weight = weight;
    }
    @XmlElement
    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }
    @XmlElement
    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }
    @XmlElement
    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public String getName(){
        return name;
    }
    public Double getWeight(){
        return weight;
    }
    public String getPassportID(){
        return passportID;
    }
    public Color getHairColor(){
        return hairColor;
    }
    public Country getNationality(){
        return nationality;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        Person person = (Person) o;
        if (getClass() != o.getClass()) return false; // Мб добавить || o == null
        return this.name.equals(person.getName()) && this.weight.equals(person.getWeight()) && this.passportID.equals(person.getPassportID()) && this.hairColor.equals(person.getHairColor()) && this.nationality.equals(person.getNationality());
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", passportID='" + passportID + '\'' +
                ", hairColor=" + hairColor +
                ", nationality=" + nationality +
                '}';
    }

}
