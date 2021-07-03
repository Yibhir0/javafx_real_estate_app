
package com.mycompany.realestate;

import com.mycompany.realestate.model.Company;
import com.mycompany.realestate.model.Insurance;
import com.mycompany.realestate.model.database.InsuranceDbAccess;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *   Insurance Controller
 * @author Yassine Ibhir
 */
public class InsuranceController implements Initializable {
    
    
    @FXML
    private TextField name;

    @FXML
    private TextField address;

    @FXML
    private TextField payment;

    @FXML
    private TextField phone;
    
      @FXML
    private Button add;

    @FXML
    private Button save;
    
      @FXML
    private Button delete;

    @FXML
    private Button edit;

   
    
    @FXML
    private TableView<Insurance> tableView;

    @FXML
    private TableColumn<Insurance, String> id;

    @FXML
    private TableColumn<Insurance, Double> paid;

    @FXML
    private TableColumn<Insurance, String> addr;

    @FXML
    private TableColumn<Insurance, String> telephone;

    @FXML
    private TableColumn<Insurance, String> iname;
    
    private InsuranceDbAccess insuranceDbAccess = new InsuranceDbAccess();
    private ObservableList<Insurance> insurances;
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {

        //set up the columns in the table
        id.setCellValueFactory(new PropertyValueFactory<>("InsuranceId"));
        addr.setCellValueFactory(new PropertyValueFactory<>("address"));
        paid.setCellValueFactory(new PropertyValueFactory<>("annualPayment"));        
        telephone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        iname.setCellValueFactory(new PropertyValueFactory<>("name"));        
        
        fillUpTable();  
        
        tableListener();
    }
    
    private void fillUpTable() {
       List<Insurance> dbInsurances = insuranceDbAccess.getList() ;
       insurances = FXCollections.observableArrayList(dbInsurances);
       tableView.setItems(insurances); 
    }
    
    /**
     * add insurance and confirm that its added
     * @param event 
     */
     @FXML
    void addNewInsurance(ActionEvent event) {
        
        if(validateFields()){
            Insurance insurance = createInsurance();
            int added = insuranceDbAccess.add(insurance);
            if (added > 0){
                insurances.add(insurance);//we add insurance to the observebalList
                clearFields();
                alertWindow("Insurance is added",0);    
            }  
        }
        else{
            alertWindow("Insurance is Not Added.Try Again",1);
        }
        } 
   
    /**
     * clear all textFields
     */
    private void clearFields(){
        address.setText(null);
        name.setText(null);
        phone.setText(null);
        payment.setText(null);
    }
    
    /**
     * create insurance object from the values entered by user
     */
    
    private Insurance createInsurance() {
        Insurance insurance = new Insurance();
        insurance.setAddress(address.getText());
        insurance.setName(name.getText());
        insurance.setPhone(phone.getText());
        insurance.setAnnualPayment(Double.parseDouble(payment.getText()));
        return insurance;
        
    }
 
        
    
    /**
     * remove Insurance
     * @param event 
     */
    @FXML
    void deleteInsurance(ActionEvent event) {
        Insurance insuranceToRemove = tableView.getSelectionModel().getSelectedItem();
        if (alertWindow("Are You sure you want to delete "+ insuranceToRemove.toString()+ " ?", 2)){
            int deleted = insuranceDbAccess.deleteInsurance(insuranceToRemove);
            if (deleted != 0){
                Company.getInstance().removeInsurance(insuranceToRemove); // remove  the insurance from properties
                insurances.remove(insuranceToRemove);
            }
            else{
                alertWindow("Insurance is Not deleted. Try Again",1);  
            }
        }
    }
    /**
     * update and saves Insurance 
     * @param event 
     */
    @FXML
    void saveUpdates(ActionEvent event) {
        Insurance insuranceToUpdate = tableView.getSelectionModel().getSelectedItem();
        if(validateFields()){
            Insurance insuranceUpdated = createInsurance();
            insuranceUpdated.setInsuranceId(insuranceToUpdate.getInsuranceId());
            int updated = insuranceDbAccess.update(insuranceUpdated);
            if (updated > 0){
                 // update the observebalList insurances
                int index = insurances.indexOf(insuranceToUpdate);
                insurances.set(index,insuranceUpdated);
                
                // update the property in the the Company List properties.
                Company.getInstance().updateInsurance(insuranceToUpdate,insuranceUpdated);
                
                clearFields();
                alertWindow("Insurance is updated",0);    
            }
            else{
                alertWindow("Insurance is Not updated.Try Again",1);
            }
        }
      
    }
    
    /**
     * SET textFields VALUES to UPDATE AND UNABLE/DISABLE BUTTONS
     * @param event 
     */
    @FXML
    void updateInsurance(ActionEvent event) {
        //disable/unable buttons
        save.setDisable(false);
        add.setDisable(true);
        edit.setDisable(true);
        delete.setDisable(true);
        setTextFields();
    }
    
    /**
     * setTextFields of the old insurance value
     */
    private void setTextFields() {
       Insurance insuranceToUpdate = tableView.getSelectionModel().getSelectedItem();
       address.setText(insuranceToUpdate.getAddress());
       name.setText(insuranceToUpdate.getName());
       phone.setText(insuranceToUpdate.getPhone());
       payment.setText(Double.toString(insuranceToUpdate.getAnnualPayment()));
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
    
       /**
     * call methods to validate inputs
     * @return valid or not
     */
    private boolean validateFields(){
     
    
        if (validateAddress() && validatePayment()&& validatePhone()){
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
     * validate if the user enters the proper value for interest.
     * @return true if yes
     */
    private boolean validatePayment() {
         try{
            Double.parseDouble(payment.getText());
           
        }
        catch (NumberFormatException e){
            return false;
        }
        return true;
    }
    
    /**
     * validate phone number
     * @return 
     */
    private boolean validatePhone() {
        return phone.getText().matches("\\d{10}");
       
    }
   
    
    @FXML
    private void switchBack() throws IOException {
        App.setRoot("contractor");
    }
    
     @FXML
    private void switchNext() throws IOException {
        App.setRoot("utility");
    }
      @FXML
    private void switchHome() throws IOException {
        App.setRoot("shelter");
    }
  
}

