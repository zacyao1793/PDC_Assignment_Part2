/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.*;
import java.io.*;
import java.nio.file.*;
import javax.swing.*;
import static org.junit.Assert.*;

public class CharacterSaveTest {

    private static String testFileName;
    private static JFrame testFrame;

    @BeforeClass
    public static void setUp() {
        // Use a random file name to ensure uniqueness
        testFileName = "Test.txt";
        testFrame = new JFrame("Test");
    }

    @Test
    public void testSaveCharacterToFile() {
        Mortal character = new Mortal(7, 10, 8, 9);
        character.setName("Test");
        character.setCareer("Vagabond");

        CharacterSave characterSave = new CharacterSave(testFileName);
        characterSave.saveCharacterToFile(character, testFrame);

        String fileContent = "";
        try {
            fileContent = new String(Files.readAllBytes(Paths.get(testFileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String expectedContent = 
                "Name: " + character.getName() + "\n" +
                "Race: " + character.getRace() + "\n" +
                "Career: " + character.getCareer() + "\n" +
                "Strength: " + character.getStrength() + "\n" +
                "Dexterity: " + character.getDexterity() + "\n" +
                "Intelligence: " + character.getIntelligence() + "\n" +
                "Faith: " + character.getFaith() + "\n";

        assertEquals(expectedContent, fileContent);
    }

    @AfterClass
    public static void cleanUp() {
        // Delete the test file
        File file = new File(testFileName);
        if (file.exists()) {
            file.delete();
        }
    }
}
