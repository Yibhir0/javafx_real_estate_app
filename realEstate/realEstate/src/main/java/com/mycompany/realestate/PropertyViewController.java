/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.realestate;

import com.mycompany.realestate.model.Property;
import javafx.fxml.FXML;

import java.io.IOException;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 *
 * @author T450
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
    private ListView<?> mortgages;

    @FXML
    private ListView<?> repairs;

    @FXML
    private ListView<?> utilities;

    
    @FXML
    private ListView<?> leases;
    @FXML
    private void switchBack() throws IOException {
        App.setRoot("property");
    }
    @FXML
    private void switchHome() throws IOException {
        App.setRoot("shelter");
    }
    
    public void showPropertyDetails(Property p){
        addrr.setText(p.getAddress());
        type.setText(p.getPropertyType());
        rentAmount.setText(Double.toString(p.getRentAmount()));
        schoolTax.setText(Double.toString(p.getSchoolTax()));
        propertyTax.setText(Double.toString(p.getPropertyTax()));
        propertyTax.setText(Double.toString(p.getPropertyTax()));
        unitNum.setText(Integer.toString(p.getUnitNum()));
        vacant.setText(Boolean.toString(p.isIsVacant()));
        insurance.setText(p.getInsurance().toString());
    }
        
}
