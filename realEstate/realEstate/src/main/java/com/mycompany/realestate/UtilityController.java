
package com.mycompany.realestate;
import com.mycompany.realestate.model.Company;
import com.mycompany.realestate.model.Property;
import com.mycompany.realestate.model.Utility;
import com.mycompany.realestate.model.database.UtilityDbAccess;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Utility controller will interact with the view and the model
 * to direct instructions, and changes of utility object
 * @author Yassine class
 */
public class UtilityController  implements Initializable {
    
    @FXML
    private TextField amount;

    @FXML
    private ComboBox<Property> propertyId =  new ComboBox();

    @FXML
    private DatePicker paymentD;
    
     @FXML
    private TableView<Utility> tableView;

    @FXML
    private TableColumn<Utility,Integer> utility;

    @FXML
    private TableColumn<Utility, Property> property;

    @FXML
    private TableColumn<Utility, LocalDate> pDate;

    @FXML
    private TableColumn<Utility, Double> amountPaid;

    @FXML
    private Button delete;

    @FXML
    private Button edit;
    
    @FXML
    private Button add;

    @FXML
    private Button save;
    
    private UtilityDbAccess udba = new UtilityDbAccess();
    
    private ObservableList<Utility> utilities;
    
    private Utility utilityToUpdate;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //set up the columns in the table
        utility.setCellValueFactory(new PropertyValueFactory<>("utilityId"));
        property.setCellValueFactory(new PropertyValueFactory<>("property"));
        amountPaid.setCellValueFactory(new PropertyValueFactory<>("amount"));        
        pDate.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));

        setPropertyComboBox();
        
        fillUpTable();
        
        tableListener();
    }
    
    /**
     * puts all utilities in table
     */
    private void fillUpTable() {
       List<Property> properties = Company.getInstance().getAllProperties(); 
       List <Utility> proUtilities = new ArrayList<>() ;
       properties.forEach(p -> {
           proUtilities.addAll(p.getUtilities());
         });
      
       utilities = FXCollections.observableArrayList(proUtilities);
       tableView.setItems(utilities); 
    }
    
       /**
     * setTextFields of the old utility values
     */
    private void setTextFields() {
       amount.setText(Double.toString(utilityToUpdate.getAmount()));
       setPropertyComboBox();
       propertyId.setValue(utilityToUpdate.getProperty());
       paymentD.setValue(utilityToUpdate.getPaymentDate());
       
    }
     /**
     * sets the values of vacant properties to choose from
     */
    private void setPropertyComboBox(){
        List<Property> properties = Company.getInstance().getVacantProperties(); 
        ObservableList<Property> data = FXCollections.observableArrayList(properties);
        propertyId.setItems(data); 
    }
    
    /**
     * clear all textFields
     */
    private void clearFields(){
        amount.setText(null);
        propertyId.setValue(null);
        setPropertyComboBox();   
        paymentD.setValue(null);
    }
 
    
      /**
     * call methods to validate inputs
     * @return valid or not
     */
    private boolean validateFields(){
        if (validateAmount() && propertyId.getValue() != null 
            &&  paymentD.getValue() != null) {
                return true;
        }     
        alertWindow("INVALID INPUTS...please enter appopriate values",1);        
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
     * validate amount paid for utility
     * @return 
     */
    private boolean validateAmount(){
        try{
            Double.parseDouble(amount.getText());
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
     * add new utility when user clicks add
     * @param event 
     */
    @FXML
    void addNewUtility(ActionEvent event) {
        if(validateFields() ){
            Utility newUtility  = createUtility();            
            int added = udba.addUtility(newUtility);
            if (added > 0){
                Property p= newUtility.getProperty();
                p.addUtility(newUtility);
                utilities.add(newUtility);//we add repair to the observebalList
                clearFields();
                alertWindow("Utility is added",0);    
            }
            else{
            alertWindow("Utility is Not Added.Try Again",1);
            }
        }
    }
    
    /**
     * create new utility instance based on values entered by user
     * @return utility
     */
    private Utility createUtility() {
        
        Utility newUtility = new Utility();
        newUtility.setAmount(Double.parseDouble(amount.getText()));
        newUtility.setProperty(propertyId.getValue());
        newUtility.setPaymentDate(paymentD.getValue());   
        return newUtility;
    }
    
    /**
     * confirms and deletes utility once user clicks delete
     * @param event 
     */
    @FXML
    void deleteUtility(ActionEvent event) {
        Utility utilityToRemove = tableView.getSelectionModel().getSelectedItem();
        if (alertWindow("Are You sure you want to delete "+ utilityToRemove.toString()+ " ?", 2)){
            int deleted = udba.deleteUtility(utilityToRemove);
            if (deleted != 0){
                Property p = utilityToRemove.getProperty();
             
                p.removeUtility(utilityToRemove);
                utilities.remove(utilityToRemove); // remove from observebalList
                alertWindow("Utility is succefully deleted",0);  
                
            }
            else{
                alertWindow("Utility is Not deleted. Try Again",1);  
            }
        }
    }
    
    /**
     * disables unused buttons and stores utility to update
     * @param event 
     */
    @FXML
    void modifyUtility(ActionEvent event) {
        
        save.setDisable(false);
        add.setDisable(true);
        edit.setDisable(true);
        delete.setDisable(true);
        utilityToUpdate = tableView.getSelectionModel().getSelectedItem();
        setTextFields();
            
    }
    
     /**
     * saves and changes the values of utility object
     * @param event 
     */
    @FXML
    void saveUpdatedUtility(ActionEvent event) {
        if(validateFields()){
            Utility utilityUpdated = createUtility();
            utilityUpdated.setUtilityId( utilityToUpdate.getUtilityId());
            int updated = udba.updateUtility(utilityUpdated);
            if (updated > 0){        
                
                int index = utilities.indexOf(utilityToUpdate);
                
                utilityToUpdate.updateUtility(utilityUpdated);
                
                // update the observebalList repairs
                utilities.set(index,utilityUpdated);
                clearFields();
                alertWindow("Utility is updated",0);    
            }
            else{
                alertWindow("Utility is Not updated.Try Again",1);
            }
        }
    }

    @FXML
    private void switchBack() throws IOException {
        App.setRoot("insurance");
    }
    
    @FXML
    private void switchNext() throws IOException {
        App.setRoot("shelter");
    }
 
}
