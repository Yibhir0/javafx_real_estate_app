
package com.mycompany.realestate.model;


import java.time.LocalDate;


/**
 * class represents repair object
 * @author Yassine Ibhir
 */
public class Repair {

    @Override
    public String toString() {
        return "Repair{" + "repairId=" + repairId + ", type=" + type + ", cost=" + cost + '}';
    }
    private int repairId;
    private String type;
    private double cost;
    private LocalDate startDate ;
    private LocalDate endDate ;
    private Property property;
    private Contractor contractor;
    
    // update existent repair
    public void updateRepair(Repair repairUpdated) {
        this.repairId = repairUpdated.repairId;
        this.type= repairUpdated.type;
        this.cost= repairUpdated.cost;
        this.startDate = repairUpdated.startDate;
        this.endDate = repairUpdated.endDate;
        this.property = repairUpdated.property;
        this.contractor= repairUpdated.contractor;
    }
    
    // getters and setters
    public int getRepairId() {
        return repairId;
    }

    public String getType() {
        return type;
    }

    public double getCost() {
        return cost;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
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

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }
 
}
