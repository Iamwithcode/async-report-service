package com.multi.async_report_service.service;

import com.multi.async_report_service.entity.Job;
import com.multi.async_report_service.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class JobService {


    private final JobRepository jobRepository;


    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }


    public Job createJob() {


        Job job = new Job();

        job.setStatus("STARTED");
        job.setTotalRecords(10000);
        job.setProcessedRecords(0);
        job.setCreatedTime(LocalDateTime.now());


        return jobRepository.save(job);
    }
}