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
public class Tenant {

    @Override
    public String toString() {
        return "Tenant{" + "tenantId=" + tenantId + ", fullName=" + fullName + '}';
    }
    private int tenantId;
    private String identity;
    private String fullName;
    private String email;
    private String phone;

    public int getTenantId() {
        return tenantId;
    }

    public String getIdentity() {
        return identity;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
