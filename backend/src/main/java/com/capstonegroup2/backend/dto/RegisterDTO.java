package com.capstonegroup2.backend.dto;

import com.capstonegroup2.backend.models.AccountHolder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {

    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank
    @Size(min = 3, max = 50)
    private String password;

    private Set<String> role;
    private String firstName;
    private String middleName;
    private String lastName;
    private String ssn;

}
