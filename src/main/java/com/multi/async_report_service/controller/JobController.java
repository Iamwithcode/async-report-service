package com.multi.async_report_service.controller;


import com.multi.async_report_service.entity.Job;
import com.multi.async_report_service.service.JobService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/jobs")
public class JobController {


    private final JobService jobService;


    public JobController(JobService jobService) {
        this.jobService = jobService;
    }



    @PostMapping("/start")
    public Job startJob() {

        return jobService.createJob();

    }

}