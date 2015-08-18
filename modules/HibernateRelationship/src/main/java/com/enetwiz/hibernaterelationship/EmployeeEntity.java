package com.enetwiz.hibernaterelationship;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Entity
@Table(name = "employee")
public class EmployeeEntity implements Serializable {
    @Id
    @GeneratedValue
    private int id = 0;

    @Column(name="first_name", nullable = false, length = 50)
    private String firstName = null;
    
    @Column(name="last_name", nullable = false, length = 50)
    private String lastName = null;
    
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyEntity company = null;

    
    public int getId() {
        return id;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String pFirstName) {
        firstName = pFirstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String pLastName) {
        lastName = pLastName;
    }
    
    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity pCompany) {
        company = pCompany;
    }
    
}
