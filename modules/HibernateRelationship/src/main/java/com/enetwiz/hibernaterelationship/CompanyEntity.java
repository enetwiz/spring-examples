package com.enetwiz.hibernaterelationship;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Mariusz G <mg@netwiz.pl>
 */
@Entity
@Table(name = "company")
public class CompanyEntity implements Serializable {
    
    @Id
    @GeneratedValue
    private int id = 0;
    
    @Column(nullable = false, length = 50)
    private String name = null;
    
    @OneToMany(mappedBy = "company")
    private Set<EmployeeEntity> employees = new HashSet<>(0);
    
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String pName) {
        name = pName;
    }
    
    public Set<EmployeeEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<EmployeeEntity> pEmployees) {
        employees = pEmployees;
    }
    
}