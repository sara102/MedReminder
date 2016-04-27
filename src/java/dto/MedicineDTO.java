/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author islam
 */



import java.util.ArrayList;
import java.util.Date;



public class MedicineDTO {
    private String name;
    private int ID;
    private String startDate;
    private int DaysLimit;
    private int repetition;
    private boolean isInterval;
    private String doseUnit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getDaysLimit() {
        return DaysLimit;
    }

    public void setDaysLimit(int DaysLimit) {
        this.DaysLimit = DaysLimit;
    }

    public int getRepetition() {
        return repetition;
    }

    public void setRepetition(int repetition) {
        this.repetition = repetition;
    }

    public boolean isIsInterval() {
        return isInterval;
    }

    public void setIsInterval(boolean isInterval) {
        this.isInterval = isInterval;
    }

    public String getDoseUnit() {
        return doseUnit;
    }

    public void setDoseUnit(String doseUnit) {
        this.doseUnit = doseUnit;
    }

    
}

