package com.example.demo.Models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class CustomUserDetails implements UserDetails {

    private final User user; // Your domain entity

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail(); // or user.getUsername()
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // customize if needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // customize if needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // customize if needed
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled(); // or true if not tracked
    }

    // Optional: expose custom fields
    public String getFullName() {
        return user.getFullName();
    }

    public Long getId() {
        return user.getId();
    }

}
