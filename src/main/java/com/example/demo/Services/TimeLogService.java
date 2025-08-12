package com.example.demo.Services;
import java.time.ZoneId;

import java.time.LocalTime;

import com.example.demo.DTO.TimeLogDto;
import com.example.demo.Models.TimeLog;
import com.example.demo.Models.User;
import com.example.demo.Repository.TimeLogRepository;
import com.example.demo.Repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
    @RequiredArgsConstructor
    public class TimeLogService {
@Autowired
        private final TimeLogRepository timeLogRepo;
@Autowired


        private final UserRepository userRepo;
@Autowired
   private final HttpSession session;
        private final LocalTime standardStartTime = LocalTime.of(8, 0);
        private final LocalTime standardEndTime = LocalTime.of(16, 0);


        public void clockIn(User user,TimeLogDto timeLogDto) {
            LocalDate today = LocalDate.now();


            if (timeLogRepo.findByUserAndDate(user, today).isPresent()) {
                throw new IllegalStateException("Already clocked in today");
            }

            TimeLog log = new TimeLog();
            log.setUser(user);
            log.setDate(today);
            log.setClockInReason(timeLogDto.getClockInReason());
            LocalTime now = LocalTime.now(ZoneId.of("Africa/Johannesburg"));
            log.setClockInTime(now);

            log.setIsLate(now.isAfter(standardStartTime));

        log.setClockedIn(true);

            timeLogRepo.save(log);
        }

        // 👤 EMPLOYEE: Clock-Out Logic
        public void clockOut(User user,TimeLogDto timeLogDto) {
            LocalDate today = LocalDate.now();
            TimeLog log = timeLogRepo.findByUserAndDate(user, today)
                    .orElseThrow(() -> new IllegalStateException("No clock-in found"));

            if (log.getClockOutTime() != null) {
                throw new IllegalStateException("Already clocked out today");
            }

            LocalTime now = LocalTime.now(ZoneId.of("Africa/Johannesburg"));
            log.setClockOutTime(now);

            Duration worked = Duration.between(log.getClockInTime(), now);
            log.setHoursWorked(worked);
            log.setClockOutReason(timeLogDto.getClockOutReason());
            log.setLeftEarly(now.isBefore(standardEndTime));
            log.setClockedIn(false);
            timeLogRepo.save(log);
        }


    public Map<String, Boolean> getClockedInStatus(User user) {
        return timeLogRepo.findTopByUserOrderByDateDesc(user)
                .map(log -> Map.of("clockedIn", log.isClockedIn()))
                .orElse(Map.of("clockedIn", false)); // or a default value
    }


    // 👤 EMPLOYEE: View Own Logs
        public List<TimeLogDto> getLogsForUser(User user) {
            return timeLogRepo.findAllByUser(user).stream()
                    .map(log -> new TimeLogDto(
                            log.getDate(),
                            log.getClockInTime(),
                            log.getClockOutTime(),
                            log.getHoursWorked(),
                            log.getIsLate(),
                            log.getClockInReason(),
                            log.getClockOutReason(),
                            log.isClockedIn(),
                            log.getLeftEarly()))
                    .collect(Collectors.toList());
        }



    }


