
package com.mycompany.realestate.model;


import java.util.ArrayList;
import java.util.List;

/**
 * This Class represents the property instance
 *  
 * @author Yassine Ibhir
 */
public  class Property {
    
    
    // unique id 
    private int propertyId;
    
    // number of units
    private int numberRooms;
    
    // type of property (condo,house etc..)
    private String propertyType;
    
    // address of the property
    private String address;
    
    // apartment number (optional)
    private int apartmentNumber;
    
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
    
    // plexProperty is a reference used in plex properties; 
    private Property plexProperty ;
   
    
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
    
    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public List<Utility> getUtilities() {
        return utilities;
    }

    public List<Repair> getRepairs() {
        return repairs;
    }
    public int getNumberRooms() {
        return numberRooms;
    }

    // setters
    
    public void setNumberRooms(int unitNum) {
        this.numberRooms = unitNum;
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
    public Property getPlexProperty() {
        return plexProperty;
    }

    public void setPlexProperty(Property plexProperty) {
        this.plexProperty = plexProperty;
    }
    
    // the below methods add item to the corresponding list
    public void addMortgage(Mortgage mortgage){
        mortgages.add(mortgage);
    }
    
    public void addLease(Lease lease){
        leases.add(0,lease);
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
    
    /**
     * update property Mortgage
     * @param oldMortgage
     * @param newMortgage 
     */
    public void updateMortage(Mortgage oldMortgage,Mortgage newMortgage){
        
        int index = this.getMortgages().indexOf(oldMortgage);
        
        this.getMortgages().set(index,newMortgage);
    }
    
    /**
     * 
     * @return the current lease
     */
    public Lease getCurrentLease(){
     int size = this.leases.size();
     if (size == 0){
         return null;
     }
     Lease lease = this.leases.get(0);
     return lease;
    }
   
    /**
     * 
     * @return String
     */
    @Override
    public String toString() {
        if (this instanceof House || this instanceof Plex){
            return " Id="+ propertyId + ", Address=" + address + '}';
        }
        if (this instanceof Condo){
            return " Id="+ propertyId + ", Address=" + address +", App#=" + apartmentNumber + '}';
        }
        return "Plex Unit{ Id="+ propertyId + ", Address=" + this.plexProperty.getAddress() +", App#=" + apartmentNumber + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.propertyId;
        return hash;
    }

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
        final Property other = (Property) obj;
        if (this.propertyId != other.propertyId) {
            return false;
        }
        return true;
    }
    
     // backUp copy af property attributes
    public void updateExistentProperty(Property backUp){
        if (backUp instanceof Condo){
            ((Condo)this).setCondoFees(((Condo)backUp).getCondoFees());
        }
        this.address = backUp.address;
        this.insurance = backUp.insurance;
        this.isVacant = backUp.isVacant;
        this.rentAmount = backUp.rentAmount;
        this.numberRooms = backUp.numberRooms;
        this.schoolTax = backUp.schoolTax;
        this.propertyTax = backUp.propertyTax;
        this.propertyType = backUp.propertyType;
        this.apartmentNumber = backUp.apartmentNumber;
    }

}
