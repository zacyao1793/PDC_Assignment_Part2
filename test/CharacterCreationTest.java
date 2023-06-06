/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.junit.Test;
import static org.junit.Assert.*;

public class CharacterCreationTest {
    
    @Test
    public void testCreateCharacterAttributes() {
        //Creating a CharacterAttributes object based on the provided race and attribute values
        System.out.println("createCharacterAttributes");
        String race = "Mortal";
        int strength = 7;
        int dexterity = 10;
        int intelligence = 8;
        int faith = 9;
        //Sets the race to "Mortal" and assigns specific attribute values. It then calls the createCharacterAttributes() method 
        CharacterAttributes result = CharacterCreation.createCharacterAttributes(race, strength, dexterity, intelligence, faith);
        //Assertion mrthods for each attributes
        assertTrue(result instanceof Mortal);
        assertEquals(strength, result.getStrength());
        assertEquals(dexterity, result.getDexterity());
        assertEquals(intelligence, result.getIntelligence());
        assertEquals(faith, result.getFaith());
        
        //Invalid race value ("InvalidRace"). The createCharacterAttributes() method is called again, and the test verifies that the returned object is null

        race = "InvalidRace";
        result = CharacterCreation.createCharacterAttributes(race, strength, dexterity, intelligence, faith);
        assertNull(result);
    }
}
