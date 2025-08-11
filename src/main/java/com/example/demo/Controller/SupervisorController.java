package com.example.demo.Controller;

import com.example.demo.DTO.AdminAttendanceDto;
import com.example.demo.DTO.LeaveRequestDto;
import com.example.demo.DTO.ResponseMessage;
import com.example.demo.Models.LeaveRequest;
import com.example.demo.Services.LeaveRequestService;
import com.example.demo.Services.SupervisorLeaveService;
import com.example.demo.Services.TimeLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/supervisor")
@RequiredArgsConstructor
public class SupervisorController {


    private final TimeLogService timeLogService;
    private final SupervisorLeaveService supervisorLeaveService;


    @PreAuthorize("hasRole('SUPERVISOR')")
    @GetMapping("/overview")
    public ResponseEntity<List<AdminAttendanceDto>> getOverview() {




        return ResponseEntity.ok(supervisorLeaveService.getAllAttendanceOverview());

    }
    @GetMapping("/todayAttendance")
    public ResponseEntity<List<AdminAttendanceDto>> getAttendancefortoday() {

        return ResponseEntity.ok(supervisorLeaveService.getTodaysAttendanceOverview());
    }
    @GetMapping("/all")
    @PreAuthorize("hasRole('SUPERVISOR')")
    public List<LeaveRequest> getAllLeaveRequests() {


        return supervisorLeaveService.GetAllLeaves();
    }

    @GetMapping("/pending")
    @PreAuthorize("hasRole('SUPERVISOR')")
    public List<LeaveRequest> getPendingRequests() {

        supervisorLeaveService.GetAllPendingLeaves();
        return  getPendingRequests();
    }

    @PutMapping("/leave/actioning")
    @PreAuthorize("hasRole('SUPERVISOR')")
    public ResponseEntity<?> actionLeaveRequest(@RequestBody LeaveRequestDto dto) {

        supervisorLeaveService.handleLeaveDecision(dto.getId(),dto.getStatus(),dto.getResponsereason());

        ResponseMessage response=new ResponseMessage();
        response.Message="You have successflly responded to Leave Request";
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/reject")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> rejectRequest(@PathVariable Long id) {
        return ResponseEntity.ok("");
    }


}
