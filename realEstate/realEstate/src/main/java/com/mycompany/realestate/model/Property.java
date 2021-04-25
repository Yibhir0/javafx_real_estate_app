
package com.mycompany.realestate.model;


import java.util.ArrayList;
import java.util.List;

/**
 * This Class represents the property instance
 *  
 * @author Yassine Ibhir
 */
public class Property {
    
    // unique id 
    private int propertyId;
    
    // number of units
    private int unitNum;
    
    // type of property (condo,house etc..)
    private String propertyType;
    
    // address of the property
    private String address;
    
    // the annual school tax amount
    private double schoolTax;
    
    // the annual property tax amount
    private double propertyTax;
    
    // the current rent amount
    private double rentAmount;
    
    // is property vacant or not
    private boolean isVacant;
    
    // Insurance provider for property
    private Insurance insurance;
    
    // List of mortgages 
    private List<Mortgage> mortgages = new ArrayList<Mortgage>();
    
    // List of all leases (10 years older)
    private List<Lease> leases = new ArrayList<Lease>();
    
    // list of all utilities when propert is Vacant(hydro)
    private List<Utility> utilities = new ArrayList<Utility>();
    
    // list of all repairs
    private List<Repair> repairs = new ArrayList<Repair>();

    // getters
    public int getPropertyId() {
        return propertyId;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public String getAddress() {
        return address;
    }

    public double getSchoolTax() {
        return schoolTax;
    }

    public double getPropertyTax() {
        return propertyTax;
    }

    public double getRentAmount() {
        return rentAmount;
    }

    public boolean isIsVacant() {
        return isVacant;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public List<Mortgage> getMortgages() {
        return mortgages;
    }

    public List<Lease> getLeases() {
        return leases;
    }

    public List<Utility> getUtilities() {
        return utilities;
    }

    public List<Repair> getRepairs() {
        return repairs;
    }
    public int getUnitNum() {
        return unitNum;
    }

    // setters
    
    public void setUnitNum(int unitNum) {
        this.unitNum = unitNum;
    }
   
    
    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSchoolTax(double schoolTax) {
        this.schoolTax = schoolTax;
    }

    public void setPropertyTax(double propertyTax) {
        this.propertyTax = propertyTax;
    }

    public void setRentAmount(double rentAmount) {
        this.rentAmount = rentAmount;
    }

    public void setIsVacant(boolean isVacant) {
        this.isVacant = isVacant;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public void setMortgages(List<Mortgage> mortgages) {
        this.mortgages = mortgages;
    }

    public void setLeases(List<Lease> leases) {
        this.leases = leases;
    }

    public void setUtilities(List<Utility> utilities) {
        this.utilities = utilities;
    }

    public void setRepairs(List<Repair> repairs) {
        this.repairs = repairs;
    }
    
    // the below methods add item to the corresponding list
    public void addMortgage(Mortgage mortgage){
        mortgages.add(mortgage);
    }
    
    public void addLease(Lease lease){
        leases.add(lease);
    }
    
    public void addRepair(Repair repair){
        repairs.add(repair);
    }
    
    public void addUtility(Utility utility){
        utilities.add(utility);
    }
    
    // the below methods delete from the corresponding list
    public void removeMortgage(Mortgage mortgage){
        mortgages.remove(mortgage);
    }
    
    public void removeLease(Lease lease){
        leases.remove(lease);
    }
    
    public void removeRepair(Repair repair){
        repairs.remove(repair);
    }
    
    public void removeUtility(Utility utility){
        utilities.remove(utility);
    }
    
    public void backUpCopy(Property backUp){
        this.propertyId = backUp.propertyId;
        this.address = backUp.address;
        this.insurance = backUp.insurance;
        this.isVacant = backUp.isVacant;
        this.rentAmount = backUp.rentAmount;
        this.unitNum = backUp.unitNum;
        this.schoolTax = backUp.schoolTax;
        this.propertyTax = backUp.propertyTax;
        this.propertyType = backUp.propertyType;
        
    }
   
    /**
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "Property{Id="+ propertyId + ", Address=" + address + '}';
    }

    
    
}
