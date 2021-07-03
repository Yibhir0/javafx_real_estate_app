
package com.mycompany.realestate.model.database;

import com.mycompany.realestate.model.Mortgage;
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
 *  class maintains the crud operations related
 * to Mortgage
 * @author Yassine Ibhir
 */
public class MortgageDbAccess {
     
    BankDbAcces bdba = new BankDbAcces();
    /**
     * returns a list of all Mortgages in database
     * @param p
     * @return  List
     */
    
    public List<Mortgage> getMortgages(Property p) {
        
        List <Mortgage> mortgages = new ArrayList<>();
        Connection con = DataBaseConnection.getConnection();
        String getInsuranceQuery = "SELECT * FROM mortgage  where property_id ="+p.getPropertyId()+"; ";	
        PreparedStatement ps;
         try {
            ps = con.prepareStatement(getInsuranceQuery);
         
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Mortgage m= new Mortgage();
                m.setMortgageId(rs.getInt("mortgage_id"));
                m.setTerm(rs.getInt("mortgage_term"));
                java.time.LocalDate sdate = rs.getDate("start_date").toLocalDate();
                m.setStartDate(sdate);
                m.setAmount(rs.getDouble("mortgage_amount"));
                m.setDownPayment(rs.getDouble("down_payment"));
                m.setBank(bdba.getBank(rs.getString("bank_id")));
                m.setFullyPaid(Boolean.parseBoolean(rs.getString("fully_paid")));
                m.setProperty(p);
                mortgages.add(m);  
            }
          } catch (SQLException ex) {
              Logger.getLogger(MortgageDbAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(MortgageDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
	
        return mortgages;
    }
    
    /***
     * add mortgage to database
     * @param mortgage
     * @return number of rows affected
     */
     public int addMortgage(Mortgage mortgage) {

        int row = 0;
        int mortgage_id = getPriamryKey();  
        String query = " insert into mortgage  values (?, ?, ?, ?, ?,?,?,?)";  
        if(mortgage_id > -1){
            Connection con = DataBaseConnection.getConnection();
            mortgage.setMortgageId(mortgage_id);
            PreparedStatement ps;
        
        try { 

            ps = con.prepareStatement(query);
            ps.setInt(1,mortgage_id);
            ps.setInt(2, mortgage.getTerm());
            
            ps.setDouble(3, mortgage.getAmount());
            java.sql.Date sqlStartDate = java.sql.Date.valueOf(mortgage.getStartDate());
            ps.setDate(4, sqlStartDate);
           
            ps.setDouble(5, mortgage.getDownPayment());
            ps.setString(6,Boolean.toString(mortgage.isFullyPaid()));
            ps.setInt(7,mortgage.getProperty().getPropertyId());
            ps.setInt(8,mortgage.getBank().getBankId());
                               
            row = ps.executeUpdate();
            
        } catch (SQLException ex) {
              Logger.getLogger(MortgageDbAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(MortgageDbAccess.class.getName()).log(Level.SEVERE, null, ex);
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
        
        String query = " select max(mortgage_id) + 1 from mortgage;";
        Connection con = DataBaseConnection.getConnection();
   
        try {
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery();
             if (rs.next()){
                primaryKey = rs.getInt(1);
             }
       } catch (SQLException ex) {
              Logger.getLogger(MortgageDbAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(MortgageDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      
        return primaryKey;
    }
    
    /**
     * update mortgage information in database 
     * @param mortgage
     * @return number of rows updated
     */
    public int updateMortgage(Mortgage mortgage) {

        String query = " update mortgage set mortgage_term = ?,mortgage_amount = ?,"
                + "start_date = ?,down_payment = ?,fully_paid = ?,property_id = ?,bank_id = ? "
                + "where mortgage_id = ?;";  
        int row = 0;
        Connection con = DataBaseConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);
           ps.setInt(1, mortgage.getTerm());
            ps.setDouble(2, mortgage.getAmount());
            java.sql.Date sqlStartDate = java.sql.Date.valueOf(mortgage.getStartDate());
            ps.setDate(3, sqlStartDate);
            ps.setDouble(4, mortgage.getDownPayment());
            ps.setString(5,Boolean.toString(mortgage.isFullyPaid()));
            ps.setInt(6,mortgage.getProperty().getPropertyId());
            ps.setInt(7,mortgage.getBank().getBankId());
            ps.setInt(8,mortgage.getMortgageId());
         
            row = ps.executeUpdate();
            System.out.println("upadated-------> " +row);
        } catch (SQLException ex) {
              Logger.getLogger(MortgageDbAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(MortgageDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return row;  
    }
    
     /**
     * delete Mortgage from database
     * @param mortgage
     * @return row affected
     */
    public int deleteMortgage(Mortgage mortgage){
        Connection con = DataBaseConnection.getConnection();
        String query = " delete from mortgage where mortgage_id = ?";  
        int row = 0;
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1,mortgage.getMortgageId());
            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MortgageDbAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(MortgageDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
       
        return row;
        
    }
}

                     //(mortgage_id int NOT NULL,
//    mortgage_term int,
//    mortgage_amount decimal,
//    start_date datetime,
//    down_payment decimal,
//    fully_paid varchar(5),
//    property_id int NOT NULL,
//    bank_id int NOT NULL,