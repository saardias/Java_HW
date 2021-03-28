package game.competition;

import game.arena.IArena;
import game.entities.sportsman.Skier;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;

/**
 * @author Saar Dias 311427496 Nitay Malka 304931801
 * representation of a ski competition
 */
public class SkiCompetition extends WinterCompetition {
    /**
     * Ctor for a SkiCompetition
     * @param arena arena of the SkiCompetition
     * @param maxCompetitors maximum number of competitors in SkiCompetition
     * @param discipline discipline of the SkiCompetition
     * @param league the kind of league of the SkiCompetition
     * @param gender the gender of SkiCompetition
     * @throws IllegalArgumentException if any of the params is null
     */
    public SkiCompetition(IArena arena, int maxCompetitors, Discipline discipline, League league, Gender gender) throws IllegalArgumentException {
        super(arena, maxCompetitors, discipline, league, gender);
    }
    /**
     * default Ctor for SkiCompetition
     */
    public SkiCompetition(){
        super();
    }
    /**
     *
     * @param competitor competitor to check
     * @return true if competitor is valid and can enter the race, otherwise returns false
     */
    @Override
    protected boolean isValidCompetitor(Competitor competitor) {
        return competitor instanceof Skier && super.isValidCompetitor(competitor);
    }
}
