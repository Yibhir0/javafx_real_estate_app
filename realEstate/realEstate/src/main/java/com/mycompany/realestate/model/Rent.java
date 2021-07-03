
package com.mycompany.realestate.model;


import java.time.LocalDate;


/**
 * This class represents Rent Object
 * @author Yassine Ibhir
 */
public class Rent {

    @Override
    public String toString() {
        return "Rent{" + "rentId=" + rentId + ", fullyBaid=" + fullyPaid + ", payment date=" + paymentDate + '}';
    }
    private int rentId;
    private String paymentMethod;
    private LocalDate paymentDate;
    private Double amountPaid;
    private boolean fullyPaid;
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

    public boolean isFullyPaid() {
        return fullyPaid;
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

    public void setFullyPaid(boolean fullyPaid) {
        this.fullyPaid = fullyPaid;
    }

    public void setLease(Lease lease) {
        this.lease = lease;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public void updateRent(Rent rentUpdated) {
        this.amountPaid = rentUpdated.amountPaid;
        this.fullyPaid = rentUpdated.fullyPaid ;
        this.paymentDate = rentUpdated.paymentDate;
        this.paymentMethod =  rentUpdated.paymentMethod;
    }
}
