package game.arena;

/**
 * @author Saar Dias 311427496 Nitay Malka 304931801
 * representation of Arena Factory - for creating IARena objects
 */
public class ArenaFactory {
    /**
     * @param arenaType string representing the type of the arena
     * @return IAena object
     */
    public IArena getArena(String arenaType){
        IArena arena = null;
        if(arenaType.equals("Winter")){
            arena = new WinterArena();
        }
        else if(arenaType.equals("Summer")){
            arena =  new SummreArena();
        }
        return arena;
    }
}
