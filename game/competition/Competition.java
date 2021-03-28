package game.competition;

import game.arena.IArena;
import game.entities.sportsman.Sportsman;
import game.entities.sportsman.WinterSportsman;
import utilities.ValidationUtils;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * @author Saar Dias 311427496 Nitay Malka 304931801
 * representation of a competition
 */
public abstract class Competition {
    private ArrayList<Competitor> activeCompetitors;
    private ArrayList<Competitor> finishedCompetitors;
    private IArena arena;
    private int maxCompetitors;
    private boolean started;
    /**
     * Ctor for a Competition
     * @param arena arena of the competition
     * @param maxCompetitors maximum number of competitors in competition
     * @throws IllegalArgumentException if arena or maxCompetitors is Null
     */
    public Competition(IArena arena, int maxCompetitors) throws IllegalArgumentException{
        ValidationUtils.assertNotNull(arena);
        ValidationUtils.assertPositive(maxCompetitors);
        this.arena = arena;
        this.maxCompetitors = maxCompetitors;
        this.activeCompetitors = new ArrayList<>();
        this.finishedCompetitors = new ArrayList<>();
        this.started = false;
    }

    /**
     * Copy Ctor for a Competition
     * @param other competition to copy
     * @throws IllegalArgumentException if arena is null or maxcompetition is negative
     */
    public Competition(Competition other) throws IllegalArgumentException{
        ValidationUtils.assertNotNull(other.arena);
        ValidationUtils.assertPositive(other.maxCompetitors);
        this.arena = other.arena;
        this.maxCompetitors = other.maxCompetitors;
        this.activeCompetitors = new ArrayList<>(other.activeCompetitors);
        this.finishedCompetitors = new ArrayList<>(other.finishedCompetitors);
        this.started = false;
    }

    /**
     * default c'tor for class
     */
    public Competition(){
        this.activeCompetitors = new ArrayList<>();
        this.finishedCompetitors = new ArrayList<>();
        this.started = false;
    }
    /**
     * Validate if a competitor can compete
     * @param competitor competitor to check
     * @return true if competitor can join the competition or false if not
     */
    protected abstract boolean isValidCompetitor(Competitor competitor);
    /**
     *
     * @param competitor competitor to add to active competitors
     * @throws IllegalArgumentException if competitor id not valid to join competition
     * @throws IllegalStateException if activeCompetitors is full
     */
    public void addCompetitor(WinterSportsman competitor) throws IllegalArgumentException , IllegalStateException  {
        ValidationUtils.assertNotNull(competitor);
        if(!this.finishedCompetitors.isEmpty()){
            this.finishedCompetitors = new ArrayList<>();
        }
        if(this.activeCompetitors.size() >= this.maxCompetitors){
            throw new IllegalStateException(this.getArena() +" is full max = " + this.getMaxCompetitors());
        }
        if(!isValidCompetitor(competitor)){
            throw new IllegalArgumentException("Invalid competitor " + competitor.toString());
        }
        competitor.initRace(this.getActiveCompetitors().size()*60,0);
        this.activeCompetitors.add(competitor);
    }
    /**
     *  check if activeCompetitors is empty
     * @return true if activeCompetitors is empty or false if not
     */
    public boolean hasActiveCompetitors(){
        return this.activeCompetitors.size() > 0;
    }
    /**
     * moves each competitor in the race and checks if a competitor finished the race
     */
    public void playTurn(){
        ArrayList<Competitor> temp = new ArrayList<>(this.activeCompetitors);
        for (Competitor competitor:temp){
            competitor.move(arena.getFriction());
            if(arena.isFinished(competitor)){
                this.activeCompetitors.remove(competitor);
                this.finishedCompetitors.add(competitor);
            }
        }
    }

    /**
     * @return activeCompetitors array
     */
    public ArrayList<Competitor> getActiveCompetitors() {
        return new ArrayList<>(activeCompetitors); // return activeCompetitors;
    }

    /**
     *
     * @return finishedCompetitors array
     */
    public ArrayList<Competitor> getFinishedCompetitors() {
        return new ArrayList<>(finishedCompetitors); // return finishedCompetitors;
    }

    /**
     * @return the arena of the competition
     */
    public IArena getArena() {
        return arena;
    }

    /**
     * @return max number of competitors
     */
    public int getMaxCompetitors() {
        return maxCompetitors;
    }

    /**
     * start the race by running all the threads
     */
    public void startRace(){
        this.started = true;
        for(Competitor competitor : activeCompetitors){
            new Thread(competitor).start();
        }
    }

    /**
     * if race is finished and started is true, turning it to false
     */
    public void checkIfFinished(){
        if(this.started) {
            if (this.getActiveCompetitors().isEmpty() && !this.finishedCompetitors.isEmpty()){
                this.started = false;
            }
        }
    }

    /**
     * @return started - indicator of running race
     */
    public boolean isStarted() {
        return started;
    }

    /**
     * @param arena arena of the competition
     */
    public void setArena(IArena arena) {
        this.arena = arena;
    }

    /**
     * @param maxCompetitors max amount of competitors
     */
    public void setMaxCompetitors(int maxCompetitors) {
        this.maxCompetitors = maxCompetitors;
    }
}
