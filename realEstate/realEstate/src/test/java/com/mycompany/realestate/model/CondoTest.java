
package com.mycompany.realestate.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * tests Condo methods
 * @author Yassine Ibhir
 */
public class CondoTest {
    
    public CondoTest() {
    }

    /**
     * Test of getCondoFees method, of class Condo.
     */
    @Test
    public void testGetCondoFees() {
        System.out.println("getCondoFees");
        Condo instance = new Condo();
        double expResult = 110.9;
        instance.setCondoFees(110.9);
        double result = instance.getCondoFees();
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of setCondoFees method, of class Condo.
     */
    @Test
    public void testSetCondoFees() {
       System.out.println("getCondoFees");
        Condo instance = new Condo();
        double expResult = 19.45;
        instance.setCondoFees(19.45);
        double result = instance.getCondoFees();
        assertEquals(expResult, result, 0.0);
    }

}
