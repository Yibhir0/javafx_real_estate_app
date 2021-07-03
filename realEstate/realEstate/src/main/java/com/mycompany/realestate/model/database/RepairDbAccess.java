
package com.mycompany.realestate.model.database;
import com.mycompany.realestate.model.Property;
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
 * @author Yassine Ibhir
 */
public class RepairDbAccess {
    
    
//     PropertyDbAccess pdba = new PropertyDbAccess();
     ContractorDbAccess cdba = new ContractorDbAccess();
     
    /**
     * returns a list of all repairs in database
     * @param p
     * @return  List
     */
    public List<Repair> getRepairs(Property p) {
        
        List <Repair> repairs = new ArrayList<>();
        Connection con = DataBaseConnection.getConnection();
        String getInsuranceQuery = "SELECT * FROM repairs where property_id ="+p.getPropertyId()+"; ";	
        PreparedStatement ps;
         try {
            ps = con.prepareStatement(getInsuranceQuery);
         
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Repair repair = new Repair();
                repair.setRepairId(rs.getInt("repair_id"));
                repair.setCost(rs.getDouble("repair_cost"));
                java.time.LocalDate startDate = rs.getDate("start_date").toLocalDate();
                repair.setStartDate(startDate);
                java.time.LocalDate endDate = rs.getDate("end_date").toLocalDate();
                repair.setEndDate(endDate);
                repair.setType(rs.getString("repair_type"));
                repair.setContractor(cdba.getContractor(rs.getString("contractor_id")));
                repair.setProperty(p);
                repairs.add(repair);  
            }
            } catch (SQLException ex) {
              Logger.getLogger(RepairDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                con.close();
                } catch (SQLException ex) {
                Logger.getLogger(RepairDbAccess.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            
	
        return repairs;
    }
     /**
     * add repair to database
     * @param repair
     * @return number of rows affected
     */
    public int addRepair(Repair repair) {
        

        int row = 0;
        int repair_id = getPriamryKey();  
        String query = " insert into repairs  values (?, ?, ?, ?, ?,?,?)";  
        if(repair_id > -1){
            Connection con = DataBaseConnection.getConnection();
            repair.setRepairId(repair_id);
            PreparedStatement ps;

            try { 

                ps = con.prepareStatement(query);
                ps.setInt(1,repair_id);
                ps.setString(2,repair.getType());
                ps.setDouble(3,repair.getCost());
                java.sql.Date startD = java.sql.Date.valueOf(repair.getStartDate());
                ps.setDate(4,startD);
                java.sql.Date endD = java.sql.Date.valueOf(repair.getEndDate());
                ps.setDate(5,endD);
                ps.setInt(6,repair.getProperty().getPropertyId());
                ps.setInt(7,repair.getContractor().getContractorId());
                row = ps.executeUpdate();

            } catch (SQLException ex) {
              Logger.getLogger(RepairDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                con.close();
                } catch (SQLException ex) {
                Logger.getLogger(RepairDbAccess.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            
        }
        return row;
    }
    
    /**
     * generate primary key for new repair
     * @return 
     */
    private int getPriamryKey() {
        int primaryKey = -1;
        
        String query = " select max(repair_id) + 1 from repairs;";
        Connection con = DataBaseConnection.getConnection();
   
        try {
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery();
             if (rs.next()){
                primaryKey = rs.getInt(1);
             }
        } catch (SQLException ex) {
              Logger.getLogger(RepairDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        finally{
            try {
               con.close();
            } catch (SQLException ex) {
              Logger.getLogger(RepairDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 

        return primaryKey;
    }
    
    /**
     * update repair information in database 
     * @param  repair
     * @return number of rows updated
     */
    public int updateRepair(Repair repair) {
        String query = " update repairs set repair_type = ?,repair_cost = ?,"
                + "start_date = ?,end_date = ?,property_id=?,contractor_id=? where repair_id = ?;"; 
 
        int row = 0;
        Connection con = DataBaseConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);
            ps.setString(1,repair.getType());
            ps.setDouble(2,repair.getCost());
            java.sql.Date startDate = java.sql.Date.valueOf(repair.getStartDate());
            ps.setDate(3,startDate);
            java.sql.Date endDate = java.sql.Date.valueOf(repair.getEndDate());
            ps.setDate(4,endDate); 
            ps.setInt(5,repair.getProperty().getPropertyId());
            ps.setInt(6,repair.getContractor().getContractorId());
            ps.setInt(7,repair.getRepairId());

         
            row = ps.executeUpdate();
            System.out.println("upadated-------> " +repair);
         } catch (SQLException ex) {
              Logger.getLogger(RepairDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        finally{
            try {
               con.close();
            } catch (SQLException ex) {
              Logger.getLogger(RepairDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
            
        return row;  
    }
    
     /**
     * delete repair from database
     * @param repair
     * @return row affected
     */
    public int deleteRent(Repair repair){
        Connection con = DataBaseConnection.getConnection();
        String query = " delete from repairs where repair_id = ?";  
        int row = 0;
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1,repair.getRepairId());
            row = ps.executeUpdate();
        } catch (SQLException ex) {
              Logger.getLogger(RepairDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        finally{
            try {
               con.close();
            } catch (SQLException ex) {
              Logger.getLogger(RepairDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
       
        return row;
        
    }
}
