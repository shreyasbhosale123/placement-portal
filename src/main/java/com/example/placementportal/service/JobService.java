package com.example.placementportal.service;

import com.example.placementportal.entity.Job;
import com.example.placementportal.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    // Save Job
    public Job saveJob(Job job) {
        return jobRepository.save(job);
    }

    // Get All Jobs
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    // Search by Company
    public List<Job> getJobsByCompany(String company) {
        return jobRepository.findByCompany(company);
    }

    // Search by Title
    public List<Job> searchJobsByTitle(String title) {
        return jobRepository.findByTitleContainingIgnoreCase(title);
    }

    // Update Job
    public Job updateJob(Long id, Job job) {
        Job existing = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        existing.setTitle(job.getTitle());
        existing.setCompany(job.getCompany());
        existing.setSalary(job.getSalary());

        return jobRepository.save(existing);
    }

    // Delete Job
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}
