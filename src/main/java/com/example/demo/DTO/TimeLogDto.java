package com.example.demo.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

    public class TimeLogDto {
        private LocalDate date;
        private LocalTime clockInTime;
        private LocalTime clockOutTime;
        private Duration hoursWorked;
        private boolean isLate;
        private String ClockInReason;
        private String ClockOutReason;

        private boolean IsClockedIn=false;
        private boolean LeftEarly;
    }



