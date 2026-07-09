package com.example.placementportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.placementportal.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}