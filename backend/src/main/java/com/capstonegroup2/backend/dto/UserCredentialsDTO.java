package com.capstonegroup2.backend.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserCredentialsDTO {
    @NotBlank
    @Size(min=3, max=60)
    private String username;

    @NotBlank
    @Size(min=3, max=60)
    private String password;

}
