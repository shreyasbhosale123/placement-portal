package com.example.placementportal.service;

import com.example.placementportal.entity.Application;
import com.example.placementportal.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    // Apply to Job (with duplicate check)
    public Application apply(Application application) {

        Long studentId = application.getStudent().getId();
        Long jobId = application.getJob().getId();

        // Check if already applied
        boolean exists = applicationRepository.existsByStudentIdAndJobId(studentId, jobId);

        if (exists) {
            throw new RuntimeException("You have already applied for this job!");
        }

        return applicationRepository.save(application);
    }

    // Get all applications
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    // Get applications of a specific student
    public List<Application> getApplicationsByStudent(Long studentId) {
        return applicationRepository.findByStudentId(studentId);
    }
}