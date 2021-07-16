package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employer extends AbstractEntity {

    @Size(min = 1, max = 100)
    private String location;

    // Part 3.1 Add Jobs field to employer
    @OneToMany
    @JoinColumn
    private List<Job> jobs = new ArrayList<Job>();

    //Added default constructor method
    public Employer(){

    }

//Generated getters and setters so that I can access data
// from other classes

    public Employer(String location, List<Job> jobs) {
        this.location = location;
        this.jobs = jobs;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
