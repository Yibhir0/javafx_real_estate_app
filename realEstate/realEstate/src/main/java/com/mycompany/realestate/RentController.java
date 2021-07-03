package com.mycompany.realestate;

import com.mycompany.realestate.model.Company;
import com.mycompany.realestate.model.Lease;
import com.mycompany.realestate.model.Property;
import com.mycompany.realestate.model.Rent;

import com.mycompany.realestate.model.database.RentDbAccess;
import javafx.fxml.FXML;
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
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Rent Controller
 * @author Yassine Ibhir
 */
public class RentController implements Initializable {

    @FXML
    private TextField amount;

    @FXML
    private DatePicker pDate;

    @FXML
    private ComboBox<Lease> leaseId = new ComboBox();

    @FXML
    private ComboBox<String> pmethod = new ComboBox();

    @FXML
    private TableColumn<Rent, String> method;

    @FXML
    private TableColumn<Rent, Integer> id;

    @FXML
    private TableColumn<Rent, Lease> lease;

    @FXML
    private TableColumn<Rent, Double> paid;

    @FXML
    private TableColumn<Rent, LocalDate> date;

    @FXML
    private TableColumn<Rent, Boolean> fullyPaid;

    @FXML
    private TableView<Rent> tableView;

    @FXML
    private Button delete;

    @FXML
    private Button edit;

    @FXML
    private Button save;

    @FXML
    private Button add;

    private ObservableList<Rent> rents;

    private RentDbAccess rdba = new RentDbAccess();

    private Rent rentToUpdate;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //set up the columns in the table
        id.setCellValueFactory(new PropertyValueFactory<>("rentId"));
        method.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        date.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));
        paid.setCellValueFactory(new PropertyValueFactory<>("amountPaid"));
        fullyPaid.setCellValueFactory(new PropertyValueFactory<>("fullyPaid"));
        lease.setCellValueFactory(new PropertyValueFactory<>("lease"));

        fillUpTable();

        tableListener();

        setLeaseComboBox();

        setMethodComboBox();
    }

    /**
     * puts and gathers all rents with associated current leases
     */
    private void fillUpTable() {
        List<Lease> leases = Company.getInstance().getAllCurrentLeases();
        List<Rent> leaseRents = new ArrayList<>();

        leases.forEach(l -> {

            leaseRents.addAll(l.getRents());

        });

        rents = FXCollections.observableArrayList(leaseRents);
        tableView.setItems(rents);
    }

    /**
     * clear all textFields
     */
    private void clearFields() {
        amount.setText(null);
        pDate.setValue(null);
        setLeaseComboBox();
        setMethodComboBox();
    }

    /**
     * setTextFields of the old rent values
     */
    private void setTextFields() {
        amount.setText(Double.toString(rentToUpdate.getAmountPaid()));
        setLeaseComboBox();
        leaseId.setValue(rentToUpdate.getLease());
        setMethodComboBox();
        pmethod.setValue(rentToUpdate.getPaymentMethod());
        pDate.setValue(rentToUpdate.getPaymentDate());

    }

    /**
     * call methods to validate inputs
     *
     * @return valid or not
     */
    private boolean validateFields() {
        if (validateAmount() && leaseId.getValue() != null && pDate.getValue() != null && pmethod.getValue() != null) {
            return true;
        }
        alertWindow("INVALID INPUTS...please enter appopriate values", 1);
        return false;
    }

    /**
     * validate amount paid for rent
     *
     * @return
     */
    private boolean validateAmount() {
        try {
            Double.parseDouble(amount.getText());
        } catch (NumberFormatException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
        return true;

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

    /**
     * listens for the selected row event and disable the unused buttons
     */
    private void tableListener() {
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            edit.setDisable(false);
            delete.setDisable(false);
            save.setDisable(true);
            add.setDisable(false);
            clearFields();

        });
    }

    /**
     * adds creates and adds rent when user clicks add button
     *
     * @param event
     */
    @FXML
    void addNewRent(ActionEvent event) {
        if (validateFields()) {
            Rent rent = createRent();
            int added = rdba.addRent(rent);
            if (added > 0) {
                Lease l = rent.getLease();
                l.addRent(rent);
                rents.add(rent);//we add lease to the observebalList
                clearFields();
                alertWindow("Rent is added", 0);
            } else {
                alertWindow("Rent is Not Added.Try Again", 1);
            }
        }
    }

    /**
     * create new rent instance based on values entered by user
     *
     * @return rent
     */
    private Rent createRent() {
        Rent rent = new Rent();
        rent.setAmountPaid(Double.parseDouble(amount.getText()));
        rent.setPaymentMethod((pmethod.getValue()));
        rent.setPaymentDate(pDate.getValue());
        rent.setLease(leaseId.getValue());
        rent.setFullyPaid(isRentPaid(rent));
        return rent;
    }

    /**
     * checks if rent is fully paid or not
     *
     * @param rent
     * @return true if yes
     */
    private boolean isRentPaid(Rent rent) {

        Lease l = rent.getLease();
        Property property = l.getProperty();
        double payment = property.getRentAmount();
        return payment <= rent.getAmountPaid();

    }

    /**
     * disable unused buttons and stores the rent to update calls setTextFields
     *
     * @param event
     */
    @FXML
    void modifyRent(ActionEvent event) {
        save.setDisable(false);
        add.setDisable(true);
        edit.setDisable(true);
        delete.setDisable(true);
        rentToUpdate = tableView.getSelectionModel().getSelectedItem();
        setTextFields();
    }

    /**
     * removes Rent
     *
     * @param event
     */
    @FXML
    void removeRent(ActionEvent event) {
        Rent rentToRemove = tableView.getSelectionModel().getSelectedItem();
        if (alertWindow("Are You sure you want to delete " + rentToRemove.toString() + " ?", 2)) {
            int deleted = rdba.deleteRent(rentToRemove);
            if (deleted != 0) {
                Lease l = rentToRemove.getLease();

                l.removeRent(rentToRemove);
                rents.remove(rentToRemove); // remove from observebalList
                alertWindow("rent is succefully deleted", 0);

            } else {
                alertWindow("rent is Not deleted. Try Again", 1);
            }
        }
    }

    /**
     * saves Updated values
     *
     * @param event
     */
    @FXML
    void saveUpdatedRent(ActionEvent event) {

        if (validateFields()) {
            Rent rentUpdated = createRent();
            rentUpdated.setRentId(rentToUpdate.getRentId());
            int updated = rdba.updateRent(rentUpdated);
            if (updated > 0) {

                int index = rents.indexOf(rentToUpdate);

                rentToUpdate.updateRent(rentUpdated);

                // update the observebalList rents
                rents.set(index, rentUpdated);
                clearFields();
                alertWindow("Rent is updated", 0);
            } else {
                alertWindow("Rent is Not updated.Try Again", 1);
            }
        }
    }

    /**
     * get all current leases and put them in comboBox
     */
    private void setLeaseComboBox() {
        List<Lease> leases = Company.getInstance().getAllCurrentLeases();
        ObservableList<Lease> pmethods = FXCollections.observableArrayList(leases);
        leaseId.setItems(pmethods);
        leaseId.getSelectionModel().selectFirst();
    }

    /**
     * set method of payment into comboBox
     */
    private void setMethodComboBox() {
        ObservableList<String> pmethods = FXCollections.observableArrayList("Cash", "Debit", "Credit", "Cheque");
        pmethod.setItems(pmethods);
        pmethod.getSelectionModel().selectFirst();
    }

    @FXML
    private void switchBack() throws IOException {
        App.setRoot("tenant");
    }

    @FXML
    private void switchNext() throws IOException {
        App.setRoot("mortgage");
    }

    @FXML
    private void switchHome() throws IOException {
        App.setRoot("shelter");
    }
}
