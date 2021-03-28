package game.entities.sportsman;

import game.entities.MobileEntity;
import game.enums.Gender;
import utilities.ValidationUtils;

/**
 *  @author Saar Dias 311427496 Nitay Malka 304931801
 *  representation of a Sportsman
 */
public abstract class Sportsman extends MobileEntity {
    private String name;
    private double age;
    private Gender gender;

    /**
     * Ctor for a Mobile Sportsman
     * @param name name of Sportsman
     * @param age age of Sportsman
     * @param gender gender of Sportsman
     * @param maxSpeed max Speed of Sportsman
     * @param acceleration acceleration of Sportsman
     */
    public Sportsman(String name, double age, Gender gender,double acceleration,double maxSpeed ) throws IllegalArgumentException{
        super(maxSpeed,acceleration);
        ValidationUtils.assertNotNullOrEmptyString(name);
        ValidationUtils.assertNotNegative(age);
        ValidationUtils.assertNotNull(gender);
        this.setName(name);
        this.setAge(age);
        this.setGender(gender);
    }

    // Getters
    /**
     * @return the name of the Sportsman
     */
    public String getName() {
        return name;
    }
    /**
     * @return the age of the Sportsman
     */
    public double getAge() {
        return age;
    }
    /**
     * @return the gender of the Sportsman
     */
    public Gender getGender() {
        return gender;
    }

    // End of Getters

    @Override
    public String toString() {
        return "Sportsman " + getName();

    }

    /**
     * @param name of the sportsman
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param age of the sportsman
     */
    public void setAge(double age) {
        this.age = age;
    }

    /**
     * @param gender of the sportsman
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
