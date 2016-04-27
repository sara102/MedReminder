/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vogella.jersey.first;

import dao.MedicineDAO;
import dao.ScheduleDAO;
import dto.MedicineDTO;
import dto.ScheduleDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;


/**
 *
 * @author islam
 */
@Path("/transporter")
public class MedicineTransporter {

    @Path("insertion")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String insertTable(@FormParam("json") String jsonStr) {

        
        System.out.println("json is: " + jsonStr);
        try {
            JSONObject obj = new JSONObject(jsonStr);
            
            JSONArray medicineTable = obj.getJSONArray("medicineTable");
            JSONArray scheduleTable = obj.getJSONArray("scheduleTable");
            JSONArray ignoredScheduleTable = obj.getJSONArray("ignoredScheduleTable");
            
            parseMedicineTable(medicineTable);
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            parseScheduleTable(scheduleTable, "schedule");
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            parseScheduleTable(ignoredScheduleTable, "ignoredschedule");
            
        } catch (JSONException ex) {
            Logger.getLogger(MedicineTransporter.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "hello";
    }
    
    private static void parseMedicineTable(JSONArray medicineTable) throws JSONException {

        List<MedicineDTO> medicines = new ArrayList<>();
        
        for (int i = 0; i < medicineTable.length(); i++) {
            MedicineDTO medicine = new MedicineDTO();

            JSONObject jsonobject = medicineTable.getJSONObject(i);

            medicine.setDaysLimit(jsonobject.getInt("daysLimit"));
            medicine.setDoseUnit(jsonobject.getString("doseUnit"));
            medicine.setID(jsonobject.getInt("id"));
            medicine.setIsInterval(jsonobject.getBoolean("timeInterval"));
            medicine.setName(jsonobject.getString("name"));
            medicine.setRepetition(jsonobject.getInt("repetition"));
            medicine.setStartDate(jsonobject.getString("startDate"));
            
            medicines.add(medicine);

        }
        MedicineDAO dao = new MedicineDAO();
        dao.insertAllData(medicines);
    }

    private static void parseScheduleTable(JSONArray scheduleTable, String table) throws JSONException {

        List<ScheduleDTO> schedules = new ArrayList<>();
        
        for (int i = 0; i < scheduleTable.length(); i++) {
            ScheduleDTO schedule = new ScheduleDTO();

            JSONObject jsonobject = scheduleTable.getJSONObject(i);

            schedule.setDose((float)jsonobject.getDouble("dose"));
            schedule.setTime(jsonobject.getLong("reminderTime"));
            schedule.setId(jsonobject.getInt("id"));

            schedules.add(schedule);

        }
        
        ScheduleDAO dao = new ScheduleDAO();
        dao.insertAllData(schedules, table);
    }
}
