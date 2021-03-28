package game.competition;

import game.arena.IArena;
import game.entities.sportsman.WinterSportsman;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;
import utilities.ValidationUtils;

/**
 * representation of a winter competition
 */
public class WinterCompetition extends Competition{
    private Discipline discipline;
    private League league;
    private Gender gender;

    /**
     * Ctor for a WinterCompetition
     * @param arena arena of the competition
     * @param maxCompetitors maximum number of competitors in competition
     * @param discipline discipline of the competition
     * @param league the kind of league of the competition
     * @param gender the gender of competitors
     * @throws IllegalArgumentException if any of the params is null
     */
    public WinterCompetition(IArena arena, int maxCompetitors,Discipline discipline, League league,Gender gender) throws IllegalArgumentException {
        super(arena, maxCompetitors);
        ValidationUtils.assertNotNull(discipline);
        ValidationUtils.assertNotNull(league);
        ValidationUtils.assertNotNull(gender);
        this.discipline = discipline;
        this.gender = gender;
        this.league = league;
    }

    public WinterCompetition(){
        super();
        this.discipline = null;
        this.gender = null;
        this.league = null;

    }
    /**
     * check if competitor is valid for the WinterCompetition
     * @param competitor competitor to check
     * @return true if competitor is instance of WinterSportsman and age,gender,discipline equals to these of the competitor
     */

    @Override
    protected boolean isValidCompetitor(Competitor competitor){
        if(competitor instanceof WinterSportsman){
            WinterSportsman winterSportsman = (WinterSportsman) competitor;
            return discipline.equals(winterSportsman.getDiscipline()) &&
                    league.isInLeague(winterSportsman.getAge()) &&
                    gender.equals(winterSportsman.getGender());
        }
        return false;
    }

    // Getters
    /**
     * @return the discipline of the competition
     */
    public Discipline getDiscipline() {
        return discipline;
    }

    /**
     *
     * @return the league of the competition
     */
    public League getLeague() {
        return league;
    }

    /**
     * @return the gender of the competition
     */
    public Gender getGender() {
        return gender;
    }
    // End Of Getters

    // SETTERS

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }


    // END SETTERS
}
