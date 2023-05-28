/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class Main_CharacterGUI {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JButton nextButton;
    private JComboBox<String> raceComboBox;
    private JComboBox<String> careerComboBox;
    private JTextArea resultTextArea;
    private CharacterAttributes character;
    private String url = "jdbc:derby://localhost:1527/characterDB;user=pdc;password=pdc";
    private JTextArea storyTextArea;
    private Database db = new Database ();
    
    public Main_CharacterGUI() {
        frame = new JFrame("Elder Ring 2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);

        // Create the CardLayout and the cardPanel.
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Create the panels for each step.
        JPanel racePanel = createRacePanel();
        JPanel careerPanel = createCareerPanel();
        JPanel finalPanel = createFinalPanel();

        // Add the panels to cardPanel.
        cardPanel.add(racePanel, "RacePanel");
        cardPanel.add(careerPanel, "CareerPanel");
        cardPanel.add(finalPanel, "FinalPanel");

        // Add the cardPanel to the frame.
        frame.add(cardPanel);

        frame.setVisible(true);
        
      
    }

    private JPanel createRacePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
    
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
        raceComboBox = new JComboBox<>(new String[]{"Mortal", "Deepkin", "Feymour", "Draconian", "Celestial"});
        raceComboBox.setFont(font);

        // Create a button to go to the next panel.
        nextButton = new JButton("Next");
        nextButton.setFont(font);

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
                + "Will you be a mortal, one of the Deepkin who dwell beneath the waves, \n"
                + "or perhaps a Feymour, touched by the magic of the faerie realms? \n"
                + "Alternatively, will you take on the form of a Draconian, with scales as hard as steel, \n"
                + "or a Celestial, born of the stars themselves?\"");
        storyTextArea.setEditable(false);

        panel.add(raceLabel, gbc);
        panel.add(raceComboBox, gbc);
        
        panel.add(storyTextArea, gbc);
        panel.add(nextButton, gbc);
    
        return panel;
    }

    
    
    
    
    
    private JPanel createCareerPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
    
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
        careerComboBox = new JComboBox<>(new String[]{"Vagabond", "Ronin", "Pagan", "Spellblade", "Witch Hunter"});
        careerComboBox.setFont(font);
        // Create a button to go to the next panel.
        nextButton = new JButton("Next");
        nextButton.setFont(font);
         
         
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String career = (String) careerComboBox.getSelectedItem();
                
                character.applyCareerModifiers(career);
                resultTextArea.setText("Race: " + character.getRace() + "\n" +
                        "Career: " + career + "\n" +
                        "Strength: " + character.getStrength() + "\n" +
                        "Dexterity: " + character.getDexterity() + "\n" +
                        "Intelligence: " + character.getIntelligence() + "\n" +
                        "Faith: " + character.getFaith());
                cardLayout.show(cardPanel, "FinalPanel");
                
               
            }
        });
        
        
        JButton backButton = new JButton("Back");
        backButton.setFont(font);
        
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
                + "what calling has your soul heard? Will you wander as a Vagabond, seeking adventure in every corner of the land? \n"
                + "Will you serve as a Ronin, a masterless samurai seeking redemption? \n"
                + "Perhaps you are a Pagan, attuned to the spirits of the earth and sky. \n"
                + "Or will you harness both magic and steel as a Spellblade? \n"
                + "Maybe you have taken the mantle of a Witch Hunter, sworn to protect the realms from the dark forces that threaten them. \n"
                + "Choose your path wisely, for it will determine your fate.\"");
        
        storyTextAreaCareer.setEditable(false);
      

        panel.add(careerLabel, gbc);
        panel.add(careerComboBox, gbc);
        panel.add(storyTextAreaCareer, gbc);
        panel.add(nextButton, gbc);
        panel.add(backButton, gbc);
        
        return panel;
    }

    
    
    
    
    
    
    
    private JPanel createFinalPanel() {
       JPanel panel = new JPanel(new GridBagLayout());
    
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

        
        
        
        JTextArea storyTextAreaFinal = new JTextArea(10, 40);
        storyTextAreaFinal.setFont(font);
        
                storyTextAreaFinal.setText( "Your soul has found its vessel, and your journey has begun, adventurer. \n"
                    + "You have chosen your calling and your fate is now intertwined with the fabric of this world. \n"
                    + "May your footsteps be guided by the light of your convictions, \n"
                    + "and your blade strike true against the darkness that threatens us all. ");
                
                storyTextAreaFinal.setEditable(false);
                
                panel.add(storyTextAreaFinal);
                
        // Create a button to print to file.
        JButton printToFileButton = new JButton("Save Character");
        printToFileButton.setFont(font);
        
        printToFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveCharacterToFile(character);
                //saveCharacterToDatabase(character);
            }
        });

        
        
        JButton backButton = new JButton("Back");
        backButton.setFont(font);
    
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            cardLayout.show(cardPanel, "CareerPanel");
        }
        });
    
        // Add the components to the panel.
        panel.add(resultTextArea, gbc);
        panel.add(storyTextAreaFinal, gbc);
        panel.add(printToFileButton, gbc);
        panel.add(backButton, gbc);
        
        return panel;
    }

    
   
    private void saveCharacterToFile(CharacterAttributes character) {
        String filename = "SavedCharacter.txt";
        File file = new File(filename);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("Race: " + character.getRace() + "\n");
            writer.write("Career: " + character.getCareer() + "\n");
            writer.write("Strength: " + character.getStrength() + "\n");
            writer.write("Dexterity: " + character.getDexterity() + "\n");
            writer.write("Intelligence: " + character.getIntelligence() + "\n");
            writer.write("Faith: " + character.getFaith() + "\n");
            JOptionPane.showMessageDialog(frame, "Character attributes have been saved to " + filename, "File Saved", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error saving character attributes to file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        
        db.saveCharacter(character);
    }
 

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main_CharacterGUI();
            }
        });
    }
}
