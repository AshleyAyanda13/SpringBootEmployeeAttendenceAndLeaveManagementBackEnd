package com.example.demo.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
    public class LeaveRequest {
        @Id
        @GeneratedValue
        private Long id;

        @ManyToOne
        private User user; // who made the request

        private LocalDate startDate;
        private LocalDate endDate;
        private String reason;

        @Enumerated(EnumType.STRING)
        private LeaveStatus status = LeaveStatus.PENDING;

        private LocalDateTime requestDate = LocalDateTime.now();


        private String responsereason;
    @Version
    private Integer version;


}
