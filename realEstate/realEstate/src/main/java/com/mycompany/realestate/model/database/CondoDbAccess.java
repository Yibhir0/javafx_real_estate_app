
package com.mycompany.realestate.model.database;

import com.mycompany.realestate.model.*;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yassine Ibhir
 */
public class CondoDbAccess extends PropertyDAO {
    
   
        /**
     * add Condo to database
     * @param property
     * @return 
     */
    @Override
    public int addProperty(Property property) {
        Connection con = DataBaseConnection.getConnection();
        int row = 0;
        int property_id = super.getPriamryKey();  
        String query = " insert into property  values (?, ?, ?, ?, ?,?,?,?,?,?,?,?,?)";  
        if(property_id > -1){
            property.setPropertyId(property_id);
            PreparedStatement ps;
        
        try {
            
            ps = con.prepareStatement(query);
            ps.setInt(1,property_id);
            ps.setString(2,property.getAddress());
            ps.setString(3,property.getPropertyType());
            ps.setDouble(4,property.getRentAmount());
            ps.setDouble(5,property.getSchoolTax());
            ps.setDouble(6,property.getPropertyTax());
            ps.setString(7,Boolean.toString(property.isIsVacant()));
            ps.setInt(8, property.getInsurance().getInsuranceId());
            ps.setInt(9, property.getNumberRooms());
            ps.setDouble(10,((Condo)property).getCondoFees());
            ps.setNull(11, Types.NULL); // condo is one unit
            ps.setInt(12, property.getApartmentNumber());
            ps.setNull(13, Types.NULL);  //no unit plexes are associated with a condo
            row = ps.executeUpdate();
 
            
        } catch (SQLException ex) {
              Logger.getLogger(CondoDbAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CondoDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
      }
      
      return row;
    }

    /**
     * get all condos from database. Creates condos objects
     * @return List of properties containing condos
     */
    @Override
    public List<Property> getAllProperties() {
        Connection con = DataBaseConnection.getConnection();
        List <Property> properties = new ArrayList<>();
        String getPropertyQuery = "SELECT * FROM property where property_type = ?";	
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(getPropertyQuery);
            ps.setString(1,"Condo");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Condo condo = new Condo();
                condo.setPropertyId(rs.getInt("property_id"));
                condo.setAddress(rs.getString("address"));
                condo.setPropertyType(rs.getString("property_type"));
                condo.setRentAmount(rs.getDouble("rent_amount"));
                condo.setSchoolTax(rs.getDouble("school_tax"));
                condo.setPropertyTax(rs.getDouble("property_tax"));
                condo.setIsVacant(Boolean.parseBoolean(rs.getString("vacant")));
                condo.setInsurance((super.idba).getInsurance(rs.getString("insurance_id")));
                condo.setNumberRooms(rs.getInt("rooms"));
                condo.setCondoFees(rs.getDouble("condo_fees"));
                condo.setApartmentNumber(rs.getInt("appartment_number"));
                List<Repair> pRepairs = (super.rdba).getRepairs(condo);
                List<Mortgage> pmortgages = (super.mdba).getMortgages(condo);
                List<Lease> pleases = (super.ldba).getLeases(condo);
                List<Utility> pUtilities = (super.udba).getUtilities(condo);
                condo.setRepairs(pRepairs);
                condo.setMortgages(pmortgages);
                condo.setLeases(pleases);
                condo.setUtilities(pUtilities);
                properties.add(condo); 
    
            }
          } catch (SQLException ex) {
              Logger.getLogger(CondoDbAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CondoDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return properties;
       
    }
    
    
    /**
     * update Condo in database
     * @param property
     * @return 
     */
    @Override
    public int updateProperty(Property property) {
        Connection con = DataBaseConnection.getConnection();
        String query = " update property set address = ?,property_type = ?,rent_amount = ?,"
                + "school_tax = ?,property_tax = ?,vacant = ?,insurance_id = ?,rooms =?,condo_fees=?, appartment_number = ?  "
                + "where property_id = ?;";  
        int row = 0;
    
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);
            ps.setString(1,property.getAddress());
            ps.setString(2,property.getPropertyType());
            ps.setDouble(3,property.getRentAmount());
            ps.setDouble(4,property.getSchoolTax());
            ps.setDouble(5,property.getPropertyTax());
            ps.setString(6,Boolean.toString(property.isIsVacant()));
            ps.setInt(7,property.getInsurance().getInsuranceId());
            ps.setInt(8,property.getNumberRooms());
            ps.setDouble(9, ((Condo)property).getCondoFees());
            ps.setInt(10,property.getApartmentNumber());
            ps.setInt(11,property.getPropertyId());
            row = ps.executeUpdate();
            System.out.println("upadated-------> " +property);
         } catch (SQLException ex) {
              Logger.getLogger(CondoDbAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CondoDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return row;
    }
}
