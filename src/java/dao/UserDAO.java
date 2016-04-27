/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.UserDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author islam
 */
public class UserDAO {

    Connection connection;

    public UserDAO() {

        setConnection();
    }

    private void setConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/medreminder", "root", "Hsghl811");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserDTO insertUser(String email, String password) {

        UserDTO user = null;

        try {
            String query = " insert into user (email, password)"
                    + " values (?, ?)";

            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, email);
            preparedStmt.setString(2, password);

            preparedStmt.execute();

            user = getUser(email, password);

            closeConnection();

        } catch (SQLException ex) {

            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        return user;
    }

    public UserDTO getUser(String email, String password) {

        UserDTO user = null;

        try {
            String selectSQL = "select id, email, password, gender, birthdate from user where email = ? AND password = ?";

            PreparedStatement preparedStmt = connection.prepareStatement(selectSQL);
            System.out.println("sent email: " + email);
            System.out.println("sent password: " + password);

            preparedStmt.setString(1, email);
            preparedStmt.setString(2, password);

            ResultSet rs = preparedStmt.executeQuery();

            while (rs.next()) {

                System.out.println("rs has next");
                user = new UserDTO();
                user.setEmail(rs.getString("email"));
                user.setGender(rs.getString("gender"));
                user.setBirthdate(rs.getLong("birthdate"));
                user.setPassword(rs.getString("password"));
                user.setId(rs.getInt("id"));
            }

            closeConnection();

        } catch (SQLException ex) {

            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        return user;
    }

    public String updateBirthdate(int id, long birthdate) {

        try {
            String updateTableSQL = "UPDATE user SET birthdate = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateTableSQL);
            preparedStatement.setLong(1, birthdate);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return "something is wrong";
        }
        
        closeConnection();
        return "data updated succefully";
    }

    public String updateGender(int id, String gender) {

        try {
            String updateTableSQL = "UPDATE user SET gender = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateTableSQL);
            preparedStatement.setString(1, gender);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return "something is wrong";
        }
        
        return "data updated succefully";
    }

}
