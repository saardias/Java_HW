package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * @author Saar Dias 311427496 Nitay Malka 304931801
 * representation of a Side panel
 */
class SidePanel extends JPanel {
    private JPanel menu;

    private Label buildArenaLabel;
    private Label arenaLengthLabel;
    private JTextField arenaLengthText;
    private Label snowSurfaceLabel;
    private JComboBox<String> snowSurfaceCombo;
    private Label weatherConditionLabel;
    private JComboBox<String> weatherConditionCombo;
    private JButton buildArenaButton;
    private JSeparator separator1;
    private Label createCompetitionLabel;
    private Label chooseCompetitionLabel;
    private JComboBox<String> chooseCompetitionCombo;
    private Label maxCompetitorsLabel;
    private JTextField maxCompetitorsText;
    private Label disciplineLabel;
    private JComboBox<String> disciplineCombo ;
    private Label leagueLabel;
    private JComboBox<String> leagueCombo;
    private Label genderLabel;
    private JComboBox<String> genderCombo ;
    private JButton createCompetitionButton ;
    private JSeparator separator2 ;
    private Label addCompetitorLabel;
    private Label nameLabel;
    private JTextField nameText;
    private Label ageLabel;
    private JTextField ageText;
    private Label MAXSpeedLabel;
    private JTextField MAXSpeedText;
    private Label accelerationLabel ;
    private JTextField accelerationText;
    private JButton addCompetitor ;
    private Label colorLabel;
    private JComboBox<String> colorCombo;
    private JSeparator separator3;
    private JButton startCompetitionButton;
    private JButton showInfoButton;
    private JSeparator separator;
    private JTextField idCompetitorText;
    private JButton makeCopies;
    private JComboBox<String> arenaType;
    private JButton quickCompetition;
    private JButton addColor;
    private JButton addAcceleration;

    /**
     * default Ctor of the side bar that initialize the menu
     */
    SidePanel()
    {
        menu  = new JPanel();
        menu.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0,3,1,3);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0;

        // activate the following methods that creates the 3 parts of the side bar
        buildArenaPart(gbc);
        createCompPart(gbc);
        addCompPart(gbc);

        startCompetitionButton = new JButton("Start Competition");
        showInfoButton = new JButton("Show Info");
        quickCompetition = new JButton("Quick game");
        addColor = new JButton("add color");
        addAcceleration = new JButton("add acceleration");
        startCompetitionButton.setPreferredSize(new Dimension(140,20));
        showInfoButton.setPreferredSize(new Dimension(140,25));
        quickCompetition.setPreferredSize(new Dimension(140,20));
        addColor.setPreferredSize(new Dimension(140,20));
        addAcceleration.setPreferredSize(new Dimension(140,20));
        gbc.gridy++;
        menu.add(addColor,gbc);
        gbc.gridy++;
        menu.add(addAcceleration,gbc);
        gbc.gridy++;
        menu.add(startCompetitionButton,gbc);
        gbc.gridy++;
        menu.add(quickCompetition,gbc);
        gbc.gridy++;
        menu.add(showInfoButton,gbc);


        this.setLayout(new GridBagLayout());
        separator = new JSeparator(JSeparator.VERTICAL);
        separator.setPreferredSize(new Dimension(1,800));
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(0,0,0,0);
        gbc2.fill = GridBagConstraints.VERTICAL;
        gbc2.gridx = 0;
        gbc2.weighty = 1;
        this.add(separator,gbc2);
        gbc2.gridx++;
        this.add(menu,gbc2);




    }

    /**
     * initalizing the upper part of the menu
     * @param gbc grid
     */
    private void addCompPart(GridBagConstraints gbc){
        // creating the buttons and insert them to the menu
         addCompetitorLabel = new Label("Add Competitor");
        addCompetitorLabel.setPreferredSize(new Dimension(160,20));
         nameLabel = new Label("Name");
        nameLabel.setPreferredSize(new Dimension(160,20));
         nameText = new JTextField();
        nameText.setPreferredSize(new Dimension(160,20));
         ageLabel = new Label("Age");
         ageLabel.setPreferredSize(new Dimension(160,20));
         ageText = new JTextField();
        ageText.setPreferredSize(new Dimension(160,20));
         MAXSpeedLabel = new Label("Max Speed");
         MAXSpeedLabel.setPreferredSize(new Dimension(160,20));
         MAXSpeedText = new JTextField();
        MAXSpeedText.setPreferredSize(new Dimension(160,20));
         accelerationLabel = new Label("Acceleration");
         accelerationLabel.setPreferredSize(new Dimension(160,20));
         accelerationText = new JTextField();
        accelerationText.setPreferredSize(new Dimension(160,20));
         addCompetitor = new JButton("Add Competitor");
        addCompetitor.setPreferredSize(new Dimension(160,20));
         colorLabel = new Label("Color");
        colorLabel.setPreferredSize(new Dimension(160,20));
         colorCombo = new JComboBox<String>(new String[] {"Black","Blue","Pink","Red","Orange","Green"});
        colorCombo.setPreferredSize(new Dimension(80,20));
        idCompetitorText = new JTextField("competitor id");
        idCompetitorText.setPreferredSize(new Dimension(80,20));
        makeCopies = new JButton("make copies");
        makeCopies.setPreferredSize(new Dimension(80,20));

         separator3 = new JSeparator(SwingConstants.HORIZONTAL);
        addCompetitorLabel.setForeground(Color.BLUE);
        separator3.setPreferredSize(new Dimension(160,1));
        menu.add(addCompetitorLabel,gbc);
        gbc.gridy++;
        menu.add(nameLabel,gbc);
        gbc.gridy++;
        menu.add(nameText,gbc);
        gbc.gridy++;
        menu.add(ageLabel,gbc);
        gbc.gridy++;
        menu.add(ageText,gbc);
        gbc.gridy++;
        menu.add(MAXSpeedLabel,gbc);
        gbc.gridy++;
        menu.add(MAXSpeedText,gbc);
        gbc.gridy++;
        menu.add(accelerationLabel,gbc);
        gbc.gridy++;
        menu.add(accelerationText,gbc);
        gbc.gridy++;

        menu.add(idCompetitorText,gbc);
        gbc.gridy++;
        menu.add(makeCopies,gbc);
        gbc.gridy++;
        menu.add(addCompetitor,gbc);
        gbc.gridy++;
        gbc.insets = new Insets(1,0,1,0);
        menu.add(separator3,gbc);
        gbc.gridy++;
        menu.add(colorCombo,gbc);
        gbc.gridy++;
        gbc.insets = new Insets(0,3,1,3);

    }
    /**
     * initalizing the middle part of the menu
     * @param gbc grid
     */
    private void createCompPart(GridBagConstraints gbc){
        // creating the buttons and insert them to the menu
         createCompetitionLabel = new Label("Create Competition");
        createCompetitionLabel.setForeground(Color.BLUE);
         chooseCompetitionLabel = new Label("Choose Competition");
        chooseCompetitionCombo = new JComboBox<String>(new String[] {"Ski","Snowboard"});
         maxCompetitorsLabel = new Label("Max Competitors");
         maxCompetitorsText = new JTextField("10");
         disciplineLabel = new Label("Discipline");
        disciplineCombo = new JComboBox<String>(new String[] {"SLALOM","GIANT SLALOM", "DOWNHILL","FREESTYLE"});
         leagueLabel = new Label("League");
         leagueCombo = new JComboBox<String>(new String[] {"JUNIOR","ADULT","SENIOR"});
         genderLabel = new Label("League");
        genderCombo = new JComboBox<String>(new String[] {"FEMALE","MALE"});
         createCompetitionButton = new JButton("Create Competition");
         separator2 = new JSeparator(SwingConstants.HORIZONTAL);
        separator2.setPreferredSize(new Dimension(160,1));
        menu.add(createCompetitionLabel,gbc);
        gbc.gridy++;
        menu.add(chooseCompetitionLabel,gbc);
        gbc.gridy++;
        menu.add(chooseCompetitionCombo,gbc);
        gbc.gridy++;
        menu.add(maxCompetitorsLabel,gbc);
        gbc.gridy++;
        menu.add(maxCompetitorsText,gbc);
        gbc.gridy++;
        menu.add(disciplineLabel,gbc);
        gbc.gridy++;
        menu.add(disciplineCombo,gbc);
        gbc.gridy++;
        menu.add(leagueLabel,gbc);
        gbc.gridy++;
        menu.add(leagueCombo,gbc);
        gbc.gridy++;
        menu.add(genderLabel,gbc);
        gbc.gridy++;
        menu.add(genderCombo,gbc);
        gbc.gridy++;
        menu.add(createCompetitionButton,gbc);
        gbc.gridy++;
        gbc.insets = new Insets(1,0,1,0);
        menu.add(separator2,gbc);
        gbc.gridy++;
        gbc.insets = new Insets(0,3,1,3);
    }
    /**
     * initalizing the bottom part of the menu
     * @param gbc grid
     */
    private void buildArenaPart(GridBagConstraints gbc) {
        // creating the buttons and insert them to the menu
         buildArenaLabel = new Label("Build Arena");
        buildArenaLabel.setForeground(Color.BLUE);
         arenaLengthLabel = new Label("Arena length");
         arenaLengthText = new JTextField("700");
         snowSurfaceLabel = new Label("Snow surface");
        arenaType = new JComboBox<String>(new String[]{"Winter","Summer"});
         snowSurfaceCombo = new JComboBox<String>(new String[] {"POWDER","CRUD","ICE"});
         weatherConditionLabel = new Label("Weather condition");
        weatherConditionCombo = new JComboBox<String>(new String[] {"SUNNY","CLOUDY","STORMY"});
         buildArenaButton = new JButton("BuildArena");
         separator1 = new JSeparator(SwingConstants.HORIZONTAL);
        separator1.setPreferredSize(new Dimension(165,1));
        gbc.insets = new Insets(0,3,0,3);
        menu.add(buildArenaLabel,gbc);
        gbc.gridy++;
        arenaType.setPreferredSize(new Dimension(160,20));
        menu.add(arenaType,gbc);
        gbc.gridy++;
        menu.add(arenaLengthLabel,gbc);
        gbc.gridy++;
        gbc.insets = new Insets(0,3,1,3);
        menu.add(arenaLengthText,gbc);
        gbc.gridy++;
        menu.add(snowSurfaceLabel,gbc);
        gbc.gridy++;
        menu.add(snowSurfaceCombo,gbc);
        gbc.gridy++;
        menu.add(weatherConditionLabel,gbc);
        gbc.gridy++;
        menu.add(weatherConditionCombo,gbc);
        gbc.gridy++;
        menu.add(buildArenaButton,gbc);
        gbc.gridy++;
        gbc.insets = new Insets(0,0,1,0);
        menu.add(separator1,gbc);
        gbc.gridy++;
        gbc.insets = new Insets(0,3,1,3);

        // action listener for the create arena button. also dealing with invalid actions
    }


    //GETTERS

    public JTextField getArenaLengthText() {
        return arenaLengthText;
    }
    public JComboBox<String> getSnowSurfaceCombo() {
        return snowSurfaceCombo;
    }
    public JComboBox<String> getWeatherConditionCombo() {
        return weatherConditionCombo;
    }
    public JButton getBuildArenaButton() {
        return buildArenaButton;
    }
    public JComboBox<String> getChooseCompetitionCombo() {
        return chooseCompetitionCombo;
    }
    public JTextField getMaxCompetitorsText() {
        return maxCompetitorsText;
    }
    public JComboBox<String> getDisciplineCombo() {
        return disciplineCombo;
    }
    public JComboBox<String> getLeagueCombo() {
        return leagueCombo;
    }
    public JComboBox<String> getGenderCombo() {
        return genderCombo;
    }
    public JButton getCreateCompetitionButton() {
        return createCompetitionButton;
    }
    public JTextField getNameText() {
        return nameText;
    }
    public JTextField getAgeText() {
        return ageText;
    }
    public JTextField getMAXSpeedText() {
        return MAXSpeedText;
    }
    public JTextField getAccelerationText() {
        return accelerationText;
    }
    public JButton getAddCompetitor() {
        return addCompetitor;
    }
    public JComboBox<String> getColorCombo() {
        return colorCombo;
    }
    public JButton getStartCompetitionButton() {
        return startCompetitionButton;
    }
    public JButton getShowInfoButton() {
        return showInfoButton;
    }
    public JTextField getIdCompetitorText() {
        return idCompetitorText;
    }
    public JButton getMkeCopies() {
        return makeCopies;
    }
    public JComboBox<String> getArenaType() {
        return arenaType;
    }
    public JButton getQuickCompetition() { return quickCompetition; }

    public JButton getAddColor() {
        return addColor;
    }
    public JButton getAddAcceleration() {
        return addAcceleration;
    }
    //END OF GETTERS
}
