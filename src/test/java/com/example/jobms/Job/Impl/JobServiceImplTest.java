package com.example.jobms.Job.Impl;

import com.example.jobms.Job.Impl.JobServiceImpl;
import com.example.jobms.Job.Job;
import com.example.jobms.Job.JobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class JobServiceImplTest {

    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private JobServiceImpl jobService;

    private Job job1;
    private Job job2;

    @BeforeEach
    void setUp() {
        job1 = new Job(1, "Software Engineer", "Develop software", 60000, 80000, "New York");
        job2 = new Job(2, "Data Scientist", "Analyze data", 70000, 90000, "San Francisco");
    }

    @Test
    void testGetJobByIdWhenValidIdThenReturnJob() {
        when(jobRepository.findById(1)).thenReturn(Optional.of(job1));

        Job result = jobService.getJobById(1);

        assertThat(result).isEqualTo(job1);
    }

    @Test
    void testGetJobByIdWhenInvalidIdThenReturnNull() {
        when(jobRepository.findById(anyInt())).thenReturn(Optional.empty());

        Job result = jobService.getJobById(999);

        assertThat(result).isNull();
    }

    @Test
    void testAddJobWhenValidJobThenSaveJob() {
        when(jobRepository.save(job1)).thenReturn(job1);

        boolean result = jobService.addJob(job1);

        verify(jobRepository).save(job1);
        assertThat(result).isTrue();
    }

    @Test
    void testAddJobWhenNullJobThenDoNotSaveJob() {
        boolean result = jobService.addJob(null);

        verify(jobRepository, never()).save(any(Job.class));
        assertThat(result).isFalse();
    }

    @Test
    void testDeleteJobWhenValidIdThenDeleteJob() {
        when(jobRepository.findById(1)).thenReturn(Optional.of(job1));

        boolean result = jobService.deleteJob(1);

        verify(jobRepository).delete(job1);
        assertThat(result).isTrue();
    }

    @Test
    void testDeleteJobWhenInvalidIdThenDoNotDeleteJob() {
        when(jobRepository.findById(anyInt())).thenReturn(Optional.empty());

        boolean result = jobService.deleteJob(999);

        verify(jobRepository, never()).delete(any(Job.class));
        assertThat(result).isFalse();
    }

    @Test
    void testUpdateJobWhenValidIdAndUpdatedJobThenSaveJob() {
        when(jobRepository.findById(1)).thenReturn(Optional.of(job1));
        Job updatedJob = new Job(1, "Updated Title", "Updated Description", 65000, 85000, "New York");

        boolean result = jobService.updateJob(1, updatedJob);

        verify(jobRepository).save(job1);
        assertThat(result).isTrue();
    }

    @Test
    void testUpdateJobWhenInvalidIdThenDoNotSaveJob() {
        when(jobRepository.findById(anyInt())).thenReturn(Optional.empty());
        Job updatedJob = new Job(999, "Updated Title", "Updated Description", 65000, 85000, "New York");

        boolean result = jobService.updateJob(999, updatedJob);

        verify(jobRepository, never()).save(any(Job.class));
        assertThat(result).isFalse();
    }

    @Test
    void testFetchJobWhenValidIdAndTitleThenReturnJob() {
        when(jobRepository.findByIdAndTitle(1, "Software Engineer")).thenReturn(job1);

        Job result = jobService.fetchJob(1, "Software Engineer");

        assertThat(result).isEqualTo(job1);
    }

    @Test
    void testFetchJobWhenInvalidIdOrTitleThenReturnNull() {
        when(jobRepository.findByIdAndTitle(anyInt(), anyString())).thenReturn(null);

        Job result = jobService.fetchJob(999, "Invalid Title");

        assertThat(result).isNull();
    }

    @Test
    void testPatchJobWhenValidIdAndPatchJobThenSaveJob() {
        when(jobRepository.findById(1)).thenReturn(Optional.of(job1));
        Job patchJob = new Job();
        patchJob.setTitle("Patched Title");

        boolean result = jobService.patchJob(1, patchJob);

        verify(jobRepository).save(job1);
        assertThat(result).isTrue();
    }

    @Test
    void testPatchJobWhenInvalidIdThenDoNotSaveJob() {
        when(jobRepository.findById(anyInt())).thenReturn(Optional.empty());
        Job patchJob = new Job();
        patchJob.setTitle("Patched Title");

        boolean result = jobService.patchJob(999, patchJob);

        verify(jobRepository, never()).save(any(Job.class));
        assertThat(result).isFalse();
    }
}
