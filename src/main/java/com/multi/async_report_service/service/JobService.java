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


        int totalRecords = job.getTotalRecords();

        int chunkSize = totalRecords / 4;


        for(int i = 0; i < 4; i++) {


            int start = (i * chunkSize) + 1;

            int end = (i + 1) * chunkSize;



            executorService.submit(() -> {


                processChunk(start, end);


            });

        }

    }

    private void processChunk(int start, int end) {


        System.out.println(
                "Processing records "
                        + start
                        + " to "
                        + end
                        + " by "
                        + Thread.currentThread().getName()
        );


        try {

            Thread.sleep(5000);

        } catch (InterruptedException e) {

            e.printStackTrace();

        }


        System.out.println(
                "Completed records "
                        + start
                        + " to "
                        + end
                        + " by "
                        + Thread.currentThread().getName()
        );

    }

}