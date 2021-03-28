package game.arena;

import game.entities.IMobileEntity;
import game.enums.SnowSurface;
import game.enums.WeatherCondition;
import utilities.ValidationUtils;
/**
 * @author Saar Dias 311427496 Nitay Malka 304931801
 * representation of a Winter arena (not real class - never used)
 */
public class SummreArena implements IArena {
    private double length;
    private SnowSurface surface;
    private WeatherCondition condition;
    /**
     * Ctor for a SummerArena
     * @param length length of the arena
     * @param surface the kind of the surface of the arena
     * @param condition the weather condition of the arena
     * @throws IllegalArgumentException if surface or condition is null or if length is negative
     */
    public SummreArena(double length, SnowSurface surface, WeatherCondition condition) throws IllegalArgumentException{
        ValidationUtils.assertPositive(length);
        ValidationUtils.assertNotNull(surface);
        ValidationUtils.assertNotNull(condition);
        this.length = length;
        this.surface = surface;
        this.condition = condition;
    }
    /**
     * default c'tor for class
     */
    public SummreArena(){
        this.length = 0;
        this.surface = null;
        this. condition = null;
    }
    /**
     * copy c'tor for class
     * @param other object to copy
     */
    public SummreArena(SummreArena other){
        if(other!=null){
            this.length = other.length;
            this.surface = other.surface;
            this.condition = other.condition;
        }
    }
    /**
     * @return the friction of the surface
     */
    @Override
    public double getFriction() {
        return surface.getFriction();
    }
    /**
     * @param mobileEntity the given entity that is part of a race
     * @return true if the given mobile entity finished the race, if not false
     */
    @Override
    public boolean isFinished(IMobileEntity mobileEntity) {
        ValidationUtils.assertNotNull(mobileEntity);
        return mobileEntity.getLocation().getY() >= this.length;
    }
    // Getters
    /**
     *
     * @return the length of the arena
     */
    public double getLength() {
        return length;
    }
    /**
     *
     * @return the surface of the arena
     */
    public SnowSurface getSurface() {
        return surface;
    }
    /**
     *
     * @return the condition of the arena
     */
    public WeatherCondition getCondition() {
        return condition;
    }

    // End Getters
    // SETTERS
    @Override
    public void setLength(double length) {
        this.length = length;
    }
    @Override
    public void setSurface(SnowSurface surface) {
        this.surface = surface;
    }
    @Override
    public void setCondition(WeatherCondition condition) {
        this.condition = condition;
    }
    // END OF SETTERS
    @Override
    public String toString() {
        return "SummerArena";
    }
}
