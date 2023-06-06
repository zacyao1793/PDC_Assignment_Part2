/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DatabaseTest {
    private Database db;
    private CharacterAttributes character;

    // A dummy subclass for testing
    private class TestCharacter extends CharacterAttributes {
        public TestCharacter(String name, String race, String career, int strength, int dexterity, int intelligence, int faith) {
            this.name = name;
            this.race = race;
            this.career = career;
            this.strength = strength;
            this.dexterity = dexterity;
            this.intelligence = intelligence;
            this.faith = faith;
        }
        
        public void display() {
            System.out.println("Name: " + name);
            System.out.println("Race: " + race);
            System.out.println("Career: " + career);
            System.out.println("Strength: " + strength);
            System.out.println("Dexterity: " + dexterity);
            System.out.println("Intelligence: " + intelligence);
            System.out.println("Faith: " + faith);
        }
    }

    @Before
    public void setUp() {
        db = new Database();

        character = new TestCharacter("Car", "Mortal", "Vegabond",7, 10, 8, 9); 
    }

    
    @Test
    public void testSaveCharacter() {
        try {
            db.saveCharacter(character);
        } catch (Exception e) {
            fail("Saving character failed with exception: " + e.getMessage());
        }
    }

    @Test
    public void testLoadCharacter() {
        try {
            // first, save the character to the database
            db.saveCharacter(character);

            // then, load the character from the database
            CharacterAttributes loadedCharacter = db.loadCharacter(character.getName());

            // Assert that the loaded character is not null
            assertNotNull(loadedCharacter);

            // finally, verify that the loaded character has the same attributes as the saved character
            assertEquals(character.getName(), loadedCharacter.getName());
            assertEquals(character.getRace(), loadedCharacter.getRace());
            assertEquals(character.getCareer(), loadedCharacter.getCareer());
            assertEquals(character.getStrength(), loadedCharacter.getStrength());
            assertEquals(character.getDexterity(), loadedCharacter.getDexterity());
            assertEquals(character.getIntelligence(), loadedCharacter.getIntelligence());
            assertEquals(character.getFaith(), loadedCharacter.getFaith());
        } catch (Exception e) {
            fail("Loading character failed " + e.getMessage());
        }
    }
}
