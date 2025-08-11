package com.example.demo.DTO;

import com.example.demo.Models.LeaveStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequestDto {


     private long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private LeaveStatus status; // PENDING, APPROVED, etc.
    private LocalDateTime requestDate;
    private String responsereason;

}
