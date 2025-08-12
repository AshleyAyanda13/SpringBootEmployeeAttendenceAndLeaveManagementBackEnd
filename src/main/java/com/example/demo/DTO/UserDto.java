package com.example.demo.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;

@Data  // This generates getters & setters
@AllArgsConstructor
@NoArgsConstructor

public class UserDto {



@Email
    private String email;
    @NotEmpty
    private String password;



    public String SetEmail(String email)
    {
        this.email=email;
        return this.email;

    }
    public String getEmail()
    {

        return this.email;

    }

    public String getPassword()
    {



        return this.password;
    }




}
