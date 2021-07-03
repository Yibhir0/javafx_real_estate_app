
package com.mycompany.realestate;

import com.mycompany.realestate.model.*;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 *  This Controller interacts with property and property View to display property details.
 * @author Yassine Ibhir
 */
public class PropertyViewController {
    
        @FXML
    private Label addrr;

    @FXML
    private Label type;

    @FXML
    private Label rentAmount;

    @FXML
    private Label vacant;

    @FXML
    private Label schoolTax;

    @FXML
    private Label propertyTax;

    @FXML
    private Label insurance;
    
    @FXML
    private Label unitNum;
    
    @FXML
    private Label tenant;
    
    @FXML
    private Label apartment;

    @FXML
    private ListView<Mortgage> mortgages;

    @FXML
    private ListView<Repair> repairs;

    @FXML
    private ListView<Utility> utilities;

    
    @FXML
    private ListView<Lease> leases;

    public void showPropertyDetails(Property p){
         
        String unitOrRoom = Integer.toString(p.getNumberRooms())+ " Rooms";
        String propertyType = p.getPropertyType();
        String add = "";
        String propertyInsurance = null;
        if (!(p.getPropertyType().equals("Plex Unit"))){
            add = p.getAddress();
            propertyInsurance= p.getInsurance().toString();
            if (p.getPropertyType().equals("Plex")){
                unitOrRoom = Integer.toString(((Plex)p).getNumberOfUnits())+" Plex"; 
            }
        }
        else{
            propertyType = "Plex Unit";
            propertyInsurance  = " See Plex";
            add = p.getPlexProperty().getAddress();
        }
        
        //
        addrr.setText(add);
        type.setText(propertyType);
        rentAmount.setText(Double.toString(p.getRentAmount()));
        schoolTax.setText(Double.toString(p.getSchoolTax()));
        propertyTax.setText(Double.toString(p.getPropertyTax()));
        apartment.setText(Integer.toString(p.getApartmentNumber()));
        unitNum.setText(unitOrRoom);
        leases.setItems(getPropertyLeases(p));
        insurance.setText(propertyInsurance);
        vacant.setText(Boolean.toString(p.isIsVacant()));
        mortgages.setItems(getPropertyMortgages(p));
        repairs.setItems(getPropertyRepairs(p));
        utilities.setItems(getPropertyUtilities(p));
        tenant.setText(getTenant(p));
        
    }

    /**
     * get history of a property
     * @param p
     * @return 
     */
    private ObservableList<Mortgage> getPropertyMortgages(Property p) {
         ObservableList<Mortgage> pmortgages = FXCollections.observableArrayList(p.getMortgages());
        
         return pmortgages;
    }
    
    /**
     * gets repairs history of a property
     * @param p
     * @return 
     */
    private ObservableList<Repair> getPropertyRepairs(Property p) {
        ObservableList<Repair> prepairs = FXCollections.observableArrayList(p.getRepairs());
         return prepairs;
    }
    
    /**
     * gets leases history of a property
     * @param p
     * @return 
     */
    private ObservableList<Lease> getPropertyLeases(Property p) {
         ObservableList<Lease> pleases = FXCollections.observableArrayList(p.getLeases());
         return pleases;
    }
    
    /**
     * gets the the current lease
     * @param p
     * @return returns the current tenant
     */
    private String getTenant(Property p) {
        Lease lease = p.getCurrentLease();
        if (lease != null){
            return lease.getTenant().toString();
        }
        return "No current Tenant";
    }
    
     /**
     * gets utilities history of a property
     * @param p
     * @return 
     */
    private ObservableList<Utility> getPropertyUtilities(Property p) {
        ObservableList<Utility> pUtilities = FXCollections.observableArrayList(p.getUtilities());
         return pUtilities;
    }
    
  
}
