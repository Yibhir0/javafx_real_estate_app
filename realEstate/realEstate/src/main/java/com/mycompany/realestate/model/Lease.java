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
public class Lease {

    @Override
    public String toString() {
        return "Lease{" + "leaseId=" + leaseId + ", tenant=" + tenant + '}';
    }
   private int leaseId;
   private int term;
   private LocalDate startDate;
   private boolean renewal;
   private Property property;
   private Tenant tenant;

    public int getLeaseId() {
        return leaseId;
    }

    public int getTerm() {
        return term;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public boolean isRenewal() {
        return renewal;
    }

    public Property getProperty() {
        return property;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setLeaseId(int leaseId) {
        this.leaseId = leaseId;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setRenewal(boolean renewal) {
        this.renewal = renewal;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }
   
}
