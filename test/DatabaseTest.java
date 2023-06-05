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

    @Before
    public void setUp() {
        db = new Database();
    }

    @Test
    public void testSaveCharacter() {
        try {
            CharacterAttributes getName = new CharacterAttributes();  // TODO: replace this with a real CharacterAttributes object
            db.saveCharacter(getName);
        } catch (Exception e) {
            fail("Saving character failed with exception: " + e.getMessage());
        }
    }
}
