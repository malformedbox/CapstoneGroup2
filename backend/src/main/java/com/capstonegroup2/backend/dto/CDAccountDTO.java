package com.capstonegroup2.backend.dto;

import com.capstonegroup2.backend.models.CDOffering;
import lombok.Data;

@Data
public class CDAccountDTO {

    private Long id;
    private double balance;
    private CDOffering cdOffering;
    private int term;

}
