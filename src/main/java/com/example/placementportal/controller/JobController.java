package com.example.placementportal.controller;

import com.example.placementportal.entity.Job;
import com.example.placementportal.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    // Create Job
    @PostMapping
    public Job createJob(@RequestBody Job job) {
        return jobService.saveJob(job);
    }

    // Get All Jobs
    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    // Search by Company
    @GetMapping("/company/{company}")
    public List<Job> getJobsByCompany(@PathVariable String company) {
        return jobService.getJobsByCompany(company);
    }

    // Search by Title
    @GetMapping("/search")
    public List<Job> searchJobsByTitle(@RequestParam String title) {
        return jobService.searchJobsByTitle(title);
    }

    // Update Job
    @PutMapping("/{id}")
    public Job updateJob(@PathVariable Long id, @RequestBody Job job) {
        return jobService.updateJob(id, job);
    }

    // Delete Job
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }
}
