/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.realestate.model.database;

import com.mycompany.realestate.model.Contractor;
import com.mycompany.realestate.model.Repair;
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
public class RepairDbAccess {
    
    
//     PropertyDbAccess pdba = new PropertyDbAccess();
     ContractorDbAccess cdba = new ContractorDbAccess();
     /**
     * returns a list of all contractors in database
     * @param property_id
     * @return  List
     */
    public List<Repair> getRepairs(int property_id) {
        
        List <Repair> repairs = new ArrayList<>();
        Connection con = DataBaseConnection.getConnection();
        String getInsuranceQuery = "SELECT * FROM repairs where property_id ="+property_id+"; ";	
        PreparedStatement ps;
         try {
            ps = con.prepareStatement(getInsuranceQuery);
         
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Repair repair = new Repair();
                repair.setRepairId(rs.getInt("repair_id"));
                repair.setCost(rs.getDouble("repair_cost"));
                repair.setStartDate(rs.getDate("start_date"));
                repair.setEndDate(rs.getDate("end_date"));
                repair.setType(rs.getString("repair_type"));
                repair.setContractor(cdba.getContractor(rs.getString("contractor_id")));
                repairs.add(repair);  
            }
         } catch (SQLException ex) {
              Logger.getLogger(InsuranceDbAccess.class.getName()).log(Level.SEVERE, null, ex);
          }    
	
        return repairs;
    }
    
}
