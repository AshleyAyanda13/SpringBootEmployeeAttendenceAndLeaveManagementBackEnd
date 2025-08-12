package com.example.demo.DTO;

import com.example.demo.Models.LeaveStatus;
import jakarta.validation.constraints.NotEmpty;
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
     @NotEmpty
    private LocalDate startDate;
    @NotEmpty
    private LocalDate endDate;
    private String reason;
    @NotEmpty
    private LeaveStatus status; // PENDING, APPROVED, etc.
    private LocalDateTime requestDate;
    private String responsereason;

}
