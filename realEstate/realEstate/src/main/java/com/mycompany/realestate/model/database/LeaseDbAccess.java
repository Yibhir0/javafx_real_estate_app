
package com.mycompany.realestate.model.database;

import com.mycompany.realestate.model.Lease;
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
 * @author Yassine Ibhir
 */
public class LeaseDbAccess {
    
    
    private RentDbAccess rdba = new RentDbAccess();
    private TenantDbAccess tdba = new TenantDbAccess();
     /**
     * returns a list of all leases in database
     * @param property
     * @return  List
     */
    
    public List<Lease> getLeases(Property p) {
        
        List <Lease> leases = new ArrayList<>();
        Connection con = DataBaseConnection.getConnection();
        String getInsuranceQuery = "SELECT * FROM lease  where property_id ="+p.getPropertyId()+" order by end_date DESC ; ";	
        PreparedStatement ps;
         try {
            ps = con.prepareStatement(getInsuranceQuery);

         
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Lease l= new Lease();
                l.setLeaseId(rs.getInt("lease_id"));
                l.setTerm(rs.getInt("lease_term"));
                java.time.LocalDate sdate = rs.getDate("start_date").toLocalDate();
                l.setStartDate(sdate);
                java.time.LocalDate edate = rs.getDate("end_date").toLocalDate();
                l.setEndDate(edate);
                l.setRenewal(Boolean.parseBoolean(rs.getString("renewal")));
                l.setLeaseFileName(rs.getString("pdfFile"));
                l.setTenant(tdba.getTenant(rs.getString("tenant_id")));
                l.setRents(rdba.getRents(l));
                l.setProperty(p);
                leases.add(l);  
            }
          } catch (SQLException ex) {
              Logger.getLogger(LeaseDbAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(LeaseDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
	
        return leases;
    }
    
    /***
     * add Lease to database
     * @param  lease
     * @return number of rows affected
     */
     public int addLease(Lease lease) {

        int row = 0;
        int lease_id = getPriamryKey();  
        String query = " insert into lease  values (?, ?, ?, ?, ?,?,?,?)";  
        if(lease_id > -1){
            Connection con = DataBaseConnection.getConnection();
            lease.setLeaseId(lease_id);
            PreparedStatement ps;
        

        try { 

            ps = con.prepareStatement(query);
            ps.setInt(1,lease_id);
            ps.setInt(2, lease.getTerm());
            java.sql.Date sqlStartDate = java.sql.Date.valueOf(lease.getStartDate());
            ps.setDate(3, sqlStartDate);
            java.sql.Date sqlEndDate = java.sql.Date.valueOf(lease.getEndDate());
            ps.setDate(4, sqlEndDate);
            ps.setString(5, Boolean.toString(lease.isRenewal()));
            ps.setString(6,lease.getLeaseFileName());
            ps.setInt(7,lease.getProperty().getPropertyId());
            ps.setInt(8,lease.getTenant().getTenantId());
                               
            row = ps.executeUpdate();
            
        } catch (SQLException ex) {
              Logger.getLogger(LeaseDbAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(LeaseDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      
            
      }
      return row;
    }
    
    /**
     * 
     * @return primary key for a new lease
     */
    private int getPriamryKey() {
        int primaryKey = 0;
        
        String query = " select max(lease_id) + 1 from lease;";
        Connection con = DataBaseConnection.getConnection();
   
        try {
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery();
             if (rs.next()){
                primaryKey = rs.getInt(1);
             }
       } catch (SQLException ex) {
              Logger.getLogger(LeaseDbAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(LeaseDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      
        return primaryKey;
    }
    
    /**
     * update lease information in database 
     * @param lease
     * @return number of rows updated
     */
    public int updateLease(Lease lease) {
 

        String query = " update lease set lease_term = ?,start_date = ?,"
                + "end_date = ?,tenant_id = ?,renewal = ?,pdfFile = ?,property_id = ? "
                + "where lease_id = ?;";  
        int row = 0;
        Connection con = DataBaseConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, lease.getTerm());
            java.sql.Date sqlStartDate = java.sql.Date.valueOf(lease.getStartDate());
            ps.setDate(2, sqlStartDate);
            java.sql.Date sqlEndDate = java.sql.Date.valueOf(lease.getEndDate());
            ps.setDate(3, sqlEndDate); 
            ps.setInt(4, lease.getTenant().getTenantId());
            ps.setString(5,Boolean.toString(lease.isRenewal()));
            ps.setString(6,lease.getLeaseFileName());
            ps.setInt(7,lease.getProperty().getPropertyId());
            ps.setInt(8,lease.getLeaseId());
         
            row = ps.executeUpdate();
            System.out.println("upadated-------> " +row);
        } catch (SQLException ex) {
              Logger.getLogger(LeaseDbAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(LeaseDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return row;  
    }
    
     /**
     * delete Lease from database
     * @param lease
     * @return row affected
     */
    public int deleteLease(Lease lease){
        
        Connection con = DataBaseConnection.getConnection();
        String query = " delete from lease where lease_id = ?";  
        int row = 0;
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1,lease.getLeaseId());
            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LeaseDbAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(LeaseDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (row > 0){
            tdba.deleteTenant(lease.getTenant());   
            }
 
        return row;
        
    }
}
