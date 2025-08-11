package com.example.demo.Controller;

import com.example.demo.DTO.LeaveRequestDto;
import com.example.demo.DTO.ResponseMessage;
import com.example.demo.Models.CustomUserDetails;
import com.example.demo.Models.LeaveRequest;
import com.example.demo.Models.User;
import com.example.demo.Services.LeaveRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave")
@RequiredArgsConstructor
public class EmployeeLeaveRequestController {

    @Autowired
    private final LeaveRequestService leaveRequestService;

    @PostMapping("/requestforleave")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<?> requestLeave(@AuthenticationPrincipal User user, @RequestBody LeaveRequestDto dto) {



        leaveRequestService.createLeaveRequest(user.getId(),dto);


        ResponseMessage responseMessage=new ResponseMessage();
        responseMessage.Message="Registration Successful";
        return ResponseEntity.ok(responseMessage);
    }

    @GetMapping("/myrequests")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<?>getMyRequests(@AuthenticationPrincipal User user) {




        return  ResponseEntity.ok(leaveRequestService.GetAllLeaveRequests(user.getId()));
    }
    @PreAuthorize("hasRole('EMPLOYEE')")
    @GetMapping("/{id}")
    public ResponseEntity<LeaveRequest> getLeaveRequestById(@PathVariable Long id) {

        return ResponseEntity.ok(leaveRequestService.getLeaveRequestById(id));
    }

    @PutMapping("/myrequests/editrequests")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<?>EditMyLeave(@RequestBody LeaveRequestDto dto) {

        leaveRequestService.EditMyLeaveLogs(dto.getId(),dto);
        ResponseMessage responseMessage=new ResponseMessage();
        responseMessage.Message="Edited Successfully";
        return  ResponseEntity.ok(responseMessage);
    }
    @DeleteMapping("/myrequests/deleterequests")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<?>DeleteMyLeaveRequest(@RequestBody LeaveRequestDto dto) {

        leaveRequestService.DeleteMyLeave(dto.getId(),dto);
        ResponseMessage responseMessage=new ResponseMessage();
        responseMessage.Message="Leave Record Deleted Successfully";
        return  ResponseEntity.ok(responseMessage);
    }

}
