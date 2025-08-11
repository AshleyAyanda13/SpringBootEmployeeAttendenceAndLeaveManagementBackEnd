package com.example.demo.Repository;

import com.example.demo.Models.LeaveRequest;
import com.example.demo.Models.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {



    List<LeaveRequest> findByUserId(Long userId);
    List<LeaveRequest> findByStatus(LeaveStatus status);


}