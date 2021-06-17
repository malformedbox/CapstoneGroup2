package com.capstonegroup2.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountHolderDTO {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String ssn;

}
