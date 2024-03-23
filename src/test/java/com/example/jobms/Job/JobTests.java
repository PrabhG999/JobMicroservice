package com.example.jobms.Job;

import com.example.jobms.Job.Job;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JobTest {

    @Test
    void testJobConstructorWithValidSalaries() {
        assertDoesNotThrow(() -> new Job(1, "Developer", "Writes code", 50000, 100000, "Remote"));
    }

    @Test
    void testJobConstructorWithMinSalaryGreaterThanMaxSalary() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Job(1, "Developer", "Writes code", 100000, 50000, "Remote"));
        assertEquals("Salary Values are invalid, Please check again", exception.getMessage());
    }

    @Test
    void testJobConstructorWithNegativeMinSalary() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Job(1, "Developer", "Writes code", -50000, 100000, "Remote"));
        assertEquals("Salary Values are invalid, Please check again", exception.getMessage());
    }

    @Test
    void testJobConstructorWithNegativeMaxSalary() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Job(1, "Developer", "Writes code", 50000, -100000, "Remote"));
        assertEquals("Salary Values are invalid, Please check again", exception.getMessage());
    }

    @Test
    void testSetAndGetId() {
        Job job = new Job();
        job.setId(1);
        assertEquals(1, job.getId());
    }

    @Test
    void testSetAndGetTitle() {
        Job job = new Job();
        job.setTitle("Developer");
        assertEquals("Developer", job.getTitle());
    }

    @Test
    void testSetAndGetDescription() {
        Job job = new Job();
        job.setDescription("Writes code");
        assertEquals("Writes code", job.getDescription());
    }

    @Test
    void testSetAndGetMinSalary() {
        Job job = new Job();
        job.setMinSalary(50000);
        assertEquals(50000, job.getMinSalary());
    }

    @Test
    void testSetAndGetMaxSalary() {
        Job job = new Job();
        job.setMaxSalary(100000);
        assertEquals(100000, job.getMaxSalary());
    }

    @Test
    void testSetAndGetLocation() {
        Job job = new Job();
        job.setLocation("Remote");
        assertEquals("Remote", job.getLocation());
    }

    @Test
    void testSetAndGetCompanyId() {
        Job job = new Job();
        job.setCompanyId(10);
        assertEquals(10, job.getCompanyId());
    }
}