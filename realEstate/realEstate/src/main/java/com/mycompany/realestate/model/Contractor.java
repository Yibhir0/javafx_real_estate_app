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
public class Contractor {

    @Override
    public String toString() {
        return "Contractor{" + "contractorId=" + contractorId + ", name=" + name + ", specialization=" + specialization + '}';
    }
    private int contractorId;
    private String name;
    private String specialization;
    private String address;
    private String phone;

    public int getContractorId() {
        return contractorId;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void setContractorId(int contractorId) {
        this.contractorId = contractorId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
