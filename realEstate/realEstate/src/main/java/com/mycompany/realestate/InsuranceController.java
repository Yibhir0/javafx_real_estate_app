/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.realestate;

import com.mycompany.realestate.model.Company;
import com.mycompany.realestate.model.Insurance;
import com.mycompany.realestate.model.Property;
import com.mycompany.realestate.model.database.InsuranceDbAccess;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author T450
 */
public class InsuranceController implements Initializable {
    
    
       @FXML
    private TextField name;

    @FXML
    private TextField address;

    @FXML
    private TextField payment;

    @FXML
    private TextField phone;
    
    @FXML
    private TableView<Insurance> tableView;

    @FXML
    private TableColumn<Insurance, String> id;

    @FXML
    private TableColumn<Insurance, Double> paid;

    @FXML
    private TableColumn<Insurance, String> addr;

    @FXML
    private TableColumn<Insurance, String> telephone;

    @FXML
    private TableColumn<Insurance, String> iname;
    
     private InsuranceDbAccess insuranceDbAccess = new InsuranceDbAccess();
     private ObservableList<Insurance> insurances;
//    
     @Override
    public void initialize(URL url, ResourceBundle rb) {

        //set up the columns in the table
        id.setCellValueFactory(new PropertyValueFactory<>("InsuranceId"));
        addr.setCellValueFactory(new PropertyValueFactory<>("address"));
        paid.setCellValueFactory(new PropertyValueFactory<>("annualPayment"));        
        telephone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        iname.setCellValueFactory(new PropertyValueFactory<>("name"));        
        fillUpTable();  
 
    }
    
    private void fillUpTable() {
       List<Insurance> dbInsurances = insuranceDbAccess.getList() ;
       insurances = FXCollections.observableArrayList(dbInsurances);
       tableView.setItems(insurances); 
    }
    
    @FXML
    private void switchBack() throws IOException {
        App.setRoot("contractor");
    }
    
     @FXML
    private void switchNext() throws IOException {
        App.setRoot("utility");
    }
      @FXML
    private void switchHome() throws IOException {
        App.setRoot("shelter");
    }

    
}

