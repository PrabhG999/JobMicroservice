package com.example.jobms.Job.DTO;

import com.example.companyms.Company.Company;
import com.example.jobms.Job.Job;

public class JobWithCompanyDTO {
    private Job job;
    private Company company;       //created obj of JOB  class and Company Class , which will be shown to the user.

    public JobWithCompanyDTO() {

    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
