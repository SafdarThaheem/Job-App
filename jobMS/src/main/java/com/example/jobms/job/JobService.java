package com.example.jobms.job;

import java.util.List;

public interface JobService {

    List<Job> findJobs();
    void createJob(Job job);
    Job getJobById(Long id);
    boolean deleteJobById(Long id);
    boolean updateJob(Long id, Job updatedJob);
}
