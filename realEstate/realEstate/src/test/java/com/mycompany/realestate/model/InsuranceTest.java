/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.realestate.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author T450
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
     * Test of toString method, of class Insurance.
     */
//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        Insurance instance = new Insurance();
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//      
//    }
//
//    /**
//     * Test of equals method, of class Insurance.
//     */
//    @Test
//    public void testEquals() {
//        System.out.println("equals");
//        Object obj = null;
//        Insurance instance = new Insurance();
//        boolean expResult = false;
//        boolean result = instance.equals(obj);
//        assertEquals(expResult, result);
//       
//    }
    
}
