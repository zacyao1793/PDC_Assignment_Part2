/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author clone
 */



public class CharacterAttributesTest {

    @Test
    public void testSetAndGetRace() {
        System.out.println("GetAndSetRace");
        CharacterAttributes instance = new CharacterAttributesPrint();
        String expResult = "Mortal";
        instance.setRace(expResult);
        String result = instance.getRace();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetCareer() {
        System.out.println("GetAndSetCareer");
        CharacterAttributes instance = new CharacterAttributesPrint();
        String expResult = "Vagabond";
        instance.setCareer(expResult);
        String result = instance.getCareer();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetName() {
        System.out.println("GetAndSetName");
        CharacterAttributes instance = new CharacterAttributesPrint();
        String expResult = "Zac";
        instance.setName(expResult);
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetStrength() {
        System.out.println("GetAndSetStrength");
        CharacterAttributes instance = new CharacterAttributesPrint();
        int expResult = 5;
        instance.setStrength(expResult);
        int result = instance.getStrength();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetDexterity() {
        System.out.println("GetAndSetDexterity");
        CharacterAttributes instance = new CharacterAttributesPrint();
        int expResult = 8;
        instance.setDexterity(expResult);
        int result = instance.getDexterity();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetIntelligence() {
        System.out.println("GetAndSetIntelligence");
        CharacterAttributes instance = new CharacterAttributesPrint();
        int expResult = 10;
        instance.setIntelligence(expResult);
        int result = instance.getIntelligence();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetAndGetFaith() {
        System.out.println("GetAndSetFaith");
        CharacterAttributes instance = new CharacterAttributesPrint();
        int expResult = 7;
        instance.setFaith(expResult);
        int result = instance.getFaith();
        assertEquals(expResult, result);
    }

    @Test
    public void testApplyRaceModifiers() {
        System.out.println("applyRaceModifiers");
        CharacterAttributes instance = new CharacterAttributesPrint();
        String race = "Mortal";
        instance.applyRaceModifiers(race);
        assertEquals(7, instance.getStrength());
        assertEquals(10, instance.getDexterity());
        assertEquals(8, instance.getIntelligence());
        assertEquals(9, instance.getFaith());
    }

    @Test
    public void testApplyCareerModifiers() {
        System.out.println("applyCareerModifiers");
        CharacterAttributes instance = new CharacterAttributesPrint();
        String career = "Vagabond";
        instance.applyCareerModifiers(career);
        assertEquals(8, instance.getStrength());
        assertEquals(3, instance.getDexterity());
        assertEquals(1, instance.getIntelligence());
        assertEquals(3, instance.getFaith());
    }

    public class CharacterAttributesPrint extends CharacterAttributes {

        public void display() {
  
        }
    }
}
