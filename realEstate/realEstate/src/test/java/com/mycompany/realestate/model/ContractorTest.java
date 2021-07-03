package com.mycompany.realestate.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests Contractor methods
 *
 * @author Yassine Ibhir
 */
public class ContractorTest {

    public ContractorTest() {
    }

    /**
     * Test of equals method, of class Contractor.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = new Contractor();
        ((Contractor) obj).setContractorId(2);
        Contractor instance = new Contractor();
        instance.setContractorId(2);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

    }
    
       /**
     * Test of equals method when false, of class Contractor.
     */
    @Test
    public void testEqualsFalse() {
        System.out.println("Notequals");
        Object obj = new Contractor();
        ((Contractor) obj).setContractorId(2);
        Contractor instance = new Contractor();
        instance.setContractorId(1);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

    }

    /**
     * Test of getContractorId method, of class Contractor.
     */
    @Test
    public void testGetContractorId() {
        System.out.println("getContractorId");
        Contractor instance = new Contractor();
        int expResult = 0;
        int result = instance.getContractorId();
        assertEquals(expResult, result);

    }

    /**
     * Test of getName method, of class Contractor.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Contractor instance = new Contractor();
        String expResult = "posix";
        instance.setName("posix");
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSpecialization method, of class Contractor.
     */
    @Test
    public void testGetSpecialization() {
        System.out.println("getSpecialization");
        Contractor instance = new Contractor();
        String expResult = "electricity";
        instance.setSpecialization("electricity");
        String result = instance.getSpecialization();
        assertEquals(expResult, result);

    }

    /**
     * Test of getAddress method, of class Contractor.
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        Contractor instance = new Contractor();
        String expResult = "23 rue mars";
        instance.setAddress("23 rue mars");
        String result = instance.getAddress();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPhone method, of class Contractor.
     */
    @Test
    public void testGetPhone() {
        System.out.println("getPhone");
        Contractor instance = new Contractor();
        String expResult = "5146795174";
        instance.setPhone("5146795174");
        String result = instance.getPhone();
        assertEquals(expResult, result);
    }
}
