/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.realestate.model.database;

import com.mycompany.realestate.model.Company;
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
public class PropertyDbAccess  {
    
    
    InsuranceDbAccess idba = new InsuranceDbAccess();
    RepairDbAccess rdba = new RepairDbAccess();
    /**
     * This method adds property to database
     * @param  p property
     * @return number of rows added
     *
     */
    public int add(Property p) {
        int row = 0;
        int property_id = getPriamryKey();  
        String query = " insert into property  values (?, ?, ?, ?, ?,?,?,?,?)";  
        if(property_id > -1){
            Connection con = DataBaseConnection.getConnection();
            PreparedStatement ps;
        
        try {
            
            ps = con.prepareStatement(query);
            ps.setInt(1,property_id);
            ps.setString(2,p.getAddress());
            ps.setString(3,p.getPropertyType());
            ps.setDouble(4,p.getRentAmount());
            ps.setDouble(5,p.getSchoolTax());
            ps.setDouble(6,p.getPropertyTax());
            ps.setString(7,Boolean.toString(p.isIsVacant()));
            ps.setInt(8, p.getInsurance().getInsuranceId());
            ps.setInt(9, p.getUnitNum());
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
        // set the property_id and add property the properties company
        if(row != 0){
            p.setPropertyId(property_id);
            Company.getInstance().addProperty(p);
        }
      return row;
    }

    
    /**
     * this method generates primary key for new property
     * @return int 
     */
    private int getPriamryKey() {
        
        int primaryKey = 0;
        
        String query = " select max(property_id) + 1 from property;";
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
     * delete property from database and global variable company
     * @param p property
     * @return int number of rows deleted
     */
    public int delete(Property  p ) {
        String query = " delete from property where property_id = ?";  
        int row = 0;
        Connection con = DataBaseConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1,p.getPropertyId());
            row = ps.executeUpdate();
            System.out.println(row);
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
        // set the property_id and add property the properties company
        if(row != 0){
            Company.getInstance().removeProperty(p);
        }
        return row;
    }
 /**
    * method gets gets and creates property
    * @param id primaryKey
    * @return Property
    */
    public Property getProperty(String id){
    
        Property p = new Property();
        
        Connection con = DataBaseConnection.getConnection();  
        
        String getInsuranceQuery = "SELECT * FROM property where property_id="+id+"; ";	
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(getInsuranceQuery);
       
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                p.setPropertyId(rs.getInt("property_id"));
                p.setAddress(rs.getString("address"));
                p.setRentAmount(rs.getDouble("rent_amount"));
                p.setSchoolTax(rs.getDouble("school_tax"));
                p.setPropertyTax(rs.getDouble("property_tax"));
                p.setIsVacant(Boolean.parseBoolean(rs.getString("vacant")));
                p.setInsurance(idba.getInsurance(rs.getString("insurance_id")));
                p.setUnitNum(rs.getInt("unit_number"));
                p.setPropertyType(rs.getString("property_type"));
            }
        }
        catch (SQLException ex) {
              Logger.getLogger(InsuranceDbAccess.class.getName()).log(Level.SEVERE, null, ex);
          }      
          return p;
      }
    /**
     * update the property in database 
     * @param p property
     * @return number of rows updated
     */
    public int update(Property p) {

        String query = " update property set address = ?,property_type = ?,rent_amount = ?,"
                + "school_tax = ?,property_tax = ?,vacant = ?,insurance_id = ?,unit_number =?  "
                + "where property_id = ?;";  
        int row = 0;
        Connection con = DataBaseConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);
            ps.setString(1,p.getAddress());
            ps.setString(2,p.getPropertyType());
            ps.setDouble(3,p.getRentAmount());
            ps.setDouble(4,p.getSchoolTax());
            ps.setDouble(5,p.getPropertyTax());
            ps.setString(6,Boolean.toString(p.isIsVacant()));
            ps.setInt(7,p.getInsurance().getInsuranceId());
            ps.setInt(8,p.getUnitNum());
            ps.setInt(9,p.getPropertyId());
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
    
    /**
     * more to implement
     */
    public void instantiate() {
        
        List <Property> properties = new ArrayList<>();
        Connection con = DataBaseConnection.getConnection();
        String getPropertyQuery = "SELECT * FROM property";	
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(getPropertyQuery);
       
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Property p = new Property();
                p.setPropertyId(rs.getInt("property_id"));
                p.setAddress(rs.getString("address"));
                p.setPropertyType(rs.getString("property_type"));
                p.setRentAmount(rs.getDouble("rent_amount"));
                p.setSchoolTax(rs.getDouble("school_tax"));
                p.setPropertyTax(rs.getDouble("property_tax"));
                p.setIsVacant(Boolean.parseBoolean(rs.getString("vacant")));
                p.setInsurance(idba.getInsurance(rs.getString("insurance_id")));
                p.setUnitNum(rs.getInt("unit_number"));
                List<Repair> pRepairs = rdba.getRepairs(rs.getInt("property_id"));
                p.setRepairs(pRepairs);
                setPropertyRepairs(pRepairs,p);
                properties.add(p);  
            }
         } catch (SQLException ex) {
            Logger.getLogger(PropertyDbAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
	
       Company.getInstance().setProperties(properties);  
    }  

    private void setPropertyRepairs(List<Repair> pRepairs,Property p) {
          for (Repair r : pRepairs){
              r.setProperty(p);
          }
          System.out.println(p.getRepairs().size());
    }
}

//-------------------------------end

//    public List<Property> getList() throws SQLException {
//        
//        List <Property> properties = new ArrayList<Property>();
//        Connection con = DataBaseConnection.getConnection();
//        String getPropertyQuery = "SELECT * FROM property";	
//        PreparedStatement ps = con.prepareStatement(getPropertyQuery);
//	ResultSet rs = ps.executeQuery();
//	while(rs.next()) {
//            Property p = new Property();
//            p.setPropertyId(rs.getInt("property_id"));
//            p.setAddress(rs.getString("address"));
//            p.setRentAmount(rs.getDouble("rent_amount"));
//            p.setSchoolTax(rs.getDouble("school_tax"));
//            p.setPropertyTax(rs.getDouble("property_tax"));
//            p.setIsVacant(Boolean.parseBoolean(rs.getString("vacant")));
//            p.setInsurance(idba.getInsurance(rs.getString("insurance_id")));
//            p.setUnitNum(rs.getInt("unit_number"));
//            properties.add(p);  
//	}
//	
//        return properties;
//    }