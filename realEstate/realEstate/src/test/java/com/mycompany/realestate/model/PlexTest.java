
package com.mycompany.realestate.model;

import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * test plex methods
 * @author Yassine Ibhir
 */
public class PlexTest {
    
    public PlexTest() {
    }

   

    /**
     * Test of getNumberOfUnits method, of class Plex.
     */
    @Test
    public void testGetNumberOfUnits() {
        System.out.println("getNumberOfUnits");
        Plex instance = new Plex();
        int expResult = 0;
        int result = instance.getNumberOfUnits();
        assertEquals(expResult, result);
    }

  
    /**
     * Test of addPlexProperty method, of class Plex.
     */
    @Test
    public void testAddPlexProperty() {
        System.out.println("addPlexProperty");
        Property p = new Property();
        Plex instance = new Plex();
        instance.addPlexProperty(p);
        Collection<? extends Property> props = instance.getPlexProperties();
        boolean result = props.contains(p);
        boolean expResult =true;
        assertEquals(expResult, result);
    }

    /**
     * Test of getPlexVacantProperties method, of class Plex.
     */
    @Test
    public void testGetPlexVacantProperties() {
        System.out.println("getPlexVacantProperties");
        Plex instance = new Plex();
        Property p1 = new Property();
        Property p2 = new Property();
        p2.setPropertyId(2);
        instance.addPlexProperty(p1);
        instance.addPlexProperty(p2);
        p1.setIsVacant(true);
        p2.setIsVacant(false);
        
        Collection<? extends Property> vacants = instance.getPlexVacantProperties();
        boolean result = vacants.contains(p2);
        boolean expResult = false;
        assertEquals(expResult, result);
    }
    
}
