package com.mycompany.realestate.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class represents Plex Object, it inherits from the base class property
 *
 * @author Yassine Ibhir
 */
public class Plex extends Property {

    // plex number of unit
    private int numberOfUnits;
    private List<Property> plexProperties = new ArrayList<>();

    public Plex() {
        super();
    }

    public void setNumberOfUnits(int plexUnits) {
        this.numberOfUnits = plexUnits;
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public List<Property> getPlexProperties() {
        return plexProperties;
    }

    public void setPlexProperties(List<Property> plexProperties) {
        this.plexProperties = plexProperties;
    }

    public void addPlexProperty(Property p) {
        this.plexProperties.add(p);
    }

    @Override
    public String toString() {
        return "Plex{" + super.toString() + '}';
    }

    /**
     * gets all vacant Plex units
     *
     * @return List
     */
    Collection<? extends Property> getPlexVacantProperties() {
        List<Property> vacantPlexProperties = new ArrayList<>();
        for (Property p : plexProperties) {
            if (p.isIsVacant()) {
                vacantPlexProperties.add(p);
            }
        }

        return vacantPlexProperties;
    }

}
