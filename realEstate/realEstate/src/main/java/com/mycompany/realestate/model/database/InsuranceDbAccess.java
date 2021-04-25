/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.realestate.model.database;

import com.mycompany.realestate.model.Company;
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
public class InsuranceDbAccess {

    /**
     * returns a list of all insurances in database
     * @return 
     */
    public List<Insurance> getList() {
        
        List <Insurance> insurances = new ArrayList<>();
        Connection con = DataBaseConnection.getConnection();
        String getInsuranceQuery = "SELECT * FROM insurance; ";	
        PreparedStatement ps;
         try {
            ps = con.prepareStatement(getInsuranceQuery);
         
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Insurance insu = new Insurance();
                insu.setInsuranceId(rs.getInt("insurance_id"));
                insu.setName(rs.getString("name"));
                insu.setAddress(rs.getString("address"));
                insu.setAnnualPayment(rs.getDouble("annual_payment"));
                insu.setPhone(rs.getString("phone"));
                insurances.add(insu);  
            }
         } catch (SQLException ex) {
              Logger.getLogger(InsuranceDbAccess.class.getName()).log(Level.SEVERE, null, ex);
          }    
	
        return insurances;
    }
    
   /**
    * method access database and creates an insurance object
    * @param id primaryKey
    * @return Insurance
    */
    public Insurance getInsurance(String id){
    
        Insurance insu = new Insurance();
        
        Connection con = DataBaseConnection.getConnection();  
        
        String getInsuranceQuery = "SELECT * FROM insurance where insurance_id="+id+"; ";	
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(getInsuranceQuery);
       
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                insu.setInsuranceId(rs.getInt("insurance_id"));
                insu.setName(rs.getString("name"));
                insu.setAnnualPayment(rs.getDouble("annual_payment"));
                insu.setAddress(rs.getString("address"));
                insu.setPhone(rs.getString("phone")); 
            }
        }
        catch (SQLException ex) {
              Logger.getLogger(InsuranceDbAccess.class.getName()).log(Level.SEVERE, null, ex);
          }      
          return insu;
      }
    /***
     * 
     * @param ins
     * @return 
     */
     public int add(Insurance ins) {
        int row = 0;
        int insurance_id = getPriamryKey();  
        String query = " insert into insurance  values (?, ?, ?, ?, ?)";  
        if(insurance_id > -1){
            Connection con = DataBaseConnection.getConnection();
            PreparedStatement ps;
        
        try { 
            
            ps = con.prepareStatement(query);
            ps.setInt(1,insurance_id);
            ps.setString(2,ins.getName());
            ps.setDouble(3,ins.getAnnualPayment());
            ps.setString(4,ins.getAddress());
            ps.setString(5,ins.getPhone());
            
            row = ps.executeUpdate();
            
        } catch (SQLException ex) {
              Logger.getLogger(PropertyDbAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(PropertyDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
      }
      return row;
    }
    
    /**
     * 
     * @return 
     */
    private int getPriamryKey() {
        int primaryKey = 0;
        
        String query = " select max(insurance_id) + 1 from insurance;";
        Connection con = DataBaseConnection.getConnection();
   
        try {
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery();
             if (rs.next()){
                primaryKey = rs.getInt(1);
             }
        } catch (SQLException ex) {
            Logger.getLogger(PropertyDbAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(PropertyDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      
        return primaryKey;
    }
    
    /**
     * update insurance information in database 
     * @param ins insurance
     * @return number of rows updated
     */
    public int update(Insurance ins) {

        String query = " update insurance set address = ?,insurance_id = ?,name = ?,"
                + "annual_payment = ?,phone = ?;";  
        int row = 0;
        Connection con = DataBaseConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);
            ps.setString(1,ins.getAddress());
            ps.setInt(2,ins.getInsuranceId());
            ps.setString(3,ins.getName());
            ps.setDouble(4,ins.getAnnualPayment());
            ps.setString(5,ins.getPhone());
         
            row = ps.executeUpdate();
            System.out.println("upadated-------> " +row);
        } catch (SQLException ex) {
              Logger.getLogger(PropertyDbAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(PropertyDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return row;  
    }
    
}
