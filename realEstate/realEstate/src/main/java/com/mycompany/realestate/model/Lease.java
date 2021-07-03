
package com.mycompany.realestate.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents Lease object
 * @author Yassine Ibhir
 */
public class Lease {

    @Override
    public String toString() {
        return "Lease{" + "leaseId=" + leaseId + ", tenant=" + tenant + '}';
    }
   private int leaseId;
   private int term;
   private LocalDate startDate;
   private LocalDate endDate;
   private boolean renewal;
   private Property property;
   private Tenant tenant;
   private String leaseFileName;
   private File pdfFile;
   private List<Rent> rents = new ArrayList<>();
   
   // the directory to store the uploaded leases
   private final static String outDir = "H:\\leases\\";
    

    public int getLeaseId() {
        return leaseId;
    }

    public int getTerm() {
        return term;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public boolean isRenewal() {
        return renewal;
    }

    public Property getProperty() {
        return property;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setLeaseId(int leaseId) {
        this.leaseId = leaseId;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setRenewal(boolean renewal) {
        this.renewal = renewal;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public String getLeaseFileName() {
        return leaseFileName;
    }

    public void setLeaseFileName(String leaseFileName) {
        this.leaseFileName = leaseFileName;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    
    public void setEndDate(){ 
        this.endDate = this.startDate.plusMonths(this.term);
    }

    public List<Rent> getRents() {
        return rents;
    }

    public void setRents(List<Rent> rents) {
        this.rents = rents;
    }
    
    public void addRent(Rent rent){
        
        this.rents.add(rent);
        
    }
    
     public void removeRent(Rent rent){
        
        this.rents.remove(rent);
        
    }
    
   /**
    * Update old lease with new lease
    * @param leaseNewUpdate 
    */
    public void updateLease(Lease leaseNewUpdate) {
      
        this.leaseId = leaseNewUpdate.leaseId;
        this.term = leaseNewUpdate.term;
        this.endDate = leaseNewUpdate.endDate;
        this.startDate =leaseNewUpdate.startDate;
        this.renewal = leaseNewUpdate.renewal;
        this.tenant = leaseNewUpdate.tenant;
        this.property= leaseNewUpdate.property;
        this.leaseFileName = leaseNewUpdate.leaseFileName;
        this.rents = leaseNewUpdate.rents;
        this.pdfFile = leaseNewUpdate.pdfFile;
    }
    
    
    public File getPdfFile() {
        return pdfFile;
    }

    public void setPdfFile(File pdfFile) {
        this.pdfFile = pdfFile;
    }
    /**
     * moving file to lease directory
     * @throws IOException 
     */
    public void copyToLeaseDirectory() throws IOException{
        Path source = this.pdfFile.toPath();
        File newPdf = new File(outDir + this.getLeaseFileName());
        Path des = newPdf.toPath() ;
        Files.copy(source,des,StandardCopyOption.REPLACE_EXISTING);

    }
    
    
    
   
}
