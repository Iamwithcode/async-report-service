package com.multi.async_report_service.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "jobs")
@Data
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String status;


    private Integer totalRecords;


    private Integer processedRecords;


    private LocalDateTime createdTime;
}