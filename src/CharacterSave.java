/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author clone
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

//Save to file method
public class CharacterSave {
    private String filename;

    public CharacterSave(String filename) {
        this.filename = filename;
    }
    //It accepts two parameters, CharacterAttributes object representing the character to be saved, and a JFrame representing the frame to display message dialogs
    public void saveCharacterToFile(CharacterAttributes character, JFrame frame) {
        File file = new File(filename);
        //Every chracter attributes
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("Name: " + character.getName() + "\n");
            writer.write("Race: " + character.getRace() + "\n");
            writer.write("Career: " + character.getCareer() + "\n");
            writer.write("Strength: " + character.getStrength() + "\n");
            writer.write("Dexterity: " + character.getDexterity() + "\n");
            writer.write("Intelligence: " + character.getIntelligence() + "\n");
            writer.write("Faith: " + character.getFaith() + "\n");
            //Message showing succesful or not
            JOptionPane.showMessageDialog(frame, "Character attributes have been saved to " + filename, "File Saved", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error saving character attributes to file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
