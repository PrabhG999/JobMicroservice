package com.example.jobms.Job.Impl;

import com.example.companyms.Company.Company; //external class
import com.example.jobms.Job.DTO.JobWithCompanyDTO;
import com.example.jobms.Job.Job;
import com.example.jobms.Job.JobRepository;
import com.example.jobms.Job.JobService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<JobWithCompanyDTO> findAll() {
        List<Job> jobs = jobRepository.findAll(); // find all the jobs from interface
        List<JobWithCompanyDTO> jobWithCompanyDTOS = new ArrayList<>();
        //map is a operation that is applied to stream, method takes function as an argument(to each element of stream)
        return jobs.stream().map(this::convertToDTO).
                collect(Collectors.toList());
        //make use of stream convert the list into a stream - elements that can be processed in a pipeline
    }

    private JobWithCompanyDTO convertToDTO(Job job) {

        JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
        jobWithCompanyDTO.setJob(job);
        RestTemplate restTemplate = new RestTemplate();
        Company company = restTemplate.getForObject("http://localhost:8081/companies/" + job.getCompanyId(),
                Company.class);
        // calling the company objected here to map it to the job - for every job we are getting the company id
        // - give company for this id and map it in DTO
        jobWithCompanyDTO.setCompany(company);


        return jobWithCompanyDTO;
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
    public boolean patchJob(int id, Job patchJob) {
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
            if (patchJob.getCompanyId() != 0) {
                existingJob.setCompanyId(patchJob.getCompanyId());
            }
            jobRepository.save(existingJob);
            return true;
        }

        return false;
    }
}
