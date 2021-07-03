
package com.mycompany.realestate;

import com.mycompany.realestate.model.Bank;
import com.mycompany.realestate.model.Mortgage;
import java.time.LocalDate;
import javafx.fxml.FXML;

import javafx.scene.control.Label;

/**
 * class displays mortgage details
 * @author Yassine iBHIR
 */
public class MortgageViewController {
     @FXML
    private Label term;

    @FXML
    private Label startDate;

    @FXML
    private Label endDate;

    @FXML
    private Label amount;

    @FXML
    private Label property;

    @FXML
    private Label bank;

    @FXML
    private Label downP;

    @FXML
    private Label fullyPaid;
    /**
     * Display all mortgage info
     */
    void showMortgageDetails(Mortgage selectedMortgage) {
        term.setText(Integer.toString(selectedMortgage.getTerm())+ " Years");
        startDate.setText(selectedMortgage.getStartDate().toString());
        LocalDate endD = selectedMortgage.getStartDate().plusYears(selectedMortgage.getTerm());
        endDate.setText(endD.toString());
        amount.setText(Double.toString(selectedMortgage.getAmount()));
        property.setText(selectedMortgage.getProperty().toString());
        bank.setText(getBankString(selectedMortgage));
        downP.setText(Double.toString(selectedMortgage.getDownPayment()));
        fullyPaid.setText(Boolean.toString(selectedMortgage.isFullyPaid()));
       
    }
    /**
     * gets bank object as string
     * @param m
     * @return string bank or no bank
     */
    private String getBankString(Mortgage m){
        Bank b = m.getBank();
        if (b != null){
            return b.toString();
        }
        return "Bank was deleted";
    }
}