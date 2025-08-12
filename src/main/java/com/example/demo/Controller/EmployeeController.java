package com.example.demo.Controller;

import com.example.demo.DTO.RegistrationDto;
import com.example.demo.DTO.ResponseMessage;
import com.example.demo.DTO.TimeLogDto;
import com.example.demo.Models.Role;
import com.example.demo.Models.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Services.TimeLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/employee/logging")
@RequiredArgsConstructor
public class EmployeeController {
    @Autowired
    private final TimeLogService timeLogService;
    private final UserRepository userRepository;
    @GetMapping("/status")
    public ResponseEntity<?> getClockStatus(@AuthenticationPrincipal User user) {

        return ResponseEntity.ok( timeLogService.getClockedInStatus(user));
    }
    @PostMapping("/clockin")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<?> clockIn(@AuthenticationPrincipal User user, @RequestBody TimeLogDto timeLogDto) {
        try {
            timeLogService.clockIn(user, timeLogDto);
            ResponseMessage responseMessage=new ResponseMessage();
            responseMessage.Message="Clockedin Successfully";
            return ResponseEntity.ok(responseMessage);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something went wrong. Please try again.");
        }
    }


    @PostMapping("/clockout")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<?> clockOut(@AuthenticationPrincipal User user, @RequestBody TimeLogDto timeLogDto) {
        try {
            timeLogService.clockOut(user,timeLogDto);

            ResponseMessage responseMessage=new ResponseMessage();
            responseMessage.Message="Clocked out Successfully";
            return ResponseEntity.ok(responseMessage);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something went wrong. Please try again.");
        }


    }

    @GetMapping("/mylogs")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public List<TimeLogDto> getMyLogs(@AuthenticationPrincipal User user) {



        return timeLogService.getLogsForUser(user);
    }

    @GetMapping("/myDetails")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public RegistrationDto getMyDetails(@AuthenticationPrincipal User user) {

        RegistrationDto data=new RegistrationDto();
        data.setEmail(user.getEmail());



        data.setUsername(user.getUsername());
        data.setSurname(user.getSurname());

        data.setAddress(user.getAddress());
        data.setDateOfBirth(user.getDateOfBirth());
        data.setIDNumber(user.getIDNumber());
        data.setGender(user.getGender());
        data.setNextOfKinName(user.getNextOfKinName());
        data.setNextOfKinNumber(user.getNextOfKinNumber());


        return data;
    }

    @PutMapping("/Edit")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<?> EditMyDetails(@AuthenticationPrincipal User userr, @RequestBody RegistrationDto user) {


        userr.setEmail(user.getEmail());



        userr.setUsername(user.getUsername());
        userr.setSurname(user.getSurname());

        userr.setAddress(user.getAddress());
        userr.setDateOfBirth(user.getDateOfBirth());
        userr.setIDNumber(user.getIDNumber());
        userr.setGender(user.getGender());
        userr.setNextOfKinName(user.getNextOfKinName());
        userr.setNextOfKinNumber(user.getNextOfKinNumber());
        userr.setMyNumber(user.getMyNumber());

        userRepository.save(userr);
        ResponseMessage responseMessage=new ResponseMessage();
        responseMessage.Message="Edit Successful";
        return ResponseEntity.ok(responseMessage);



    }




}
