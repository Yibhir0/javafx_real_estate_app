/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.realestate.model;

import java.time.LocalDate;

/**
 *
 * @author T450
 */
public class Utility {

    @Override
    public String toString() {
        return "Utility{" + "utilityId=" + utilityId + ", amount=" + amount + '}';
    }
    private int utilityId;
    private double amount;
    private LocalDate paymentDate;
    private Property property;

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
}
