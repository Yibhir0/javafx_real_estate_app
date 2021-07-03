
package com.mycompany.realestate;

import com.mycompany.realestate.model.Bank;
import com.mycompany.realestate.model.Company;
import com.mycompany.realestate.model.database.BankDbAcces;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

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
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Bank controller
 * @author Yassine Ibhir
 */
public class BankController implements Initializable {
    @FXML
    private TextField name;

    @FXML
    private TextField address;

    @FXML
    private TextField intrestRate;
    
    
    @FXML
    private TextField phone;
    
    @FXML
    private TableColumn<Bank, String> id;

    @FXML
    private TableColumn<Bank, String> bname;

    @FXML
    private TableColumn<Bank, String> addr;

    @FXML
    private TableColumn<Bank, Double> irate;

    @FXML
    private TableColumn<Bank, String> phon;
    
    @FXML
    private Button delete;

    @FXML
    private Button edit;
    
    @FXML
    private Button add;

    @FXML
    private Button save;

    @FXML
    private TableView<Bank> tableView;
    
    private BankDbAcces bankDbAccess = new BankDbAcces();
    
    private ObservableList<Bank> banks;
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {

        //set up the columns in the table
        id.setCellValueFactory(new PropertyValueFactory<>("bankId"));
        addr.setCellValueFactory(new PropertyValueFactory<>("address"));
        irate.setCellValueFactory(new PropertyValueFactory<>("intrestRate"));        
        phon.setCellValueFactory(new PropertyValueFactory<>("phone"));
        bname.setCellValueFactory(new PropertyValueFactory<>("name"));        
        
        
        fillUpTable();
        
        tableListener();
 
    }
    
    /**
     * puts all bank objects inside the table
     */
    private void fillUpTable() {
       List<Bank> dBanks = bankDbAccess.getList() ;
       banks = FXCollections.observableArrayList(dBanks);
       tableView.setItems(banks); 
    }
   
    @FXML
    void addNewBank(ActionEvent event) {
        
       if(validateFields()){
            Bank bank = createBank();
            int added = bankDbAccess.addBank(bank);
            if (added > 0){
                banks.add(bank);//we add bank to the observebalList
                clearFields();
                alertWindow("Bank is added",0);    
            }
            else{
            alertWindow("Bank is Not Added.Try Again",1);
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
        intrestRate.setText(null);
    }
    
      /**
     * create bank object from the values entered by user
     */
    
    private Bank createBank() {
        Bank bank = new Bank();
        bank.setAddress(address.getText());
        bank.setName(name.getText());
        bank.setPhone(phone.getText());
        bank.setIntrestRate(Double.parseDouble(intrestRate.getText()));
        return bank;
        
    }

    @FXML
    void modifyBank(ActionEvent event) {
         //disable/unable buttons
        save.setDisable(false);
        add.setDisable(true);
        edit.setDisable(true);
        delete.setDisable(true);
        setTextFields();
    }
    
       /**
     * setTextFields of the old bank values
     */
    private void setTextFields() {
       Bank bankToUpdate = tableView.getSelectionModel().getSelectedItem();
       address.setText(bankToUpdate.getAddress());
       name.setText(bankToUpdate.getName());
       phone.setText(bankToUpdate.getPhone());
       intrestRate.setText(Double.toString(bankToUpdate.getIntrestRate()));
    }


    @FXML
     /**
      * removes bank and all mortgages related to it...
      */
            
    void removeBank(ActionEvent event) {
        Bank bankToRemove = tableView.getSelectionModel().getSelectedItem();
        if (alertWindow("Are You sure you want to delete "+ bankToRemove.toString(), 2)){
            int deleted = bankDbAccess.deleteBank(bankToRemove);
            if (deleted != 0){
                Company.getInstance().removeMortgageBank(bankToRemove); // remove  the bank from mortgages
                banks.remove(bankToRemove); // remove from observebalList
            }
            else{
                alertWindow("Bank is Not deleted. Try Again",1);  
            }
        }
    }

    @FXML
    void saveUpdatedBank(ActionEvent event) {
        Bank bankToUpdate = tableView.getSelectionModel().getSelectedItem();
        if(validateFields()){
            Bank bankUpdated = createBank();
            bankUpdated.setBankId(bankToUpdate.getBankId());
            int updated = bankDbAccess.updateBank(bankUpdated);
            if (updated > 0){
                 // update the observebalList banks
                int index = banks.indexOf(bankToUpdate);
                banks.set(index,bankUpdated);
                
                // update the property in the the Company List properties.
                Company.getInstance().updateBank(bankToUpdate,bankUpdated);
                
                clearFields();
                alertWindow("Bank is updated",0);    
            }
            else{
                alertWindow("Bank is Not updated.Try Again",1);
            }
        }
    }
    
     /**
     * call methods to validate inputs
     * @return valid or not
     */
    private boolean validateFields(){
     
    
        if (validateAddress() && validateIntrestRate()&& validatePhone() && name.getText() != null ){
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
        if (address.getText() != null){
            return address.getText().matches("^[0-9]+\\s[a-zA-Z]+.*");
        } 
        return false;
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
    private boolean validateIntrestRate() {
        if (intrestRate.getText() == null){
            return false;
        }
        try{
            Double.parseDouble(intrestRate.getText());
           
        }
        catch (NumberFormatException e){
            return false;
        }
        return true;
    }
    /**
     * validate phone
     * @return true if valid
     */
    private boolean validatePhone() {
        if (phone.getText() == null){
            return false;
        }
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
    void switchNext(ActionEvent event) throws IOException {
        App.setRoot("repair");
    }
    @FXML
    private void switchHome() throws IOException {
        App.setRoot("shelter");
    }
    @FXML
    void switchBack(ActionEvent event) throws IOException  {
         App.setRoot("mortgage");
    }
    
    
}
