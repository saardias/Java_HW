package game.entities;

import utilities.Point;

/**
 * @author Saar Dias 311427496 Nitay Malka 304931801
 * interface that represent behavior of mobile entity
 */
public interface IMobileEntity {
    /**
     * move the a mobile entity
     * @param friction reduce acceleration by a factor of (1-friction)
     */
    public void move(double friction);
    /**
     * @return the location of the entity
     */
    public Point getLocation();
}
