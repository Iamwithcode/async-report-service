package com.multi.async_report_service.repository;

import com.multi.async_report_service.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JobRepository
        extends JpaRepository<Job, Long> {

}