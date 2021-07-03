package com.mycompany.realestate.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * tests Company methods
 *
 * @author Yassine Ibhir
 */
public class CompanyTest {

    public CompanyTest() {
    }

    /**
     * Test of getInstance method, of class Company.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        Company expResult = Company.getInstance();
        Company result = Company.getInstance();
        assertEquals(expResult, result);
    }

    @Test
    public void testRemoveProperty() {
        System.out.println("removeProperty");
        Property p = new Condo();
        Company instance = Company.getInstance();
        instance.addProperty(p);
        instance.removeProperty(p);
        int result = instance.getProperties().size();
        int expected = 0;
        instance.removeAllProperties();// prepare for next Test
        assertEquals(expected, result);
    }

    /**
     * Test of addProperty method, of class Company.
     */
    @Test
    public void testAddProperty() {
        System.out.println("addProperty");
        Property p = new Plex();
        Company instance = Company.getInstance();
        instance.addProperty(p);
        int result = instance.getProperties().size();
        int expected = 1;
        instance.removeAllProperties();
        assertEquals(expected, result);
    }

    /**
     * Test of getProperties method, of class Company.
     */
    @Test
    public void testGetProperties() {
        System.out.println("getProperties");
        Company instance = Company.getInstance();
        List<Property> props = new ArrayList<>();
        instance.setProperties(props);
        List<Property> expResult = props;
        List<Property> result = instance.getProperties();
        instance.removeAllProperties();
        assertEquals(expResult, result);

    }

    /**
     * Test of setProperties method, of class Company.
     */
    @Test
    public void testSetProperties() {
        System.out.println("setProperties");
        Property p = new House();
        List<Property> props = new ArrayList<>();
        props.add(p);
        Company instance = Company.getInstance();
        instance.setProperties(props);
        int result = instance.getProperties().size();
        int expected = 1;
        instance.removeAllProperties();
        assertEquals(expected, result);
    }

}
