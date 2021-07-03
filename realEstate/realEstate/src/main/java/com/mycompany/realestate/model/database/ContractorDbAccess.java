
package com.mycompany.realestate.model.database;

import com.mycompany.realestate.model.Contractor;
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
              Logger.getLogger(ContractorDbAccess.class.getName()).log(Level.SEVERE, null, ex);
          }    
	
        return contractors;
    }
    
    /**
    * method gets and creates Contractor
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
              Logger.getLogger(ContractorDbAccess.class.getName()).log(Level.SEVERE, null, ex);
          }      
          return c;
     }
    
    /**
     * add Contractor to database
     * @param contractor
     * @return number of rows affected
     */
    public int addContractor(Contractor contractor) {
        int row = 0;
        int contractor_id = getPriamryKey();  
        String query = " insert into contractor  values (?, ?, ?, ?, ?)";  
        if(contractor_id > -1){
            Connection con = DataBaseConnection.getConnection();
            contractor.setContractorId(contractor_id);
            PreparedStatement ps;
        
            try { 
                ps = con.prepareStatement(query);
                ps.setInt(1,contractor_id);
                ps.setString(2,contractor.getName());
                ps.setString(3,contractor.getSpecialization());
                ps.setString(4,contractor.getAddress());
                ps.setString(5,contractor.getPhone());

                row = ps.executeUpdate();

             } catch (SQLException ex) {
                  Logger.getLogger(ContractorDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ContractorDbAccess.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            
        }
        return row;
    }
    
    /**
     * generate primary key for new bank
     * @return 
     */
    private int getPriamryKey() {
        int primaryKey = -1;
        
        String query = " select max(contractor_id) + 1 from contractor;";
        Connection con = DataBaseConnection.getConnection();
   
        try {
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery();
             if (rs.next()){
                primaryKey = rs.getInt(1);
             }
        } catch (SQLException ex) {
              Logger.getLogger(ContractorDbAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ContractorDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
      
        return primaryKey;
    }
    
    /**
     * update Contractor information in database 
     * @param  contractor
     * @return number of rows updated
     */
    public int updateContractor(Contractor contractor) {


        String query = " update contractor set address = ?,name = ?,"
                + "specialization = ?,phone = ? where contractor_id = ?;";  
        int row = 0;
        Connection con = DataBaseConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);
            ps.setString(1,contractor.getAddress());
            ps.setString(2,contractor.getName());
            ps.setString(3,contractor.getSpecialization());
            ps.setString(4,contractor.getPhone());
            ps.setInt(5,contractor.getContractorId());
         
            row = ps.executeUpdate();
            System.out.println("upadated-------> " +row);
        } catch (SQLException ex) {
              Logger.getLogger(ContractorDbAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ContractorDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return row;  
    }
    
     /**
     * delete Contractor from database
     * @param contractor
     * @return row affected
     */
    public int deleteContractor(Contractor contractor){
        Connection con = DataBaseConnection.getConnection();
        String query = " delete from contractor where contractor_id = ?";  
        int row = 0;
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1,contractor.getContractorId());
            row = ps.executeUpdate();
          } catch (SQLException ex) {
              Logger.getLogger(ContractorDbAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ContractorDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
       
        return row;
        
    }
    
}
