package com.yychainsaw.pojo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Plan {
    private Long planId;
    private User user;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status; // ACTIVE, COMPLETED
}
