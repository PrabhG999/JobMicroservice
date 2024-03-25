package com.example.jobms.Job;
import com.example.jobms.Job.DTO.JobWithCompanyDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService; //object of challenge service creating a dependency injection using Spring IOC

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<JobWithCompanyDTO>> findAll() {
        List<JobWithCompanyDTO> jobs = jobService.findAll();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobWithCompanyDTO> findAll(@PathVariable int id) {
        JobWithCompanyDTO getJobById = jobService.getJobById(id);
        if (getJobById != null) {
            return new ResponseEntity<>(getJobById,
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping
    public ResponseEntity<String> addJob(@RequestBody Job job) {
        boolean isJobAdded = jobService.addJob(job);
        if (isJobAdded) {
            return new ResponseEntity<>("Job added Successfully",
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Job was not added Successfully",
                    HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable int id) {
        boolean isDeleted = jobService.deleteJob(id);
        if (isDeleted) {
            return new ResponseEntity<>("The Job is deleted Successfully",
                    HttpStatus.OK);
        }
        return new ResponseEntity<>("Job Was Not Deleted , Please check the ID exists or not",
                HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable int id, @RequestBody Job updatedJob) {
        boolean isUpdated = jobService.updateJob(id, updatedJob);
        if (isUpdated) {
            return new ResponseEntity<>("Job Updated Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Job was not updated successfully", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/{title}")
    public ResponseEntity<Job> fetchJob(@PathVariable int id, @PathVariable String title) {
        Job fetchJob = jobService.fetchJob(id, title);

        if (fetchJob != null) {
            return new ResponseEntity<>(fetchJob, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> patchJob(@PathVariable int id, @RequestBody Job patchJob) {
        boolean isPatched = jobService.patchJob(id, patchJob);
        if (isPatched) {
            return new ResponseEntity<>("Job Patched Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Job Was Not Updated", HttpStatus.BAD_REQUEST);
        }
    }
}
