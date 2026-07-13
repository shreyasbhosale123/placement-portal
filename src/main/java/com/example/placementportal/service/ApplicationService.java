package com.example.placementportal.service;

import com.example.placementportal.entity.Application;
import com.example.placementportal.entity.Job;
import com.example.placementportal.entity.Student;
import com.example.placementportal.repository.ApplicationRepository;
import com.example.placementportal.repository.JobRepository;
import com.example.placementportal.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private JobRepository jobRepository;

    // Apply to Job (with entity resolution and duplicate check)
    public Application apply(Application application) {

        Long studentId = application.getStudent().getId();
        Long jobId = application.getJob().getId();

        // Resolve full Student entity
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // Resolve full Job entity
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        // Check if already applied
        boolean exists = applicationRepository.existsByStudentIdAndJobId(studentId, jobId);

        if (exists) {
            throw new RuntimeException("You have already applied for this job!");
        }

        // Attach resolved entities
        application.setStudent(student);
        application.setJob(job);

        // Set defaults
        application.setStatus("Applied");
        application.setAppliedDate(LocalDate.now());

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

    // Get applications for a specific job
    public List<Application> getApplicationsByJob(Long jobId) {
        return applicationRepository.findByJobId(jobId);
    }

    // Update application status
    public Application updateStatus(Long id, String status) {
        Application existing = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        existing.setStatus(status);

        return applicationRepository.save(existing);
    }

    // Delete application
    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }
}
