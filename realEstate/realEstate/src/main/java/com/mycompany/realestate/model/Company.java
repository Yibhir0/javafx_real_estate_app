/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.realestate.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author T450
 */
public class Company {
  private final static String companyName = "This is Your Home";
  private  static Company INSTANCE = null;
  private List<Property> properties = new ArrayList<Property>();
  
  private Company(){}
  
  public static Company  getInstance() {
      
    if (INSTANCE == null){
            INSTANCE = new Company();
    }
        return INSTANCE;
  }
  
  public void addProperty(Property p){
      properties.add(p);
  }
  public void removeProperty(Property p){
      properties.remove(p);
  }
   public List<Property> getProperties(){
      return properties;
  }
   public void setProperties(List<Property> props){
       properties = props;
   }

 
}
