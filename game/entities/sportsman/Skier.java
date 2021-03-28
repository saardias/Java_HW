package game.entities.sportsman;

import game.enums.Discipline;
import game.enums.Gender;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Saar Dias 311427496 Nitay Malka 304931801
 * representation of a Skier
 */
public class Skier extends WinterSportsman {
    /**
     * Ctor for a Skier
     * @param name         name of Skier
     * @param age          age of Skier
     * @param gender       gender of Skier
     * @param acceleration acceleration of Skier
     * @param maxSpeed     max speed of Skier
     * @param discipline   discipline of Skier
     */
    public Skier(String name, double age, Gender gender, double acceleration, double maxSpeed,
                       Discipline discipline,int id,String color) throws IllegalArgumentException {
        super(name, age, gender, acceleration, maxSpeed, discipline,id);
        if(gender == Gender.MALE){
            setImage("Blue");
        }
        else
            setImage("Pink");
    }

    /**
     * Copy Ctor for a Skier
     * @param other Skierto copy from
     * @throws IllegalArgumentException
     */
    public Skier(Skier other) throws IllegalArgumentException {
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
        return "Skier " + this.getName();
    }

    /**
     * @return new copy of the Skier
     */
    public Skier clone(){
        return new Skier(this);

    }

    /**
     * method for setting the image of the competitor according to his color
     * @param color color of the image
     */
    public void setImage(String color) {
        try {
            switch (color) {
                case "Blue":
                    this.img = ImageIO.read(new File("src\\SkiBlue.png"));
                    break;
                case "Pink":
                    this.img = ImageIO.read(new File("src\\SkiPink.png"));
                    break;
                case "Black":
                    this.img = ImageIO.read(new File("src\\SkiBlack.png"));
                    break;
                case "Orange":
                    this.img = ImageIO.read(new File("src\\SkiOrange.png"));
                    break;
                case "Red":
                    this.img = ImageIO.read(new File("src\\SkiRed.png"));
                    break;
                case "Green":
                    this.img = ImageIO.read(new File("src\\SkiGreen.png"));
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
        e.printStackTrace();
    }

    }

    /**
     * upgrade method that update the id and color of the skier
     * @param id id of the skier
     * @param color color of the skier
     */
    public void upgrade(int id,String color){
        this.id = id;
        this.color = color;
    }
}
