package com.example.demo.Repository;

import com.example.demo.Models.TimeLog;
import com.example.demo.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TimeLogRepository extends JpaRepository<TimeLog, Long> {

    Optional<TimeLog> findByUserAndDate(User user, LocalDate date);


    List<TimeLog> findByDate(LocalDate date);


    List<TimeLog> findAllByUser(User user);
    Optional<TimeLog> findTopByUserOrderByDateDesc(User user);



}
