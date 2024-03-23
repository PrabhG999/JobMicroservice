package com.example.jobms.Job;
import jakarta.persistence.*;

@Entity
//class supposed to be mapped to a table , the table name is same as class , make constructor and set primary key.
@Table(name = "Job")
public class Job {

    //info we need to store for the JOB + Contractor and then getter setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private double minSalary;
    private double maxSalary;
    private String location;
    private int companyId;

    public Job() {
        //need a default constructor for Entity in JPA
        //Why ? - Entity are Objects that represent the persistent data in DB - Requirement of JPA
        //JPA creates instances of Entity class During the retrieval of Data from DBase, populates the properties with retrieved data.
        // without Default Constructor , JPA wont be able to Instantiate Any Object of Entity class for Retrieval!
    }

    public Job(int id, String title, String description, double minSalary, double maxSalary, String location) {
        //constructor constructing Job Object in jobServiceImpl
        this.id = id; //primary key of entity
        this.title = title;
        this.description = description;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.location = location;
        if (maxSalary < 0 || minSalary < 0 || minSalary > maxSalary) {
            throw new IllegalArgumentException("Salary Values are invalid, Please check again");
        }

    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(double minSalary) {
        this.minSalary = minSalary;
    }

    public double getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(double maxSalary) {
        this.maxSalary = maxSalary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
