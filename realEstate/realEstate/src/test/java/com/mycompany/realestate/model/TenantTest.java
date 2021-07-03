
package com.mycompany.realestate.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * tests Tenant methods
 * @author Yassine Ibhir
 */
public class TenantTest {
    
    public TenantTest() {
    }

    /**
     * Test of equals method, of class Tenant.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Tenant instance = new Tenant();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of getTenantId method, of class Tenant.
     */
    @Test
    public void testGetTenantId() {
        System.out.println("getTenantId");
        Tenant instance = new Tenant();
        int expResult = 0;
        int result = instance.getTenantId();
        assertEquals(expResult, result);
    }
    
}
