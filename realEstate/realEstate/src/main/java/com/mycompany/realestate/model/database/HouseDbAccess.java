
package com.mycompany.realestate.model.database;
import com.mycompany.realestate.model.House;
import com.mycompany.realestate.model.Lease;
import com.mycompany.realestate.model.Mortgage;
import com.mycompany.realestate.model.Property;
import com.mycompany.realestate.model.Repair;
import com.mycompany.realestate.model.Utility;
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
 * this class implements the PropertyDbAccessI Interface.
 * contains all crud operations related to condo
 * @author Yassine
 */
public class HouseDbAccess extends PropertyDAO {
    
    public HouseDbAccess(){
        super();
    }
    
    /**
     * add condo to database
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
            ps.setNull(10,Types.NULL); // no condo fees. it's a house.
            ps.setNull(11,Types.NULL);// no number of units in a house.
            ps.setNull(12,Types.NULL); // noappartment# in a house
            ps.setNull(13,Types.NULL); // not plex unit
     
            row = ps.executeUpdate();
            
        } catch (SQLException ex) {
              Logger.getLogger(HouseDbAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(HouseDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
      }
    
      return row;
    }

    /**
     * get all houses from database. Creates Houses objects
     * @return List of properties containing houses
     */
    @Override
    public List<Property> getAllProperties() {
        Connection con = DataBaseConnection.getConnection();
        List <Property> properties = new ArrayList<>();
        String getPropertyQuery = "SELECT * FROM property where property_type = ?";	
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(getPropertyQuery);
            ps.setString(1,"House");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                House house = new House();
                house.setPropertyId(rs.getInt("property_id"));
                house.setAddress(rs.getString("address"));
                house.setPropertyType(rs.getString("property_type"));
                house.setRentAmount(rs.getDouble("rent_amount"));
                house.setSchoolTax(rs.getDouble("school_tax"));
                house.setPropertyTax(rs.getDouble("property_tax"));
                house.setIsVacant(Boolean.parseBoolean(rs.getString("vacant")));
                house.setInsurance((super.idba).getInsurance(rs.getString("insurance_id")));
                house.setNumberRooms(rs.getInt("rooms"));
                List<Repair> pRepairs = (super.rdba).getRepairs(house);
                List<Mortgage> pmortgages = (super.mdba).getMortgages(house);
                List<Lease> pleases = (super.ldba).getLeases(house);
                List<Utility> pUtilities = (super.udba).getUtilities(house);
                house.setRepairs(pRepairs);
                house.setMortgages(pmortgages);
                house.setLeases(pleases);
                house.setUtilities(pUtilities);
                properties.add(house);  
            }
         } catch (SQLException ex) {
            Logger.getLogger(HouseDbAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
         finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(HouseDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return properties;
       
    }
    
    
    /**
     * update house from database
     * @param property
     * @return 
     */
    @Override
    public int updateProperty(Property property) {
        Connection con = DataBaseConnection.getConnection();
        String query = " update property set address = ?,property_type = ?,rent_amount = ?,"
                + "school_tax = ?,property_tax = ?,vacant = ?,insurance_id = ?,rooms =?  "
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
            ps.setInt(9,property.getPropertyId());
            row = ps.executeUpdate();
            System.out.println("upadated-------> " +property);
        } catch (SQLException ex) {
            Logger.getLogger(HouseDbAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
         finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(HouseDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return row;
    }
    
}
