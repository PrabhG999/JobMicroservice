package com.example.jobms.Job.Impl;
import com.example.jobms.Job.Job;
import com.example.jobms.Job.JobRepository;
import com.example.jobms.Job.JobService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    //private final List<Job> jobs = new ArrayList<>(); //disable array list bcs we making JPA methods
    //define repo obj
    private final JobRepository jobRepository; //JPA a Bean managed by SPRING it will be autowired at RUNTIME

    @Autowired
    public JobServiceImpl(JobRepository jobRepository) { //Dependency Injection
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public Job getJobById(int id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean addJob(@RequestBody Job job) {
        if (job != null) {
            jobRepository.save(job);
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional
    public boolean deleteJob(int id) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();        //company is a part of Job entity as companyid with their getter setters so we can remove the logic here!
            jobRepository.delete(job);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateJob(int id, @RequestBody Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get(); //getting the Job object from the optional
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            job.setCompanyId(updatedJob.getCompanyId());

            jobRepository.save(job);
            return true;

        }
        return false;
    }

    @Override
    public Job fetchJob(int id, String title) {
        return jobRepository.findByIdAndTitle(id, title);
    }

    @Override
    public boolean patchJob(int id, @RequestBody Job patchJob) {
        Optional<Job> jobPatchOptional = jobRepository.findById(id);
        if (jobPatchOptional.isPresent()) {
            Job existingJob = jobPatchOptional.get();
            if (patchJob.getTitle() != null) {
                existingJob.setTitle(patchJob.getTitle());
            }
            if (patchJob.getDescription() != null) {
                existingJob.setDescription(patchJob.getDescription());
            }
            if (patchJob.getMinSalary() != 0.0) {
                existingJob.setMinSalary(patchJob.getMinSalary());
            }
            if (patchJob.getMaxSalary() != 0.0) {
                existingJob.setMaxSalary(patchJob.getMaxSalary());
            }
            if (patchJob.getLocation() != null) {
                existingJob.setLocation(patchJob.getLocation());
            }
            if(patchJob.getCompanyId()!= 0){
                existingJob.setCompanyId(patchJob.getCompanyId());
            }
            jobRepository.save(existingJob);
            return true;
        }

        return false;
    }
}
