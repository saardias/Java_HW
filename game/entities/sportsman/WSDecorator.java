package game.entities.sportsman;

/**
 * @author Saar Dias 311427496 Nitay Malka 304931801
 * representation of a WSDecorator
 */
public abstract class WSDecorator implements IWinterSportsman {
    protected IWinterSportsman winterSportsman;
    WSDecorator(IWinterSportsman winterSportsman){
        this.winterSportsman = winterSportsman;
    }


}
