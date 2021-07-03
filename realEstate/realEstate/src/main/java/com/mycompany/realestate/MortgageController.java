
package com.mycompany.realestate;

import com.mycompany.realestate.model.Bank;
import com.mycompany.realestate.model.Company;
import com.mycompany.realestate.model.Mortgage;
import com.mycompany.realestate.model.Property;
import com.mycompany.realestate.model.database.BankDbAcces;
import com.mycompany.realestate.model.database.MortgageDbAccess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * Mortgage Controller
 * @author Yassine Ibhir
 */
public class MortgageController implements Initializable {
   
     @FXML
    private TextField term;

    @FXML
    private TextField mortgageAmount;

    @FXML
    private TextField downPay;

    @FXML
    private ComboBox<Property> propertyId = new ComboBox();

    @FXML
    private ComboBox<Bank> bankId = new ComboBox();
     
   
    
    @FXML
    private Button save;

    @FXML
    private Button add;
    
      @FXML
    private Button edit;

    @FXML
    private Button delete;
    
      @FXML
    private Button view;


    @FXML
    private ComboBox<Boolean> isPaid = new ComboBox();

    
    @FXML
    private DatePicker startDate;

    @FXML
    private TableColumn<Mortgage, String> id;

    @FXML
    private TableColumn<Mortgage, Property> pid;

    @FXML
    private TableColumn<Mortgage, Integer> mterm;

    @FXML
    private TableColumn<Mortgage, Bank> bid;

    @FXML
    private TableColumn<Mortgage, LocalDate> sdate;

    @FXML
    private TableColumn<Mortgage, Double> mamount;

    @FXML
    private TableColumn<Mortgage, Double> dpay;

    @FXML
    private TableColumn<Mortgage, Boolean> paid;
    
   @FXML
    private TableView<Mortgage> tableView;
    
    
    private ObservableList<Mortgage> mortgages;
    
    private BankDbAcces bankDbAccess = new BankDbAcces();
    
    private MortgageDbAccess mortgageDbAccess = new MortgageDbAccess();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        setTableColumns();     
        
        fillUpTable();
        
        setFullyPaidComboBox();
        
        setPropertyComboBox();
        
        setBankComboBox();
        
        tableListener();
 
    }
    
    private void setTableColumns(){
         //set up the columns in the table
        id.setCellValueFactory(new PropertyValueFactory<>("mortgageId"));
        pid.setCellValueFactory(new PropertyValueFactory<>("property"));
        mterm.setCellValueFactory(new PropertyValueFactory<>("term"));        
        sdate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        bid.setCellValueFactory(new PropertyValueFactory<>("bank"));
        mamount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        dpay.setCellValueFactory(new PropertyValueFactory<>("downPayment"));
        paid.setCellValueFactory(new PropertyValueFactory<>("fullyPaid"));  
    }
    
    private void fillUpTable() {
       List<Property> properties = Company.getInstance().getProperties(); 
       List <Mortgage> proMortgages = new ArrayList<>() ;
       properties.forEach(p -> {
           proMortgages.addAll(p.getMortgages());
         });
      
       mortgages = FXCollections.observableArrayList(proMortgages);
       tableView.setItems(mortgages); 
    }
    
         /**
     * clears text from fields
     */
    private void clearFields() {
       term.setText(null);
       mortgageAmount.setText(null);
       downPay.setText(null);
       startDate.setValue(null);
       setFullyPaidComboBox();
       setPropertyComboBox();
       setBankComboBox();
    }
    
    /**
     * validate numeric value (term) entered by user
     * @return valid if true
     */
    private Boolean validateNumeric(){
        try{
            Integer.parseInt(term.getText());
            Double.parseDouble(mortgageAmount.getText());
            Double.parseDouble(downPay.getText());
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
     * sets The values of fullyPaid status comboBox
     */
    private void setFullyPaidComboBox() {
        ObservableList<Boolean> ismortgagePaid = FXCollections.observableArrayList(false,true);
        isPaid.setItems(ismortgagePaid );
        isPaid.getSelectionModel().selectFirst();
        
    }
    
    /**
     * gets all the properties and put them in the ComboBox
     */
    private void setPropertyComboBox(){
        
        List <Property> properties = Company.getInstance().getProperties();

        ObservableList<Property> props = FXCollections.observableArrayList(properties);
        propertyId.setItems(props);
    }

     
    @FXML
    private void switchToView() throws IOException {
        Mortgage selectedMortgage =  tableView.getSelectionModel().getSelectedItem();
        Stage viewWindow = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mortgageView.fxml"));
        Parent root = loader.load();
        MortgageViewController controller = loader.getController();
        viewWindow.setTitle("Mortgage View");
        controller.showMortgageDetails(selectedMortgage);
        viewWindow.setScene(new Scene(root, 640, 480));
        viewWindow.show(); 
    
    }
    
     /**
     * listens for table selected raw event and disable the unused buttons
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
     * validate inputs
     * @return 
     */
    private boolean validateFields(){
        if (validateNumeric() && propertyId.getValue() != null && bankId.getValue() != null
                && isPaid.getValue() != null && startDate.getValue() != null){
            return true;
        }
        alertWindow("INVALID INPUTS...please enter appopriate values",1);
        return false;    
    }
    
    @FXML
    void addNewMortgage(ActionEvent event) {
        if(validateFields()){
            Mortgage mortgage = createMortgage();              
            
            int added = mortgageDbAccess.addMortgage(mortgage);
            if (added > 0){
                // better option
                Property p = mortgage.getProperty();
                p.addMortgage(mortgage);

                mortgages.add(mortgage);//we add mortgage to the observebalList
                clearFields();
                alertWindow("Mortage is added",0);    
            }
            else{
            alertWindow("Mortgage is Not Added.Try Again",1);
            }
        }
       
    }
    
  
    @FXML
    void deleteMortgage(ActionEvent event) {
        Mortgage mortgageToRemove = tableView.getSelectionModel().getSelectedItem();
        if (alertWindow("Are You sure you want to delete "+ mortgageToRemove.toString()+ " ?", 2)){
            int deleted = mortgageDbAccess.deleteMortgage(mortgageToRemove);
            if (deleted != 0){
//              Company.getInstance().removeMortgageBank(bankToRemove); // remove  the bank from properties
                Property p = mortgageToRemove.getProperty();
                p.removeMortgage(mortgageToRemove);
                mortgages.remove(mortgageToRemove); // remove from observebalList
            }
            else{
                alertWindow("Mortgage is Not deleted. Try Again",1);  
            }
        }
    }

    @FXML
    void modifyMortgage(ActionEvent event) {
        save.setDisable(false);
        add.setDisable(true);
        edit.setDisable(true);
        delete.setDisable(true);
        view.setDisable(true);
        Mortgage mortgageToUpdate = tableView.getSelectionModel().getSelectedItem();
        setTextfields(mortgageToUpdate);
    }
    
    /**
     * set values to update 
     * @param m 
     */
    private void setTextfields(Mortgage m){
      term.setText(Integer.toString(m.getTerm()));
      mortgageAmount.setText(Double.toString(m.getAmount()));
      downPay.setText(Double.toString(m.getDownPayment()));
      propertyId.setValue(m.getProperty());
      bankId.setValue(m.getBank());
      isPaid.setValue(m.isFullyPaid());
      startDate.setValue(m.getStartDate());
    
    }

    @FXML
    void saveUpdatedMortgage(ActionEvent event) {
        Mortgage mortgageToUpdate = tableView.getSelectionModel().getSelectedItem();
        if (validateFields()){
            Mortgage mortgageNewUpdate =  createMortgage(); 
            mortgageNewUpdate.setMortgageId(mortgageToUpdate.getMortgageId());
          
            int updated = mortgageDbAccess.updateMortgage(mortgageNewUpdate);
            
            if (updated > 0){
                alertWindow("Mortgage "+ mortgageNewUpdate +" is Updated.",0); 
                // update the observebalList properties
                int index = mortgages.indexOf(mortgageToUpdate);
                mortgages.set(index,mortgageNewUpdate);
                
                mortgageToUpdate.updateMortgae(mortgageNewUpdate);

                
            }
            
            else{
                alertWindow("Mortgage "+ (mortgageToUpdate)+" is Not Updated--->Data base  problem.",1); 
            }
            
        }
        clearFields();
        add.setDisable(false);
        save.setDisable(true);
    }
    

    @FXML
    void switchNext(ActionEvent event) throws IOException {
        App.setRoot("bank");
    }
    @FXML
    void switchBack(ActionEvent event) throws IOException {
        App.setRoot("rent");
    }
    @FXML
    private void switchHome() throws IOException {
        App.setRoot("shelter");
    }

    private void setBankComboBox() {
        List<Bank> dBanks = bankDbAccess.getList() ;
        ObservableList<Bank> banks = FXCollections.observableArrayList(dBanks);
        bankId.setItems(banks);
    }
    /**
     * create Mortgage
     * @return 
     */
    private Mortgage createMortgage() {
        Mortgage mortgage = new Mortgage();
        mortgage.setTerm(Integer.parseInt(term.getText()));
        mortgage.setAmount(Double.parseDouble(mortgageAmount.getText()));
        mortgage.setDownPayment(Double.parseDouble(downPay.getText()));
        mortgage.setFullyPaid(isPaid.getValue());
        mortgage.setProperty(propertyId.getValue());
        mortgage.setBank(bankId.getValue());
        mortgage.setStartDate(startDate.getValue());
    
        return mortgage;
    }

//    void addMortgage(Property property) {
//         propertyId.setValue(property);
//         
//    }
    
    
}
