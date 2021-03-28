package game.entities.sportsman;

/**
 * @author Saar Dias 311427496 Nitay Malka 304931801
 * representation of a SpeedySportsman
 */
public class SpeedySportsman extends WSDecorator {
    private double acceleration;
    /**
     * Ctor of t coloredSportsman
     * @param winterSportsman the original winter sportsman
     * @param acceleration the acceleration to add to the decorator
     */
    public SpeedySportsman(IWinterSportsman winterSportsman, double acceleration){
        super(winterSportsman);
        this.acceleration = acceleration;
    }
    /**
     * add acceleration to the winter sportsman
     */
    public void addAcceleration(){
        ((WinterSportsman)this.winterSportsman).setAcceleration(
                ((WinterSportsman)this.winterSportsman).getActualAcceleration() +this.acceleration);
    }
}
