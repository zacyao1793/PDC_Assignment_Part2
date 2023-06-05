/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import static org.junit.Assert.*;

public class CharacterInfoTest {
    
    @Test
    public void testRun() {
        String testInput = "2\n3\ny\noutput.txt\n";
        Scanner scanner = new Scanner(testInput);
        CharacterInfo charInfo = new CharacterInfo(scanner);
        
        //redirect output to a file to avoid console printing during test
        try {
            PrintStream fileOut = new PrintStream("./test.txt");
            System.setOut(fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        charInfo.run();
        
        //check if the output file was created
        File outputFile = new File("SavedCharacter.txt");
        assertTrue(outputFile.exists());

        //add additional tests to check the contents of the file, if needed
        
        //cleanup
        outputFile.delete();
    }
}
