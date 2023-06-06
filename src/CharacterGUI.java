/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CharacterGUI {
    // GUI components
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JButton nextButton;
    private JButton backButton;
    private JButton printToFileButton;
    private JComboBox<String> raceComboBox;
    private JComboBox<String> careerComboBox;
    private JTextArea resultTextArea;
    private JTextArea loadedresultTextArea;
    private CharacterAttributes character;
    private JTextArea storyTextArea;
    private Database db = new Database ();
    private JTextField nameField;
    
    
    
    public CharacterGUI() {
        
        //Constructors for the GUI
        frame = new JFrame("Elder Ring 2");// The main window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Close bottom
        frame.setSize(1000, 800);// Size of the main window

        
        
        // Create the CardLayout and the cardPanel.
        cardLayout = new CardLayout();// Layout manager for the main panel
        cardPanel = new JPanel(cardLayout);// Main panel with cardLayout as its layout manager

    
        // Create each of the panels for the CardLayout
        JPanel titlePanel = createTitlePanel();
        JPanel racePanel = createRacePanel();
        JPanel careerPanel = createCareerPanel();
        JPanel namePanel = createNamePanel();
        JPanel finalPanel = createFinalPanel();
        JPanel loadPanel = createLoadPanel();
       
        // Add each panel to the CardLayout
        cardPanel.add(titlePanel, "TitlePanel");
        cardPanel.add(racePanel, "RacePanel");
        cardPanel.add(careerPanel, "CareerPanel");
        cardPanel.add(namePanel, "NamePanel");
        cardPanel.add(finalPanel, "FinalPanel");
        cardPanel.add(loadPanel, "LoadPanel");
        
        // Add the cardPanel to the frame.
        frame.add(cardPanel);
       // Make it visible
        frame.setVisible(true);
        
   
    }

    // Method to create the title panel
    private JPanel createTitlePanel() {
        
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(30, 30, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 4, 4, 4);

        Font font = new Font("Dialog", Font.PLAIN, 24);

        // Create the title label
        JLabel titleLabel = new JLabel("Elder Ring 2");
        titleLabel.setFont(new Font("Dialog", Font.PLAIN, 50));
        titleLabel.setForeground(Color.WHITE);

        // Create the start button
        JButton startButton = new JButton("Start");
        startButton.setFont(font);
        startButton.setBackground(new Color(51, 51, 51));
        startButton.setForeground(Color.WHITE);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Move to the next panel (RacePanel)
                cardLayout.show(cardPanel, "RacePanel");
            }
        });
        // Add to the panel
        panel.add(titleLabel, gbc);
        panel.add(startButton, gbc);

        return panel;
    }
    
    
    
     // Method to create the race panel
    private JPanel createRacePanel() {
        //Layout of the panel
        JPanel panel = new JPanel(new GridBagLayout());
         panel.setBackground(new Color(51, 51, 51));
        
        //I asked ChatGPT how to organize the layout of each panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER; 
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        gbc.insets = new Insets(4, 4, 4, 4); 

        //Text Font and Sizes
        Font font = new Font("Dialog", Font.PLAIN, 18);
            
        // Create the race selection components.
        JLabel raceLabel = new JLabel("Race:");
        raceLabel.setFont(font);
        raceLabel.setForeground(Color.WHITE);
        
        //Racecombo box for selecting race
        raceComboBox = new JComboBox<>(new String[]{"Mortal", "Deepkin", "Feymour", "Draconian", "Celestial"});
        raceComboBox.setFont(font);
        raceComboBox.setBackground(new Color(51, 51, 51));
        raceComboBox.setForeground(Color.WHITE);

        // Button to go to the next panel.
        nextButton = new JButton("Next");
        nextButton.setFont(font);
        nextButton.setBackground(new Color(51, 51, 51));
        nextButton.setForeground(Color.WHITE);

        
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Add the attibutes points to the character upon the choice from the comboBox
                String race = (String) raceComboBox.getSelectedItem();
                character = CharacterCreation.createCharacterAttributes(race, 0, 0, 0, 0);
                character.applyRaceModifiers(race);
                // Move to the next panel (CareerPanel)
                cardLayout.show(cardPanel, "CareerPanel");
            }
        });
        
           //Bottom for loading character
            JButton loadButton = new JButton("Load Character");
            loadButton.setFont(font);
            loadButton.setBackground(new Color(51, 51, 51));
            loadButton.setForeground(Color.WHITE);

            
            loadButton.addActionListener(new ActionListener() {
                
        @Override  
            public void actionPerformed(ActionEvent e) {
            //A pop up box for user enter previous created character's name
            String name = JOptionPane.showInputDialog(frame, "Enter the name of the character to load");
            //Load the character from the database
            loadCharacterFromDatabase(name);
        }
    });
        

        //Story telling    
        storyTextArea = new JTextArea(10, 40);
        storyTextArea.setFont(font);
        storyTextArea.setText("\"Greetings, traveler. Before we proceed, tell me, which of these forms will your soul inhabit?\n"
                + "\nWill you be a mortal, one of the Deepkin who dwell beneath the waves, \n"
                + "\nor perhaps a Feymour, touched by the magic of the faerie realms? \n"
                + "\nAlternatively, will you take on the form of a Draconian, with scales as hard as steel, \n"
                + "\nor a Celestial, born of the stars themselves?\"");
        storyTextArea.setEditable(false);
        storyTextArea.setBackground(new Color(51, 51, 51));
        storyTextArea.setForeground(Color.WHITE);

        //Add components to the panel
        panel.add(raceLabel, gbc);
        panel.add(raceComboBox, gbc);
        
        panel.add(storyTextArea, gbc);
        panel.add(nextButton, gbc);
        panel.add(loadButton, gbc);
        
        return panel;
    }

    
    
    
    
    // Method to create the career panel
    private JPanel createCareerPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
    
        panel.setBackground(new Color(102, 102, 102));
        
        //I asked ChatGPT how to organize the layout of boxes
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER; 
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        gbc.insets = new Insets(4, 4, 4, 4); 
        
        
        //Fonts
        Font font = new Font("Dialog", Font.PLAIN, 18);
        
        
        // Create the career selection
        JLabel careerLabel = new JLabel("Career:");
        careerLabel.setFont(font);
        careerLabel.setBackground(new Color(102, 102, 102));
        careerLabel.setForeground(Color.WHITE);
        
        //CareerComboBox for selectin career
        careerComboBox = new JComboBox<>(new String[]{"Vagabond", "Ronin", "Pagan", "Spellblade", "Witch Hunter"});
        careerComboBox.setFont(font);
        careerComboBox.setBackground(new Color(102, 102, 102));
        careerComboBox.setForeground(Color.WHITE);
        // Create a button to go to the next panel.
        nextButton = new JButton("Next");
        nextButton.setFont(font);
        nextButton.setBackground(new Color(102, 102, 102));
        nextButton.setForeground(Color.WHITE);
         
        
        
        
        nextButton.addActionListener(new ActionListener() {
    @Override
    
    public void actionPerformed(ActionEvent e) {
        //Add up the career attributes to the race attributes, detail see the CharacterAttributes class
        String career = (String) careerComboBox.getSelectedItem();
        character.applyCareerModifiers(career);
        // Move to the next panel (NamePanel)
        cardLayout.show(cardPanel, "NamePanel");
    }
});

        //Back bottome 
        JButton backButton = new JButton("Back");
        backButton.setFont(font);
        backButton.setBackground(new Color(102, 102, 102));
        backButton.setForeground(Color.WHITE);
        
        backButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Show RacePanel if user click Back bottom
            cardLayout.show(cardPanel, "RacePanel");
        }
    });
        

        // Add the components to the panel.
        panel.add(careerLabel);
        panel.add(careerComboBox);
        panel.add(nextButton);
        
        //Story telling
        JTextArea storyTextAreaCareer = new JTextArea(10, 40);
        storyTextAreaCareer.setFont(font);
        storyTextAreaCareer.setText("\"Your journey begins now, traveler. Before we face the trials ahead, tell me, \n"
                + "\nwhat calling has your soul heard? Will you wander as a Vagabond, seeking adventure in every corner of the land? \n"
                + "\nWill you serve as a Ronin, a masterless samurai seeking redemption? \n"
                + "\nPerhaps you are a Pagan, attuned to the spirits of the earth and sky. \n"
                + "\nOr will you harness both magic and steel as a Spellblade? \n"
                + "\nMaybe you have taken the mantle of a Witch Hunter, sworn to protect the realms from the dark forces that threaten them. \n"
                + "\nChoose your path wisely, for it will determine your fate.\"");
        
        storyTextAreaCareer.setEditable(false);
        storyTextAreaCareer.setBackground(new Color(102, 102, 102));
        storyTextAreaCareer.setForeground(Color.WHITE);

        
        // Add the components to the panel.
        panel.add(careerLabel, gbc);
        panel.add(careerComboBox, gbc);
        panel.add(storyTextAreaCareer, gbc);
        panel.add(nextButton, gbc);
        panel.add(backButton, gbc);
        
        return panel;
    }

    
    
    // Method to create the name panel
    private JPanel createNamePanel() {
        
    JPanel panel = new JPanel(new GridBagLayout());
    panel.setBackground(new Color(153, 153, 153));

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridwidth = GridBagConstraints.REMAINDER; 
    gbc.fill = GridBagConstraints.HORIZONTAL; 
    gbc.insets = new Insets(4, 4, 4, 4);

    Font font = new Font("Dialog", Font.PLAIN, 18);

    //Name box for entering name
    JLabel nameLabel = new JLabel("What name does your soul bear?");
    nameLabel.setFont(font);
    nameLabel.setBackground(new Color(153, 153, 153));
    nameLabel.setForeground(Color.WHITE);

    nameField = new JTextField(20);
    nameField.setFont(font);
    nameField.setBackground(new Color(153, 153, 153));
    nameField.setForeground(Color.WHITE);

    //Next bottom
    JButton nextButton = new JButton("Next");
    nextButton.setFont(font);
    nextButton.setBackground(new Color(153, 153, 153));
    nextButton.setForeground(Color.WHITE);
    //Back bottom
    JButton backButton = new JButton("Back");
    backButton.setFont(font);
    backButton.setBackground(new Color(153, 153, 153));
    backButton.setForeground(Color.WHITE);
    
    nextButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        
        String name = nameField.getText();
        
        //I asked ChatGPT to add a feature that pops up a notification when the user enters a number or any non-alphabetic characters in the name field
        if(!name.matches("[a-zA-Z]+")){
            JOptionPane.showMessageDialog(frame, "Invalid input. Please enter only characters.", "Input error",
                JOptionPane.ERROR_MESSAGE);

            nameField.setText(""); //Clear the input field
            return;
        }
        
        //ResultTextArea for displaying stats in the final panel
        character.setName(name);
        resultTextArea.setText(
            "Name: " + character.getName() + "\n" +
            "Race: " + character.getRace() + "\n" +
            "Career: " + character.getCareer() + "\n" +
            "Strength: " + character.getStrength() + "\n" +
            "Dexterity: " + character.getDexterity() + "\n" +
            "Intelligence: " + character.getIntelligence() + "\n" +
            "Faith: " + character.getFaith());
        cardLayout.show(cardPanel, "FinalPanel");
    }
    
    
});
    
    
    backButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Back to Career panel
            cardLayout.show(cardPanel, "CareerPanel");
        }
    });

     // Add the components to the panel.
    panel.add(nameLabel, gbc);
    panel.add(nameField, gbc);
    panel.add(nextButton, gbc);
    panel.add(backButton, gbc);
     
    return panel;
}
    
    
    // Method to create the final panel
    private JPanel createFinalPanel() {
        
       JPanel panel = new JPanel(new GridBagLayout());
       panel.setBackground(new Color(204, 204, 204));
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER; 
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        gbc.insets = new Insets(4, 4, 4, 4);
        
        
        
        //Fonts
         Font font = new Font("Dialog", Font.PLAIN, 18);
         
        // Create the result text area.
        resultTextArea = new JTextArea(10, 40);
        resultTextArea.setFont(font);
        resultTextArea.setEditable(false);
        resultTextArea.setBackground(new Color(204, 204, 204));
        resultTextArea.setForeground(Color.WHITE);
        

        
        
        //Stroy Telling
        JTextArea storyTextAreaFinal = new JTextArea(10, 40);
        storyTextAreaFinal.setFont(font);
        
                storyTextAreaFinal.setText( "Your soul has found its vessel, and your journey has begun, adventurer. \n"
                    + "\nYou have chosen your calling and your fate is now intertwined with the fabric of this world. \n"
                    + "\nMay your footsteps be guided by the light of your convictions, \n"
                    + "\nand your blade strike true against the darkness that threatens us all...... ");
                
                storyTextAreaFinal.setEditable(false);
                storyTextAreaFinal.setBackground(new Color(204, 204, 204));
                storyTextAreaFinal.setForeground(Color.WHITE);
                
                panel.add(storyTextAreaFinal);
                
        // Create a button to print to file.
        JButton printToFileButton = new JButton("Save Character");
        printToFileButton.setFont(font);
        printToFileButton.setBackground(new Color(204, 204, 204));
        printToFileButton.setForeground(Color.WHITE);
        
        printToFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Character save to file called "SavedCharacter"
                saveCharacterToFile(character);
                
            }
        });

        
        
        JButton backButton = new JButton("Back");
        backButton.setFont(font);
        backButton.setBackground(new Color(204, 204, 204));
        backButton.setForeground(Color.WHITE);
    
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            cardLayout.show(cardPanel, "NamePanel");
        }
        });
    
        // Add the components to the panel.
        
        panel.add(resultTextArea, gbc);
        panel.add(storyTextAreaFinal, gbc);
        panel.add(printToFileButton, gbc);
        panel.add(backButton, gbc);
        
        return panel;
    }

    
   
    
    // Method to create the load character panel
     private JPanel createLoadPanel() {
         
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(153, 153, 153));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 4, 4, 4);

        Font font = new Font("Dialog", Font.PLAIN, 18);

        //A panel shows the stats of the loaded character
        JLabel nameLabel = new JLabel("Loaded Character Stats:");
        nameLabel.setFont(font);
        nameLabel.setBackground(new Color(153, 153, 153));
        nameLabel.setForeground(Color.WHITE);

        loadedresultTextArea = new JTextArea(10, 40);
        loadedresultTextArea.setFont(font);
        loadedresultTextArea.setEditable(false);
        loadedresultTextArea.setBackground(new Color(153, 153, 153));
        loadedresultTextArea.setForeground(Color.WHITE);

        //Back bottom
        JButton backButton = new JButton("Back");
        backButton.setFont(font);
        backButton.setBackground(new Color(153, 153, 153));
        backButton.setForeground(Color.WHITE);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "RacePanel");
            }
        });

        panel.add(nameLabel, gbc);
        panel.add(loadedresultTextArea, gbc);
        panel.add(backButton, gbc);

        return panel;
    }

     //I asked ChatGPT to seperate the saveCharacterToFile method to another class
    private void saveCharacterToFile(CharacterAttributes character) {
    CharacterSave fileWriter = new CharacterSave("SavedCharacter.txt");
    fileWriter.saveCharacterToFile(character, frame);
    
    Database db = new Database();
    
    db.saveCharacter(character);
}
     
    
    //Method to load a character's details from a database
    private void loadCharacterFromDatabase(String name) {
        Database db = new Database();
        CharacterAttributes loadedCharacter = db.loadCharacter(name);
        //Similar to the stats in the Final Panel
        //Showing all the value stored in the database
        if (loadedCharacter != null) {
            character = loadedCharacter;
            loadedresultTextArea.setText(
                    "Name: " + character.getName() + "\n" +
                            "Race: " + character.getRace() + "\n" +
                            "Career: " + character.getCareer() + "\n" +
                            "Strength: " + character.getStrength() + "\n" +
                            "Dexterity: " + character.getDexterity() + "\n" +
                            "Intelligence: " + character.getIntelligence() + "\n" +
                            "Faith: " + character.getFaith());
            cardLayout.show(cardPanel, "LoadPanel");
        } 
        }
    }

    
    
