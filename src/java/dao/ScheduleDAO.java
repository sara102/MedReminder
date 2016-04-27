/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.ScheduleDTO;
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
public class ScheduleDAO {

    Connection connection;

    public ScheduleDAO() {

        setConnection();
    }

    private void setConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager
                    .getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7116984", "sql7116984", "N8qjBKcGL4");
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

    public void insertAllData(List<ScheduleDTO> schedules, String table) {

        for (ScheduleDTO schedule : schedules) {

            try {
                String query = " insert into " + table + " (id, dose, reminder_time)"
                        + " values (?, ?, ?)";

                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setInt(1, schedule.getId());
                preparedStmt.setFloat(2, schedule.getDose());
                preparedStmt.setLong(3, schedule.getTime());

                preparedStmt.execute();

                

            } catch (SQLException ex) {

                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        closeConnection();
    }
}
