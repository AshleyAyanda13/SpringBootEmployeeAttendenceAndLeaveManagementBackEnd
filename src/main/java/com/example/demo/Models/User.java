
package com.example.demo.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "users")
@Data


public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String surname;
    private String email;
    private String password;
    private String Address;
    private Date DateOfBirth;

    private String IDNumber;
    private String Gender;
    private String NextOfKinName;
    private String NextOfKinNumber;
    private String myNumber;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String setEmail(String email)
    {

      return   this.email=email;
    }
    @Override
    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }
    public String setUsername(String username) {
        return this.username=username;
    }
    public String setSurname(String surname) {
        return this.surname=surname;
    }

    public String getFullName() {
        return username + " " + surname;
    }
    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }
}
