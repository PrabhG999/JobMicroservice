package com.example.jobms.Job;

import com.example.jobms.Job.DTO.JobWithCompanyDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface JobService {

    List<JobWithCompanyDTO> findAll();

    JobWithCompanyDTO getJobById(int id);

    boolean addJob(@RequestBody Job job);

    boolean deleteJob(int id);

    boolean updateJob(int id, @RequestBody Job updatedJob);

    Job fetchJob(int id, String title);

    boolean patchJob(int id, @RequestBody Job patchJob);
}

