
package com.mycompany.realestate.model;

/**
 *
 * @author Yassine Ibhir
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

//    @Override
//    public int hashCode() {
//        int hash = 3;
//        hash = 37 * hash + this.contractorId;
//        return hash;
//    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Contractor other = (Contractor) obj;
        return this.contractorId == other.contractorId;
    }
    
   
    
}
