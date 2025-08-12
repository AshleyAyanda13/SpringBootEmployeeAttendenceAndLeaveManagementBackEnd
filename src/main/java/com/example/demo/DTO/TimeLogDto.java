package com.example.demo.DTO;


import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty
    private LocalDate date;
    @NotEmpty
    private LocalTime clockInTime;
    @NotEmpty
    private LocalTime clockOutTime;
    @NotEmpty
    private Duration hoursWorked;
    @NotEmpty
    private boolean isLate;
        private String ClockInReason;
        private String ClockOutReason;

        private boolean IsClockedIn=false;
        private boolean LeftEarly;
    }



