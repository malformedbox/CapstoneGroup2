package com.capstonegroup2.backend.dto;

import lombok.Data;

@Data
public class AccountHolderDTO {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String ssn;

}
