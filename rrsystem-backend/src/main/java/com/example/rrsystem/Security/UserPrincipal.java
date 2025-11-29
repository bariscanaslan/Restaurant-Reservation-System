package com.example.rrsystem.Security;

import com.example.rrsystem.Entities.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {

    private final UserInfo user;

    public UserPrincipal(UserInfo user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        switch (user.getUserType()) {
            case 1:
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                break;
            case 2:
                authorities.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
                break;
            case 3:
                authorities.add(new SimpleGrantedAuthority("ROLE_RESTAURANT_OWNER"));
                break;
            default:
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                break;
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    public String getName() {
        return user.getName();
    }

    public String getSurname() {
        return user.getSurname();
    }

    public String getEmail() {
        return user.getEmail();
    }

    public String getPhone() {
        return user.getPhone();
    }

    public int getUserType() {
        return user.getUserType();
    }

    public String getPhoto() {
        return user.getPhoto();
    }

    public Long getId() {
        return user.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getActiveness() == 1;
    }
}


