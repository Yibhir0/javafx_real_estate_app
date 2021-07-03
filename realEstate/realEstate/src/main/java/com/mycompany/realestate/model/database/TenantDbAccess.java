
package com.mycompany.realestate.model.database;

import com.mycompany.realestate.model.Tenant;
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
public class TenantDbAccess {
    
     /**
     * returns a list of tenants in database
     * @return 
     */
    public List<Tenant> getList() {
        
        List <Tenant> tenants = new ArrayList<>();
        Connection con = DataBaseConnection.getConnection();
        String getInsuranceQuery = "SELECT * FROM tenant; ";	
        PreparedStatement ps;
         try {
            ps = con.prepareStatement(getInsuranceQuery);
         
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Tenant tenant = new Tenant();
                tenant.setTenantId(rs.getInt("tenant_id"));
                tenant.setFullName(rs.getString("full_name"));
                tenant.setIdentity(rs.getString("driver_licence_id"));
                tenant.setPhone(rs.getString("phone"));
                tenant.setEmail(rs.getString("email_address"));
                tenants.add(tenant); 

            }
        } catch (SQLException ex) {
              Logger.getLogger(TenantDbAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(TenantDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
	
        return tenants;
    }
    /**
     * add tenant to database
     * @param tenant
     * @return number of rows affected
     */
    public int addTenant(Tenant tenant) {
        int row = 0;
        int tenant_id = getPriamryKey();  
        String query = " insert into tenant  values (?, ?, ?, ?, ?)";  
        if(tenant_id > -1){
            Connection con = DataBaseConnection.getConnection();
            tenant.setTenantId(tenant_id);
            PreparedStatement ps;
        
            try { 

                ps = con.prepareStatement(query);
                ps.setInt(1,tenant_id);
                ps.setString(2,tenant.getIdentity());
                ps.setString(3,tenant.getFullName());
                ps.setString(4,tenant.getEmail());
                ps.setString(5,tenant.getPhone());


                row = ps.executeUpdate();

            } catch (SQLException ex) {
              Logger.getLogger(TenantDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                con.close();
                } catch (SQLException ex) {
                Logger.getLogger(TenantDbAccess.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            
        }
        return row;
    }
    
    /**
     * generate primary key for new tenant
     * @return 
     */
    private int getPriamryKey() {
        int primaryKey = 0;
        
        String query = " select max(tenant_id) + 1 from tenant;";
        Connection con = DataBaseConnection.getConnection();
   
        try {
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery();
             if (rs.next()){
                primaryKey = rs.getInt(1);
             }
        } catch (SQLException ex) {
              Logger.getLogger(TenantDbAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(TenantDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
      
        return primaryKey;
    }
    
    /**
     * update tenant information in database 
     * @param  tenant
     * @return number of rows updated
     */
    public int updateTenant(Tenant tenant) {

        String query = " update tenant set driver_licence_id  = ?,full_name = ?,"
                + "email_address = ?,phone = ? where tenant_id = ?;";  
        int row = 0;
        Connection con = DataBaseConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);
            ps.setString(1,tenant.getIdentity());
            ps.setString(2,tenant.getFullName());
            ps.setString(3,tenant.getEmail());
            ps.setString(4,tenant.getPhone());
            ps.setInt(5,tenant.getTenantId());
         
            row = ps.executeUpdate();
            System.out.println(tenant.getTenantId()+"upadated-------> " +row);
        } catch (SQLException ex) {
              Logger.getLogger(TenantDbAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(TenantDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
        return row;  
    }
    
     /**
     * delete tenant from database
     * @param tenant
     * @return row affected
     */
    public int deleteTenant(Tenant tenant){
        Connection con = DataBaseConnection.getConnection();
        String query = " delete from tenant where tenant_id = ?";  
        int row = 0;
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1,tenant.getTenantId());
            row = ps.executeUpdate();
        } catch (SQLException ex) {
              Logger.getLogger(TenantDbAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(TenantDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
       
        return row;
        
    }
    
       
   /**
    * method access database and creates tenant object
    * @param id primaryKey
    * @return tenant
    */
    public Tenant getTenant(String id){

        Tenant tenant = new Tenant();
        
        Connection con = DataBaseConnection.getConnection();  
        
        String getInsuranceQuery = "SELECT * FROM tenant where tenant_id="+id+"; ";	
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(getInsuranceQuery);
       
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                tenant.setTenantId(rs.getInt("tenant_id"));
                tenant.setFullName(rs.getString("full_name"));
                tenant.setEmail(rs.getString("email_address"));
                tenant.setIdentity(rs.getString("driver_licence_id"));
                tenant.setPhone(rs.getString("phone")); 
            }
            else{
                return null;
            }
        } catch (SQLException ex) {
              Logger.getLogger(TenantDbAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(TenantDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
          return tenant;
      }
}
