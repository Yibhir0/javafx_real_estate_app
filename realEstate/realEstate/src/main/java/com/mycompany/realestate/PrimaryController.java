package com.mycompany.realestate;

import javafx.fxml.FXML;

import java.io.IOException;

public class PrimaryController {
     @FXML
    private void switchToProperty() throws IOException {
        App.setRoot("property");
    }
    
    @FXML
    private void switchToLease() throws IOException {
        App.setRoot("lease");
    }
    @FXML
    private void switchToTenant() throws IOException {
        App.setRoot("tenant");
    }
    
    @FXML
    private void switchToRent() throws IOException {
        App.setRoot("rent");
    }
    @FXML
    private void switchToMortgage() throws IOException {
        App.setRoot("mortgage");
    }
    @FXML
    private void switchToBank() throws IOException {
        App.setRoot("bank");
    }
     @FXML
    private void switchToRepair() throws IOException {
        App.setRoot("repair");
    }
    @FXML
    private void switchToContractor() throws IOException {
        App.setRoot("contractor");
    }
    
    @FXML
    private void switchToInsurance() throws IOException {
        App.setRoot("insurance");
    }
      @FXML
    private void switchToUtility() throws IOException {
        App.setRoot("utility");
    }
    
}
