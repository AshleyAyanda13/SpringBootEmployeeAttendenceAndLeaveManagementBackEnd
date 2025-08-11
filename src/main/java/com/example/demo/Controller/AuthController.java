package com.example.demo.Controller;

import com.example.demo.DTO.RegistrationDto;
import com.example.demo.DTO.ResponseMessage;
import com.example.demo.DTO.UserDto;
import com.example.demo.Models.Role;
import com.example.demo.Models.User;
import com.example.demo.Repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")

public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();


    public AuthController(AuthenticationManager authenticationManager,UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }
    @PreAuthorize("hasRole('EMPLOYEE')")

    @PostMapping(value = "/signup", consumes = "application/json")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationDto user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("User already exists.");
        }




        if (!user.getPassword().equals(user.getRepeatPassword())) {


            return ResponseEntity.badRequest().body("Password and Confirm Password don't match");
        }


        User userr=new User();
        userr.setEmail(user.getEmail());
        userr.setPassword(passwordEncoder.encode(user.getPassword()));
        userr.setRole(Role.EMPLOYEE);

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
        responseMessage.Message="Registration Successful";
        return ResponseEntity.ok(responseMessage);

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/login", consumes = "application/json")
    public ResponseEntity<?> login(@RequestBody UserDto loginRequest,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {





        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());

        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
        securityContextRepository.saveContext(context, request, response);

        HttpSession session = request.getSession(false);





        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("message", "You are successfully logged in");


        responseBody.put("status", "success");
        responseBody.put("timestamp", LocalDateTime.now()); // optional

        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("ROLE_UNKNOWN");

        return ResponseEntity.ok(Map.of("role", role));
    }

}