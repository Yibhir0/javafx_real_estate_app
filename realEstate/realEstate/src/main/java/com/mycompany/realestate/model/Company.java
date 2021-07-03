package com.mycompany.realestate.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a singleton class that represents the company. It has all properties
 * that we access through out the execution of the program.
 *
 * @author Yassine
 */
public class Company {

    private final static String companyName = "This is Your Home";
    private static Company INSTANCE = null;
    private final List<Property> properties = new ArrayList<Property>();
    
    private Company() {
    }

    /**
     * returns an instance of the class
     *
     * @return Company
     */
    public static Company getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new Company();
        }
        return INSTANCE;
    }

    /**
     * add property to the list
     * @param p
     */
    public void addProperty(Property p) {
        properties.add(p);
    }

    /**
     * removes property from a list
     * @param p
     */
    public void removeProperty(Property p) {
        properties.remove(p);
    }

    /**
     * returns all the properties
     * @return
     */
    public List<Property> getProperties() {
        return properties;
    }

    /**
     * gets properties for leases. This excludes Plex properties and includes
     * Plex units.
     * @return list of of all properties 
     */
    public List<Property> getAllProperties() {
        List<Property> allProperties = new ArrayList<>();
        properties.forEach(p -> {
            if (p instanceof Plex) {
                allProperties.addAll(((Plex) p).getPlexProperties());

            } else {
                allProperties.add(p);
            }
        });

        return allProperties;
    }

    /**
     * sets all properties
     * @param props
     */
    public void setProperties(List<Property> props) {
        properties.addAll(props);
    }

    /**
     * removes all properties
     */
    public void removeAllProperties() {
        properties.clear();
    }

    /**
     * set Insurance to null when insurance is removed
     * @param insuranceToRemove
     */
    public void removeInsurance(Insurance insuranceToRemove) {
        for (int i = 0; i < properties.size(); i++) {
            if (properties.get(i).getInsurance().equals(insuranceToRemove)) {
                properties.get(i).setInsurance(null);
            }
        }
    }

    /**
     * methods finds all the properties that have the old insurance and update
     * with the new one
     * @param insuranceToUpdate
     * @param insuranceUpdated
     */
    public void updateInsurance(Insurance insuranceToUpdate, Insurance insuranceUpdated) {
        for (int i = 0; i < properties.size(); i++) {
            if (properties.get(i).getInsurance().equals(insuranceToUpdate)) {
                properties.get(i).setInsurance(insuranceUpdated);
            }
        }
    }

    /**
     * removes all banks from the associated mortgages
     * @param bankToRemove
     */
    public void removeMortgageBank(Bank bankToRemove) {
        for (int i = 0; i < properties.size(); i++) {
            List<Mortgage> mortgages = properties.get(i).getMortgages();
            for (int j = 0; j < mortgages.size(); j++) {
                if (mortgages.get(j).getBank().equals(bankToRemove)) {
                    mortgages.get(j).setBank(null);
                }
            }
        }
    }

    /**
     * updates bank in all mortgages
     * @param bankToUpdate
     * @param bankUpdated
     */
    public void updateBank(Bank bankToUpdate, Bank bankUpdated) {
        for (int i = 0; i < properties.size(); i++) {
            List<Mortgage> mortgages = properties.get(i).getMortgages();
            for (int j = 0; j < mortgages.size(); j++) {
                if (mortgages.get(j).getBank().equals(bankToUpdate)) {
                    mortgages.get(j).setBank(bankUpdated);
                }
            }
        }
    }

    /**
     * update contractor in all repairs
     * @param contractorToUpdate
     * @param contractorUpdated
     */
    public void updateContractor(Contractor contractorToUpdate, Contractor contractorUpdated) {
        for (int i = 0; i < properties.size(); i++) {
            List<Repair> repairs = properties.get(i).getRepairs();
            for (int j = 0; j < repairs.size(); j++) {
                if (repairs.get(j).getContractor().equals(contractorToUpdate)) {
                    repairs.get(j).setContractor(contractorUpdated);

                }
            }
        }
    }

    /**
     * removes tenant from leases
     * @param tenantToRemove
     */
    public void removeTenant(Tenant tenantToRemove) {
        for (int i = 0; i < properties.size(); i++) {
            List<Lease> leases = properties.get(i).getLeases();
            for (int j = 0; j < leases.size(); j++) {
                if (leases.get(j).getTenant().equals(tenantToRemove)) {
                    leases.get(j).setTenant(null);

                }
            }
        }
    }

    /**
     * gets all current leases
     * @return current leases
     */
    public List<Lease> getAllCurrentLeases() {
        List<Lease> leases = new ArrayList<>();
        for (int i = 0; i < properties.size(); i++) {
            if (properties.get(i) instanceof Plex) {
                List<Lease> plexUnitsLeases = getPlexUnitsLeases(properties.get(i));
                leases.addAll(plexUnitsLeases);
            } else {
                Lease l = properties.get(i).getCurrentLease();
                if (l != null) {
                    leases.add(l);
                }
            }
        }

        return leases;
    }

    /**
     * gets all vacant properties
     * @return all vacant properties
     */
    public List<Property> getVacantProperties() {
        List<Property> vacantProperties = new ArrayList<>();
        properties.forEach(p -> {
            if (p instanceof Plex) {
                vacantProperties.addAll(((Plex) p).getPlexVacantProperties());
            } else {
                if (p.isIsVacant()) {
                    vacantProperties.add(p);
                }
            }
        });

        return vacantProperties;

    }

    /**
     * helper method to get all current leases for properties within a plex
     * @param plex
     * @return current leases of plex properties
     */
    private List<Lease> getPlexUnitsLeases(Property plex) {
        List<Lease> plexUnitsLeases = new ArrayList<>();
        List<Property> plexProperties = ((Plex) plex).getPlexProperties();
        plexProperties.stream().map(p -> p.getCurrentLease()).filter(l -> (l != null)).forEachOrdered(l -> {
            plexUnitsLeases.add(l);
        });
        return plexUnitsLeases;
    }
}
