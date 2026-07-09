package com.example.placementportal.controller;

import com.example.placementportal.entity.Application;
import com.example.placementportal.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    // Apply to job
    @PostMapping("/apply")
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
}