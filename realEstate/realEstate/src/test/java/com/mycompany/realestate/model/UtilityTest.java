
package com.mycompany.realestate.model;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * tests utility methods
 * @author Yassine Ibhir
 */
public class UtilityTest {
    
    public UtilityTest() {
    }

   

    /**
     * Test of getUtilityId method, of class Utility.
     */
    @Test
    public void testGetUtilityId() {
        System.out.println("getUtilityId");
        Utility instance = new Utility();
        int expResult = 0;
        int result = instance.getUtilityId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAmount method, of class Utility.
     */
    @Test
    public void testGetAmount() {
        System.out.println("getAmount");
        Utility instance = new Utility();
        double expResult = 0.0;
        double result = instance.getAmount();
        assertEquals(expResult, result, 0.0);
    }
    /**
     * Test of getProperty method, of class Utility.
     */
    @Test
    public void testGetProperty() {
        System.out.println("getProperty");
        Utility instance = new Utility();
        Property expResult = null;
        Property result = instance.getProperty();
        assertEquals(expResult, result);
    }
  
}
