/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.realestate;

import com.mycompany.realestate.model.Company;
import com.mycompany.realestate.model.Condo;
import com.mycompany.realestate.model.House;
import com.mycompany.realestate.model.Insurance;
import com.mycompany.realestate.model.Plex;
import com.mycompany.realestate.model.Property;
import com.mycompany.realestate.model.database.*;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Stage;

/**
 * Property Controller
 *
 * @author Yassine Ibhir
 */
public class PropertyController implements Initializable {

    // We will create a property object with these fxml instances
    @FXML
    private TextField address;

    @FXML
    private TextField appartment;

    @FXML
    private Label changeText;

    @FXML
    private TextField oddAttribute;

    @FXML
    private ComboBox<String> type;

    @FXML
    private TextField rentAmount;

    @FXML
    private TextField schoolTax;

    @FXML
    private ComboBox<Boolean> vacant = new ComboBox();

    @FXML
    private TextField propertyTax;

    @FXML
    private ComboBox<Insurance> insurance = new ComboBox();

    @FXML
    private TextField numOfRooms;

    // db access instances
    private HouseDbAccess houseDbAccess = new HouseDbAccess();

    private PlexDbAccess plexDbAccess = new PlexDbAccess();

    private CondoDbAccess condoDbAccess = new CondoDbAccess();

    // buttons
    @FXML
    private Button delete;

    @FXML
    private Button edit;

    @FXML
    private Button view;

    @FXML
    private Button addProperty;

    @FXML
    private Button save;

    // properties table 
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

    // insurance database access
    private InsuranceDbAccess insuranceDbAccess = new InsuranceDbAccess();

    // selected property of the table. 
    private Property propertyToUpdate;

    private ObservableList<Property> properties;

    // plex properties table
    @FXML
    private TableView<Property> tableView2;

    @FXML
    private TableColumn<Property, Integer> unit_id;

    @FXML
    private TableColumn<Property, Integer> appartment_num;

    @FXML
    private TableColumn<Property, Double> t2_rent;

    @FXML
    private TableColumn<Property, Boolean> t2_vacant;

    @FXML
    private TableColumn<Property, Plex> plex_id;

    @FXML
    private TableColumn<Property, Integer> unitRooms;

    // plex properties buttons
    @FXML
    private Button editPproperty;

    @FXML
    private Button viewPproperty;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

//        clearFields();
        //set up the columns in the table 1 (properties)
        setUpTableColumns();
        //set up the columns in the table 2(plex properties)
        setUpTable2Columns();
        // put the property values in table View
        fillUpTable();
        //set up the value of comboBox (vacant)
        setVacantComboBox();
        // set uo the values of comboxBox(propertyType)
        setTypeComboBox();
        // set up the values of comboBox (insurance)
        setInsuranceComboBox();
        // property table view listner
        tableListener();

        // plex property table view listner
        tableListener2();
        // changes text
        comboBoxListener();
    }

    /**
     * listens for table selected row event and enable edit and view buttons for
     * plex property table
     */
    private void tableListener2() {
        tableView2.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            editPproperty.setDisable(false);
            viewPproperty.setDisable(false);

        });
    }

    /**
     * listens for table selected row event and disable the unused buttons
     * checks if the selected row is a plex to show all of its properties in the
     * plex property table.
     */
    private void tableListener() {
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection instanceof Plex) {
                fillUpTable2(newSelection);
            }
            edit.setDisable(false);
            delete.setDisable(false);
            view.setDisable(false);
            save.setDisable(true);
            addProperty.setDisable(false);
            type.setDisable(false);
            clearFields();

        });
    }

    /*
    set up columns with attributes of the property object
     */
    private void setUpTableColumns() {
        id.setCellValueFactory(new PropertyValueFactory<>("propertyId"));
        addr.setCellValueFactory(new PropertyValueFactory<>("address"));
        amount.setCellValueFactory(new PropertyValueFactory<>("rentAmount"));
        propertyType.setCellValueFactory(new PropertyValueFactory<>("propertyType"));
        schoTax.setCellValueFactory(new PropertyValueFactory<>("schoolTax"));
        propTax.setCellValueFactory(new PropertyValueFactory<>("propertyTax"));
        propVacant.setCellValueFactory(new PropertyValueFactory<>("isVacant"));
        pieces.setCellValueFactory(new PropertyValueFactory<>("numberRooms"));
        insu.setCellValueFactory(new PropertyValueFactory<>("insurance"));
    }

    /*
     set up columns with attributes of the plex properties object
     */
    private void setUpTable2Columns() {

        unit_id.setCellValueFactory(new PropertyValueFactory<>("propertyId"));

        addr.setCellValueFactory(new PropertyValueFactory<>("address"));

        t2_rent.setCellValueFactory(new PropertyValueFactory<>("rentAmount"));

        t2_vacant.setCellValueFactory(new PropertyValueFactory<>("isVacant"));

        unitRooms.setCellValueFactory(new PropertyValueFactory<>("numberRooms"));

        appartment_num.setCellValueFactory(new PropertyValueFactory<>("apartmentNumber"));

        plex_id.setCellValueFactory(new PropertyValueFactory<>("plexProperty"));
        System.out.println("Ok");
    }

    /**
     * Calls the global variable company to get all properties. It sets all
     * properties to the table view
     */
    private void fillUpTable() {
        List<Property> dbProperties = Company.getInstance().getProperties();
        properties = FXCollections.observableArrayList(dbProperties);
        tableView.setItems(properties);
    }

    /**
     * shows all properties of the selected plex in the second table
     */
    private void fillUpTable2(Property p) {

        List<Property> plexProperties = ((Plex) p).getPlexProperties();
        ObservableList<Property> pProperties = FXCollections.observableArrayList(plexProperties);
        tableView2.setItems(pProperties);
    }

    /**
     * Creates, validates and add new property
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void addNewProperty(ActionEvent event) throws IOException {

        if (validateFields()) {
            Property property = createProperty();
            property = setPropertyAttributes(property);
            int added = addPropertyToDataBase(property);
            if (added > 0) {
                properties.add(property);//we add property to the observebalList
                Company.getInstance().addProperty(property);
                clearFields();
                alertWindow("Property is added", 0);
            } else {
                alertWindow("property is Not Added.Try Again", 1);
            }
        }
    }

    /**
     * adds property to database
     *
     * @param property
     * @return row affected
     */
    private int addPropertyToDataBase(Property property) {

        PropertyDAO propertyDbAccess = getPropertyDAOaccess(property);
        int added = propertyDbAccess.addProperty(property);
        return added;
    }

    /**
     * sets the values for adding or updating property.
     *
     * @return property
     */
    private Property setPropertyAttributes(Property property) {

        property.setAddress(address.getText());
        property.setPropertyType(type.getValue());
        property.setRentAmount(Double.parseDouble(rentAmount.getText()));
        property.setSchoolTax(Double.parseDouble(schoolTax.getText()));
        property.setPropertyTax(Double.parseDouble(propertyTax.getText()));
        property.setIsVacant(vacant.getValue());
        property.setNumberRooms(Integer.parseInt(numOfRooms.getText()));
        property.setApartmentNumber(Integer.parseInt(appartment.getText()));
        property.setInsurance(insurance.getValue());

        return property;
    }

    /**
     * create the appropriate property that inherits from the concrete base
     * class Property
     *
     * @return Property
     */
    private Property createProperty() {

        Property property;

        switch (type.getValue()) {
            case "Condo": {
                property = new Condo();
                // set condo fees
                ((Condo) property).setCondoFees(Double.parseDouble(oddAttribute.getText()));
                break;

            }
            case "Plex": {
                property = new Plex();
                // set plex number of units
                ((Plex) property).setNumberOfUnits(Integer.parseInt(oddAttribute.getText()));
                // create plex properties
                property = createPlexProperties(property);
                break;
            }
            default: {
                property = new House();
            }
        }

        return property;
    }

    /**
     * creates new properties of Plex property based on number of units and sets
     * apartment numbers
     *
     * @param property
     * @return
     */
    private Property createPlexProperties(Property property) {
        int units = ((Plex) property).getNumberOfUnits();
        for (int i = 0; i < units; i++) {
            int appNum = i + 100;
            Property p = new Property();
            p.setApartmentNumber(appNum);
            p.setPropertyType("Plex Unit");
            p.setPlexProperty(property);
            p.setIsVacant(true);
            ((Plex) property).addPlexProperty(p);
        }
        return property;
    }

    /**
     * call methods to validate inputs
     *
     * @return valid or not
     */
    private boolean validateFields() {
        if (validateAddress() && validateNumericData()) {
            return true;
        }
        alertWindow("INVALID INPUTS...please enter appopriate values", 1);
        return false;
    }

    /**
     * confirms with the user and removes property
     *
     * @param event
     */
    @FXML
    void deleteProperty(ActionEvent event) {
        Property propertyToRemove = tableView.getSelectionModel().getSelectedItem();
        if (alertWindow("Are You sure you want to delete " + propertyToRemove.toString() + " ?", 2)) {
            int deleted = getPropertyDAOaccess(propertyToRemove).deleteProperty(propertyToRemove);
            if (deleted != 0) {
                properties.remove(propertyToRemove);// Delete from observebalList
                Company.getInstance().removeProperty(propertyToRemove);

            } else {
                alertWindow("property is Not deleted. Try Again", 1);
            }
        }
    }

    /**
     * puts back the values of the selected property into the fields, and
     * disable all buttons except the save button
     */
    @FXML
    void updateProperty(ActionEvent event) {
        save.setDisable(false);
        addProperty.setDisable(true);
        edit.setDisable(true);
        delete.setDisable(true);
        view.setDisable(true);
        propertyToUpdate = tableView.getSelectionModel().getSelectedItem();
        type.setDisable(true);
        setTextfields();
        setNonSharedFields();

    }

    /**
     *
     * @param propertyToUpdate
     */
    private void setNonSharedFields() {

        if (propertyToUpdate instanceof Plex) {
            oddAttribute.setText(Integer.toString((((Plex) propertyToUpdate).getNumberOfUnits())));
            oddAttribute.setDisable(true);
        }
        if (propertyToUpdate instanceof Condo) {
            oddAttribute.setText(Double.toString((((Condo) propertyToUpdate).getCondoFees())));
            oddAttribute.setDisable(false);
        }
    }

    /**
     * saves updated property to the database. It creates a new property, copies
     * the id of the old property and update the value in The Company properties
     * list and ObservebalList of table view. It notifies the user wether the
     * Property is updated or not.
     */
    @FXML
    void saveUpdates(ActionEvent event) {

        if (validateFields()) {
            Property propertyNewUpdate = createProperty(); // creates new property based on the property type
            Property propertyUpdated = setPropertyAttributes(propertyNewUpdate); // sets the new values
            propertyUpdated.setPropertyId(propertyToUpdate.getPropertyId()); // get the id from the old property

            PropertyDAO propertyDbAccess = getPropertyDAOaccess(propertyUpdated); // get the specific instance of db access

            int updated = propertyDbAccess.updateProperty(propertyUpdated);

            if (updated > 0) {
                // update the observebalList properties
                int index = properties.indexOf(propertyToUpdate);

                propertyToUpdate.updateExistentProperty(propertyUpdated);

                properties.set(index, propertyToUpdate);
                alertWindow("Property is updated", 0);
            } else {
                alertWindow("Property is not updated", 1);
            }

            clearFields();
        } else {
            alertWindow("inputs are not valid..try again", 1);
        }
        addProperty.setDisable(false);
        save.setDisable(true);
        type.setDisable(false);
    }

    /**
     * sets values of a row back to the fields for editing.
     */
    private void setTextfields() {

        address.setText(propertyToUpdate.getAddress());
        setTypeComboBox();
        type.setValue(propertyToUpdate.getPropertyType());
        rentAmount.setText(Double.toString(propertyToUpdate.getRentAmount()));
        schoolTax.setText(Double.toString(propertyToUpdate.getSchoolTax()));
        propertyTax.setText(Double.toString(propertyToUpdate.getPropertyTax()));
        numOfRooms.setText(Integer.toString(propertyToUpdate.getNumberRooms()));
        vacant.setValue(propertyToUpdate.isIsVacant());
        setInsuranceComboBox();
        insurance.setValue(propertyToUpdate.getInsurance());
        appartment.setText(Integer.toString(propertyToUpdate.getApartmentNumber()));

    }

    /**
     * pops up a window with information,error or confirmation message
     *
     * @param message
     */
    private boolean alertWindow(String message, int i) {
        AlertType[] alertTypes = {AlertType.INFORMATION, AlertType.ERROR, AlertType.CONFIRMATION};
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
        rentAmount.setText("0");
        schoolTax.setText("0");
        propertyTax.setText("0");
        numOfRooms.setText("0");
        oddAttribute.setText("0");
        appartment.setText("0");
        setVacantComboBox();
        setInsuranceComboBox();
        setTypeComboBox();

    }

    /**
     * This method validates the address, the user enters returns Boolean
     *
     */
    private boolean validateAddress() {
        return address.getText().matches("^[0-9]+\\s[a-zA-Z]+.*");
    }

    /**
     * This method checks if user enters valid numeric
     *
     * @return Boolean
     */
    private boolean validateNumericData() {

        try {
            Double.parseDouble(rentAmount.getText());
            Double.parseDouble(schoolTax.getText());
            Double.parseDouble(propertyTax.getText());
            Double.parseDouble(oddAttribute.getText());
            Integer.parseInt(numOfRooms.getText());
            Integer.parseInt(appartment.getText());
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * sets The values of vacant status comboBox
     */
    private void setVacantComboBox() {

        ObservableList<Boolean> isPropertyVcant = FXCollections.observableArrayList(true, false);
        vacant.setItems(isPropertyVcant);
        vacant.getSelectionModel().selectFirst();

    }

    /**
     * gets all the insurances and put them in the ComboBox
     */
    private void setInsuranceComboBox() {
        //get insurances from database
        List<Insurance> insurances = insuranceDbAccess.getList();
        ObservableList<Insurance> data = FXCollections.observableArrayList(insurances);
        insurance.setItems(data);
        insurance.getSelectionModel().selectFirst();
    }

    /**
     * sets The values of the property Types to the comboBox
     */
    private void setTypeComboBox() {
        ObservableList<String> types = FXCollections.observableArrayList("House", "Plex", "Condo");
        type.setItems(types);
        type.getSelectionModel().selectFirst();
    }

    /**
     * Switch and pass the property object to property view
     *
     * @throws IOException
     */
    @FXML
    private void switchToView() throws IOException {
        Property propertyView = tableView.getSelectionModel().getSelectedItem();
        viewProperty(propertyView);
    }

    /**
     * Opens a new window with property details
     *
     * @param p
     * @throws IOException
     */
    private void viewProperty(Property p) throws IOException {
        Stage viewWindow = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("propertyView.fxml"));
        Parent root = loader.load();
        PropertyViewController controller = loader.getController();
        viewWindow.setTitle("Property View");
        controller.showPropertyDetails(p);
        viewWindow.setScene(new Scene(root, 640, 480));
        viewWindow.show();
    }

    /**
     * view plex unit
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void viewPlexProperty(ActionEvent event) throws IOException {
        Property propertyView = tableView2.getSelectionModel().getSelectedItem();
        viewProperty(propertyView);
    }

    /**
     * listens for comboBox new value to change the label text which indicates
     * the value to enter for the chosen property.
     */
    private void comboBoxListener() {
        // Prperty Type Listener.. Changes the text of a label that describes the value that should be enterd.
        type.setOnAction((event) -> {

            String selectedItem = type.getSelectionModel().getSelectedItem();
            String labelText = "pass------------>";

            oddAttribute.setDisable(true);
            appartment.setDisable(true);
            rentAmount.setDisable(false);
            numOfRooms.setDisable(false);

            if (selectedItem != null) {
                if (selectedItem.equals("Plex")) {
                    oddAttribute.setDisable(false);
                    labelText = "Plex Number Of Units";
                    rentAmount.setDisable(true);
                    numOfRooms.setDisable(true);
                } else if (selectedItem.equals("Condo")) {
                    oddAttribute.setDisable(false);
                    appartment.setDisable(false);
                    labelText = "Condos fees";
                } else {
                    oddAttribute.setDisable(true);
                }

                changeText.setText(labelText);
            } else {
                setTypeComboBox();
            }

        });
    }

    /**
     * gets database access instance(Plex,house or condo)
     */
    private PropertyDAO getPropertyDAOaccess(Property property) {
        PropertyDAO propertyDAOaccess = null;
        if (property instanceof House) {
            propertyDAOaccess = houseDbAccess;
        } else if (property instanceof Plex) {
            propertyDAOaccess = plexDbAccess;
        } else if (property instanceof Condo) {
            propertyDAOaccess = condoDbAccess;
        }
        return propertyDAOaccess;
    }

    /**
     * switch to other view to plex unit
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void editPlexProperty(ActionEvent event) throws IOException {
        Stage viewWindow = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("updatePropertyPlex.fxml"));
        Parent root = loader.load();
        UpdatePlexPropertyController controller = loader.getController();
        viewWindow.setTitle("Update Property Plex");
        Property property = tableView2.getSelectionModel().getSelectedItem();
        controller.updatePlexProperty(property);
        viewWindow.setScene(new Scene(root, 640, 480));
        viewWindow.show();
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

}
