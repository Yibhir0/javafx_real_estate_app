
package com.mycompany.realestate;

import com.mycompany.realestate.model.Company;
import com.mycompany.realestate.model.Condo;
import com.mycompany.realestate.model.House;
import com.mycompany.realestate.model.Lease;
import com.mycompany.realestate.model.Property;
import com.mycompany.realestate.model.Tenant;
import com.mycompany.realestate.model.database.CondoDbAccess;
import com.mycompany.realestate.model.database.HouseDbAccess;
import com.mycompany.realestate.model.database.LeaseDbAccess;
import com.mycompany.realestate.model.database.PlexDbAccess;
import com.mycompany.realestate.model.database.PropertyDAO;
import com.mycompany.realestate.model.database.TenantDbAccess;
import java.io.File;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * Lease Controller
 * @author Yassine Ibhir
 */
public class LeaseController implements Initializable {
    
    @FXML
    private TextField term;

    @FXML
    private Button add;

    @FXML
    private Button save;

    @FXML
    private DatePicker sdate;

    @FXML
    private ComboBox<Tenant> tenantId;

    @FXML
    private ComboBox<Property> propertyId;

    @FXML
    private ComboBox<Boolean> renewal = new ComboBox();
   
    @FXML
    private Label leaseUpload;
    @FXML
    private TableColumn<Lease, Integer> id;

    @FXML
    private TableColumn<Lease, Property> pid;

    @FXML
    private TableColumn<Lease, Date> startD;

    @FXML
    private TableColumn<Lease, Date> endDate;

    @FXML
    private TableColumn<Lease, Tenant>tenant;

    @FXML
    private TableColumn<Lease, Boolean> renews;

    @FXML
    private TableColumn<Lease, String> pdfFile;
    
    @FXML
    private TableView<Lease> tableView;

    @FXML
    private Button delete;

    @FXML
    private Button edit;

    @FXML
    private Button view;
    
    TenantDbAccess tdba = new TenantDbAccess();
    
    LeaseDbAccess ldba = new LeaseDbAccess();
    
    private ObservableList<Lease> leases;
    
    private File selectedPdfFile;
    
    private Lease leaseToUpdate;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        setTableColumns();     
        
        fillUpTable();

        setPropertyComboBox();
        
        setTenantComboBox();
        
        setRenewalComboBox();
        
        tableListener();
 
    }

    /**
     * sets the table columns with the leases attributes
     */
    private void setTableColumns(){
         //set up the columns in the table
        id.setCellValueFactory(new PropertyValueFactory<>("leaseId"));
        pid.setCellValueFactory(new PropertyValueFactory<>("property"));
        endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));        
        startD.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        renews.setCellValueFactory(new PropertyValueFactory<>("renewal"));
        tenant.setCellValueFactory(new PropertyValueFactory<>("tenant"));
        pdfFile.setCellValueFactory(new PropertyValueFactory<>("leaseFileName"));
         
    }
    
    
    /**
     * puts all leases in the table
     */
    private void fillUpTable() {
       List<Property> properties = Company.getInstance().getAllProperties(); 
       List <Lease> proLeases = new ArrayList<>() ;
       properties.forEach(p -> {
           proLeases.addAll(p.getLeases());
         });
      
       leases = FXCollections.observableArrayList(proLeases);
       tableView.setItems(leases); 
    }
    
    /**
     * gets the values entered by user,creates a lease and stores it. 
     * @param event 
     */
    @FXML
    void addNewLease(ActionEvent event) {
        if(validateFields() ){
            Lease lease = createLease();            
            
            int added = ldba.addLease(lease);
            if (added > 0){
                uploadLeaseToDirectory(lease);
                Property p = lease.getProperty();
                p.addLease(lease);
                p.setIsVacant(false);
                 // update in database
                updatePropertyState(p);
                leases.add(lease);//we add lease to the observebalList
                clearFields();
                alertWindow("Lease is added",0);    
            }
            else{
            alertWindow("Lease is Not Added.Try Again",1);
            }
        }
    }
    
    /**
     * calls copyTo lease directory method to put store the file locally
     */
    private void uploadLeaseToDirectory(Lease l){
        try {
            l.copyToLeaseDirectory();
        } catch (IOException ex) {
            Logger.getLogger(LeaseController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    /**
     * creates a lease with the values entered bu user
     * @return lease
     */
    private Lease createLease(){
        Lease lease = new Lease();
        lease.setTerm(Integer.parseInt(term.getText()));
        lease.setRenewal(renewal.getValue());
        lease.setProperty(propertyId.getValue());
        lease.setTenant(tenantId.getValue());
        lease.setStartDate(sdate.getValue());
        lease.setEndDate();
        lease.setPdfFile(selectedPdfFile);
        lease.setLeaseFileName(lease.getTenant().getFullName()+lease.getLeaseId()+'_'+lease.getEndDate()+".pdf");
        return lease;
    }
    
    /**
     * disables all buttons except the save button to allow the user
     * to change the lease values
     * @param event 
     */
    @FXML
    void modifyLease(ActionEvent event) {
        save.setDisable(false);
        add.setDisable(true);
        edit.setDisable(true);
        delete.setDisable(true);
        view.setDisable(true);
        leaseToUpdate = tableView.getSelectionModel().getSelectedItem();
        setTextfields();
    }
    
        /**
     * set values to update 
     * @param m 
     */
    private void setTextfields(){
      term.setText(Integer.toString(leaseToUpdate.getTerm()));
      renewal.setValue(leaseToUpdate.isRenewal());
      propertyId.setValue(leaseToUpdate.getProperty());
      tenantId.setValue(leaseToUpdate.getTenant());
      sdate.setValue(leaseToUpdate.getStartDate());
      
      leaseUpload.setText("Please upload a new pdf lease");
    
    }

    /**
     * confirms and removes lease
     * @param event 
     */
    @FXML
    void removeLease(ActionEvent event) {
        Lease leaseToRemove = tableView.getSelectionModel().getSelectedItem();
        if (alertWindow("Are You sure you want to delete "+ leaseToRemove.toString()+ " ?", 2)){
            int deleted = ldba.deleteLease(leaseToRemove);
            if (deleted != 0){
                Property p = leaseToRemove.getProperty();
                
                if (leaseToRemove == p.getCurrentLease()){
                    p.setIsVacant(true);
                     // update in database
                    updatePropertyState(p);
                }
                p.getLeases().remove(leaseToRemove);
                leases.remove(leaseToRemove); // remove from observebalList
                
            }
            else{
                alertWindow("Lease is Not deleted. Try Again",1);  
            }
        }
    }
    
    /**
     * saves updated values
     * @param event 
     */
    @FXML
    void saveUpdatedLease(ActionEvent event) {
        if (validateFields()){
            Lease leaseNewUpdate =  createLease(); 
            leaseNewUpdate.setLeaseId(leaseToUpdate.getLeaseId());
          
            int updated = ldba.updateLease(leaseNewUpdate);
            
            if (updated > 0){
                alertWindow("Lease "+ leaseNewUpdate +" is Updated.",0); 
                
                  /**
                   * if  property value of the new lease is not equal to the property of the old one
                   * then the old one should be vacant
                   **/
                  
                if(!(leaseToUpdate.getProperty().equals(leaseNewUpdate.getProperty()))){
                        leaseToUpdate.getProperty().setIsVacant(true);
                        updatePropertyState(leaseToUpdate.getProperty());
                }
                
                int index = leases.indexOf(leaseToUpdate);
                
                // update lease object
                leaseToUpdate.updateLease(leaseNewUpdate);
                
                // update the observebalList leases
                leases.set(index,leaseToUpdate);
                
                //put the updated lease file in the local directory
                uploadLeaseToDirectory(leaseToUpdate);
                
                // set vacant to false(not vacant)
                Property p = leaseToUpdate.getProperty();
                p.setIsVacant(false);
                // update in database
                updatePropertyState(p);
            }
            
            else{
                alertWindow("lease "+ (leaseToUpdate)+" is Not Updated--->Data base  problem.",1); 
            }
            
        }
        clearFields();
        add.setDisable(false);
        save.setDisable(true);

    }
    
    /**
     * updates the value of vacant in database
     * @param p 
     */
    private void updatePropertyState(Property p){
        PropertyDAO propertyDAOaccess = getPropertyDAOaccess(p);
        if (p.getPropertyType().equals("Plex Unit")){
            ((PlexDbAccess)propertyDAOaccess).updatePlexUnit(p);
        }
        else{
           propertyDAOaccess.updateProperty(p); 
        }
  
    }
    
     /**
     * gets database access instance(Plex,house or condo)
     */
    
    private PropertyDAO getPropertyDAOaccess(Property property){
        PropertyDAO propertyDAOaccess;
        if (property instanceof House){
            propertyDAOaccess = new HouseDbAccess();
        }
        else if (property instanceof Condo){
            propertyDAOaccess = new CondoDbAccess(); 
        }
        else{
            propertyDAOaccess = new PlexDbAccess();
        }
        return propertyDAOaccess;
    }
    
     /**
     * validate inputs
     * @return 
     */
    private boolean validateFields(){
        if (validateNumeric() && propertyId.getValue() != null && tenantId.getValue() != null
                && renewal.getValue() != null && sdate.getValue() != null){
            if (selectedPdfFile != null) {
                return true;
            }
            else{
                alertWindow("Please upload a pdf file for the lease",1);
            }
        }
        else{
            alertWindow("INVALID INPUTS...please enter appopriate values",1);
        }
        return false; 
    }
     /**
     * clears text from fields
     */
    private void clearFields() {
       term.setText(null);
       sdate.setValue(null);
       setRenewalComboBox();
       leaseUpload.setText(null);
       selectedPdfFile = null;
       setPropertyComboBox();
       setTenantComboBox();
    }
    
    /**
     * validate all numeric values enter by user
     * @return valid if true
     */
    private Boolean validateNumeric(){
        try{
            Integer.parseInt(term.getText());
        }  
           
        catch (NumberFormatException e){
            return false;
        }
        catch (Exception e){
            return false;
        }
        return true;
    
    }
    
     /**
     * pops up a window with information,error or confirmation message 
     * @param message 
     */
    private boolean alertWindow(String message,int i){
        Alert.AlertType [] alertTypes = {Alert.AlertType.INFORMATION,Alert.AlertType.ERROR,Alert.AlertType.CONFIRMATION};
        Alert a = new Alert(alertTypes[i]);
        a.setTitle("Read the message below");
        a.setContentText(message);
        a.setResizable(true);
        Optional<ButtonType> result = a.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK; 
     }
    /**
     * put all properties in comboBox
     */
    private void setPropertyComboBox(){
    
        List<Property> properties = Company.getInstance().getAllProperties();
         ObservableList<Property> allProperties = FXCollections.observableArrayList(properties);
         propertyId.setItems(allProperties);
        
    }
    
    /**
     * put all tenants in comboBox
     */
    private void setTenantComboBox(){
       List<Tenant> tenants = tdba.getList();
       ObservableList<Tenant> allTenants = FXCollections.observableArrayList(tenants);
       tenantId.setItems(allTenants);
    }
    
    /**
     * set renewal comboBox 
     */
    
    private void setRenewalComboBox(){
        ObservableList<Boolean> isRenew = FXCollections.observableArrayList(false,true);
        renewal.setItems(isRenew );
        renewal.getSelectionModel().selectFirst();
    }
    
        
     /**
     * listens for table selected row event and disable the unused buttons
     */
    private void tableListener(){    
        tableView.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection, newSelection) -> {
                edit.setDisable(false);
                delete.setDisable(false);
                view.setDisable(false);
                add.setDisable(false);
                save.setDisable(true);
                clearFields();

            });
    }
    
    /**
     * gets the value of the chosen file and stores it
     * @param event 
     */
    @FXML
    void uploadLease(ActionEvent event) {
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Text Files", "*.pdf"));
              
        selectedPdfFile = fileChooser.showOpenDialog(null);
        if (selectedPdfFile != null){
            leaseUpload.setText("File is uploaded: "+selectedPdfFile.getName());
            
        }
    }
    
    /**
     * switch to view details about a particular lease
     * @throws IOException 
     */
    @FXML
    private void switchToView() throws IOException {
        Lease lease = tableView.getSelectionModel().getSelectedItem();
        Stage viewWindow = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("leaseView.fxml"));
        Parent root = loader.load();
        LeaseViewController controller = loader.getController();
        viewWindow.setTitle("Lease View");
        controller.showLeaseDetails(lease);
        viewWindow.setScene(new Scene(root, 640, 480));
        viewWindow.show(); 
        
    }
    
    
    @FXML
    private void switchBack() throws IOException {
        App.setRoot("property");
    }
    
     @FXML
    private void switchNext() throws IOException {
        App.setRoot("tenant");
    }
      @FXML
    private void switchHome() throws IOException {
        App.setRoot("shelter");
    }
    
}
