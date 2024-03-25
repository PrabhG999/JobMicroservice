package com.example.jobms.Job.DTO;


public class CompanyDTO {
    private int companyId;
    private String name;
    private String description;

    public int getId() {
        return companyId;
    }

    public void setId(int companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
