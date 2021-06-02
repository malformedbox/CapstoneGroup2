package com.capstonegroup2.backend.security;

import com.capstonegroup2.backend.models.UserCredentials;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/*
    Please note that implmenting UserDetails class requires override of method
    String getUsername() which has a lowercase n. In our UserCredentials class, we use capital N on userName.
 */
public class UserCredentialsImplementation implements UserDetails {

    private Long id;

    private String username;
    @JsonIgnore
    private String password;
    private List<GrantedAuthority> authorities;

    public UserCredentialsImplementation() {}
    public UserCredentialsImplementation(Long id, String username,String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
    public UserCredentialsImplementation(UserCredentials userCredentials) {
        this.username = userCredentials.getUsername();
        this.password = userCredentials.getPassword();
    }

    public static UserCredentialsImplementation build(UserCredentials userCredentials) {
        return new UserCredentialsImplementation(
                userCredentials.getId(),
                userCredentials.getUsername(),
                userCredentials.getPassword()
        );
    }

    public Long getId() { return id; }

    @Override
    public String getPassword() { return password; }

    @Override
    public String getUsername() { return username; }

    @Override
    public boolean isAccountNonExpired() { return true;	}

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserCredentialsImplementation user = (UserCredentialsImplementation) o;
        return Objects.equals(id, user.id);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
