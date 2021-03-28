package game.state;

import game.entities.sportsman.WinterSportsman;

/**
 * @author Saar Dias 311427496 Nitay Malka 304931801
 * interface that represent a state
 */
public interface State {
    /**
     * method for changing the state of the given context
     * @param ctx
     */
    public void changeState(WinterSportsman ctx);
    /**
     * method for changing the next state of the given context
     * @param ctx
     */
    public void changeFutureState(WinterSportsman ctx);
}
