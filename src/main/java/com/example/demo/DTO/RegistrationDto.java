package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data // Generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto {

    @NotEmpty
    private String username;
    @NotEmpty
    private String surname;
@Email
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String RepeatPassword;
    @NotEmpty
    private String Address;
    private Date DateOfBirth;
    @NotEmpty
    @Size(min = 13, max = 13, message = "Must be exactly 13 characters")
    @JsonProperty("IDNumber")
    private String IDNumber;
    @NotEmpty
    private String Gender;

    @NotEmpty
    private String NextOfKinName;
    @NotEmpty
    private String NextOfKinNumber;
    @NotEmpty
    private String myNumber;

}
