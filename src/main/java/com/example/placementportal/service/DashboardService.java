package com.example.placementportal.service;

import com.example.placementportal.dto.DashboardResponse;
import com.example.placementportal.repository.ApplicationRepository;
import com.example.placementportal.repository.JobRepository;
import com.example.placementportal.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    public DashboardResponse getDashboardStats() {

        long totalStudents = studentRepository.count();
        long totalJobs = jobRepository.count();
        long totalApplications = applicationRepository.count();

        return new DashboardResponse(
                totalStudents,
                totalJobs,
                totalApplications
        );
    }
}