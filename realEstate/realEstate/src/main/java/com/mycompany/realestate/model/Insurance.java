/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.realestate.model;

/**
 * Class represents the insurance object
 * @author T450
 */
public class Insurance {
    
    // unique id
    private int insuranceId;
    
    // name of the insurance company
    private String name;
    
    // address of the company
    private String address;
    
    //phone number
    private String phone;
    
    // insurance annual payment for any real estate
    private double annualPayment;
    
    //getters

    public int getInsuranceId() {
        return insuranceId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public double getAnnualPayment() {
        return annualPayment;
    }
    
    //setters

    public void setInsuranceId(int insuranceId) {
        this.insuranceId = insuranceId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAnnualPayment(double annualPayment) {
        this.annualPayment = annualPayment;
    }

    @Override
    public String toString() {
        return insuranceId+"{" + "name=" + name + ", annualPayment=" + annualPayment + '}';
    }
    
    /**
	 * @override equals method.
	 * @author Yassine
	 */
    @Override
    public boolean equals(Object obj) {

		if(!(obj instanceof Insurance) || obj == null) {
			return false;
		}
                    
               return ((Insurance) obj).insuranceId == this.insuranceId;
		
				
	}

	/**
	 * @override CompareTo method.
	 * @author Yassine
	 */
//	public int compareTo(Insurance movie) {
//

//	}
//	
    
    
    
}
