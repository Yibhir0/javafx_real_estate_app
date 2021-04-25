/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.realestate;

import com.mycompany.realestate.model.Company;
import com.mycompany.realestate.model.Insurance;
import com.mycompany.realestate.model.Property;
import com.mycompany.realestate.model.database.InsuranceDbAccess;
import com.mycompany.realestate.model.database.PropertyDbAccess;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author T450
 */
public class PropertyController implements Initializable {
    
    // We will create a property object with these fxml instances
    @FXML
    private TextField address;

    @FXML
    private TextField type;

    @FXML
    private TextField rentAmount;

    @FXML
    private TextField schoolTax;

    @FXML
    private ComboBox vacant = new ComboBox();

    @FXML
    private TextField propertyTax;
    
    @FXML
    private ComboBox insurance = new ComboBox();
    
    @FXML
    private TextField numOfUnit;
    
    private PropertyDbAccess propertyDbAccess = new PropertyDbAccess();
     
  
     
    //configure the table
     
    @FXML
    private TableView<Property> tableView;
    @FXML
    private TableColumn<Property, String> id;

    @FXML
    private TableColumn<Property, String> addr;

    @FXML
    private TableColumn<Property, Double> amount;

    @FXML
    private TableColumn<Property, String> propertyType;

    @FXML
    private TableColumn<Property, Double> schoTax;

    @FXML
    private TableColumn<Property, Double> propTax;

    @FXML
    private TableColumn<Property, Insurance> insu;

    @FXML
    private TableColumn<Property, String> propVacant;
    
    @FXML
    private TableColumn<Property, Integer> pieces;
     
    private InsuranceDbAccess insuranceDbAccess = new InsuranceDbAccess();
    
    private  Property backUp = new Property();
    
    private ObservableList<Property> properties;
    
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {

        //set up the columns in the table
        id.setCellValueFactory(new PropertyValueFactory<>("propertyId"));
        addr.setCellValueFactory(new PropertyValueFactory<>("address"));
        amount.setCellValueFactory(new PropertyValueFactory<>("rentAmount"));
        propertyType.setCellValueFactory(new PropertyValueFactory<>("propertyType"));
        schoTax.setCellValueFactory(new PropertyValueFactory<>("schoolTax"));
        propTax.setCellValueFactory(new PropertyValueFactory<>("propertyTax"));
        propVacant.setCellValueFactory(new PropertyValueFactory<>("isVacant"));
        pieces.setCellValueFactory(new PropertyValueFactory<>("unitNum"));
        insu.setCellValueFactory(new PropertyValueFactory<>("insurance"));
        
        fillUpTable();  
        //set up the value of comboBox (vacant)
        vacant.getItems().addAll("Yes","No");
        vacant.setValue("Yes");
        // set up the values of comboBox (insurance)
        setInsuranceComboBox(); 
       
    }
    
      /**
     * Calls the global variable company to get all properties.
     * It sets all properties to the table view
     */
    private void fillUpTable() {
        List<Property> dbProperties = Company.getInstance().getProperties();
       properties = FXCollections.observableArrayList(dbProperties);
       tableView.setItems(properties);  
    }
    
    /**
     * Creates, validates and add new property
     * @param event
     * @throws IOException 
     */
    @FXML
    void addNewProperty(ActionEvent event) throws IOException {
            
       if(validateFields()){
            Property property = validateProperty(new Property());
            int added = propertyDbAccess.add(property);
            if (added > 0 && property != null){
            properties.add(property);//we add property to the observebalList
            clearFields();
            alertWindow("Property is added... Do you want to add Mortgage",2);  
            }
            else{
            alertWindow("property is Not Added.Try Again",1);
            }
        } 
    }
      
    /**
     * validate and create property for adding or updating property.
     * @return property
     */
    private Property validateProperty(Property property) {
        
            property.setAddress(address.getText());
            property.setPropertyType(type.getText());
            property.setRentAmount(Double.parseDouble(rentAmount.getText()));
            property.setSchoolTax(Double.parseDouble(schoolTax.getText()));
            property.setPropertyTax(Double.parseDouble(propertyTax.getText()));
            property.setIsVacant(convertVacantOption());
            property.setUnitNum(Integer.parseInt(numOfUnit.getText()));
            // get id of selected insurabce????
            property.setInsurance(getIns());  

        return property;
    }
    
    /**
     * call methods to validate inputs
     * @return 
     */
    private boolean validateFields(){
        if (validateAddress() && validateType() && validateNumericData()){
            return true;
        }
        else{
           alertWindow("INVALID INPUTS...please enter appopriate values",1);
        }
        return false;
    }
    /**
     * confirms and removes property: NEED TO maKE SURE ROW IS DELETED
     * @param event 
     */
    @FXML
    void deleteProperty(ActionEvent event) {
        Property propertyToRemove = tableView.getSelectionModel().getSelectedItem();
        if (alertWindow("Are You sure you want to delete "+ propertyToRemove.toString()+ " ?",2)){
            int deleted = propertyDbAccess.delete(propertyToRemove);
            if (deleted != 0){
                properties.remove(propertyToRemove);// Delete frfom observebalList
            }
            else{
              alertWindow("property is Not deleted. Try Again",1);  
            }
            
        }
    }
    
    /**
     * update the selected property
     */
    @FXML
    void updateProperty(ActionEvent event){
        
        Property selected = tableView.getSelectionModel().getSelectedItem();
        setTextfields(selected);
        // backUp is the value we will keep in case there is a problem of updating
        backUp = validateProperty(backUp);
        backUp.setPropertyId(selected.getPropertyId());
    }
    
    /**
     * saves updated property to the database
     */
     @FXML
    void saveUpdates(ActionEvent event) {
        // this property is the old value(not updated) it has refernce to propeties in company
          Property selected = tableView.getSelectionModel().getSelectedItem();
          if (validateFields()){
                Property property = validateProperty(selected);
                 // now if we suuccessfully added the updated property to database we change the view
                int updated = propertyDbAccess.update(property);
                if (updated > 0){
                    alertWindow("Property "+ property +" is Updated.",0);
                    clearFields();
                }
                  // else if we did not added property, we want to keep the old property and notify user(backUp)
                else{
                   alertWindow("Property "+ (backUp)+" is Not Updated--->Data base  problem.",1);
                   property.backUpCopy(backUp);//
                   System.out.println(backUp);
                   clearFields();
                   System.out.println(Company.getInstance().getProperties().contains(property));
                }
            }

    }
    /**
     * sets values of a row back to the fields for editing.
     */
    private void setTextfields(Property selected){
       
       address.setText(selected.getAddress());
       type.setText(selected.getPropertyType());
       rentAmount.setText(Double.toString(selected.getRentAmount()));
       schoolTax.setText(Double.toString(selected.getSchoolTax()));
       propertyTax.setText(Double.toString(selected.getPropertyTax()));
       numOfUnit.setText(Integer.toString(selected.getUnitNum()));
       vacant.setValue(convertVacantToString(selected.isIsVacant()));
       setInsuranceComboBox();
       ObservableList<Insurance> insurs = insurance.getItems();
       for (Insurance ins : insurs){
            if (selected.getInsurance().equals(ins)){
                insurance.setValue(ins);
                break;
            }
        }
   } 
   
   /**
   
    /**
     * pops up a window with information,error or confirmation message 
     * @param message 
     */
    private boolean alertWindow(String message,int i){
        AlertType [] alertTypes = {AlertType.INFORMATION,AlertType.ERROR,AlertType.CONFIRMATION};
        Alert a = new Alert(alertTypes[i]);
        a.setTitle("Read the message below");
        a.setContentText(message);
         a.setResizable(true);
        Optional<ButtonType> result = a.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK; 
     }
    
    /**
     * clears text from fields
     */
    private void clearFields() {
       address.setText(null);
       type.setText(null);
       rentAmount.setText(null);
       schoolTax.setText(null);
       propertyTax.setText(null);
       numOfUnit.setText(null);
       vacant.setValue("Yes");
       setInsuranceComboBox();
       insurance.getSelectionModel().selectFirst();
    }

    /**
     This method validates the address, the user enters
     * returns Boolean
     * */
    
    private boolean validateAddress(){
        return address.getText().matches("^[0-9]+\\s[a-zA-Z]+.*");
    }
    /**
     * This method returns true or false based on the selected option
     * of the comboBox (vacant: yes = true, not vacant: no = false)
     **/
    
    private boolean convertVacantOption(){
        return vacant.getValue().equals("Yes");
    }
    private String convertVacantToString(boolean v){
        if(v){
            return "Yes";
        }
        else{
            return "No";
        }
    }
    /**
     * returns Insurance object
     * 
     */
    private Insurance getIns(){
        String s = insurance.getValue().toString();
        int i =  s.indexOf('{');
        String insuranceId = s.substring(0,i);
        InsuranceDbAccess idba = new InsuranceDbAccess();
        Insurance insur = idba.getInsurance(insuranceId);
        return insur;
    }
    
    /** This method will validate the type of property based on length
     * 
     * @return Boolean
     */
    
    private boolean validateType(){
        return type.getText().length()>= 4;   
    }
    
    /**
     * This method checks if user enters valid numeric
     * @return Boolean
     */
    
    private boolean validateNumericData(){
        try{
            Double.parseDouble(rentAmount.getText());
            Double.parseDouble(schoolTax.getText());
            Double.parseDouble(propertyTax.getText());
            Integer.parseInt(numOfUnit.getText());
        }
        catch (NumberFormatException e){
            return false;
        }
        return true;
    }
    

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("shelter");
    }

    @FXML
    private void switchNext() throws IOException {
        App.setRoot("lease");
    }

    @FXML
    private void switchHome() throws IOException {
        App.setRoot("shelter");
    }
    /**
     * Switch and pass the property object to property view
     * @throws IOException 
     */
    @FXML
    private void switchToView() throws IOException {
        Stage viewWindow = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("propertyView.fxml"));
        Parent root = loader.load();
        PropertyViewController controller = loader.getController();
        viewWindow.setTitle("Property View");
        Property propertyView = tableView.getSelectionModel().getSelectedItem();
        controller.showPropertyDetails(propertyView);
        viewWindow.setScene(new Scene(root, 640, 480));
        viewWindow.show(); 
    }
  
 
    /**
     * gets all the insurances and puts them in the ComboBox
     */
    private void setInsuranceComboBox(){
     
        List<Insurance> insurances = insuranceDbAccess.getList();
        ObservableList<Insurance> data = FXCollections.observableArrayList(insurances);
        insurance.setItems(data);
        insurance.getSelectionModel().selectFirst();

    }
}