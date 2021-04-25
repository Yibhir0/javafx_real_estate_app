/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.realestate.model;

/**
 *
 * @author T450
 */
public class Bank {

    @Override
    public String toString() {
        return "Bank{" + "bankId=" + bankId + ", name=" + name + ", intrestRate=" + intrestRate + '}';
    }
   private int bankId;
   private String name;
   private double intrestRate;
   private String address;
   private String phone;

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
