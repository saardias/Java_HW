package game.competition;

import GUI.MainFrame;
/**
 * @author Saar Dias 311427496 Nitay Malka 304931801
 * interface that represent behavior of competition builder
 */
public interface CompetitionBuilder {
    public SkiCompetition getSkiCompetition();
    public void buildCompetition(int N);
    public void buildArena();
    public void buildDiscipline();
    public void buildLeague();
    public void buildGender();
    public void buildCompetitiors(MainFrame frame,int num);
}
