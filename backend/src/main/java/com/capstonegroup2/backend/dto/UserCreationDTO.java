package com.capstonegroup2.backend.dto;

import com.capstonegroup2.backend.models.AccountHolder;
import lombok.Data;

@Data
public class UserCreationDTO {

    private String username;
    private String password;
    private AccountHolder accountHolder;

}
