
package com.mycompany.realestate.model.database;


import com.mycompany.realestate.model.Lease;
import com.mycompany.realestate.model.Mortgage;
import com.mycompany.realestate.model.Plex;
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
 *
 * @author Yassine Ibhir
 */
public class PlexDbAccess extends PropertyDAO {
 
       /**
     * add plex to database
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
                ps.setNull(4,Types.NULL); // rent column
                ps.setDouble(5,property.getSchoolTax());
                ps.setDouble(6,property.getPropertyTax());
                ps.setString(7,Boolean.toString(property.isIsVacant()));
                ps.setInt(8, property.getInsurance().getInsuranceId());
                ps.setNull(9,Types.NULL); // number of rooms will be in each plex unit
                ps.setNull(10,Types.NULL); // condo fees column (plex is not a condo)
                ps.setInt(11,((Plex)property).getNumberOfUnits() );
                ps.setNull(12,Types.NULL); // appartment number column
                ps.setNull(13,Types.NULL); // plexid column if unit belongs to a plex
                row = ps.executeUpdate();          

            } catch (SQLException ex) {
                  Logger.getLogger(PlexDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PlexDbAccess.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        if (row > 0){
            int added = addPlexProperties(property);
            if (added == ((Plex)property).getNumberOfUnits()){
                return row;
            }
            else{
                super.deleteProperty(property);
                row = 0;
            }
        }

        return row;
    }

    /**
     * get all plexes from database. 
     * @return List of  plexes and their properties
     */
    @Override
    public List<Property> getAllProperties() {
        
        Connection con = DataBaseConnection.getConnection();
        List <Property> properties = new ArrayList<>();
        String getPropertyQuery = "SELECT * FROM property where property_type = ?";
     
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(getPropertyQuery);
            ps.setString(1,"Plex");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Plex plex = new Plex();
                plex.setPropertyId(rs.getInt("property_id"));
                plex.setAddress(rs.getString("address"));
                plex.setRentAmount(rs.getDouble("rent_amount"));
                plex.setPropertyType(rs.getString("property_type"));
                plex.setSchoolTax(rs.getDouble("school_tax"));
                plex.setPropertyTax(rs.getDouble("property_tax"));
                plex.setIsVacant(Boolean.parseBoolean(rs.getString("vacant")));
                plex.setInsurance((super.idba).getInsurance(rs.getString("insurance_id")));
                plex.setNumberOfUnits(rs.getInt("plex_unit_num"));
                List<Repair> pRepairs = (super.rdba).getRepairs(plex);
                List<Mortgage> pmortgages = (super.mdba).getMortgages(plex);
                plex.setRepairs(pRepairs);
                plex.setMortgages(pmortgages);
          
                List<Property> plexProperties = getPlexUnits(plex);
                ((Plex)plex).setPlexProperties(plexProperties);

                properties.add(plex);  
            }
           } catch (SQLException ex) {
              Logger.getLogger(PlexDbAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(PlexDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return properties;
       
    }
    /**
     * this method will get attributes from database and set those attributes
     * to the properties within a plex
     * such us leases,apartment number...etc
     * @param plex
     * @return 
     */
    private List<Property> getPlexUnits(Property plex){
    
        String getPropertyQuery = "select * from property where plex_id = ?;";

        List<Property> plexProperties = new ArrayList<>();
        
        Connection con = DataBaseConnection.getConnection();
        
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(getPropertyQuery);
            ps.setInt(1,((Plex) plex).getPropertyId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                                                             
                Property pPlex = new Property();
                pPlex.setPropertyId(rs.getInt("property_id"));
                pPlex.setRentAmount(rs.getDouble("rent_amount"));
                pPlex.setIsVacant(Boolean.parseBoolean(rs.getString("vacant")));
                pPlex.setNumberRooms(rs.getInt("rooms"));
                pPlex.setApartmentNumber(rs.getInt("appartment_number"));
                pPlex.setPropertyType(rs.getString("property_type"));
                pPlex.setAddress(((Plex) plex).getAddress());
                pPlex.setPlexProperty(plex);
                List<Utility> pUtilities = (super.udba).getUtilities(pPlex);
                List<Lease> pleases = (super.ldba).getLeases(pPlex);
                pPlex.setLeases(pleases);
                pPlex.setUtilities(pUtilities);
                plexProperties.add(pPlex);  
            }
           } catch (SQLException ex) {
              Logger.getLogger(PlexDbAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(PlexDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return plexProperties;
    }
    /**
     * update plex in database
     * @param property
     * @return 
     */
    @Override
    public int updateProperty(Property property) {
        
        Connection con = DataBaseConnection.getConnection();
        String query = " update property set address = ?,property_type = ?,rent_amount = ?,"
                + "school_tax = ?,property_tax = ?,vacant = ?,insurance_id = ?,rooms =?,plex_unit_num=?  "
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
            ps.setInt(9, ((Plex)property).getNumberOfUnits());
            ps.setInt(10,property.getPropertyId());
            row = ps.executeUpdate();
            System.out.println("upadated-------> " +property);
        } catch (SQLException ex) {
              Logger.getLogger(PlexDbAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(PlexDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return row;
    }
    
    /**
     * ass plex units to the database
     * @param p
     * @return 
     */
        private int addPlexProperties(Property p){
            int row = 0;
            String query = " insert into property (property_id, rent_amount, vacant,rooms,appartment_number,plex_id,address,property_type)"
                    + "values (?, ?, ?, ?, ?,?,?,?)";

            List<Property> plexProperties = ((Plex)p).getPlexProperties();
            int number_of_units = plexProperties.size();

            for (int i= 0;i<number_of_units;i++){
                int property_id = super.getPriamryKey();
                Connection con = DataBaseConnection.getConnection();
                if(property_id > -1){
                    plexProperties.get(i).setPropertyId(property_id);
                    PreparedStatement ps;

                    try {  
                        ps = con.prepareStatement(query);
                        ps.setInt(1,property_id);
                        ps.setDouble(2, plexProperties.get(i).getRentAmount());
                        ps.setString(3,Boolean.toString(plexProperties.get(i).isIsVacant()));
                        ps.setInt(4,plexProperties.get(i).getNumberRooms());
                        ps.setInt(5,plexProperties.get(i).getApartmentNumber());
                        ps.setInt(6,((Plex)p).getPropertyId());
                        ps.setString(7,((Plex)p).getAddress());
                        ps.setString(8,plexProperties.get(i).getPropertyType());
                        int added = ps.executeUpdate();
                        row+=added;

                    } catch (SQLException ex) {
                          Logger.getLogger(PlexDbAccess.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    finally{
                        try {
                            con.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(PlexDbAccess.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                }
    
            }

            return row;
       
        }
        
   /**
    * update plex property from database
    * @param property
    * @return 
    */
    public int updatePlexUnit(Property property) {
        
        Connection con = DataBaseConnection.getConnection();
        String query = " update property set rent_amount = ?,vacant = ?,rooms =?,appartment_number=?  "
                + "where property_id = ?;";  
        int row = 0;
       
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);
            ps.setDouble(1,property.getRentAmount());
            ps.setString(2,Boolean.toString(property.isIsVacant()));
            ps.setInt(3,property.getNumberRooms());
            ps.setInt(4,property.getApartmentNumber());
            ps.setInt(5,property.getPropertyId());
            row = ps.executeUpdate();
            System.out.println("upadated-------> " +property);
        } catch (SQLException ex) {
              Logger.getLogger(PlexDbAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(PlexDbAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return row;
    }
    
    
}
