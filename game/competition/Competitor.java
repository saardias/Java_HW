package game.competition;

import game.entities.IMobileEntity;

/**
 * @author Saar Dias 311427496 Nitay Malka 304931801
 * interface that represent a behavior of a competitor
 */
public interface Competitor extends IMobileEntity, Runnable,Cloneable {
    /**
     * start the race for this competitor
     */
    public void initRace(double x,double y);

    /**
     * get id of competitor
     * @return
     */
    int getId();
}
