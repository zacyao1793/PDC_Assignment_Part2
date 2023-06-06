/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.*;

public class MortalTest {
    
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private Mortal instance;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        instance = new Mortal(7, 10, 8, 9);
        instance.setCareer("Vagabond");
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testDisplay() {
        instance.display();
        String expected = 
                
                           "Race: Mortal\n"
                        + "Career: Vagabond\n"
                        + "Strength: 7\n"
                        + "Dexterity: 10\n"
                        + "Intelligence: 8\n"
                        + "Faith: 9\n";
        assertEquals(expected, outContent.toString());
    }
}
