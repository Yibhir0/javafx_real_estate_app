package com.mycompany.realestate.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * tests insurance methods
 *
 * @author Yassine Ibhir
 */
public class InsuranceTest {

    public InsuranceTest() {
    }

    /**
     * Test of getInsuranceId method, of class Insurance.
     */
    @Test
    public void testGetInsuranceId() {
        System.out.println("getInsuranceId");
        Insurance instance = new Insurance();
        instance.setInsuranceId(20);
        int expResult = 20;
        int result = instance.getInsuranceId();
        assertEquals(expResult, result);

    }

    /**
     * Test of getName method, of class Insurance.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Insurance instance = new Insurance();
        String expResult = "phoenix";
        instance.setName(expResult);
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAddress method, of class Insurance.
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        Insurance instance = new Insurance();
        String expResult = "1234 rue la fontaine";
        instance.setAddress("1234 rue la fontaine");
        String result = instance.getAddress();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPhone method, of class Insurance.
     */
    @Test
    public void testGetPhone() {
        System.out.println("getPhone");
        Insurance instance = new Insurance();
        String expResult = "phone";
        instance.setPhone("phone");
        String result = instance.getPhone();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method when true, of class Insurance.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = new Insurance();
        Insurance instance = new Insurance();
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

    }

    /**
     * Test of equals method when FALSE, of class Insurance.
     */
    @Test
    public void testEqualsFalse() {
        System.out.println("Notequals");
        Object obj = new Insurance();
        Insurance instance = new Insurance();
        instance.setInsuranceId(1);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

    }

}
