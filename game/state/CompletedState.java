package game.state;

import game.entities.sportsman.WinterSportsman;

/**
 * @author Saar Dias 311427496 Nitay Malka 304931801
 * representation of complete state
 */
public class CompletedState implements State {
    @Override
    public void changeState(WinterSportsman ctx) {
        ctx.setState(this);
    }
    @Override
    public void changeFutureState(WinterSportsman ctx) {
        ctx.setFutureState(this);
    }

    @Override
    public String toString() {
        return "Finished";
    }
}
