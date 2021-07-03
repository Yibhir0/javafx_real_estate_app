
package com.mycompany.realestate.model;

import java.io.File;
import java.time.LocalDate;
import static java.time.LocalDate.of;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests Lease methods
 * @author Yassine Ibhir
 */
public class LeaseTest {
    
    public LeaseTest() {
    }

    /**
     * Test of getLeaseId method, of class Lease.
     */
    @Test
    public void testGetLeaseId() {
        System.out.println("getLeaseId");
        Lease instance = new Lease();
        int expResult = 0;
        int result = instance.getLeaseId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTerm method, of class Lease.
     */
    @Test
    public void testGetTerm() {
        System.out.println("getTerm");
        Lease instance = new Lease();
        instance.setTerm(12);
        int expResult = 12;
        int result = instance.getTerm();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStartDate method, of class Lease.
     */
    @Test
    public void testGetStartDate() {
        System.out.println("getStartDate");
        Lease instance = new Lease();
        LocalDate date = of(2021,3,22);
        instance.setStartDate(date);
        LocalDate expResult = of(2021,3,22);
        LocalDate result = instance.getStartDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of isRenewal method, of class Lease.
     */
    @Test
    public void testIsRenewal() {
        System.out.println("isRenewal");
        Lease instance = new Lease();
        instance.setRenewal(true);
        boolean expResult = true;
        boolean result = instance.isRenewal();
        assertEquals(expResult, result);
    }

    /**
     * Test of getProperty method, of class Lease.
     */
    @Test
    public void testGetProperty() {
        System.out.println("getProperty");
        Lease instance = new Lease();
        Property expResult = new Property();
        instance.setProperty(expResult);
        Property result = instance.getProperty();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTenant method, of class Lease.
     */
    @Test
    public void testGetTenant() {
        System.out.println("getTenant");
        Lease instance = new Lease();
        Tenant expResult = new Tenant();
        instance.setTenant(expResult);
        Tenant result = instance.getTenant();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLeaseFileName method, of class Lease.
     */
    @Test
    public void testGetLeaseFileName() {
        System.out.println("getLeaseFileName");
        Lease instance = new Lease();
        instance.setLeaseFileName("brad lou.pdf");
        
        String expResult = "brad lou.pdf";
        String result = instance.getLeaseFileName();
        assertEquals(expResult, result);
    }

    
}
