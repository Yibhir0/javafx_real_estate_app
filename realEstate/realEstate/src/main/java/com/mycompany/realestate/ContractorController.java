
package com.mycompany.realestate;

import com.mycompany.realestate.model.Company;
import com.mycompany.realestate.model.Contractor;
import com.mycompany.realestate.model.database.ContractorDbAccess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Contractor controller
 * @author Yassine Ibhir
 */
public class ContractorController implements Initializable {
    
    @FXML
    private TextField name;

    @FXML
    private TextField address;

    @FXML
    private TextField speciality;

    @FXML
    private TextField phone;
    
    @FXML
    private Button add;

    @FXML
    private Button save;
    
     @FXML
    private Button edit;

    @FXML
    private Button delete;

    @FXML
    private TableColumn<Contractor, String> id;

    @FXML
    private TableColumn<Contractor, String> special;

    @FXML
    private TableColumn<Contractor, String> addr;

    @FXML
    private TableColumn<Contractor, String> phon;

    @FXML
    private TableColumn<Contractor, String> cname;
    
    @FXML
    private TableView<Contractor> tableView;
    
    private ContractorDbAccess contractorDbAccess = new ContractorDbAccess();
    private ObservableList<Contractor> contractors;
    
         @Override
    public void initialize(URL url, ResourceBundle rb) {

        //set up the columns in the table
        id.setCellValueFactory(new PropertyValueFactory<>("contractorId"));
        addr.setCellValueFactory(new PropertyValueFactory<>("address"));
        special.setCellValueFactory(new PropertyValueFactory<>("specialization"));        
        phon.setCellValueFactory(new PropertyValueFactory<>("phone"));
        cname.setCellValueFactory(new PropertyValueFactory<>("name"));        
        
        fillUpTable();  
        
        tableListener();
 
    }
    
    private void fillUpTable() {
       List<Contractor> dbContractors = contractorDbAccess.getList() ;
       contractors = FXCollections.observableArrayList(dbContractors);
       tableView.setItems(contractors); 
    }
    
    /**
     * add new contractor to database
     * @param event 
     */
    @FXML
    void addNewContractor(ActionEvent event) {
        
       if(validateFields()){
            Contractor contractor = createContractor();
            int added = contractorDbAccess.addContractor(contractor);
            if (added > 0){
                contractors.add(contractor);//we add bank to the observebalList
                clearFields();
                alertWindow("Contractor is added",0);    
            }
            else{
            alertWindow("Contractor is Not Added.Try Again",1);
        }
        }
        

    }
    
    /**
     * clear all textFields
     */
    private void clearFields(){
        address.setText(null);
        name.setText(null);
        phone.setText(null);
        speciality.setText(null);
    }
    
      /**
     * create contractor object from the values entered by user
     */
    
    private Contractor createContractor() {
        Contractor contractor = new Contractor();
        contractor.setAddress(address.getText());
        contractor.setName(name.getText());
        contractor.setPhone(phone.getText());
        contractor.setSpecialization(speciality.getText());
        return contractor;
        
    }

    @FXML
    void modifyContractor(ActionEvent event) {
         //disable/unable buttons
        save.setDisable(false);
        add.setDisable(true);
        edit.setDisable(true);
        delete.setDisable(true);
        setTextFields();
    }
    
       /**
     * setTextFields of the old contractor values
     */
    private void setTextFields() {
       Contractor contractorToUpdate = tableView.getSelectionModel().getSelectedItem();
       address.setText(contractorToUpdate.getAddress());
       name.setText(contractorToUpdate.getName());
       phone.setText(contractorToUpdate.getPhone());
       speciality.setText(contractorToUpdate.getSpecialization());
    }


    @FXML
     /**
      * removes bank and all mortgages related to it...
      */
            
    void removeContractor(ActionEvent event) {
        Contractor contractorToRemove = tableView.getSelectionModel().getSelectedItem();
        if (alertWindow("Are You sure you want to delete "+ contractorToRemove.toString()+ " ?", 2)){
            int deleted = contractorDbAccess.deleteContractor(contractorToRemove);
            if (deleted != 0){
                contractors.remove(contractorToRemove); // remove from observebalList
            }
            else{
                alertWindow("contractor is Not deleted. Try Again",1);  
            }
        }
    }
    
    /**
     * save updates to database
     * @param event 
     */
    @FXML
    void saveUpdatedContractor(ActionEvent event) {
        Contractor contractorToUpdate = tableView.getSelectionModel().getSelectedItem();
        if(validateFields()){
            Contractor contractorUpdated = createContractor();
            contractorUpdated.setContractorId(contractorToUpdate.getContractorId());
            int updated = contractorDbAccess.updateContractor(contractorUpdated);
            if (updated > 0){
                 // update the observebalList contractore
                int index = contractors.indexOf(contractorToUpdate);
                contractors.set(index,contractorUpdated);
                
                // update the property in the the Company List properties.
                Company.getInstance().updateContractor(contractorToUpdate,contractorUpdated);
                
                clearFields();
                alertWindow("Contractor is updated",0);    
            }
            else{
                alertWindow("Contractor is Not updated.Try Again",1);
            }
        }
    }
    
     /**
     * call methods to validate inputs
     * @return valid or not
     */
    private boolean validateFields(){
     
    
        if (validateAddress() && validatePhone()){
            return true;
        }
        else{
           alertWindow("INVALID INPUTS...please enter appopriate values",1);
        }
        return false;
        
    }
    
    /**
     This method validates the address, the user enters
     * returns Boolean
     * */
    private boolean validateAddress(){
        return address.getText().matches("^[0-9]+\\s[a-zA-Z]+.*");
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
     * validate phone
     * @return true if valid
     */
    private boolean validatePhone() {
        return phone.getText().matches("\\d{10}");
       
    }
           /**
     * listens for the selected row event and disable the unused buttons
     */
    private void tableListener(){    
        tableView.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection, newSelection) -> {
                edit.setDisable(false);
                delete.setDisable(false);
                save.setDisable(true);
                add.setDisable(false);
                clearFields();

            });
    }

    @FXML
    void switchBack(ActionEvent event) throws IOException  {
         App.setRoot("repair");
    }

    @FXML
    void switchNext(ActionEvent event) throws IOException {
        App.setRoot("insurance");
    }
      @FXML
    private void switchHome() throws IOException {
        App.setRoot("shelter");
    }
}
