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
public class AdminAttendanceDto {
    private LocalDate date;
    private String fullNames;



    // Name of the employee
    private LocalTime clockInTime;
    // When they arrived
    private LocalTime clockoutTime;
    private String ClockInReason;
    private String ClockOutReason;

    private Duration HoursWorked;
    private Boolean isLate;
    private boolean LeftEarly;








}
