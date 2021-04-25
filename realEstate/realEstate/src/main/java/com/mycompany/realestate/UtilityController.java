/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.realestate;

import javafx.fxml.FXML;

import java.io.IOException;

/**
 *
 * @author T450
 */
public class UtilityController {
       @FXML
    private void switchBack() throws IOException {
        App.setRoot("insurance");
    }
    
     @FXML
    private void switchNext() throws IOException {
        App.setRoot("shelter");
    }
}
