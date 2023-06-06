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
   
    public void createSchema() {
    try (Statement stmt = connection.createStatement()) {
        ResultSet resultSet = connection.getMetaData().getSchemas(null, "PDC");
        if (!resultSet.next()) {
            String createSchemaSQL = "CREATE SCHEMA pdc";
            stmt.execute(createSchemaSQL);
            System.out.println("Created schema in given database...");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void createTable() {
    try (Statement stmt = connection.createStatement()) {
        ResultSet resultSet = connection.getMetaData().getTables(null, "PDC", "CHARACTERS", null);
        if (!resultSet.next()) {
            String createTableSQL = "CREATE TABLE pdc.characters ("
                    + "name VARCHAR(255), "
                    + "race VARCHAR(255), "
                    + "career VARCHAR(255), "
                    + "strength INT, "
                    + "dexterity INT, "
                    + "intelligence INT, "
                    + "faith INT)";
            stmt.execute(createTableSQL);
            System.out.println("Created table in given database...");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    
    public void saveCharacter(CharacterAttributes character) {
        
        
        //String sql = "INSERT INTO characters(name, race, career, strength, dexterity, intelligence, faith) VALUES(?, ?, ?, ?, ?, ?, ?)";
String sql = "INSERT INTO pdc.characters(name, race, career, strength, dexterity, intelligence, faith) VALUES(?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, character.getName());
            pstmt.setString(2, character.getRace());
            pstmt.setString(3, character.getCareer());
            pstmt.setInt(4, character.getStrength());
            pstmt.setInt(5, character.getDexterity());
            pstmt.setInt(6, character.getIntelligence());
            pstmt.setInt(7, character.getFaith());

            pstmt.executeUpdate();
            System.out.println("Character Saved ");

            
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    public CharacterAttributes loadCharacter(String name) {
        
        //String sql = "SELECT * FROM characters WHERE name = ?";
        String sql = "SELECT * FROM pdc.characters WHERE name = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet resultSet = pstmt.executeQuery();
            
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
    
    //I asked ChatGPT write a shutdown method for the database
   public void shutdown() {
    try {
        DriverManager.getConnection("jdbc:derby:;shutdown=true");
    } catch (SQLException e) {
        if (((e.getErrorCode() == 50000) && ("XJ015".equals(e.getSQLState())))) {     
            System.out.println("Derby shut down normally");
        } else {
            System.out.println("Derby did not shut down normally");
            e.printStackTrace();
        }
    }
}
}

