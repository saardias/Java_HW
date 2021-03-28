package game.competition;

import game.arena.IArena;
import game.entities.sportsman.Snowboarder;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;

/**
 * @author Saar Dias 311427496 Nitay Malka 304931801
 * representation of a snowboard competition
 */
public class SnowboardCompetition extends WinterCompetition {
    /**
     * Ctor for a SnowboardCompetition
     * @param arena          arena of the SnowboardCompetition
     * @param maxCompetitors maximum number of competitors in SnowboardCompetition
     * @param discipline     discipline of the SnowboardCompetition
     * @param league         the kind of league of the SnowboardCompetition
     * @param gender         the gender of SnowboardCompetition
     * @throws IllegalArgumentException if any of the params is null
     */
    public SnowboardCompetition(IArena arena, int maxCompetitors, Discipline discipline, League league, Gender gender) throws IllegalArgumentException {
        super(arena, maxCompetitors, discipline, league, gender);
    }
    /**
     * @param competitor competitor to check
     * @return true if competitor is valid and can enter the race, otherwise returns false
     */
    @Override
    protected boolean isValidCompetitor(Competitor competitor) {
        return competitor instanceof Snowboarder && super.isValidCompetitor(competitor);
    }
}
