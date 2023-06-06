/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author clone
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.io.File;
import java.io.IOException;
import java.sql.Statement;
        
public class Database {
    //Running database in Network server mode
    //private final String url = "jdbc:derby://localhost:1527/characterDB";
    
    //Running database in Embedded mode
    private final String url = "jdbc:derby:characterDB;create=true";

    private final String user = "pdc"; 
    private final String password = "pdc";
    private Connection connection;
    
/**
    public Database() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    **/
    
    //Sets up the connection to the Derby database in Embedded mode
    public Database() {
    try {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        connection = DriverManager.getConnection(url);
        createSchema();
        createTable();
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    }
}
   //Creates a schema named "pdc" if it doesn't already exist in the database, it was original created in Network server mode but the progrm still need to create the schema again or it will show error
    public void createSchema() {
    try (Statement stmt = connection.createStatement()) {
        ResultSet resultSet = connection.getMetaData().getSchemas(null, "PDC");
        if (!resultSet.next()) {
            String createSchemaSQL = "CREATE SCHEMA pdc";
            stmt.execute(createSchemaSQL);
        }
    } catch (SQLException e) {
        //Handle exceptions and errors
        e.printStackTrace();
    }
}
//This method creates a table named "characters" within the "pdc" schema if it doesn't already exist
public void createTable() {
    try (Statement stmt = connection.createStatement()) {
        ResultSet resultSet = connection.getMetaData().getTables(null, "PDC", "CHARACTERS", null);
        if (!resultSet.next()) {
            //Collum types and names
            String createTableSQL = "CREATE TABLE pdc.characters ("
                    + "name VARCHAR(255), "
                    + "race VARCHAR(255), "
                    + "career VARCHAR(255), "
                    + "strength INT, "
                    + "dexterity INT, "
                    + "intelligence INT, "
                    + "faith INT)";
            stmt.execute(createTableSQL);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    //Save CharacterAttributes to the database
    public void saveCharacter(CharacterAttributes character) {
        
        
        //String sql = "INSERT INTO characters(name, race, career, strength, dexterity, intelligence, faith) VALUES(?, ?, ?, ?, ?, ?, ?)";
        String sql = "INSERT INTO pdc.characters(name, race, career, strength, dexterity, intelligence, faith) VALUES(?, ?, ?, ?, ?, ?, ?)";

        
        //Prepares SQL INSERT statement
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, character.getName());
            pstmt.setString(2, character.getRace());
            pstmt.setString(3, character.getCareer());
            pstmt.setInt(4, character.getStrength());
            pstmt.setInt(5, character.getDexterity());
            pstmt.setInt(6, character.getIntelligence());
            pstmt.setInt(7, character.getFaith());
            //executes the statement
            pstmt.executeUpdate();
            System.out.println("Character Saved ");

            
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    //I asked ChatGPT write a load character method since its a bit complex
    //Retrieves character attributes from the database based on the provided name
    public CharacterAttributes loadCharacter(String name) {
        
        //String sql = "SELECT * FROM characters WHERE name = ?";
        String sql = "SELECT * FROM pdc.characters WHERE name = ?";

        //Prepares an SQL SELECT statement
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet resultSet = pstmt.executeQuery();
            
            //Sets the name parameter in the statement
            if (resultSet.next()) {
                String race = resultSet.getString("race");
                String career = resultSet.getString("career");
                int strength = resultSet.getInt("strength");
                int dexterity = resultSet.getInt("dexterity");
                int intelligence = resultSet.getInt("intelligence");
                int faith = resultSet.getInt("faith");
                
                CharacterAttributes character = CharacterCreation.createCharacterAttributes(race, strength, dexterity, intelligence, faith);
                character.setCareer(career);
                character.setName(name);
                
                return character;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return null;
    }
    
    
    
    
    //Shutdown the database once program closed
   public void shutdown() {
    try {
        DriverManager.getConnection("jdbc:derby:;shutdown=true");
    } catch (SQLException e) {
       e.printStackTrace();
    }
}
}

