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
public class LeaseController {
   
    
    @FXML
    private void switchBack() throws IOException {
        App.setRoot("property");
    }
    
     @FXML
    private void switchNext() throws IOException {
        App.setRoot("tenant");
    }
      @FXML
    private void switchHome() throws IOException {
        App.setRoot("shelter");
    }
      @FXML
    private void switchToView() throws IOException {
        
        App.setRoot("leaseView");
        
    }
}
