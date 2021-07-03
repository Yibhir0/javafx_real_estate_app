
package com.mycompany.realestate.model;

import java.time.LocalDate;


/**
 * Mortgage object
 * @author Yassine Ibhir
 */
public class Mortgage {

    @Override
    public String toString() {
        return "Mortgage{" + "mortgageId=" + mortgageId + ", amount=" + amount + ", fullyPaid=" + fullyPaid + '}';
    }
    private int mortgageId;
    private int term;
    private double amount;
    private LocalDate startDate;
    private double downPayment;
    private boolean fullyPaid;
    private Property property;
    private Bank bank;
    
    // getters
    public int getMortgageId() {
        return mortgageId;
    }

    public int getTerm() {
        return term;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public double getDownPayment() {
        return downPayment;
    }

    public boolean isFullyPaid() {
        return fullyPaid;
    }

    public Property getProperty() {
        return property;
    }

    public Bank getBank() {
        return bank;
    }
    
    //setters
    public void setMortgageId(int mortgageId) {
        this.mortgageId = mortgageId;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setDownPayment(double downPayment) {
        this.downPayment = downPayment;
    }

    public void setFullyPaid(boolean fullyPaid) {
        this.fullyPaid = fullyPaid;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void updateMortgae(Mortgage mortgageNewUpdate) {
        this.mortgageId = mortgageNewUpdate.mortgageId;
        this.term = mortgageNewUpdate.term;
        this.amount = mortgageNewUpdate.amount;
        this.startDate = mortgageNewUpdate.startDate;
        this.downPayment = mortgageNewUpdate.downPayment;
        this.fullyPaid = mortgageNewUpdate.fullyPaid;
        this.property= mortgageNewUpdate.property;
        this.bank = mortgageNewUpdate.bank;
    }
//      private int mortgageId;
//    private int term;
//    private double amount;
//    private Date startDate;
//    private double downPayment;
//    private boolean fullyPaid;
//    private Property property;
//    private Bank bank;
}
