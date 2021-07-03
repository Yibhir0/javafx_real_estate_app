
package com.mycompany.realestate.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test Repair methods
 * @author Yassine Ibhir
 */
public class RepairTest {
    
    public RepairTest() {
    }

 

    /**
     * Test of getRepairId method, of class Repair.
     */
    @Test
    public void testGetRepairId() {
        System.out.println("getRepairId");
        Repair instance = new Repair();
        int expResult = 0;
        int result = instance.getRepairId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getType method, of class Repair.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        Repair instance = new Repair();
        instance.setType("carpentry");
        String expResult = "carpentry";
        String result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCost method, of class Repair.
     */
    @Test
    public void testGetCost() {
        System.out.println("getCost");
        Repair instance = new Repair();
        double expResult = 0.0;
        double result = instance.getCost();
        assertEquals(expResult, result, 0.0);
    }
 
}
