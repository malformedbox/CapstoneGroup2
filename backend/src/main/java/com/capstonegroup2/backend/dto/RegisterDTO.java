package com.capstonegroup2.backend.dto;

import com.capstonegroup2.backend.models.AccountHolder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class RegisterDTO {

    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank
    @Size(min = 3, max = 50)
    private String password;

    private Set<String> role;
    private AccountHolder accountHolder;

}
