package game.entities;
import utilities.Point;
import utilities.ValidationUtils;

import java.util.Observable;

/**
 * @author Saar Dias 311427496 Nitay Malka 304931801
 * A representation of an entity
 */
public abstract class Entity extends Observable{
    private Point location;

    /**
     * Default Ctor for an entity sets location to be (0,0).
     */
    public Entity() {
        this.location = new Point(0,0);
    }
    /**
     * Ctor for an entity
     * @param location initial point
     */
    public Entity(Point location) throws IllegalArgumentException {
        this();
        ValidationUtils.assertNotNull(location);
        this.location = new Point(location);
    }

    // Getters
    /**
     * @return the location of the entity
     */
    public Point getLocation() {
        return this.location;
    }
    // End of Getters

    //Setters
    /**
     * setting the location of the entity
     * @param location a point representing location
     */
    public void setLocation(Point location) {
        ValidationUtils.assertNotNull(location);
        this.location = new Point(location);
    }
    //End of Setters
}
