/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.realestate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 *
 * @author T450
 */
public class BankController {
    @FXML
    private TextField name;

    @FXML
    private TextField address;

    @FXML
    private TextField intrestRate;

    @FXML
    void switchBack(ActionEvent event) throws IOException  {
         App.setRoot("mortgage");
    }

    @FXML
    void switchNext(ActionEvent event) throws IOException {
        App.setRoot("repair");
    }
      @FXML
    private void switchHome() throws IOException {
        App.setRoot("shelter");
    }
    
}
