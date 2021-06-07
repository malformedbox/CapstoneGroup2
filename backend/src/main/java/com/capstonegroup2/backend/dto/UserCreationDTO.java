package com.capstonegroup2.backend.dto;

import lombok.Data;

@Data
public class UserCreationDTO {

    private String username;
    private String password;
    private AccountHolderDTO accountHolderDTO;

}
