package com.example.placementportal.dto;

public class DashboardResponse {

    private long totalStudents;
    private long totalJobs;
    private long totalApplications;

    public DashboardResponse(long totalStudents,
                             long totalJobs,
                             long totalApplications) {
        this.totalStudents = totalStudents;
        this.totalJobs = totalJobs;
        this.totalApplications = totalApplications;
    }

    public long getTotalStudents() {
        return totalStudents;
    }

    public long getTotalJobs() {
        return totalJobs;
    }

    public long getTotalApplications() {
        return totalApplications;
    }
}