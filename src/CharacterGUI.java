/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
public class CharacterGUI {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JButton nextButton;
    private JComboBox<String> raceComboBox;
    private JComboBox<String> careerComboBox;
    private JTextArea resultTextArea;
    private CharacterAttributes character;
    private JTextArea storyTextArea;
    private Database db = new Database ();
    private JTextField nameField;
    
    
    
    public CharacterGUI() {
        frame = new JFrame("Elder Ring 2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);

        // Create the CardLayout and the cardPanel.
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

    
        // Create the panels for each step.
        JPanel racePanel = createRacePanel();
        JPanel careerPanel = createCareerPanel();
        JPanel namePanel = createNamePanel();
        JPanel finalPanel = createFinalPanel();
        
       
        // Add the panels to cardPanel.
        cardPanel.add(racePanel, "RacePanel");
        cardPanel.add(careerPanel, "CareerPanel");
        cardPanel.add(namePanel, "NamePanel");
        cardPanel.add(finalPanel, "FinalPanel");
        
        // Add the cardPanel to the frame.
        frame.add(cardPanel);

        frame.setVisible(true);
        
 
      
    }

    
    
    private JPanel createRacePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
         panel.setBackground(new Color(51, 51, 51));
        
        //I asked ChatGPT how to organize the layout of boxes
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
        
        
        raceComboBox = new JComboBox<>(new String[]{"Mortal", "Deepkin", "Feymour", "Draconian", "Celestial"});
        raceComboBox.setFont(font);
        raceComboBox.setBackground(new Color(51, 51, 51));
        raceComboBox.setForeground(Color.WHITE);

        // Create a button to go to the next panel.
        nextButton = new JButton("Next");
        nextButton.setFont(font);
        nextButton.setBackground(new Color(51, 51, 51));
        nextButton.setForeground(Color.WHITE);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String race = (String) raceComboBox.getSelectedItem();
                character = CharacterCreation.createCharacterAttributes(race, 0, 0, 0, 0);
                character.applyRaceModifiers(race);
                cardLayout.show(cardPanel, "CareerPanel");
            }
        });

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

        panel.add(raceLabel, gbc);
        panel.add(raceComboBox, gbc);
        
        panel.add(storyTextArea, gbc);
        panel.add(nextButton, gbc);
    
        return panel;
    }

    
    
    
    
    
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
        String career = (String) careerComboBox.getSelectedItem();
        character.applyCareerModifiers(career);
        cardLayout.show(cardPanel, "NamePanel");
    }
});

        
        JButton backButton = new JButton("Back");
        backButton.setFont(font);
        backButton.setBackground(new Color(102, 102, 102));
        backButton.setForeground(Color.WHITE);
        
        backButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            cardLayout.show(cardPanel, "RacePanel");
        }
    });
        

        // Add the components to the panel.
        panel.add(careerLabel);
        panel.add(careerComboBox);
        panel.add(nextButton);
        
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

        panel.add(careerLabel, gbc);
        panel.add(careerComboBox, gbc);
        panel.add(storyTextAreaCareer, gbc);
        panel.add(nextButton, gbc);
        panel.add(backButton, gbc);
        
        return panel;
    }

    
    
    
    private JPanel createNamePanel() {
    JPanel panel = new JPanel(new GridBagLayout());
    panel.setBackground(new Color(153, 153, 153));

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridwidth = GridBagConstraints.REMAINDER; 
    gbc.fill = GridBagConstraints.HORIZONTAL; 
    gbc.insets = new Insets(4, 4, 4, 4);

    Font font = new Font("Dialog", Font.PLAIN, 18);

    JLabel nameLabel = new JLabel("What name does your soul bear?");
    nameLabel.setFont(font);
    nameLabel.setBackground(new Color(153, 153, 153));
    nameLabel.setForeground(Color.WHITE);

    nameField = new JTextField(20);
    nameField.setFont(font);
    nameField.setBackground(new Color(153, 153, 153));
    nameField.setForeground(Color.WHITE);

    JButton nextButton = new JButton("Next");
    nextButton.setFont(font);
    nextButton.setBackground(new Color(153, 153, 153));
    nextButton.setForeground(Color.WHITE);

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
            cardLayout.show(cardPanel, "CareerPanel");
        }
    });

    panel.add(nameLabel, gbc);
    panel.add(nameField, gbc);
    panel.add(nextButton, gbc);
    panel.add(backButton, gbc);
     
    return panel;
}
    
    
    
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
                saveCharacterToFile(character);
                //saveCharacterToDatabase(character);
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

    
   //I asked ChatGPT to seperate the saveCharacterToFile method to another class
    private void saveCharacterToFile(CharacterAttributes character) {
    CharacterSave fileWriter = new CharacterSave("SavedCharacter.txt");
    fileWriter.saveCharacterToFile(character, frame);
    
    db.saveCharacter(character);
}
 

    
}
**/
public class CharacterGUI {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JButton nextButton;
    private JButton backButton;
    private JButton printToFileButton;
    private JComboBox<String> raceComboBox;
    private JComboBox<String> careerComboBox;
    private JTextArea resultTextArea;
    private CharacterAttributes character;
    private JTextArea storyTextArea;
    private Database db = new Database ();
    private JTextField nameField;
    private JPanel loadPanel;
    
    
    public CharacterGUI() {
        frame = new JFrame("Elder Ring 2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);

        // Create the CardLayout and the cardPanel.
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

    
        // Create the panels for each step.
        JPanel racePanel = createRacePanel();
        JPanel careerPanel = createCareerPanel();
        JPanel namePanel = createNamePanel();
        JPanel finalPanel = createFinalPanel();
        loadPanel = createLoadPanel();
       
        // Add the panels to cardPanel.
        cardPanel.add(racePanel, "RacePanel");
        cardPanel.add(careerPanel, "CareerPanel");
        cardPanel.add(namePanel, "NamePanel");
        cardPanel.add(finalPanel, "FinalPanel");
        cardPanel.add(loadPanel, "LoadPanel");
        
        // Add the cardPanel to the frame.
        frame.add(cardPanel);

        frame.setVisible(true);
        
 
      
    }

    
    
    private JPanel createRacePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
         panel.setBackground(new Color(51, 51, 51));
        
        //I asked ChatGPT how to organize the layout of boxes
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
        
        
        raceComboBox = new JComboBox<>(new String[]{"Mortal", "Deepkin", "Feymour", "Draconian", "Celestial"});
        raceComboBox.setFont(font);
        raceComboBox.setBackground(new Color(51, 51, 51));
        raceComboBox.setForeground(Color.WHITE);

        // Create a button to go to the next panel.
        nextButton = new JButton("Next");
        nextButton.setFont(font);
        nextButton.setBackground(new Color(51, 51, 51));
        nextButton.setForeground(Color.WHITE);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String race = (String) raceComboBox.getSelectedItem();
                character = CharacterCreation.createCharacterAttributes(race, 0, 0, 0, 0);
                character.applyRaceModifiers(race);
                cardLayout.show(cardPanel, "CareerPanel");
            }
        });
        
        
        JButton loadButton = new JButton("Load Character");
            loadButton.setFont(font);
            loadButton.setBackground(new Color(51, 51, 51));
            loadButton.setForeground(Color.WHITE);

            loadButton.addActionListener(new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent e) {
            String name = JOptionPane.showInputDialog(frame, "Enter the name of the character to load");
            loadCharacterFromDatabase(name);
        }
    });
        

            
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

        panel.add(raceLabel, gbc);
        panel.add(raceComboBox, gbc);
        
        panel.add(storyTextArea, gbc);
        panel.add(nextButton, gbc);
        panel.add(loadButton, gbc);
        
        return panel;
    }

    
    
    
    
    
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
        String career = (String) careerComboBox.getSelectedItem();
        character.applyCareerModifiers(career);
        cardLayout.show(cardPanel, "NamePanel");
    }
});

        
        JButton backButton = new JButton("Back");
        backButton.setFont(font);
        backButton.setBackground(new Color(102, 102, 102));
        backButton.setForeground(Color.WHITE);
        
        backButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            cardLayout.show(cardPanel, "RacePanel");
        }
    });
        

        // Add the components to the panel.
        panel.add(careerLabel);
        panel.add(careerComboBox);
        panel.add(nextButton);
        
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

        panel.add(careerLabel, gbc);
        panel.add(careerComboBox, gbc);
        panel.add(storyTextAreaCareer, gbc);
        panel.add(nextButton, gbc);
        panel.add(backButton, gbc);
        
        return panel;
    }

    
    
    
    private JPanel createNamePanel() {
    JPanel panel = new JPanel(new GridBagLayout());
    panel.setBackground(new Color(153, 153, 153));

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridwidth = GridBagConstraints.REMAINDER; 
    gbc.fill = GridBagConstraints.HORIZONTAL; 
    gbc.insets = new Insets(4, 4, 4, 4);

    Font font = new Font("Dialog", Font.PLAIN, 18);

    JLabel nameLabel = new JLabel("What name does your soul bear?");
    nameLabel.setFont(font);
    nameLabel.setBackground(new Color(153, 153, 153));
    nameLabel.setForeground(Color.WHITE);

    nameField = new JTextField(20);
    nameField.setFont(font);
    nameField.setBackground(new Color(153, 153, 153));
    nameField.setForeground(Color.WHITE);

    JButton nextButton = new JButton("Next");
    nextButton.setFont(font);
    nextButton.setBackground(new Color(153, 153, 153));
    nextButton.setForeground(Color.WHITE);

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
            cardLayout.show(cardPanel, "CareerPanel");
        }
    });

    panel.add(nameLabel, gbc);
    panel.add(nameField, gbc);
    panel.add(nextButton, gbc);
    panel.add(backButton, gbc);
     
    return panel;
}
    
    
    
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
                saveCharacterToFile(character);
                //saveCharacterToDatabase(character);
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

    
   //I asked ChatGPT to seperate the saveCharacterToFile method to another class
    private void saveCharacterToFile(CharacterAttributes character) {
    CharacterSave fileWriter = new CharacterSave("SavedCharacter.txt");
    fileWriter.saveCharacterToFile(character, frame);
    
    Database db = new Database();
    
    db.saveCharacter(character);
}
    
    
     private JPanel createLoadPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(153, 153, 153));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 4, 4, 4);

        Font font = new Font("Dialog", Font.PLAIN, 18);

        JLabel nameLabel = new JLabel("Loaded Character Stats:");
        nameLabel.setFont(font);
        nameLabel.setBackground(new Color(153, 153, 153));
        nameLabel.setForeground(Color.WHITE);

        resultTextArea = new JTextArea(10, 40);
        resultTextArea.setFont(font);
        resultTextArea.setEditable(false);
        resultTextArea.setBackground(new Color(153, 153, 153));
        resultTextArea.setForeground(Color.WHITE);

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
        panel.add(resultTextArea, gbc);
        panel.add(backButton, gbc);

        return panel;
    }

     
     
    private void loadCharacterFromDatabase(String name) {
        Database db = new Database();
        CharacterAttributes loadedCharacter = db.loadCharacter(name);

        if (loadedCharacter != null) {
            character = loadedCharacter;
            resultTextArea.setText(
                    "Name: " + character.getName() + "\n" +
                            "Race: " + character.getRace() + "\n" +
                            "Career: " + character.getCareer() + "\n" +
                            "Strength: " + character.getStrength() + "\n" +
                            "Dexterity: " + character.getDexterity() + "\n" +
                            "Intelligence: " + character.getIntelligence() + "\n" +
                            "Faith: " + character.getFaith());
            cardLayout.show(cardPanel, "LoadPanel");
        } else {
            resultTextArea.setText("Character not found.");
        }
    }

    
    
}