package com.mycompany.realestate;

import com.mycompany.realestate.model.Company;
import com.mycompany.realestate.model.Contractor;
import com.mycompany.realestate.model.Property;
import com.mycompany.realestate.model.Repair;
import com.mycompany.realestate.model.database.ContractorDbAccess;
import com.mycompany.realestate.model.database.RepairDbAccess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
 * Repair controller will interact with the view and the model to direct
 * instructions, and changes of the repair object
 *
 * @author Yassine Ibhir
 */
public class RepairController implements Initializable {

    @FXML
    private TextField cost;

    @FXML
    private ComboBox<Property> props = new ComboBox();

    @FXML
    private ComboBox<Contractor> contracts = new ComboBox();

    @FXML
    private DatePicker sdate;

    @FXML
    private DatePicker edate;

    @FXML
    private TextField type;

    @FXML
    private TableView<Repair> tableView;

    @FXML
    private TableColumn<Repair, String> id;

    @FXML
    private TableColumn<Repair, Property> property;

    @FXML
    private TableColumn<Repair, Double> rcost;

    @FXML
    private TableColumn<Repair, Date> startD;

    @FXML
    private TableColumn<Repair, Date> endD;

    @FXML
    private TableColumn<Repair, Contractor> cont;

    @FXML
    private TableColumn<Repair, String> rtype;

    private ObservableList<Repair> repairs;

    private ContractorDbAccess cdba = new ContractorDbAccess();

    private RepairDbAccess rdba = new RepairDbAccess();

    private Repair repairToUpdate;

    // buttons
    @FXML
    private Button delete;

    @FXML
    private Button edit;

    @FXML
    private Button save;

    @FXML
    private Button add;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //set up the columns in the table
        id.setCellValueFactory(new PropertyValueFactory<>("repairId"));
        property.setCellValueFactory(new PropertyValueFactory<>("property"));
        rcost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        startD.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endD.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        cont.setCellValueFactory(new PropertyValueFactory<>("contractor"));
        rtype.setCellValueFactory(new PropertyValueFactory<>("type"));

        setContractorComboBox();

        setPropertyComboBox();

        fillUpTable();

        tableListener();

    }

    /**
     * puts all repairs in table
     */
    private void fillUpTable() {
        List<Property> properties = Company.getInstance().getProperties();
        List<Repair> proRepairs = new ArrayList<>();
        properties.forEach(p -> {
            proRepairs.addAll(p.getRepairs());
        });

        repairs = FXCollections.observableArrayList(proRepairs);
        tableView.setItems(repairs);
    }

    /**
     * setTextFields of the old rent values
     */
    private void setTextFields() {
        cost.setText(Double.toString(repairToUpdate.getCost()));
        setPropertyComboBox();
        props.setValue(repairToUpdate.getProperty());
        setContractorComboBox();
        contracts.setValue(repairToUpdate.getContractor());
        sdate.setValue(repairToUpdate.getStartDate());
        edate.setValue(repairToUpdate.getEndDate());
        type.setText(repairToUpdate.getType());
    }

    /**
     * clear all textFields
     */
    private void clearFields() {
        cost.setText(null);
        props.setValue(null);
        setPropertyComboBox();
        contracts.setValue(null);
        setContractorComboBox();
        sdate.setValue(null);
        edate.setValue(null);
        type.setText(null);
    }

    /**
     * call methods to validate inputs
     *
     * @return valid or not
     */
    private boolean validateFields() {
        if (validateCost() && props.getValue() != null
                && contracts.getValue() != null && sdate.getValue() != null
                && edate.getValue() != null && type.getText() != null) {
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
    private boolean validateCost() {
        try {
            Double.parseDouble(cost.getText());
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
     * add new Repair to database
     *
     * @param event
     */
    @FXML
    void addNewRepair(ActionEvent event) {
        if (validateFields()) {
            Repair repair = createRepair();
            int added = rdba.addRepair(repair);
            if (added > 0) {
                Property p = repair.getProperty();
                p.addRepair(repair);
                repairs.add(repair);//we add repair to the observebalList
                clearFields();
                alertWindow("Repair is added", 0);
            } else {
                alertWindow("Repair is Not Added.Try Again", 1);
            }
        }
    }

    /**
     * create new repair instance based on values entered by user
     *
     * @return rent
     */
    private Repair createRepair() {
        Repair repair = new Repair();
        repair.setCost(Double.parseDouble(cost.getText()));
        repair.setProperty(props.getValue());
        repair.setContractor(contracts.getValue());
        repair.setStartDate(sdate.getValue());
        repair.setEndDate(edate.getValue());
        repair.setType(type.getText());
        return repair;

    }

    /**
     * removes repair
     *
     * @param event
     */
    @FXML
    void deleteRepair(ActionEvent event) {
        Repair repairToRemove = tableView.getSelectionModel().getSelectedItem();
        if (alertWindow("Are You sure you want to delete " + repairToRemove.toString() + " ?", 2)) {
            int deleted = rdba.deleteRent(repairToRemove);
            if (deleted != 0) {
                Property p = repairToRemove.getProperty();

                p.removeRepair(repairToRemove);
                repairs.remove(repairToRemove); // remove from observebalList
                alertWindow("Repair is succefully deleted", 0);

            } else {
                alertWindow("Repair is Not deleted. Try Again", 1);
            }
        }
    }

    /**
     * disable unused buttons and sets values to update
     *
     * @param event
     */
    @FXML
    void modifyRepair(ActionEvent event) {
        save.setDisable(false);
        add.setDisable(true);
        edit.setDisable(true);
        delete.setDisable(true);
        repairToUpdate = tableView.getSelectionModel().getSelectedItem();
        setTextFields();
    }

    /**
     * saves and changes the values of repair object
     *
     * @param event
     */
    @FXML
    void saveUpdatedRepair(ActionEvent event) {
        if (validateFields()) {
            Repair repairUpdated = createRepair();
            repairUpdated.setRepairId(repairToUpdate.getRepairId());
            int updated = rdba.updateRepair(repairUpdated);
            if (updated > 0) {

                int index = repairs.indexOf(repairToUpdate);

                repairToUpdate.updateRepair(repairUpdated);

                // update the observebalList repairs
                repairs.set(index, repairUpdated);
                clearFields();
                alertWindow("Repair is updated", 0);
            } else {
                alertWindow("Repair is Not updated.Try Again", 1);
            }
        }
    }

    /**
     * sets the values of contractors to choose from
     */
    private void setContractorComboBox() {
        List<Contractor> contractors = cdba.getList();
        ObservableList<Contractor> data = FXCollections.observableArrayList(contractors);
        contracts.setItems(data);
    }

    /**
     * sets the values of properties to choose from
     */
    private void setPropertyComboBox() {
        List<Property> properties = Company.getInstance().getProperties();
        ObservableList<Property> data = FXCollections.observableArrayList(properties);
        props.setItems(data);
    }

    @FXML
    void switchBack(ActionEvent event) throws IOException {
        App.setRoot("bank");
    }

    @FXML
    void switchNext(ActionEvent event) throws IOException {
        App.setRoot("contractor");
    }

    @FXML
    private void switchHome() throws IOException {
        App.setRoot("shelter");
    }
}
