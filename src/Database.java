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

public class Database {
    private final String url = "jdbc:derby://localhost:1527/characterDB";
    private final String user = "pdc"; 
    private final String password = "pdc";

    public void saveCharacter(CharacterAttributes character) {
        String sql = "INSERT INTO characters(name, race, career, strength, dexterity, intelligence, faith) VALUES(?, ?, ?, ?, ?, ?, ?)";

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
}

