package com.mycompany.realestate.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * tests House methods
 *
 * @author Yassine Ibhir
 */
public class HouseTest {

    public HouseTest() {
    }

    /**
     * Test of toString method, of class House.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        House instance = new House();
        String expResult = "House{ Id=1, Address=23 rue}}";
        instance.setAddress("23 rue");
        instance.setPropertyId(1);
        String result = instance.toString();
        assertEquals(expResult, result);

    }

}
