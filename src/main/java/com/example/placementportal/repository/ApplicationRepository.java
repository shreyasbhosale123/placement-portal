package com.example.placementportal.repository;

import com.example.placementportal.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    // Check if student already applied to a job
    boolean existsByStudentIdAndJobId(Long studentId, Long jobId);

    // Get all applications of a student
    List<Application> findByStudentId(Long studentId);
}