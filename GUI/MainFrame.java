package GUI;

import game.arena.ArenaFactory;
import game.arena.IArena;
import game.arena.WinterArena;
import game.competition.*;
import game.entities.MobileEntity;
import game.entities.sportsman.*;
import game.enums.*;
import GUI.GameExceptions.*;
import game.state.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @author Saar Dias 311427496 Nitay Malka 304931801
 * implementation of a runnable frame that use for the visuals of the game
 */
public class MainFrame implements Runnable,ActionListener , Observer {
    private JFrame frame;
    private ImagePanel MainPanel;
    private SidePanel SideBar;

    private InfoTable infoTable;

    private IArena arena = null ;
    private WinterCompetition competition = null ;
    private WinterSportsman currentPrototpye = null;

    private double startTime;


    /**
     * Default Ctor for a the Main Frame
     */
    public MainFrame(){
        frame = new JFrame("Competition");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1000,950));
        frame.setLayout(new BorderLayout());
        MainPanel = new ImagePanel();
        SideBar = new SidePanel();
        frame.add(MainPanel,BorderLayout.CENTER);
        frame.add(SideBar,BorderLayout.EAST);
        frame.setVisible(true);



        SideBar.getBuildArenaButton().addActionListener(this);
        SideBar.getCreateCompetitionButton().addActionListener(this);
        SideBar.getAddCompetitor().addActionListener(this);
        SideBar.getStartCompetitionButton().addActionListener(this);
        SideBar.getShowInfoButton().addActionListener(this);
        SideBar.getMkeCopies().addActionListener(this);
        SideBar.getQuickCompetition().addActionListener(this);
        SideBar.getAddColor().addActionListener(this);
        SideBar.getAddAcceleration().addActionListener(this);


    }
    /**
     * function for setting up the image of the Arena on the Main panel
     * @throws IOException if there is problem with image
     */
    private void setUpArenaImg() throws IOException {
        MainPanel.setUpImage(this.arena.getCondition());
        frame.setSize(new Dimension(1000,(int)(this.arena.getLength()*1.2)));
        frame.invalidate();
        frame.revalidate();
        frame.repaint();
    }
    /**
     * function for setting up the image of the competitors on the Main panel
     * @param kind the kind of the competitors of the current competition
     * @throws IOException if there is problem with image
     */
    private void setUpCompetitorsImg(String kind) throws IOException {
        frame.invalidate();
        frame.revalidate();
        frame.repaint();
    }
    /**
     * delete the current competition
     */
    private void DeleteCompetition(){
        this.competition = null;
        MainPanel.DeleteCompetition();
    }

    //GETTERS
    /**
     * @return the arena of the competition
     */
    public IArena getArena() {
        return arena;
    }
    /**
     * @return the current competition
     */
    public WinterCompetition getCompetition() {
        return competition;
    }
    //GETTERS END
    //SETTERS
    /**
     * setting the arena of the competition in the class
     * @param arena winter arena for the competition that the user create
     */
    public void setArena(IArena arena) {
        this.arena = arena;
    }
    /**
     * setting the competition in the class
     * @param competition winter competition that the user create
     */
    public void setCompetition(WinterCompetition competition) {
        this.competition = competition;
    }
    //SETTERS END

    /**
     * check if given id is already used by another racer
     * @param id to check
     * @return true if id is valid otherwise false
     */
    private boolean validId(int id){
        for( Competitor racer : this.competition.getActiveCompetitors() )
        {
            if(racer.getId() == id){
                return false;
            }
        }
        return true;
    }

    /**
     * method that generate random id for a competitor
     * @return random id
     */
    private int generateRandomId(){
        int randomId = new Random().nextInt(20);
        while(!validId(randomId)){
            randomId = new Random().nextInt(20);
        }
        return randomId;
    }

    /**
     * method for adding a competitor to the current competition
     * @param competitor competitor to add
     */
    public void addToCompetition(WinterSportsman competitor ){
        Random Rand = new Random();
        State active = new ActiveState();
        State injured = new InjuredState();
        State disabled = new DisabledState();
        active.changeState(competitor);
        int opt = Rand.nextInt(20);
        if(opt <3){
            injured.changeFutureState(competitor);
            competitor.setChangePoint(Rand.nextInt((int)this.arena.getLength()));
        }
        else if(opt >18) {
            disabled.changeFutureState(competitor);
            competitor.setChangePoint(Rand.nextInt((int) this.arena.getLength()));
        }
        else{
            active.changeFutureState(competitor);
            competitor.setChangePoint(0);
        }
        this.competition.addCompetitor(competitor);
        competitor.addObserver(this);
        this.MainPanel.repaint();
    }

    /**
     * method for starting the competition
     */
    private void startCompetition(){
        startTime = System.currentTimeMillis();
        this.competition.startRace();
    }

    /**
     * refreshing the Main panel every 30 milliseconds
     */
    @Override
    public void run() {
        while(true){

        }
    }
    /**
     * Activating the game
     */
    public static void main(String[] args) {
        MainFrame Screen = new MainFrame();
        Thread t = new Thread(Screen);
        t.start();
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        // Event For making quick competition ------- Builder ----
        if(event.getSource() == SideBar.getQuickCompetition()){

            int N = Integer.parseInt(JOptionPane.showInputDialog("enter number of competitors:"));
            if( N > 20 || N < 0){
                JOptionPane.showMessageDialog(null,"invalid number.");
                return;
            }
            SkiCompetitionBuilder builder =  new SkiCompetitionBuilder();
            SkiCompetition skiCompetition;
            builder.buildCompetition(20);
            builder.buildDiscipline();
            builder.buildLeague();
            builder.buildGender();
            builder.buildArena();
            skiCompetition = builder.getSkiCompetition();
            this.competition = skiCompetition;
            this.arena = skiCompetition.getArena();
            builder.buildCompetitiors(this,N);



            MainPanel.setActiveCompetition(getCompetition());
            for ( Competitor winterSportsman : this.getCompetition().getActiveCompetitors()){
                //this.addToCompetition((WinterSportsman) winterSportsman);
                ((MobileEntity)winterSportsman).addObserver(this);
                this.MainPanel.repaint();
            }

            try {
                setUpArenaImg();
                setUpCompetitorsImg(SideBar.getChooseCompetitionCombo().getSelectedItem().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(competition.getActiveCompetitors().size() > 10){
                int toAdd = (competition.getActiveCompetitors().size()-10)*50;
                frame.setSize(new Dimension(1000+toAdd,(int)(arena.getLength()*1.2)));
            }
        }
        // Event For making copies ------ USAGE OF PROTOTYPE -----
        if(event.getSource() == SideBar.getMkeCopies()){
            try {
                int num = Integer.parseInt(JOptionPane.showInputDialog("enter number of copies:"));
                if( num > this.competition.getMaxCompetitors() - this.competition.getActiveCompetitors().size()){
                    JOptionPane.showMessageDialog(null,"number is too big.");
                    return;
                }
                int idToCopy = Integer.parseInt(JOptionPane.showInputDialog("enter id of competitor:"));
                for(Competitor racer : competition.getActiveCompetitors()){
                    if(idToCopy == racer.getId()){
                        this.currentPrototpye = (WinterSportsman) racer;
                    }
                }

                if(currentPrototpye == null){
                    JOptionPane.showMessageDialog(null,"invalid id.");
                    return;
                }

                ClonesMaker cloner = new ClonesMaker();

                if(currentPrototpye instanceof Skier)
                {
                    int randomIndex = new Random().nextInt(SideBar.getColorCombo().getItemCount());
                    int id;
                    for(int i = 0 ; i< num ; i++){
                        id = generateRandomId();
                        Skier basicSkier = ((Skier) currentPrototpye).clone();
                        Skier newSkier = cloner.makeSkierClone(basicSkier,id,SideBar.getColorCombo().getItemAt(randomIndex));
                        addToCompetition(newSkier);

                        newSkier.setArena((WinterArena) getCompetition().getArena());
                    }
                }
                if(currentPrototpye instanceof Snowboarder)
                {
                    int randomIndex = new Random().nextInt(SideBar.getColorCombo().getItemCount());
                    int id = generateRandomId();
                    for(int i = 0 ; i< num ; i++){
                        Snowboarder basicSnowboarder = ((Snowboarder) currentPrototpye).clone();
                        Snowboarder newSnowBoarder = cloner.makeSnowboarderClone(basicSnowboarder,id,SideBar.getColorCombo().getItemAt(randomIndex));
                        addToCompetition(newSnowBoarder);
                        //getCompetition().addCompetitor(newSnowBoarder);
                        //newSnowBoarder.addObserver(this);
                        //this.MainPanel.repaint();
                        newSnowBoarder.setArena((WinterArena) getCompetition().getArena());
                    }
                }

                if(competition.getActiveCompetitors().size() > 10){
                    int toAdd = (competition.getActiveCompetitors().size()-10)*50;
                    frame.setSize(new Dimension(1000+toAdd,(int)(arena.getLength()*1.2)));
                }
            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null,"invalid input values! try again.");
            }

        }
        // Event For Creating Arena
        if(event.getSource() == SideBar.getBuildArenaButton()){
            int length = 700;
            SnowSurface surface = null;
            WeatherCondition weatherCondition = null;
            try {
                length = Integer.parseInt(SideBar.getArenaLengthText().getText());
                if (length < 700 || length >900){
                    throw new IllegalArgumentException();
                }

                switch (SideBar.getSnowSurfaceCombo().getSelectedItem().toString()){
                    case "POWDER": surface = SnowSurface.POWDER;
                        break;
                    case "CRUD" : surface = SnowSurface.CRUD;
                        break;
                    case "ICE" : surface = SnowSurface.ICE;
                    default:
                        break;
                }
                switch (SideBar.getWeatherConditionCombo().getSelectedItem().toString()){
                    case "SUNNY": weatherCondition = WeatherCondition.SUNNY;
                        break;
                    case "CLOUDY": weatherCondition = WeatherCondition.CLOUDY;
                        break;
                    case "STORMY": weatherCondition = WeatherCondition.STORMY;
                    default:
                        break;
                }
                ArenaFactory arenaFactory = new ArenaFactory();
                IArena arena = arenaFactory.getArena(SideBar.getArenaType().getSelectedItem().toString());
                arena.setLength(length);
                arena.setSurface(surface);
                arena.setCondition(weatherCondition);

                this.arena = arena;

                setUpArenaImg();

                if(getCompetition()!=null){
                    DeleteCompetition();
                }
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null,"invalid input values! try again.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Event For Creating Competition
        if(event.getSource() == SideBar.getCreateCompetitionButton()){
            WinterCompetition winterCompetition;
            int maxComp = 10;
            IArena arena =null;
            Discipline discipline = null;
            League league = null;
            Gender gender = null;
            try {
                maxComp = Integer.parseInt(SideBar.getMaxCompetitorsText().getText());
                arena = getArena();
                if (arena == null) {
                    throw new NoArenaException();
                }
                if (maxComp < 1 || maxComp > 20) {
                    throw new IllegalArgumentException();
                }
                        /*
                          Creating instance with dynamic loading here
                         */
                String path = "game.competition.";
                String type = "";
                Class c;
                ClassLoader cl = ClassLoader.getSystemClassLoader();
                switch (SideBar.getChooseCompetitionCombo().getSelectedItem().toString()){
                    case "Ski": type = path + "SkiCompetition";
                        break;
                    case "Snowboard": type = path + "SnowboardCompetition";
                        break;
                    default:
                        break;
                }
                //GETTING DISCIPLINE
                switch (SideBar.getDisciplineCombo().getSelectedItem().toString()){
                    case "SLALOM": discipline = Discipline.SLALOM;
                        break;
                    case "GIANT SLALOM": discipline = Discipline.GIANT_SLALOM;
                        break;
                    case "DOWNHILL":discipline = Discipline.DOWNHILL;
                        break;
                    case "FREESTYLE": discipline = Discipline.FREESTYLE;
                    default:
                        break;
                }
                //GETTING LEAGUE
                switch(SideBar.getLeagueCombo().getSelectedItem().toString()){
                    case "JUNIOR": league = League.JUNIOR;
                        break;
                    case"ADULT": league = League.ADULT;
                        break;
                    case "SENIOR": league = League.SENIOR;
                        break;
                    default:
                        break;
                }
                //GETTING GENDER
                switch(SideBar.getGenderCombo().getSelectedItem().toString()){
                    case "FEMALE": gender = Gender.FEMALE;
                        break;
                    case "MALE": gender = Gender.MALE;
                        break;
                    default:
                        break;
                }

                c = cl.loadClass(type);
                Constructor con = c.getConstructor(IArena.class,int.class,
                        Discipline.class,League.class,Gender.class);
                winterCompetition = (WinterCompetition)con.newInstance(arena,maxComp,discipline,league,gender);
                setCompetition(winterCompetition);
                MainPanel.setActiveCompetition(getCompetition());
                setUpCompetitorsImg(SideBar.getChooseCompetitionCombo().getSelectedItem().toString());


            } catch (IllegalArgumentException e){
                JOptionPane.showMessageDialog(null,"invalid input values! try again.");
            }
            catch (NoArenaException e) {
                JOptionPane.showMessageDialog(null,"Please create arena first.");
            } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException | IOException e) {
                e.printStackTrace();
            }
        }
        // Event For Creating Competitor
        if(event.getSource() == SideBar.getAddCompetitor() ){
            String name;
            double age;
            Gender gender;
            double acceleration;
            double maxSpeed;
            int id;
            String color;
            Discipline discipline;
            Competition competition = getCompetition();
            WinterSportsman winterSportsman;
            try {
                if (competition == null) {
                    throw new NoCompetitionException();
                }
                if (getCompetition().getMaxCompetitors()<=getCompetition().getActiveCompetitors().size()){
                    throw new FullCompetition();
                }
                if(getCompetition().isStarted()){
                    throw new RaceStartedException();
                }
                id = Integer.parseInt(SideBar.getIdCompetitorText().getText());
                for( Competitor racer : this.competition.getActiveCompetitors() )
                {
                    if(racer.getId() == id){
                        JOptionPane.showMessageDialog(null,"enter new id please.");
                        return;
                    }
                }
                age = Double.parseDouble(SideBar.getAgeText().getText());
                acceleration = Double.parseDouble(SideBar.getAccelerationText().getText());
                maxSpeed = Double.parseDouble(SideBar.getMAXSpeedText().getText());
                name = SideBar.getNameText().getText();
                gender = getCompetition().getGender();
                discipline = getCompetition().getDiscipline();

                color = SideBar.getColorCombo().getSelectedItem().toString();

                /*
                   Creating instance with dynamic loading here
                */
                String path = "game.entities.sportsman.";
                String type = "";
                Class c;
                ClassLoader cl = ClassLoader.getSystemClassLoader();

                switch (getCompetition().getClass().getSimpleName()){
                    case "SkiCompetition": type = path + "Skier";
                        break;
                    case "SnowboardCompetition": type = path + "Snowboarder";
                        break;
                    default:
                        break;
                }
                c = cl.loadClass(type);
                Constructor con = c.getConstructor(String.class,double.class,Gender.class,double.class,double.class,Discipline.class,int.class,String.class);
                winterSportsman = (WinterSportsman) con.newInstance(name, age, gender, acceleration, maxSpeed, discipline, id, color);
                addToCompetition(winterSportsman);
                //winterSportsman.addObserver(this);
                //this.MainPanel.repaint();
                //getCompetition().addCompetitor(winterSportsman);
                winterSportsman.setArena((WinterArena) getCompetition().getArena());


                if(competition.getActiveCompetitors().size()>10){
                    int toAdd = (competition.getActiveCompetitors().size()-10)*50;
                    frame.setSize(new Dimension(1000+toAdd,(int)(arena.getLength()*1.2)));
                }

            }catch (NoCompetitionException e){
                JOptionPane.showMessageDialog(null,"Please create a competition.");
            }
            catch (FullCompetition e){
                JOptionPane.showMessageDialog(null,"No space in competition.");
            }
            catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null,"invalid input values! try again.");
            }
            catch (IllegalArgumentException e){
                JOptionPane.showMessageDialog(null,"Competitor does not fit! enter another.");
            } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            } catch (RaceStartedException e) {
                JOptionPane.showMessageDialog(null,"Can't start race while running.");
            }
        }
        // Event For Starting Competition
        if(event.getSource() == SideBar.getStartCompetitionButton()){
            try {
                if(getArena() == null){
                    throw new NoArenaException();
                }
                if(getCompetition() == null){
                    throw  new NoCompetitionException();
                }
                if(getCompetition().getActiveCompetitors().size() == 0){
                    throw new NoCompetitorsException();
                }
                if(getCompetition().isStarted()){
                    throw new RaceStartedException();
                }
                startCompetition();

            } catch (NoArenaException e) {
                JOptionPane.showMessageDialog(null,"Please create an arena.");
            } catch (NoCompetitionException e) {
                JOptionPane.showMessageDialog(null,"Please create a competition.");
            } catch (NoCompetitorsException e) {
                JOptionPane.showMessageDialog(null,"Please add competitors.");
            } catch (RaceStartedException e) {
                JOptionPane.showMessageDialog(null,"Can't start race while running.");
            }
        }
        // Event For Show Info
        if(event.getSource() == SideBar.getShowInfoButton()){

            try {
                if(getArena() == null){
                    throw new NoArenaException();
                }
                if(getCompetition() == null){
                    throw  new NoCompetitionException();
                }
                if (competition.getActiveCompetitors().size() == 0
                        && competition.getFinishedCompetitors().size() == 0) {
                    throw new NoCompetitorsException();
                }
                showInfo();
            } catch (NoCompetitorsException e) {
                JOptionPane.showMessageDialog(null,"Empty competition.");
            } catch (NoCompetitionException e) {
                JOptionPane.showMessageDialog(null,"Please create a competition competition.");
            } catch (NoArenaException e) {
                JOptionPane.showMessageDialog(null,"Please create an arena.");
            }

        }
        // Event For add acceleration
        if(event.getSource() == SideBar.getAddAcceleration()){
            IWinterSportsman winterSportsman = null;
            int idToCopy = Integer.parseInt(JOptionPane.showInputDialog("enter id of competitor:"));
            for (Competitor racer : competition.getActiveCompetitors()) {
                if (idToCopy == racer.getId()) {
                    winterSportsman = (WinterSportsman) racer;
                }
            }
            double acc = Double.parseDouble(JOptionPane.showInputDialog("enter Acceleration to add:"));
            if( acc > 50){
                JOptionPane.showMessageDialog(null,"Too much!");
                return;
            }
            if( winterSportsman == null){
                JOptionPane.showMessageDialog(null,"Invalid id.");
                return;
            }
            winterSportsman = new SpeedySportsman(winterSportsman,acc);
            ((SpeedySportsman)winterSportsman).addAcceleration();


        }
        // Event For add Color
        if(event.getSource() == SideBar.getAddColor()) {
            IWinterSportsman winterSportsman = null;
            int idToCopy = Integer.parseInt(JOptionPane.showInputDialog("enter id of competitor:"));
            for (Competitor racer : competition.getActiveCompetitors()) {
                if (idToCopy == racer.getId()) {
                    winterSportsman = (WinterSportsman) racer;
                }
                }
            if( winterSportsman == null){
                JOptionPane.showMessageDialog(null,"Invalid id.");
                return;
            }
            winterSportsman = new ColoredSportsman(winterSportsman,SideBar.getColorCombo().getSelectedItem().toString());
            ((ColoredSportsman)winterSportsman).addColor();
            this.MainPanel.repaint();

        }
    }

    /**
     * method for opening the info table
     */
    public  void showInfo(){
        if (infoTable != null)
            infoTable.dispose();

        infoTable = new InfoTable(competition,competition.getActiveCompetitors().size()
                +competition.getFinishedCompetitors().size());
    }

    /**
     * update method that repaint the image panel each time  being triggered
     * if the current winter sportsamn is in state injured - opening a window with the time
     * @param o the triggering competitior
     * @param arg
     */
    @Override
    public synchronized void  update(Observable o, Object arg) {
        this.MainPanel.repaint();
        if(((WinterSportsman)o).getState() instanceof InjuredState){
            double currentTime = System.currentTimeMillis();
            double time = (currentTime - this.startTime )/1000;
            JOptionPane.showMessageDialog(null,"racer " +((WinterSportsman)o).getName() +
                    " is injured! after " + time + "sec.");
        }
    }
}
