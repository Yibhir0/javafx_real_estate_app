
package com.mycompany.realestate.model;

import java.time.LocalDate;

/**
 * This class represents the Utility object
 * @author Yassine Ibhir
 */
public class Utility {

    @Override
    public String toString() {
        return "Utility{" + "utilityId=" + utilityId + ", amount=" + amount + ", Payment Date = "+ paymentDate + '}';
    }
    private int utilityId;
    private double amount;
    private LocalDate paymentDate;
    private Property property;
    
    // getters and setters
    public int getUtilityId() {
        return utilityId;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public Property getProperty() {
        return property;
    }

    public void setUtilityId(int utilityId) {
        this.utilityId = utilityId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
    
    /**
     * update existent utility
     * @param utilityUpdated 
    */
    public void updateUtility(Utility utilityUpdated) {
        this.utilityId = utilityUpdated.utilityId;
        this.property = utilityUpdated.property;
        this.paymentDate = utilityUpdated.paymentDate;
        this.amount = utilityUpdated.amount;
    }
}
