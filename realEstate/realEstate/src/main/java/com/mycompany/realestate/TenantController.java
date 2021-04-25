/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.realestate;

/**
 *
 * @author T450
 */

import javafx.fxml.FXML;

import java.io.IOException;

/**
 *
 * @author T450
 */
public class TenantController {
    
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
