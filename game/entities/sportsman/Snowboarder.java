package game.entities.sportsman;

import game.enums.Discipline;
import game.enums.Gender;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * @author Saar Dias 311427496 Nitay Malka 304931801
 * representation of a snowboarder
 */
public class Snowboarder extends WinterSportsman {
    /**
     * Ctor for a Snowboarder
     * @param name         name of Snowboarder
     * @param age          age of Snowboarder
     * @param gender       gender of Snowboarder
     * @param acceleration acceleration of Snowboarder
     * @param maxSpeed     max speed of Snowboarder
     * @param discipline   discipline of Snowboarder
     */
    public Snowboarder(String name, double age, Gender gender, double acceleration, double maxSpeed,
                       Discipline discipline,int id,String color) throws IllegalArgumentException {
        super(name, age, gender, acceleration, maxSpeed, discipline,id);
        if(gender == Gender.MALE){
            setImage("Blue");
        }
        else
            setImage("Pink");
    }
    /**
     * Copy Ctor for a Snowboarder
     * @param other Snowboarder to copy from
     * @throws IllegalArgumentException
     */
    public Snowboarder(Snowboarder other) throws IllegalArgumentException {
        super(other.getName(), other.getAge(), other.getGender(), other.acceleration, other.getMaxSpeed(),
                other.getDiscipline(),other.getId());
        if(other.getGender() == Gender.MALE){
            setImage("Blue");
        }
        else
            setImage("Pink");
    }

    @Override
    public String toString() {
        return "Snowboarder " + this.getName();
    }
    /**
     * @return new copy of the Snowboarder
     */
    public Snowboarder clone(){
        return new Snowboarder(this);
    }

    /**
     * method for setting the image of the competitor according to his color
     * @param color color of the image
     */
    public void setImage(String color) {
        try {
            switch (color) {
                case "Blue":
                    this.img = ImageIO.read(new File("src\\SnowboardBlue.png"));
                    break;
                case "Pink":
                    this.img = ImageIO.read(new File("src\\SnowboardPink.png"));
                    break;
                case "Black":
                    this.img = ImageIO.read(new File("src\\SnowboardBlack.png"));
                    break;
                case "Orange":
                    this.img = ImageIO.read(new File("src\\SnowboardOrange.png"));
                    break;
                case "Red":
                    this.img = ImageIO.read(new File("src\\SnowboardRed.png"));
                    break;
                case "Green":
                    this.img = ImageIO.read(new File("src\\SnowboardGreen.png"));
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * upgrade method that update the id and color of the Snowboarder
     * @param id id of the Snowboarder
     * @param color color of the Snowboarder
     */
    public void upgrade(int id,String color){
        this.id = id;
        this.color = color;
    }
}
