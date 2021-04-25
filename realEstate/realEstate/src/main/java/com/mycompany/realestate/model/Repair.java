/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.realestate.model;


import java.util.Date;

/**
 *
 * @author T450
 */
public class Repair {

    @Override
    public String toString() {
        return "Repair{" + "repairId=" + repairId + ", type=" + type + ", cost=" + cost + '}';
    }
    private int repairId;
    private String type;
    private double cost;
    private Date startDate ;
    private Date endDate ;
    private Property property;
    private Contractor contractor;

    public int getRepairId() {
        return repairId;
    }

    public String getType() {
        return type;
    }

    public double getCost() {
        return cost;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Property getProperty() {
        return property;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public void setRepairId(int repairId) {
        this.repairId = repairId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }
    
    
}
