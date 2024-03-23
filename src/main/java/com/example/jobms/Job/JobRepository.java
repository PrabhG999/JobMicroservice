package com.example.jobms.Job;

import org.springframework.data.jpa.repository.JpaRepository;


public interface JobRepository extends JpaRepository<Job, Integer> {
    /*or CrudRepository  //JPARepo better than crud ,mention the class and uniqueID data type which is Integer in out case.
    spring data jpa repo
    will make an IMPL file for the interface*/
    Job findByIdAndTitle(int id, String title);
}
