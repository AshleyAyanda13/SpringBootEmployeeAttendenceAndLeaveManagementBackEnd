package com.example.demo.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TimeLog {


        @Id
        @GeneratedValue
        private Long id;

        @ManyToOne
        private User user;

        private LocalDate date;
        private LocalTime clockInTime;
        private LocalTime clockOutTime;

        private Boolean isLate;
        private Duration hoursWorked;
        private String ClockInReason;
        private String ClockOutReason;
        private boolean isClockedIn=false;
        private Boolean LeftEarly=false;
}
