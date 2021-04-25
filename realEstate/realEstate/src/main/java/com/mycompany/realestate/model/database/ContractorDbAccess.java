/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.realestate.model.database;

import com.mycompany.realestate.model.Contractor;
import com.mycompany.realestate.model.Insurance;
import com.mycompany.realestate.model.Property;
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
public class ContractorDbAccess {
    
    /**
     * returns a list of all contractors in database
     * @return 
     */
    public List<Contractor> getList() {
        
        List <Contractor> contractors = new ArrayList<>();
        Connection con = DataBaseConnection.getConnection();
        String getInsuranceQuery = "SELECT * FROM contractor; ";	
        PreparedStatement ps;
         try {
            ps = con.prepareStatement(getInsuranceQuery);
         
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Contractor cont = new Contractor();
                cont.setContractorId(rs.getInt("contractor_id"));
                cont.setName(rs.getString("name"));
                cont.setAddress(rs.getString("address"));
                cont.setSpecialization(rs.getString("specialization"));
                cont.setPhone(rs.getString("phone"));
     
                contractors.add(cont);  
            }
         } catch (SQLException ex) {
              Logger.getLogger(InsuranceDbAccess.class.getName()).log(Level.SEVERE, null, ex);
          }    
	
        return contractors;
    }
    
    /**
    * method gets gets and creates Contractor
    * @param id primaryKey
    * @return Contractor
    */
    public Contractor getContractor(String id){
    
        Contractor c = new Contractor();
        
        Connection con = DataBaseConnection.getConnection();  
        
        String getInsuranceQuery = "SELECT * FROM contractor where contractor_id="+id+"; ";	
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(getInsuranceQuery);
       
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                c.setContractorId(rs.getInt("contractor_id"));
                c.setName(rs.getString("name"));
                c.setAddress(rs.getString("address"));
                c.setSpecialization(rs.getString("specialization"));
                c.setPhone(rs.getString("phone"));
            }
        }
        catch (SQLException ex) {
              Logger.getLogger(InsuranceDbAccess.class.getName()).log(Level.SEVERE, null, ex);
          }      
          return c;
      }
    
}
