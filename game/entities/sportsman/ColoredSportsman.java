package game.entities.sportsman;


/**
 * @author Saar Dias 311427496 Nitay Malka 304931801
 * representation of a ColoredSportsman
 */
public class ColoredSportsman extends WSDecorator {
    private String color;

    /**
     * Ctor of t coloredSportsman
     * @param winterSportsman the original winter sportsman
     * @param color the color to add to the decorator
     */
    public ColoredSportsman(IWinterSportsman winterSportsman,String color){
        super(winterSportsman);
        this.color = color;
    }

    /**
     * add color to the winter sportsman
     */
    public void addColor(){
        ((WinterSportsman)this.winterSportsman).setColor(this.color);
        ((WinterSportsman)this.winterSportsman).setImage(this.color);
    }


}
