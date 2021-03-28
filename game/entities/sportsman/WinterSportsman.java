package game.entities.sportsman;

import game.arena.IArena;
import game.competition.Competitor;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;
import game.state.*;
import utilities.Point;
import utilities.ValidationUtils;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * @author Saar Dias 311427496 Nitay Malka 304931801
 * representation of a Winter Sportsman
 */
public abstract class WinterSportsman extends Sportsman implements Competitor , IWinterSportsman {

    private Discipline discipline;
    private IArena arena;
    protected int id;
    protected String color;
    protected BufferedImage img;
    private State surrentState;
    private State FutureState;
    private double changePoint;

    /**
     * Ctor for a Mobile WinterSportsman
     * @param name name of Sportsman
     * @param age age of Sportsman
     * @param gender gender of Sportsman
     * @param maxSpeed max speed of Sportsman
     * @param acceleration acceleration of Sportsman
     * @param discipline discipline of Sportsman
     */
    public WinterSportsman(String name, double age, Gender gender, double acceleration,
                           double maxSpeed,Discipline discipline,int id) throws IllegalArgumentException {
        super(name, age, gender, acceleration, maxSpeed);
        ValidationUtils.assertNotNull(discipline);
        ValidationUtils.assertNotNull(id);
        this.discipline = discipline;
        this.id = id;
        this.setState(new ActiveState());
        if(gender == Gender.MALE) {
            this.color = "Blue";
        }
        else if(gender == Gender.FEMALE){
            this.color = "Pink";
        }
    }
    /**
     * copy Ctor for a Mobile WinterSportsman
     * @param other WinterSportsman to copy
     */
    public WinterSportsman(WinterSportsman other) throws IllegalArgumentException {
        super(other.getName(), other.getAge(), other.getGender(), other.acceleration, other.getMaxSpeed());
        this.discipline = other.discipline;
    }
    /**
     * set the initial location of the Winter Sportsman in a race
     */
    @Override
    public void initRace(double x,double y) {
        this.setLocation(new Point(x,y));
    }
    /**
     * @return the acceleration of the WinterSportsman with a bonus
     */
    @Override
    public double getAcceleration(){
        return super.getAcceleration() + League.calcAccelerationBonus(this.getAge());
    }
    /**
     * @return the acceleration of the Winter sportsman without the bonus
     */
    public double getActualAcceleration(){
        return this.acceleration;
    }
    // Getters
    /**
     * @return discipline of the WinterSportsman
     */
    public Discipline getDiscipline() {
        return discipline;
    }

    /**
     * @return the arena of the competitor
     */
    public IArena getArena() {
        return arena;
    }
    /**
     * @return the id of the competitor
     */
    public int getId() {
        return id;
    }
    //End Of Getters
    @Override
    public String toString() {
        return "WinterSportsman " + this.getName();
    }
    /**
     * adding to the winter sportsman a competition
     * @param arena arena to add
     */
    public void setArena(IArena arena){
        this.arena = arena;
    }

    /**
     * @param color color of the winter sportsman
     */
    public void setColor(String color) {
        this.color = color;
    }
    /**
     * @param acc acceleration of the winter sportsman
     */
    public void setAcceleration(double acc) {
        this.acceleration = acc ;
    }
    /**
     * setting the image of the sportsman
     * @param color color of the image
     */
    public abstract void setImage(String color);
    /**
     * @return true if the winter sportsman finished the arena otherwise false
     */
    public boolean isFinished(){
        return this.arena.isFinished(this);
    }
    /**
     * run method that checks and update the state of the competitor if needed and also making his move
     */
    @Override
    public void run() {
        State changer;
        Random rnd = new Random();
        int odds;
        while(!arena.isFinished(this) && (this.surrentState instanceof ActiveState ||
                this.surrentState instanceof InjuredState)){
            if(this.surrentState instanceof InjuredState) {
                odds = rnd.nextInt(10);
                if(odds <2){
                    changer = new ActiveState();
                    changer.changeState(this);
                }
                else{
                    changer = new DisabledState();
                    changer.changeState(this);
                }
            }
            if(this.getFutureState() instanceof InjuredState){
                odds = rnd.nextInt(10);
                if(odds <4) {
                    if (this.getLocation().getY() > this.getChangePoint()) {
                        changer = new InjuredState();
                        changer.changeState(this);
                    }
                }
            }
            if(this.getFutureState() instanceof DisabledState){
                if(this.getLocation().getY() > this.getChangePoint()){
                    changer = new DisabledState();
                    changer.changeState(this);
                }
            }
            this.move(this.arena.getFriction());
            try{
                sleep(100);
            }catch(InterruptedException e){ }
        }
        if(this.isFinished()) {
            changer = new CompletedState();
            changer.changeState(this);
        }
        setChanged();
        notifyObservers();
    }

    /**
     * draw method for showing the image of the sportsman on the image panel
     * @param g
     */
    public void draw(Graphics g) {
        g.drawImage(this.img, (int) this.getLocation().getX(), (int) this.getLocation().getY()
                , 50, 50, null);
    }

    /**
     *
     * @return the state of the winter sportsman
     */
    public State getState() {
        return surrentState;
    }

    /**
     * @param state of the winter sportsman
     */
    public void setState(State state) {
        this.surrentState = state;
    }

    /**
     * @return the next state the winter sportsman may have
     */
    public State getFutureState() {
        return FutureState;
    }

    /**
     * @param futureState set the next sate of the winter sportsman
     */
    public void setFutureState(State futureState) {
        FutureState = futureState;
    }

    /**
     * @return get the point in which the winter sportsman changed his state
     */
    public double getChangePoint() {
        return changePoint;
    }

    /**
     * @param changePoint  point in which the winter sportsman changed his state
     */
    public void setChangePoint(double changePoint) {
        this.changePoint = changePoint;
    }


}
