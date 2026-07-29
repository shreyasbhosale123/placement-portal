package com.example.placementportal.controller;

import com.example.placementportal.entity.Application;
import com.example.placementportal.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    // Apply to job
    @PostMapping("/applications")
    public Application applyJob(@RequestBody Application application) {
        return applicationService.apply(application);
    }

    // Get all applications
    @GetMapping("/applications")
    public List<Application> getApplications() {
        return applicationService.getAllApplications();
    }

    // Get applications of a specific student
    @GetMapping("/students/{studentId}/applications")
    public List<Application> getApplicationsByStudent(@PathVariable Long studentId) {
        return applicationService.getApplicationsByStudent(studentId);
    }

    // Get applications of a specific job
    @GetMapping("/jobs/{jobId}/applications")
    public List<Application> getApplicationsByJob(@PathVariable Long jobId) {
        return applicationService.getApplicationsByJob(jobId);
    }

    // Update application status
    @PutMapping("/applications/{id}")
    public Application updateStatus(@PathVariable Long id,
                                    @RequestParam String status) {
        return applicationService.updateStatus(id, status);
    }

    // Delete application
    @DeleteMapping("/applications/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplication(id);
        return ResponseEntity.noContent().build();
    }
}