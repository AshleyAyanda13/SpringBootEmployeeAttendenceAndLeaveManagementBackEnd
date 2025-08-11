package com.example.demo.Services;

import com.example.demo.DTO.LeaveRequestDto;
import com.example.demo.Models.LeaveRequest;
import com.example.demo.Models.LeaveStatus;
import com.example.demo.Models.User;
import com.example.demo.Repository.LeaveRequestRepository;
import com.example.demo.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;
    private final UserRepository userRepository;

    public LeaveRequest createLeaveRequest(Long userId, LeaveRequestDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        LeaveRequest request = new LeaveRequest();
        request.setUser(user);

        request.setStartDate(dto.getStartDate());
        request.setEndDate(dto.getEndDate());
        request.setReason(dto.getReason());
        request.setStatus(LeaveStatus.PENDING);
        request.setRequestDate(LocalDateTime.now());
        request.setResponsereason("");

        return leaveRequestRepository.save(request);
    }


    public List<LeaveRequestDto> GetAllLeaveRequests(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<LeaveRequest> leaveRequests = leaveRequestRepository.findByUserId(userId);


        return leaveRequests.stream()
                .map(leave -> new LeaveRequestDto(
                        leave.getId(),
                        leave.getStartDate(),
                        leave.getEndDate(),
                        leave.getReason(),
                        leave.getStatus(),
                        leave.getRequestDate(),
                        leave.getResponsereason()))
                .collect(Collectors.toList());


    }
    public LeaveRequest getLeaveRequestById(Long id) {
        return leaveRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave not found"));

    }

    public LeaveRequest EditMyLeaveLogs(Long leaveId, LeaveRequestDto updated) {
System.out.println(leaveId);
            LeaveRequest existing =leaveRequestRepository.findById(leaveId)
                    .orElseThrow(() -> new RuntimeException("Leave with Id Not Found"));




            existing.setStartDate(updated.getStartDate());
        existing.setEndDate(updated.getEndDate());
        existing.setReason(updated.getReason());
        existing.setStatus(LeaveStatus.PENDING);
            return leaveRequestRepository.save(existing);
       }




    public String DeleteMyLeave(Long leaveId, LeaveRequestDto updated) {

        LeaveRequest existing =leaveRequestRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave with Id Not Found"));




     leaveRequestRepository.delete(existing);

        return "Deleted Succefully" ;
    }
}













