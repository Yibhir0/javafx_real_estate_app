
package com.mycompany.realestate.model;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests Rent methods
 * @author Yassine Ibhir
 */
public class RentTest {
    
    public RentTest() {
    }

    /**
     * Test of getRentId method, of class Rent.
     */
    @Test
    public void testGetRentId() {
        System.out.println("getRentId");
        Rent instance = new Rent();
        int expResult = 0;
        int result = instance.getRentId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPaymentMethod method, of class Rent.
     */
    @Test
    public void testGetPaymentMethod() {
        System.out.println("getPaymentMethod");
        Rent instance = new Rent();
        instance.setPaymentMethod("Cash");
        String expResult = "Cash";
        String result = instance.getPaymentMethod();
        assertEquals(expResult, result);
    }

   
    /**
     * Test of isFullyPaid method, of class Rent.
     */
    @Test
    public void testIsFullyPaid() {
        System.out.println("isFullyPaid");
        Rent instance = new Rent();
        boolean expResult = false;
        boolean result = instance.isFullyPaid();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLease method, of class Rent.
     */
    @Test
    public void testGetLease() {
        System.out.println("getLease");
        Rent instance = new Rent();
        Lease expResult = null;
        Lease result = instance.getLease();
        assertEquals(expResult, result);
    }

    
    /**
     * Test of updateRent method, of class Rent.
     */
    @Test
    public void testUpdateRent() {
        System.out.println("updateRent");
        Rent rentUpdated = new Rent();
        rentUpdated.setAmountPaid(200.0);
        Rent instance = new Rent();
        instance.updateRent(rentUpdated);
        double expResult = 200;
        double result = instance.getAmountPaid();
        assertEquals(expResult, result);
    }
    
}
