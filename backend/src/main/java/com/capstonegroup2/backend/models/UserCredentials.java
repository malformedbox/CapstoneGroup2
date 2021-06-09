package com.capstonegroup2.backend.models;

import com.capstonegroup2.backend.enums.Roles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "user_details")
@Data
@NoArgsConstructor
public class UserCredentials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "User Name is a required field")
    private String username;
    @NotBlank(message = "Password is a required field")
    private String password;

//    private Roles role;
    private String role;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_holder_id")
    private AccountHolder accountHolder;

    public UserCredentials(String username, String password) {
        this.username = username;
        this.password = password;
//        this.role = Roles.USER;
        this.role = "USER";
    }
}
