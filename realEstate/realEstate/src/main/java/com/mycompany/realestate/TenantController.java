package com.mycompany.realestate;

import com.mycompany.realestate.model.Company;
import com.mycompany.realestate.model.Tenant;
import com.mycompany.realestate.model.database.TenantDbAccess;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
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
 * Controller maintains the behavior of the tenant view
 *
 * @author Yassine Ibhir
 */
public class TenantController implements Initializable {

    @FXML
    private TextField driverId;

    @FXML
    private TextField fullName;

    @FXML
    private TextField email;

    @FXML
    private TextField phoneNumber;

    @FXML
    private Button delete;

    @FXML
    private Button edit;

    @FXML
    private Button add;

    @FXML
    private Button save;

    @FXML
    private TableView<Tenant> tableView;

    @FXML
    private TableColumn<Integer, Tenant> id;

    @FXML
    private TableColumn<String, Tenant> name;

    @FXML
    private TableColumn<String, Tenant> identity;

    @FXML
    private TableColumn<String, Tenant> mail;

    @FXML
    private TableColumn<String, Tenant> phone;

    private TenantDbAccess tenantDbAccess = new TenantDbAccess();

    private ObservableList<Tenant> tenants;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //set up the columns in the table
        id.setCellValueFactory(new PropertyValueFactory<>("tenantId"));
        name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        identity.setCellValueFactory(new PropertyValueFactory<>("identity"));
        mail.setCellValueFactory(new PropertyValueFactory<>("email"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        fillUpTable();

        tableListener();

    }

    /**
     * puts all tenant objects inside the table
     */
    private void fillUpTable() {
        List<Tenant> dtenants = tenantDbAccess.getList();
        tenants = FXCollections.observableArrayList(dtenants);
        tableView.setItems(tenants);
    }

    @FXML
    void addNewTenant(ActionEvent event) {

        if (validateFields()) {
            Tenant tenant = createTenant();
            int added = tenantDbAccess.addTenant(tenant);
            if (added > 0) {
                tenants.add(tenant);//we add tenant to the observebalList
                clearFields();
                alertWindow("tenant is added", 0);
            } else {
                alertWindow("tenant is Not Added.Try Again", 1);
            }
        }

    }

    /**
     * create Tenant object from the values entered by user
     */
    private Tenant createTenant() {
        Tenant tenant = new Tenant();
        tenant.setIdentity(driverId.getText());
        tenant.setFullName(fullName.getText());
        tenant.setPhone(phoneNumber.getText());
        tenant.setEmail(email.getText());
        return tenant;

    }

    /**
     * call methods to validate inputs
     *
     * @return valid or not
     */
    private boolean validateFields() {

        if (validateEmail() && validatePhone() && fullName.getText() != null && driverId.getText() != null) {
            return true;
        } else {
            alertWindow("INVALID INPUTS...please enter appopriate values", 1);
        }
        return false;
    }

    /**
     * This method validates the email address, the user enters returns Boolean
     *
     */
    private boolean validateEmail() {

        if (email.getText() != null) {
            String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
            Pattern emailPat = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
            Matcher matcher = emailPat.matcher(email.getText());
            return matcher.find();
        }
        return false;
    }

    /**
     * validate phone
     *
     * @return true if valid
     */
    private boolean validatePhone() {
        if (phoneNumber.getText() == null) {
            return false;
        }
        return phoneNumber.getText().matches("\\d{10}");

    }

    @FXML
    void modifyTenant(ActionEvent event) {
        //disable/unable buttons
        save.setDisable(false);
        add.setDisable(true);
        edit.setDisable(true);
        delete.setDisable(true);
        setTextFields();
    }

    /**
     * clear all textFields
     */
    private void clearFields() {
        driverId.setText(null);
        fullName.setText(null);
        phoneNumber.setText(null);
        email.setText(null);
    }

    /**
     * setTextFields of the old bank values
     */
    private void setTextFields() {
        Tenant tenantToUpdate = tableView.getSelectionModel().getSelectedItem();
        driverId.setText(tenantToUpdate.getIdentity());
        fullName.setText(tenantToUpdate.getFullName());
        phoneNumber.setText(tenantToUpdate.getPhone());
        email.setText(tenantToUpdate.getEmail());
    }

    /**
     * listens for the selected row event and disable the unused buttons
     */
    private void tableListener() {
        tableView.getSelectionModel().selectedItemProperty().addListener(
        (ObservableValue<? extends Tenant> obs, Tenant oldSelection, Tenant newSelection) -> {
            edit.setDisable(false);
            delete.setDisable(false);
            save.setDisable(true);
            add.setDisable(false);
            clearFields();

        });
    }

    /**
     * pops up a window with information,error or confirmation message
     *
     * @param message
     */
    private boolean alertWindow(String message, int i) {
        Alert.AlertType[] alertTypes = {Alert.AlertType.INFORMATION, Alert.AlertType.ERROR, Alert.AlertType.CONFIRMATION};
        Alert a = new Alert(alertTypes[i]);
        a.setTitle("Read the message below");
        a.setContentText(message);
        a.setResizable(true);
        Optional<ButtonType> result = a.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    @FXML
    /**
     * removes bank and all mortgages related to it...
     */

    void removeTenant(ActionEvent event) {
        Tenant tenantToRemove = tableView.getSelectionModel().getSelectedItem();
        if (alertWindow("Are You sure you want to delete " + tenantToRemove.toString(), 2)) {
            int deleted = tenantDbAccess.deleteTenant(tenantToRemove);
            if (deleted != 0) {
                Company.getInstance().removeTenant(tenantToRemove); // remove  the tenant from property lease
                tenants.remove(tenantToRemove); // remove from observebalList
            } else {
                alertWindow("Tenant is Not deleted. Try Again", 1);
            }
        }
    }

    @FXML
    void saveUpdatedTenant(ActionEvent event) {
        Tenant tenantToUpdate = tableView.getSelectionModel().getSelectedItem();
        if (validateFields()) {
            Tenant tenantUpdated = createTenant();
            tenantUpdated.setTenantId(tenantToUpdate.getTenantId());
            int updated = tenantDbAccess.updateTenant(tenantUpdated);
            if (updated > 0) {
                // update the observebalList banks
                int index = tenants.indexOf(tenantToUpdate);
                tenants.set(index, tenantUpdated);


                clearFields();
                alertWindow("Tenant is updated", 0);
            } else {
                alertWindow("Tenant is Not updated.Try Again", 1);
            }
        }
    }

    @FXML
    private void switchBack() throws IOException {
        App.setRoot("lease");
    }

    @FXML
    private void switchNext() throws IOException {
        App.setRoot("rent");
    }

    @FXML
    private void switchHome() throws IOException {
        App.setRoot("shelter");
    }
}
