package com.mycompany.realestate;

import com.mycompany.realestate.model.Company;
import com.mycompany.realestate.model.Lease;
import com.mycompany.realestate.model.Property;
import com.mycompany.realestate.model.Rent;
import com.mycompany.realestate.model.Tenant;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * primary controller allows navigation to all views
 * and also puts information about vacantProperties, current leases and rent updates
 * @author Yassine 
 */
public class PrimaryController implements Initializable {
    
// vacant properties components
    @FXML
    private TableView<Property> vacantPropertyTable;

    @FXML
    private TableColumn<Property, Integer> propertyId;

    @FXML
    private TableColumn<Property, String> pType;

    @FXML
    private TableColumn<Property, String> vacantAddress;

    @FXML
    private TableColumn<Property, Integer> apartment;
    
    // current leases table 
     @FXML
    private TableView<Lease> currentLeasesTable;

    @FXML
    private TableColumn<Lease, Integer> leaseId;

    @FXML
    private TableColumn<Lease, Integer> endD;

    @FXML
    private TableColumn<Lease, Boolean> renewal;

    @FXML
    private TableColumn<Lease, Tenant> tenant;
    
    
    @FXML
    private ListView<Rent> rentStatus;

     @Override
    public void initialize(URL url, ResourceBundle rb) {
        setUpVacantTable();
        fillUpVacantTable();
        setUpLeaseTable();
        fillUpLeaseTable();
        tableListener();
    }
    
       
    /**
     * puts values of vacant properties inside the table
     */
    private void fillUpVacantTable() {
        List<Property> properties = Company.getInstance().getVacantProperties(); 
        ObservableList<Property> data = FXCollections.observableArrayList(properties);
        vacantPropertyTable.setItems(data); 
    }
    /**
     * sets current leases table columns
     */
    private void setUpLeaseTable(){
         //set up the columns in the table
        leaseId.setCellValueFactory(new PropertyValueFactory<>("leaseId"));
        endD.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        renewal.setCellValueFactory(new PropertyValueFactory<>("renewal"));        
        tenant.setCellValueFactory(new PropertyValueFactory<>("tenant"));  
    }
    
    /**
     * puts values of current leases inside the table
     */
    private void fillUpLeaseTable() {
        List<Lease> leases = Company.getInstance().getAllCurrentLeases(); 
        ObservableList<Lease> data = FXCollections.observableArrayList(leases);
        currentLeasesTable.setItems(data); 
    }
    
       /**
     * sets vacant property table columns
     */
    private void setUpVacantTable(){
         //set up the columns in the table
        propertyId.setCellValueFactory(new PropertyValueFactory<>("propertyId"));
        pType.setCellValueFactory(new PropertyValueFactory<>("propertyType"));
        vacantAddress.setCellValueFactory(new PropertyValueFactory<>("address"));        
        apartment.setCellValueFactory(new PropertyValueFactory<>("apartmentNumber"));  
    }
 
    private void tableListener(){    
        currentLeasesTable.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection, newSelection) -> {
            fillUpListViewRents(newSelection);
        });
    }
    
    /**
     * displays rentStatus of  a current lease
     * @param newSelection 
     */
    private void fillUpListViewRents(Lease newSelection) {
        ObservableList<Rent> prents = FXCollections.observableArrayList(newSelection.getRents());
        rentStatus.setItems(prents);
        
    }
    // navigation menu events
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
