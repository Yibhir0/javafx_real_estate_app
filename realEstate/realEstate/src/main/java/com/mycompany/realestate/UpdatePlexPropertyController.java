package com.mycompany.realestate;

import com.mycompany.realestate.model.Property;
import com.mycompany.realestate.model.database.PlexDbAccess;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * updates Plex unit property
 *
 * @author Yassine Ibhir
 */
public class UpdatePlexPropertyController {

    @FXML
    private TextField apartment;

    @FXML
    private ComboBox<Boolean> vacant = new ComboBox();

    @FXML
    private TextField rooms;

    @FXML
    private TextField rentAmount;

    @FXML
    private Label description;

    private PlexDbAccess pdba = new PlexDbAccess();

    private Property propertyToUpdate;

    void updatePlexProperty(Property property) {
        propertyToUpdate = property;
        apartment.setText(Integer.toString(property.getApartmentNumber()));
        setVacantComboBox();
        vacant.setValue(property.isIsVacant());
        rentAmount.setText(Double.toString(property.getRentAmount()));
        rooms.setText(Integer.toString(property.getNumberRooms()));
        description.setText("You are editining " + property + "in " + property.getPlexProperty());
    }

    /**
     * sets The values of vacant status comboBox
     */
    private void setVacantComboBox() {
        ObservableList<Boolean> isPropertyVcant = FXCollections.observableArrayList(true, false);
        vacant.setItems(isPropertyVcant);
    }

    @FXML
    void clearAll(ActionEvent event) {
        System.out.println("hellloooo");
        apartment.setText(null);
        setVacantComboBox();
        vacant.setValue(true);
        rentAmount.setText(null);
        rooms.setText(null);
    }

    /**
     * SAVES AND UPDATES PLEX PROPERTIES
     *
     * @param event
     */
    @FXML
    void saveUpdatedUnit(ActionEvent event) {
        if (validateNumericData() && vacant.getValue() != null) {
            Property plexUnit = createPlexUnit();

            int updated = pdba.updatePlexUnit(plexUnit);

            // now if we successfully  updated property in database we update the existent property
            if (updated > 0) {

                alertWindow("Plex unit" + propertyToUpdate + " is Updated.", 0);

                propertyToUpdate.updateExistentProperty(plexUnit);

            } // else if property was not updated in Database, we  notify the user.
            else {
                alertWindow("PlexUnit " + (propertyToUpdate) + " is Not Updated--->Data base  problem.", 1);
            }
        } else {
            alertWindow("Please fillUp the fields with appropriate values", 1);
        }
    }

    /**
     * This method checks if user enters valid numeric
     *
     * @return Boolean
     */
    private boolean validateNumericData() {

        try {
            Double.parseDouble(rentAmount.getText());
            Integer.parseInt(rooms.getText());
            Integer.parseInt(apartment.getText());
        } catch (NumberFormatException e) {
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
     * creates a new plexUnit based on the value entered
     *
     * @return plexUnit
     */
    private Property createPlexUnit() {
        Property plexUnit = new Property();
        plexUnit.setPropertyId(propertyToUpdate.getPropertyId());
        plexUnit.setPlexProperty(propertyToUpdate.getPlexProperty());
        plexUnit.setApartmentNumber(Integer.parseInt(apartment.getText()));
        plexUnit.setRentAmount(Double.parseDouble(rentAmount.getText()));
        plexUnit.setIsVacant(vacant.getValue());
        plexUnit.setPropertyType(propertyToUpdate.getPropertyType());
        plexUnit.setNumberRooms(Integer.parseInt(rooms.getText()));

        return plexUnit;
    }
}
