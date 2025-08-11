package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // This generates getters & setters
@AllArgsConstructor
@NoArgsConstructor

public class UserDto {




    private String email;
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
