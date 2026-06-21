package com.multi.async_report_service.service;


import com.multi.async_report_service.entity.Job;
import com.multi.async_report_service.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;



@Service
public class JobService {


    private final JobRepository jobRepository;

    private final ExecutorService executorService;



    public JobService(
            JobRepository jobRepository,
            ExecutorService executorService
    ) {

        this.jobRepository = jobRepository;
        this.executorService = executorService;

    }



    public Job createJob() {


        Job job = new Job();

        job.setStatus("STARTED");
        job.setTotalRecords(10000);
        job.setProcessedRecords(0);
        job.setCreatedTime(LocalDateTime.now());


        Job savedJob = jobRepository.save(job);



        executorService.submit(() -> {


            processJob(savedJob);


        });



        return savedJob;

    }



    private void processJob(Job job) {


        System.out.println(
                "Processing started by : "
                        + Thread.currentThread().getName()
        );


        try {

            Thread.sleep(10000);


        } catch (InterruptedException e) {

            e.printStackTrace();

        }


        System.out.println(
                "Processing completed by : "
                        + Thread.currentThread().getName()
        );

    }

}