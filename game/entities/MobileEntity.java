package game.entities;

import utilities.Point;

/**
 * @author Saar Dias 311427496 Nitay Malka 304931801
 * A representation of a mobile entity
 */
public abstract class MobileEntity extends Entity implements IMobileEntity {
    private double maxSpeed;
    protected double acceleration;
    protected double speed;
    protected double oldYplace;

    /**
     * Ctor for a Mobile Entity
     * @param maxSpeed entity's max speed
     * @param acceleration  entity's acceleration
     */
    public MobileEntity(double maxSpeed, double acceleration) {
        //super();
        this.setMaxSpeed(maxSpeed);
        this.acceleration = acceleration;
        this.speed = 0;
    }

    // Getters
    /**
     * @return the maxSpeed of the Entity
     */
    public double getMaxSpeed() {
        return maxSpeed;
    }
    /**
     * @return the acceleration of the Entity
     */
    public double getAcceleration() {
        return acceleration;
    }
    /**
     * @return the speed of the Entity
     */
    public double getSpeed() {
        return speed;
    }
    // end Getters

    /**
     * method for moving the entity by changing the Y value of the location of the entity
     * @param friction reduce acceleration by a factor of (1-friction)
     */
    @Override
    public void move(double friction) {
        this.oldYplace = this.getLocation().getY();
        if(this.speed < this.getMaxSpeed()){
            this.speed = Math.min(this.getSpeed()+(1-friction)*this.getAcceleration(),this.getMaxSpeed());
        }
        this.setLocation(this.getLocation().offset(0,this.speed));
        if( oldYplace < getLocation().getY()){
            setChanged();
            notifyObservers();
        }

    }

    /**
     * @return the location of the entity
     */
    @Override
    public Point getLocation() {
        return super.getLocation();
    }
    /**
     * setting the Max Speed of the entity
     */
    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}
