/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.realestate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 *
 * @author T450
 */
public class MortgageController {
   

    @FXML
    private TextField term;

    @FXML
    private TextField mortgageAmount;

    @FXML
    private TextField downPay;

    @FXML
    private ComboBox<?> propertyId;

    @FXML
    private ComboBox<?> bankId;

    @FXML
    private TextField isPaid;

    @FXML
    private DatePicker startDate;

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
    
    @FXML
    private void switchToView() throws IOException {
        App.setRoot("mortgageView");
    }
    
}
