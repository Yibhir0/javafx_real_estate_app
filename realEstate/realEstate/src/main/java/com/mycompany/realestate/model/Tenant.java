
package com.mycompany.realestate.model;

/**
 *
 * @author Yassine Ibhir
 */
public class Tenant {

    @Override
    public String toString() {
        return "Tenant{" + "tenantId=" + tenantId + ", fullName=" + fullName + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.tenantId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tenant other = (Tenant) obj;
        if (this.tenantId != other.tenantId) {
            return false;
        }
        return true;
    }
    private int tenantId;
    private String identity;
    private String fullName;
    private String email;
    private String phone;

    public int getTenantId() {
        return tenantId;
    }

    public String getIdentity() {
        return identity;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
