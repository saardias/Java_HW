package game.competition;

import GUI.MainFrame;
import game.arena.ArenaFactory;
import game.arena.IArena;
import game.entities.sportsman.ClonesMaker;
import game.entities.sportsman.Skier;
import game.enums.*;
/**
 * @author Saar Dias 311427496 Nitay Malka 304931801
 * representation of a Ski Competition Builder
 */
public class SkiCompetitionBuilder implements CompetitionBuilder{
    private SkiCompetition skiCompetition;

    /**
     * @return ski competition
     */
    @Override
    public SkiCompetition getSkiCompetition() {
        return skiCompetition;
    }
    /**
     *  build competition with N as max Competitors
     * @param N number of max Competitors
     */
    @Override
    public void buildCompetition(int N) {
        skiCompetition = new SkiCompetition();
        skiCompetition.setMaxCompetitors(N);
    }

    /**
     * method for creating Winter Arena for the competition
     */
    @Override
    public void buildArena() {
        ArenaFactory arenaFactory = new ArenaFactory();
        IArena arena = arenaFactory.getArena("Winter");
        arena.setLength(800);
        arena.setSurface(SnowSurface.POWDER);
        arena.setCondition(WeatherCondition.SUNNY);
        skiCompetition.setArena(arena);
    }
    /**
     * method for adding discipline for the arena
     */
    @Override
    public void buildDiscipline() {
        skiCompetition.setDiscipline(Discipline.DOWNHILL);
    }
    /**
     * method for adding the league of the Competition
     */
    @Override
    public void buildLeague() {
        skiCompetition.setLeague(League.ADULT);
    }
    /**
     * method for adding the gender of the competition
     */
    @Override
    public void buildGender() {
        skiCompetition.setGender(Gender.MALE);
    }

    /**
     * method for adding competitors to competition
     * @param frame the frame of the Gui
     * @param num number of competitors to add
     */
    @Override
    public void buildCompetitiors(MainFrame frame,int num) {
        int id = 1;
        ClonesMaker clonesMaker = new ClonesMaker();
        Skier skier = new Skier("skier", 21, Gender.MALE, 0.5, 40,
                Discipline.DOWNHILL, id, "Black");
        skiCompetition.addCompetitor(skier);
        skier.setArena(skiCompetition.getArena());
        for (int i = 0; i < num-1; i++) {
            id++;
            Skier basicSkier = skier.clone();
            Skier newSkier = clonesMaker.makeSkierClone(basicSkier, id, "Black");
            frame.addToCompetition(newSkier);
            newSkier.setArena(skiCompetition.getArena());

        }
    }

}
