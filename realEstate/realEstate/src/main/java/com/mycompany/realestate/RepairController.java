/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.realestate;

import com.mycompany.realestate.model.Company;
import com.mycompany.realestate.model.Contractor;
import com.mycompany.realestate.model.Insurance;
import com.mycompany.realestate.model.Property;
import com.mycompany.realestate.model.Repair;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author T450
 */
public class RepairController implements Initializable {
    
     @FXML
    private TextField cost;

    @FXML
    private ComboBox<Property> props;

    @FXML
    private ComboBox<Contractor> contracts;

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
//    
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
        fillUpTable();  
 
    }
    
    private void fillUpTable() {
       List<Property> properties = Company.getInstance().getProperties(); 
       List <Repair> proRepairs = new ArrayList<>() ;
       properties.forEach(p -> {
           proRepairs.addAll(p.getRepairs());
         });
      
       repairs = FXCollections.observableArrayList(proRepairs);
       tableView.setItems(repairs); 
    }
    

       @FXML
    void switchBack(ActionEvent event) throws IOException  {
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
