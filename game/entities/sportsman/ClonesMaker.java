package game.entities.sportsman;

/**
 * @author Saar Dias 311427496 Nitay Malka 304931801
 * representation of a ClonesMaker
 */
public class ClonesMaker {
    /**
     * @param basicSkier a copy of another skier to update and change the id and color
     * @param id to change
     * @param color to change
     * @return the skier with the updated id and color
     */
    public Skier makeSkierClone(Skier basicSkier, int id,String color ){
        basicSkier.upgrade(id,color);
        return basicSkier;
    }
    /**
     * @param basicSnowboarder a copy of another Snowboarder to update and change the id and color
     * @param id to change
     * @param color to change
     * @return the Snowboarder with the updated id and color
     */
    public Snowboarder makeSnowboarderClone(Snowboarder basicSnowboarder, int id,String color ){
        basicSnowboarder.upgrade(id,color);
        return basicSnowboarder;
    }
}
