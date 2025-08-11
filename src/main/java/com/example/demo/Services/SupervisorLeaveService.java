package com.example.demo.Services;

import com.example.demo.DTO.AdminAttendanceDto;
import com.example.demo.Models.LeaveRequest;
import com.example.demo.Models.LeaveStatus;
import com.example.demo.Models.TimeLog;
import com.example.demo.Repository.LeaveRequestRepository;
import com.example.demo.Repository.TimeLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.Models.LeaveStatus.*;

@RequiredArgsConstructor
@Service
public class SupervisorLeaveService
{

private final LeaveRequestRepository leaveRequestRepository;
    private final TimeLogRepository timeLogRepository;


    public List<AdminAttendanceDto> getAllAttendanceOverview() {

        List<TimeLog> logs = timeLogRepository.findAll();

        return logs.stream()
                .map(log -> new AdminAttendanceDto(
                        log.getDate(),
                        log.getUser().getFullName(),

                        log.getClockInTime(),
                        log.getClockOutTime()

                        ,log.getClockInReason(),
                        log.getClockOutReason(),
                        log.getHoursWorked(),
                        log.getIsLate(),
                        log.getLeftEarly()))








                .collect(Collectors.toList());
    }
    public List<AdminAttendanceDto> getTodaysAttendanceOverview() {
        LocalDate today = LocalDate.now();
        List<TimeLog> logs = timeLogRepository.findByDate(today);


        return logs.stream()
                .map(log -> new AdminAttendanceDto(
                        log.getDate(),
                        log.getUser().getFullName(),

                        log.getClockInTime(),
                        log.getClockOutTime()

                        ,log.getClockInReason(),
                        log.getClockOutReason(),
                        log.getHoursWorked(),
                        log.getIsLate(),
                        log.getLeftEarly()))








                .collect(Collectors.toList());
    }


public List<LeaveRequest> GetAllLeaves()
{




    return leaveRequestRepository.findAll();
}


    public List<LeaveRequest> GetAllPendingLeaves()
    {




        return leaveRequestRepository.findByStatus(PENDING);
    }
 public LeaveRequest handleLeaveDecision(Long leaveId, LeaveStatus leaveStatus,String ResponseMessage) {


     LeaveRequest existing = leaveRequestRepository.findById(leaveId).orElseThrow(() -> new RuntimeException("Leave with Id Not Found"));




     if (leaveStatus == LeaveStatus.APPROVED) {


         existing.setStatus(LeaveStatus.APPROVED);

     } else if (leaveStatus == LeaveStatus.REJECTED) {

         existing.setStatus(LeaveStatus.REJECTED);

     }else if(leaveStatus==LeaveStatus.PENDING)
     {
         existing.setStatus(LeaveStatus.PENDING);
     }
existing.setResponsereason(ResponseMessage);

     return leaveRequestRepository.save(existing);

    }

}



