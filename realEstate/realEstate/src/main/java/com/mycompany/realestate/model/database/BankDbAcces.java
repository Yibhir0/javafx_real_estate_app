/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.realestate.model.database;

import com.mycompany.realestate.model.Bank;
import com.mycompany.realestate.model.Insurance;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author T450
 */
public class BankDbAcces {
    
    /**
     * returns a list of all financial Institution in database
     * @return 
     */
    public List<Bank> getList() {
        
        List <Bank> banks = new ArrayList<>();
        Connection con = DataBaseConnection.getConnection();
        String getInsuranceQuery = "SELECT * FROM bank; ";	
        PreparedStatement ps;
         try {
            ps = con.prepareStatement(getInsuranceQuery);
         
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Bank bank = new Bank();
                bank.setBankId(rs.getInt("bank_id"));
                bank.setName(rs.getString("name"));
                bank.setIntrestRate(rs.getDouble("intrest_rate"));
                bank.setPhone(rs.getString("phone"));
                bank.setAddress(rs.getString("address"));
                banks.add(bank);  
            }
         } catch (SQLException ex) {
              Logger.getLogger(InsuranceDbAccess.class.getName()).log(Level.SEVERE, null, ex);
          }    
	
        return banks;
    }
}
