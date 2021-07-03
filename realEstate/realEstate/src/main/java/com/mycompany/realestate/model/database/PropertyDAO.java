
package com.mycompany.realestate.model.database;

import com.mycompany.realestate.model.Company;
import com.mycompany.realestate.model.Property;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Abstract class contains methods shared between properties that implements it.
 * It has abstract methods to override.
 * @author Yassine Ibhir
 */
public abstract class PropertyDAO {
   
    
    // databas  access to insurance
    public InsuranceDbAccess idba = new InsuranceDbAccess();
    
    // data base access to repairs
    public RepairDbAccess rdba = new RepairDbAccess();
    // data base access to mortgages
    public MortgageDbAccess mdba = new MortgageDbAccess();
    
    // data base access to leases
    public LeaseDbAccess ldba = new LeaseDbAccess();
    
    // data base access to utilities
    public UtilityDbAccess udba = new UtilityDbAccess();
    /**
     * add property to database
     * @param property
     * @return number of rows affected
     */
    public abstract int addProperty(Property property);
    
    /**
     * getAll properties from database
     * @return 
     */
    public abstract List<Property> getAllProperties();
    
    /**
     * update property in database
     * @param property
     * @return 
     */
    public abstract int updateProperty(Property property);
    
    /**
     * this method calls the abstract method getAllProperties in the children classes
     * to get all properties
     */
    public void instantiateProperties(){
        List <Property> properties = this.getAllProperties();
        Company.getInstance().setProperties(properties);
    }
    
    /**
     * delete property from database
     * @param property
     * @return roe affected
     */
    public int deleteProperty(Property property){
        Connection con = DataBaseConnection.getConnection();
         String query = " delete from property where property_id = ?";  
        int row = 0;
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1,property.getPropertyId());
            row = ps.executeUpdate();
            System.out.println(row);
        } catch (SQLException ex) {
              Logger.getLogger(PropertyDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(PropertyDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
        return row;
        
    }
    
    /**
     * this method generates primary key for new property
     * @return int return new generated primary key for new property
     */
    public int getPriamryKey() {
        Connection con = DataBaseConnection.getConnection();
        int primaryKey = -1;
        
        String query = " select max(property_id) + 1 from property;";
   
        try {
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery();
             if (rs.next()){
                primaryKey = rs.getInt(1);
             }
        } catch (SQLException ex) {
            Logger.getLogger(PropertyDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(PropertyDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      
        return primaryKey;
        
    }

}
