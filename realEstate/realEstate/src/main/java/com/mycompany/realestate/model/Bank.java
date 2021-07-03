package com.mycompany.realestate.model;

/**
 * This class represents Bank Object
 *
 * @author Yassine Ibhir
 */
public class Bank {

    private int bankId;
    private String name;
    private double intrestRate;
    private String address;
    private String phone;

    @Override
    public String toString() {
        return "Bank{" + "bankId=" + bankId + ", name=" + name + ", intrestRate=" + intrestRate + '}';
    }

    /**
     * @override equals method.
     */
    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Bank) || this == null) {
            return false;
        }

        return ((Bank) obj).bankId == this.bankId;

    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.bankId;
        return hash;
    }
    
    // getters and setters
    public int getBankId() {
        return bankId;
    }

    public String getName() {
        return name;
    }

    public double getIntrestRate() {
        return intrestRate;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIntrestRate(double intrestRate) {
        this.intrestRate = intrestRate;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
