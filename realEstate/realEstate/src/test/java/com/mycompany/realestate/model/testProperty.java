/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.realestate.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author T450
 */
public class testProperty {
    
    /**
     * Test of setPropertyId and getPropertyId methods, of class Property.
     */
    @Test
    public void testGetSetPropertyId() {
        int propertyId = 1;
        Property instance = new Property();
        instance.setPropertyId(propertyId);
        int expected = 1;
        int result = instance.getPropertyId();
        assertEquals(expected,result);
       
    }

    /**
     * Test of setAddress and getAddress methods, of class Property.
     */
    @Test
    public void testGetSetAddress() {
        String propertyId = "5430 cote saint luc";
        Property instance = new Property();
        instance.setAddress(propertyId);
        String expected = "5430 cote saint luc";
        String result = instance.getAddress();
        assertEquals(expected,result);
    }

    /**
     * Test of setType and getType methods, of class Property.
     */
    @Test
    public void testGetSetType() {
        String type = "duplex";
        Property instance = new Property();
        instance.setPropertyType(type);
        String expected = "duplex";
        String result = instance.getPropertyType();
        assertEquals(expected,result);
    }
    
     /**
     * Test of setunitNum and getunitNum methods, of class Property.
     */
    @Test
    public void testGetSetUnitNum() {
        int unitNum = 6 ;
        Property instance = new Property();
        instance.setUnitNum(unitNum);
        int expected = 6;
        int result = instance.getUnitNum();
        assertEquals(expected,result);
    }

    /**
     * Test of getSchoolTax and set schoolTax method, of class Property.
     */
    @Test
    public void testGetSetSchoolTax() {
        Property instance = new Property();
        double expResult = 1000.0;
        instance.setSchoolTax(expResult);
        double result = instance.getSchoolTax();
        assertEquals(expResult, result, 0.0);
       
    }

    /**
     * Test of getPropertyTax and setPropertyTax method, of class Property.
     */
    @Test
    public void testGetSetPropertyTax() {
        Property instance = new Property();
        double expResult = 5050.0;
        instance.setPropertyTax(expResult);
        double result = instance.getPropertyTax();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getRentAmount and set rentAmount methods, of class Property.
     */
    @Test
    public void testGetSetRentAmount() {
        Property instance = new Property();
        double expResult = 1000.0;
        instance.setRentAmount(expResult);
        double result = instance.getRentAmount();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of isIsVacant and setIsVacant methods, of class Property.
     */
    @Test
    public void testIsIsVacant() {
        Property instance = new Property();
        boolean expResult = true;
        instance.setIsVacant(expResult);
        boolean result = instance.isIsVacant();
        assertEquals(expResult, result);
    }

    /**
     * Test of getInsurance method, of class Property.
     */
    @Test
    public void testGetSetInsurance() {
        Property instance = new Property();
        Insurance expResult = null;
        Insurance result = instance.getInsurance();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMortgages and setMortgages method, of class Property.
     */
    @Test
    public void testGetSetMortgages() {
        Property instance = new Property();
        List<Mortgage> expResult = new ArrayList<>();
        instance.setMortgages(expResult);
        List<Mortgage> result = instance.getMortgages();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLeases and setLeases method, of class Property.
     */
    @Test
    public void testGetSetLeases() {
        Property instance = new Property();
        List<Lease> expResult = new ArrayList<>();
        instance.setLeases(expResult);
        List<Lease> result = instance.getLeases();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUtilities and setUtilities method, of class Property.
     */
    @Test
    public void testGetSetUtilities() {
        Property instance = new Property();
        List<Utility> expResult = new ArrayList<>();
        instance.setUtilities(expResult);
        List<Utility> result = instance.getUtilities();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRepairs and setRepairs method, of class Property.
     */
    @Test
    public void testGetSetRepairs() {
        Property instance = new Property();
        List<Repair> expResult = new ArrayList<>();
        instance.setRepairs(expResult);
        List<Repair> result = instance.getRepairs();
        assertEquals(expResult, result);
    }
    /**
     * Test of toString method, of class Property.
     */
    @Test
    public void testToString() {
        Property instance = new Property();
        String expResult = "Property{Id=1, Address=2 rue laval}";
        instance.setAddress("2 rue laval");
        instance.setPropertyId(1);
        String result = instance.toString();
        assertEquals(expResult, result);
        
    }
    
    /**
     * 
     * test addMortgage 
     */
    @Test
    public void testAddMortgage(){
        Property instance = new Property();
        Mortgage m = new Mortgage();
        instance.addMortgage(m);
        int expResult = 1;
        int result = instance.getMortgages().size();
        assertEquals(expResult, result);
    }
    
     /**
     * 
     * test addMortgage 
     */
    @Test
    public void testAddLease(){
        Property instance = new Property();
        Mortgage m = new Mortgage();
        instance.addMortgage(m);
        int expResult = 1;
        int result = instance.getMortgages().size();
        assertEquals(expResult, result);
    }
    
    /**
     * 
     * test addRepair
     */
    @Test
    public void testAddRepair(){
        Property instance = new Property();
        Repair r = new Repair();
        instance.addRepair(r);
        int expResult = 1;
        int result = instance.getRepairs().size();
        assertEquals(expResult, result);
    }
    
     /**
     * 
     * test addUtility
     */
    @Test
    public void testAddUtility(){
        Property instance = new Property();
        Utility u = new Utility();
        instance.addUtility(u);
        int expResult = 1;
        int result = instance.getUtilities().size();
        assertEquals(expResult, result);
    }
    
    /**
     * 
     * test removeMortgage 
     */
    @Test
    public void testRemoveMortgage(){
        Property instance = new Property();
        Mortgage m1 = new Mortgage();
        Mortgage m2 = new Mortgage();
        instance.addMortgage(m1);
        instance.addMortgage(m2);
        instance.removeMortgage(m1);
        boolean expResult = false;
        boolean result = instance.getMortgages().contains(m1);
        assertEquals(expResult, result);
    }
    
    /**
     * 
     * test removeRepair 
     */
    @Test
    public void testRemoveRepair(){
        Property instance = new Property();
        Repair r1 = new Repair();
        Repair r2 = new Repair();
        instance.addRepair(r1);
        instance.addRepair(r2);
        instance.removeRepair(r2);
        boolean expResult = false;
        boolean result = instance.getRepairs().contains(r2);
        assertEquals(expResult, result);
    }
    
       /**
     * 
     * test removeLease 
     */
    @Test
    public void testRemoveLease(){
        Property instance = new Property();
        Lease l1 = new Lease();
        Lease l2 = new Lease();
        instance.addLease(l1);
        instance.addLease(l2);
        instance.removeLease(l2);
        boolean expResult = false;
        boolean result = instance.getLeases().contains(l2);
        assertEquals(expResult, result);
    }
    
         /**
     * 
     * test removeUtility
     */
    @Test
    public void testRemoveUtility(){
        Property instance = new Property();
        Utility u1 = new Utility();
        Utility u2 = new Utility();
        instance.addUtility(u1);
        instance.addUtility(u2);
        instance.removeUtility(u2);
        boolean expResult = false;
        boolean result = instance.getUtilities().contains(u2);
        assertEquals(expResult, result);
    }
    

    
}
