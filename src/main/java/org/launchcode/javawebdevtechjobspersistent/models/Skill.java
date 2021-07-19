package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Skill extends AbstractEntity {

 // Added a field for a longer description of the skill
 //Added getters and setters

    private String description;

    //Annotations
    //Added jobs field
    @ManyToMany(mappedBy = "skills")
    private List<Job> jobs;

    //Added default constructor

    public Skill(){

    }


    public Skill(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

}