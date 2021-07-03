
package com.mycompany.realestate.model;

/**
 *
 * @author Yassine Ibhir
 */
public class Condo extends Property {
    // condos fees
    private double condoFees;
    
    
    
    public Condo(){
        super();
    }

   // getters and setters
    public double getCondoFees() {
        return condoFees;
    }

    
    public void setCondoFees(double condosFees) {
        this.condoFees = condosFees;
    }

   

    @Override
    public String toString() {
        return "Condo{ "+ super.toString()+'}';
    }
    
}

// @Override
//    public void updateExistentProperty(Property backUp){
//        super.updateExistentProperty(backUp);
//        this.condoFees = ((Condo) backUp).condoFees;
//    }