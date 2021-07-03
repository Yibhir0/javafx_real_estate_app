
package com.mycompany.realestate.model.database;

import com.mycompany.realestate.model.Bank;
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
 * @author Yassine
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
              Logger.getLogger(BankDbAcces.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BankDbAcces.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
	
        return banks;
    }
    /**
     * add bank to database
     * @param bank
     * @return number of rows affected
     */
    public int addBank(Bank bank) {
        int row = 0;
        int bank_id = getPriamryKey();  
        String query = " insert into Bank  values (?, ?, ?, ?, ?)";  
        if(bank_id > -1){
            Connection con = DataBaseConnection.getConnection();
            bank.setBankId(bank_id);
            PreparedStatement ps;
        
            try { 

                ps = con.prepareStatement(query);
                ps.setInt(1,bank_id);
                ps.setString(2,bank.getName());
                ps.setDouble(3,bank.getIntrestRate());
                ps.setString(4,bank.getAddress());
                ps.setString(5,bank.getPhone());

                row = ps.executeUpdate();

             } catch (SQLException ex) {
                  Logger.getLogger(BankDbAcces.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BankDbAcces.class.getName()).log(Level.SEVERE, null, ex);
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
        
        String query = " select max(bank_id) + 1 from bank;";
        Connection con = DataBaseConnection.getConnection();
   
        try {
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery();
             if (rs.next()){
                primaryKey = rs.getInt(1);
             }
        } catch (SQLException ex) {
              Logger.getLogger(BankDbAcces.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BankDbAcces.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
      
        return primaryKey;
    }
    
    /**
     * update bank information in database 
     * @param  bank
     * @return number of rows updated
     */
    public int updateBank(Bank bank) {

        String query = " update bank set address = ?,name = ?,"
                + "intrest_rate = ?,phone = ? where bank_id = ?;";  
        int row = 0;
        Connection con = DataBaseConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);
            ps.setString(1,bank.getAddress());
            ps.setString(2,bank.getName());
            ps.setDouble(3,bank.getIntrestRate());
            ps.setString(4,bank.getPhone());
            ps.setInt(5,bank.getBankId());
         
            row = ps.executeUpdate();
            System.out.println("upadated-------> " +row);
   } catch (SQLException ex) {
              Logger.getLogger(BankDbAcces.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BankDbAcces.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
        return row;  
    }
    
     /**
     * delete Bank from database
     * @param bank
     * @return row affected
     */
    public int deleteBank(Bank bank){
        Connection con = DataBaseConnection.getConnection();
        String query = " delete from bank where bank_id = ?";  
        int row = 0;
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1,bank.getBankId());
            row = ps.executeUpdate();
          } catch (SQLException ex) {
              Logger.getLogger(BankDbAcces.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BankDbAcces.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
       
        return row;
        
    }
    
       
   /**
    * method access database and creates bank object
    * @param id primaryKey
    * @return bank
    */
    public Bank getBank(String id){
    
        Bank bank = new Bank();
        
        Connection con = DataBaseConnection.getConnection();  
        
        String getInsuranceQuery = "SELECT * FROM bank where bank_id="+id+"; ";	
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(getInsuranceQuery);
       
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                bank.setBankId(rs.getInt("bank_id"));
                bank.setName(rs.getString("name"));
                bank.setIntrestRate(rs.getDouble("intrest_rate"));
                bank.setAddress(rs.getString("address"));
                bank.setPhone(rs.getString("phone")); 
            }
        } catch (SQLException ex) {
              Logger.getLogger(BankDbAcces.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BankDbAcces.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
          return bank;
      }
}
