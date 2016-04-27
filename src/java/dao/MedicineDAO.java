/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.MedicineDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author islam
 */
public class MedicineDAO {
    
    Connection connection;

    public MedicineDAO() {

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
    
    public void insertAllData(List<MedicineDTO> medicines) {

        for (MedicineDTO medicine : medicines) {

            try {
                String query = " insert into medicine (id, name, start_date, days_limit, repetition_by_days, time_interval, dose_unit)"
                        + " values (?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setInt(1, medicine.getID());
                preparedStmt.setString(2, medicine.getName());
                preparedStmt.setString(3, medicine.getStartDate());
                preparedStmt.setInt(4, medicine.getDaysLimit());
                preparedStmt.setInt(5, medicine.getRepetition());
                preparedStmt.setBoolean(6, medicine.isIsInterval());
                preparedStmt.setString(7, medicine.getDoseUnit());
                

                preparedStmt.execute();

                

            } catch (SQLException ex) {

                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConnection();
            
        }
    }
    
}
