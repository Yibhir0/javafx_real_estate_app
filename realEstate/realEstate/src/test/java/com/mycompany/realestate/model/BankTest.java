package com.mycompany.realestate.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * class tests all methods of the bank object
 *
 * @author Yassine Ibhir
 */
public class BankTest {

    public BankTest() {
    }

    /**
     * Test of toString method
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Bank instance = new Bank();
        String expResult = "Bank{bankId=1, name=td, intrestRate=2.2}";
        instance.setBankId(1);
        instance.setName("td");
        instance.setIntrestRate(2.2);
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getBankId method, of class Bank.
     */
    @Test
    public void testGetBankId() {
        System.out.println("getBankId");
        Bank instance = new Bank();
        int expResult = 0;
        int result = instance.getBankId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Bank.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Bank instance = new Bank();
        String expResult = "td";
        instance.setName("td");
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIntrestRate method, of class Bank.
     */
    @Test
    public void testGetIntrestRate() {
        System.out.println("getIntrestRate");
        Bank instance = new Bank();
        double expResult = 0.0;
        double result = instance.getIntrestRate();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getAddress method, of class Bank.
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        Bank instance = new Bank();
        String expResult = "23 rue";
        instance.setAddress("23 rue");
        String result = instance.getAddress();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPhone method, of class Bank.
     */
    @Test
    public void testGetPhone() {
        System.out.println("getPhone");
        Bank instance = new Bank();
        String expResult = "5142345342";
        instance.setPhone("5142345342");
        String result = instance.getPhone();
        assertEquals(expResult, result);
    }

    /**
     * Test equals method when true
     */
    @Test
    public void testToEquals() {
        System.out.println("equalsTrue");
        Bank instance1 = new Bank();
        instance1.setBankId(1);
        Bank instance2 = new Bank();
        instance2.setBankId(1);
        boolean expected = true;
        boolean result = instance1.equals(instance2);
        assertEquals(expected, result);
    }

    /**
     * Test equals method when false
     */
    @Test
    public void testToEqualsFalse() {
        System.out.println("equalsFalse");
        Bank instance1 = new Bank();
        instance1.setBankId(2);
        Bank instance2 = new Bank();
        instance2.setBankId(1);
        boolean expected = false;
        boolean result = instance1.equals(instance2);
        assertEquals(expected, result);
    }

}
