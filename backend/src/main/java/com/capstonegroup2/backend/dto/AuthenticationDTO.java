package com.capstonegroup2.backend.dto;

import lombok.Data;

@Data
public class AuthenticationDTO {
    private final String jwt;

    public AuthenticationDTO(String jwt) {
        this.jwt=jwt;
    }
}