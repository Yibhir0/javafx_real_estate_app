/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.realestate;

import com.mycompany.realestate.model.Contractor;
import com.mycompany.realestate.model.Insurance;
import com.mycompany.realestate.model.database.ContractorDbAccess;
import javafx.event.ActionEvent;
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
public class ContractorController implements Initializable {
    
    @FXML
    private TextField name;

    @FXML
    private TextField address;

    @FXML
    private TextField speciality;

    @FXML
    private TextField phone;

    @FXML
    private TableColumn<Contractor, String> id;

    @FXML
    private TableColumn<Contractor, String> special;

    @FXML
    private TableColumn<Contractor, String> addr;

    @FXML
    private TableColumn<Contractor, String> phon;

    @FXML
    private TableColumn<Contractor, String> cname;
    
    @FXML
    private TableView<Contractor> tableView;
    
    private ContractorDbAccess contractorDbAccess = new ContractorDbAccess();
    private ObservableList<Contractor> contractors;
    
         @Override
    public void initialize(URL url, ResourceBundle rb) {

        //set up the columns in the table
        id.setCellValueFactory(new PropertyValueFactory<>("contractorId"));
        addr.setCellValueFactory(new PropertyValueFactory<>("address"));
        special.setCellValueFactory(new PropertyValueFactory<>("specialization"));        
        phon.setCellValueFactory(new PropertyValueFactory<>("phone"));
        cname.setCellValueFactory(new PropertyValueFactory<>("name"));        
        fillUpTable();  
 
    }
    
    private void fillUpTable() {
       List<Contractor> dbContractors = contractorDbAccess.getList() ;
       contractors = FXCollections.observableArrayList(dbContractors);
       tableView.setItems(contractors); 
    }
    @FXML
    void switchBack(ActionEvent event) throws IOException  {
         App.setRoot("repair");
    }

    @FXML
    void switchNext(ActionEvent event) throws IOException {
        App.setRoot("insurance");
    }
      @FXML
    private void switchHome() throws IOException {
        App.setRoot("shelter");
    }
}
