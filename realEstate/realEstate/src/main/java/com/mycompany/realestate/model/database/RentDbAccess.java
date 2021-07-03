
package com.mycompany.realestate.model.database;

import com.mycompany.realestate.model.Lease;
import com.mycompany.realestate.model.Rent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yassine Ibhir
 */
public class RentDbAccess {
    /**
    * method access database and creates all rent objects
    * related to a lease
    * @param  lease
    * @return rents
    */
    public List<Rent> getRents(Lease lease){

        int leaseId = lease.getLeaseId();
        List<Rent> rents = new ArrayList<>();
        
        Connection con = DataBaseConnection.getConnection();  
        
        String getInsuranceQuery = "SELECT * FROM rent where lease_id="+leaseId+"; ";	
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(getInsuranceQuery);
       
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                Rent rent = new Rent();
                rent.setRentId(rs.getInt("rent_id"));
                rent.setAmountPaid(rs.getDouble("amount_paid"));
                rent.setPaymentMethod(rs.getString("payment_method"));
                java.time.LocalDate paymentD = rs.getDate("payment_date").toLocalDate();
                rent.setPaymentDate(paymentD);
                rent.setFullyPaid(Boolean.parseBoolean(rs.getString("fully_paid")));
                rent.setLease(lease);
                rents.add(rent);
            }
        } catch (SQLException ex) {
              Logger.getLogger(RentDbAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(RentDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rents;
    }
    
    /**
     * add rent to database
     * @param rent
     * @return number of rows affected
     */
    public int addRent(Rent rent) {
        int row = 0;
        int rent_id = getPriamryKey();  
        String query = " insert into rent  values (?, ?, ?, ?, ?,?)";  
        if(rent_id > -1){
            Connection con = DataBaseConnection.getConnection();
            rent.setRentId(rent_id);
            PreparedStatement ps;

            try { 

                ps = con.prepareStatement(query);
                ps.setInt(1,rent_id);
                ps.setDouble(2,rent.getAmountPaid());
                ps.setString(3,rent.getPaymentMethod());
                java.sql.Date paymentD = java.sql.Date.valueOf(rent.getPaymentDate());
                
                ps.setDate(4,paymentD);
                ps.setString(5,Boolean.toString(rent.isFullyPaid()));
                ps.setInt(6,rent.getLease().getLeaseId());

                row = ps.executeUpdate();

            } catch (SQLException ex) {
              Logger.getLogger(RentDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                con.close();
                } catch (SQLException ex) {
                Logger.getLogger(RentDbAccess.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            
        }
        return row;
    }
    
    /**
     * generate primary key for new rent
     * @return 
     */
    private int getPriamryKey() {
        int primaryKey = -1;
        
        String query = " select max(rent_id) + 1 from rent;";
        Connection con = DataBaseConnection.getConnection();
   
        try {
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery();
             if (rs.next()){
                primaryKey = rs.getInt(1);
             }
   } catch (SQLException ex) {
              Logger.getLogger(RentDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                con.close();
                } catch (SQLException ex) {
                Logger.getLogger(RentDbAccess.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  
      
        return primaryKey;
    }
    
    /**
     * update rent information in database 
     * @param  rent
     * @return number of rows updated
     */
    public int updateRent(Rent rent) {
        String query = " update rent set fully_paid = ?,amount_paid = ?,"
                + "payment_method = ?,payment_date = ?,lease_id=? where rent_id = ?;";  
        int row = 0;
        Connection con = DataBaseConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);
            ps.setString(1,Boolean.toString(rent.isFullyPaid()));
            ps.setDouble(2,rent.getAmountPaid());
            ps.setString(3,rent.getPaymentMethod());
            java.sql.Date sqlPaymentDate = java.sql.Date.valueOf(rent.getPaymentDate());
            ps.setDate(4,sqlPaymentDate);
            ps.setInt(5,rent.getLease().getLeaseId());
            ps.setInt(6,rent.getRentId());
             
         
            row = ps.executeUpdate();
            System.out.println(rent+"upadated-------> " +rent);
        } catch (SQLException ex) {
              Logger.getLogger(RentDbAccess.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
                try {
                con.close();
                } catch (SQLException ex) {
                Logger.getLogger(RentDbAccess.class.getName()).log(Level.SEVERE, null, ex);
                }
        }  
            
        return row;  
    }
    
     /**
     * delete rent from database
     * @param rent
     * @return row affected
     */
    public int deleteRent(Rent rent){
        Connection con = DataBaseConnection.getConnection();
        String query = " delete from rent where rent_id = ?";  
        int row = 0;
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1,rent.getRentId());
            row = ps.executeUpdate();
        } catch (SQLException ex) {
              Logger.getLogger(RentDbAccess.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
                try {
                con.close();
                } catch (SQLException ex) {
                Logger.getLogger(RentDbAccess.class.getName()).log(Level.SEVERE, null, ex);
                }
        } 
       
        return row;
        
    }
}
