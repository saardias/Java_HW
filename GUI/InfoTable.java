package GUI;

import game.competition.Competition;
import game.competition.Competitor;
import game.entities.sportsman.WinterSportsman;
import game.state.ActiveState;
import game.state.CompletedState;
import game.state.DisabledState;
import game.state.InjuredState;

import javax.swing.*;
import java.util.ArrayList;
/**
 * @author Saar Dias 311427496 Nitay Malka 304931801
 * representation of Info Table
 */
public class InfoTable extends JFrame{
    /**
     * Ctor of the Info Table that creates the table for the competitors in the current competition
     * @param competition the current competition
     * @param racersNumber the number of competitors in competition
     */
    public InfoTable(Competition competition, int racersNumber){
        //  the headlines of the table
        super("Competitors information");
        String[] columnNames = {"Name",
                            "ID",
                             "Acceleration" ,
                             "Speed",
                             "Max speed",
                             "Location",
                             "State",
                             };
        //  the info of the table
        String[][] data = new String[racersNumber][7];
        int i;
        ArrayList<WinterSportsman> temp = new ArrayList<WinterSportsman>();
        // adding the competitors of the competition to  the array list of the table
        //adding competitors with complete state
        for (Competitor comp : competition.getActiveCompetitors()) {
            if(((WinterSportsman) comp).getState() instanceof CompletedState) {
                temp.add((WinterSportsman) comp);
            }
        }
        //adding competitors with active state
        for (Competitor comp : competition.getActiveCompetitors()) {
            if(((WinterSportsman) comp).getState() instanceof ActiveState) {
                temp.add((WinterSportsman) comp);
            }
        }
        //sorting the array
        for (i = 0; i < temp.size() - 1; i++) {
            WinterSportsman current = temp.get(i);
            WinterSportsman next = temp.get(i + 1);
            if (current.getLocation().getY() < next.getLocation().getY()) {
                WinterSportsman tempRacer = temp.get(i);
                temp.set(i,temp.get(i+1));
                temp.set(i+1,tempRacer);
            }
        }
        //adding competitors with injured state
        for (Competitor comp : competition.getActiveCompetitors()) {
            if(((WinterSportsman) comp).getState() instanceof InjuredState) {
                temp.add((WinterSportsman) comp);
            }
        }
        //adding competitors with disabled state
        for (Competitor comp : competition.getActiveCompetitors()) {
            if(((WinterSportsman) comp).getState() instanceof DisabledState) {
                temp.add((WinterSportsman) comp);
            }
        }
        //getting the info from the array  to the data
        for (i = 0; i < temp.size(); i++) {
            data[i][0] = temp.get(i).getName();
            data[i][1] = String.valueOf(temp.get(i).getId());
            data[i][2] = String.valueOf(temp.get(i).getAcceleration());
            data[i][3] = String.valueOf(temp.get(i).getSpeed());
            data[i][4] = String.valueOf(temp.get(i).getMaxSpeed());
            data[i][5] = String.valueOf(temp.get(i).getLocation().getY());
            data[i][6] = temp.get(i).getState().toString();
        }

        //creating JTable with the info
        JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel tabPan = new JPanel();
        tabPan.add(scrollPane);                   

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(tabPan);
        pack();
        setVisible(true); 
    }
}