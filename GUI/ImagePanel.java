package GUI;

import game.competition.Competitor;
import game.competition.WinterCompetition;
import game.entities.sportsman.WinterSportsman;
import game.enums.WeatherCondition;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Saar Dias 311427496 Nitay Malka 304931801
 * implementation pf a panel that contains images
 */
public class ImagePanel extends JPanel {
    private BufferedImage img;
    private WinterCompetition winterCompetition;
    public ImagePanel(){
        img = null;
        winterCompetition= null;
    }

    /**
     * method for setting the image of the arena on the image panel
     * @param condition indicate the needed image for the current competition
     * @throws IOException if there is a problem with the image
     */
    public void setUpImage(WeatherCondition condition) throws IOException {
        switch(condition){
            case SUNNY:
                img = ImageIO.read(new File("src\\Sunny.jpg"));
                break;
            case CLOUDY:
                img = ImageIO.read(new File("src\\Cloudy.jpg"));
                break;
            case STORMY:
                img = ImageIO.read(new File("src\\Stormy.jpg"));
                break;
            default:
                break;
        }
    }

    /**
     * drawing the image of the arena on the screen and drawing the images of the competitors
     * @param g graphics
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img,0,0,getWidth(),getHeight(),null);
        if(winterCompetition!=null) {
            for (Competitor competitor : winterCompetition.getActiveCompetitors()) {
                ((WinterSportsman)competitor).draw(g);
            }
            for (Competitor competitor : winterCompetition.getFinishedCompetitors()) {
                ((WinterSportsman)competitor).draw(g);
            }
        }
    }

    /**
     * setting the competition by reference
     * @param winterCompetition the current competition
     */
    public void setActiveCompetition(WinterCompetition winterCompetition){
        this.winterCompetition = winterCompetition;
    }

    /**
     * delete the current competition from class
     */
    public void DeleteCompetition(){
        winterCompetition = null;
    }

}
