
package com.mycompany.realestate.model.database;

import com.mycompany.realestate.model.Property;
import com.mycompany.realestate.model.Utility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class manages CRUD operations within Utility table
 * @author Yassine Ibhir
 */
public class UtilityDbAccess {
   
     
    /**
     * returns a list of all utilities in database
     * @param p
     * @return  List
     */
    public List<Utility> getUtilities(Property p) {
        
        List <Utility> utilities = new ArrayList<>();
        Connection con = DataBaseConnection.getConnection();
        String getInsuranceQuery = "SELECT * FROM utility where property_id ="+p.getPropertyId()+"; ";	
        PreparedStatement ps;
         try {
            ps = con.prepareStatement(getInsuranceQuery);
         
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Utility utility = new Utility();
                utility.setUtilityId(rs.getInt("utility_id"));
                utility.setAmount(rs.getDouble("utility_amount"));
                java.time.LocalDate paymentD = rs.getDate("payment_date").toLocalDate();
                utility.setPaymentDate(paymentD);
                utility.setProperty(p);         
                utilities.add(utility); 

            }
              } catch (SQLException ex) {
              Logger.getLogger(UtilityDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                   con.close();
                } catch (SQLException ex) {
                  Logger.getLogger(UtilityDbAccess.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
	
        return utilities;
    }
     /**
     * add utility to database
     * @param utility
     * @return number of rows affected
     */
    public int addUtility(Utility utility) {

        int row = 0;
        int utility_id = getPriamryKey();  
        String query = " insert into utility  values (?, ?, ?, ?)";  
        if(utility_id > -1){
            Connection con = DataBaseConnection.getConnection();
            utility.setUtilityId(utility_id);
            PreparedStatement ps;

            try { 

                ps = con.prepareStatement(query);
                ps.setInt(1,utility_id);
                ps.setDouble(2,utility.getAmount());
                java.sql.Date paymentD = java.sql.Date.valueOf(utility.getPaymentDate());
                ps.setDate(3, paymentD);
                ps.setInt(4,utility.getProperty().getPropertyId());
                row = ps.executeUpdate();

            } catch (SQLException ex) {
              Logger.getLogger(UtilityDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                   con.close();
                } catch (SQLException ex) {
                  Logger.getLogger(UtilityDbAccess.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
        }
        return row;
    }
    
    /**
     * generate primary key for new utility
     * @return 
     */
    private int getPriamryKey() {
        int primaryKey = -1;
        
        String query = " select max(utility_id) + 1 from utility;";
        Connection con = DataBaseConnection.getConnection();
   
        try {
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery();
             if (rs.next()){
                primaryKey = rs.getInt(1);
             }
        } catch (SQLException ex) {
              Logger.getLogger(UtilityDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        finally{
            try {
               con.close();
            } catch (SQLException ex) {
              Logger.getLogger(UtilityDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  

        return primaryKey;
    }
    
    /**
     * update utility information in database 
     * @param  utility
     * @return number of rows updated
     */
    public int updateUtility(Utility utility) {
        String query = " update utility set utility_amount = ?,payment_date = ?,"
                + "property_id = ? where utility_id = ?;"; 

        int row = 0;
        Connection con = DataBaseConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);
            ps.setDouble(1,utility.getAmount());
            java.sql.Date paymentDate = java.sql.Date.valueOf(utility.getPaymentDate());
            ps.setDate(2,paymentDate);     
            ps.setInt(3,utility.getProperty().getPropertyId());
            ps.setInt(4,utility.getUtilityId());

         
            row = ps.executeUpdate();
            System.out.println("upadated-------> " +utility);
       } catch (SQLException ex) {
              Logger.getLogger(UtilityDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        finally{
            try {
               con.close();
            } catch (SQLException ex) {
              Logger.getLogger(UtilityDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
            
        return row;  
    }
    
     /**
     * delete utility from database
     * @param utility
     * @return row affected
     */
    public int deleteUtility(Utility utility){
        Connection con = DataBaseConnection.getConnection();
        String query = " delete from utility where utility_id = ?";  
        int row = 0;
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1,utility.getUtilityId());
            row = ps.executeUpdate();
        } catch (SQLException ex) {
              Logger.getLogger(UtilityDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        finally{
            try {
               con.close();
            } catch (SQLException ex) {
              Logger.getLogger(UtilityDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
       
        return row;
        
    }
}
