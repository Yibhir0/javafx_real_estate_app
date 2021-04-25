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
public class Rent {

    @Override
    public String toString() {
        return "Rent{" + "rentId=" + rentId + ", fullyBaid=" + fullyBaid + ", lease=" + lease + '}';
    }
    private int rentId;
    private String paymentMethod;
    private LocalDate paymentDate;
    private boolean fullyBaid;
    private Lease lease;

    public int getRentId() {
        return rentId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public boolean isFullyBaid() {
        return fullyBaid;
    }

    public Lease getLease() {
        return lease;
    }

    public void setRentId(int rentId) {
        this.rentId = rentId;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setFullyBaid(boolean fullyBaid) {
        this.fullyBaid = fullyBaid;
    }

    public void setLease(Lease lease) {
        this.lease = lease;
    }
}
