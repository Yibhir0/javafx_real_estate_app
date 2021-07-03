package com.mycompany.realestate.model;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test Mortgage methods
 *
 * @author Yassine Ibhir
 */
public class MortgageTest {

    public MortgageTest() {
    }

    /**
     * Test of getMortgageId method, of class Mortgage.
     */
    @Test
    public void testGetMortgageId() {
        System.out.println("getMortgageId");
        Mortgage instance = new Mortgage();
        int expResult = 0;
        int result = instance.getMortgageId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTerm method, of class Mortgage.
     */
    @Test
    public void testGetTerm() {
        System.out.println("getTerm");
        Mortgage instance = new Mortgage();
        instance.setTerm(5);
        int expResult = 5;
        int result = instance.getTerm();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAmount method, of class Mortgage.
     */
    @Test
    public void testGetAmount() {
        System.out.println("getAmount");
        Mortgage instance = new Mortgage();
        double expResult = 0.0;
        double result = instance.getAmount();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getStartDate method, of class Mortgage.
     */
    @Test
    public void testGetStartDate() {
        System.out.println("getStartDate");
        Mortgage instance = new Mortgage();
        LocalDate expResult = null;
        LocalDate result = instance.getStartDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDownPayment method, of class Mortgage.
     */
    @Test
    public void testGetDownPayment() {
        System.out.println("getDownPayment");
        Mortgage instance = new Mortgage();
        double expResult = 0.0;
        double result = instance.getDownPayment();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of isFullyPaid method, of class Mortgage.
     */
    @Test
    public void testIsFullyPaid() {
        System.out.println("isFullyPaid");
        Mortgage instance = new Mortgage();
        instance.setFullyPaid(true);
        boolean expResult = true;
        boolean result = instance.isFullyPaid();
        assertEquals(expResult, result);
    }

    /**
     * Test of getProperty method, of class Mortgage.
     */
    @Test
    public void testGetProperty() {
        System.out.println("getProperty");
        Mortgage instance = new Mortgage();
        Property expResult = null;
        Property result = instance.getProperty();
        assertEquals(expResult, result);
    }

    /**
     * Test of getBank method, of class Mortgage.
     */
    @Test
    public void testGetBank() {
        System.out.println("getBank");
        Mortgage instance = new Mortgage();
        Bank expResult = new Bank();
        instance.setBank(expResult);
        Bank result = instance.getBank();
        assertEquals(expResult, result);
    }

    /**
     * Test of updateMortgae method, of class Mortgage.
     */
    @Test
    public void testUpdateMortgae() {
        System.out.println("updateMortgae");
        Mortgage mortgageNewUpdate = new Mortgage();
        mortgageNewUpdate.setMortgageId(1);
        Mortgage instance = new Mortgage();
        instance.setMortgageId(2);
        instance.updateMortgae(mortgageNewUpdate);
        int expected = 1;
        int result = instance.getMortgageId();
        assertEquals(expected, result);
    }

}
