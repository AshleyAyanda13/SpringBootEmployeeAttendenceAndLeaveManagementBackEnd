package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data // Generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto {


    private String username;
    private String surname;

    private String email;

    private String password;
    private String RepeatPassword;
    private String Address;
    private Date DateOfBirth;
    @JsonProperty("IDNumber")
    private String IDNumber;
    private String Gender;
    private String NextOfKinName;
    private String NextOfKinNumber;
    private String myNumber;

}
