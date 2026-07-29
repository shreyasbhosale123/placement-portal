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

    // Apply to Job
    public Application apply(Application application) {

        Long studentId = application.getStudent().getId();
        Long jobId = application.getJob().getId();

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        boolean exists = applicationRepository.existsByStudentIdAndJobId(studentId, jobId);

        if (exists) {
            throw new RuntimeException("You have already applied for this job!");
        }

        application.setStudent(student);
        application.setJob(job);

        application.setStatus("Applied");
        application.setAppliedDate(LocalDate.now());

        return applicationRepository.save(application);
    }

    // Get all applications
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    // Get applications by student
    public List<Application> getApplicationsByStudent(Long studentId) {
        return applicationRepository.findByStudentId(studentId);
    }

    // Get applications by job
    public List<Application> getApplicationsByJob(Long jobId) {
        return applicationRepository.findByJobId(jobId);
    }

    // Update status
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