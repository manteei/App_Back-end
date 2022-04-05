package SetItems;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Класс описывающий координаты лабораторной работы.
 */
@XmlRootElement(name = "coordinates")
public class Coordinates {
    private long x;
    private long y; //Значение поля должно быть больше -732
    public Coordinates(){}
    public Coordinates(long x, long y){
        this.x = x;
        this.y = y;
    }
    public long getX(){
        return x;
    }
    public long getY(){
        return y;
    }

    @XmlElement(name = "x")
    public void setX(long x) {
        this.x = x;
    }

    @XmlElement(name = "y")
    public void setY(long y) {
        if (y > -732) {
            this.y = y;
        }
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
