package game.arena;

import game.entities.IMobileEntity;
import game.enums.SnowSurface;
import game.enums.WeatherCondition;

/**
 * @author Saar Dias 311427496 Nitay Malka 304931801
 * interface that represent behavior of arena
 */
public interface IArena {
    double getFriction();
    boolean isFinished(IMobileEntity mobileEntity);
    WeatherCondition getCondition();
    double getLength();
    void setLength(double length);
    void setSurface(SnowSurface surface);
    void setCondition(WeatherCondition condition);
}
