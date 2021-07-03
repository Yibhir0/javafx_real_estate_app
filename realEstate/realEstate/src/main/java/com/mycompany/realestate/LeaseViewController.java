
package com.mycompany.realestate;

import com.mycompany.realestate.model.Lease;
import com.mycompany.realestate.model.Rent;
import com.mycompany.realestate.model.Tenant;
import javafx.fxml.FXML;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * Lease View Controller displays details about a lease
 * @author Yassine Ibhir
 */
public class LeaseViewController {
      @FXML
    private Label tenant;

    @FXML
    private Label address;

    @FXML
    private Label starDate;

    @FXML
    private Label endDate;

    @FXML
    private Label renewal;

    @FXML
    private Label pdfFile;

    @FXML
    private ListView<Rent> rents;
    
    
    @FXML
    private void switchBack() throws IOException {
        App.setRoot("lease");
    }
    @FXML
    private void switchHome() throws IOException {
        App.setRoot("shelter");
    }

    void showLeaseDetails(Lease lease) {
        tenant.setText(getTenant(lease));
        rents.setItems(getLeaseRents(lease));
        address.setText(lease.getProperty().toString());
        starDate.setText(lease.getStartDate().toString());
        endDate.setText(lease.getEndDate().toString());
        renewal.setText(Boolean.toString(lease.isRenewal()));
        pdfFile.setText(lease.getLeaseFileName());
        
    }
    private String getTenant(Lease lease){
        Tenant t = lease.getTenant();
        if(t != null){
            return t.toString();
        }
        return "tenant was deleted";
    }
     /**
     * get history of a lease rent 
     * @param lease
     * @return rents
     */
    private ObservableList<Rent> getLeaseRents( Lease lease) {
         ObservableList<Rent> lRents = FXCollections.observableArrayList(lease.getRents());
        
         return lRents;
    }
    
  
    
}
