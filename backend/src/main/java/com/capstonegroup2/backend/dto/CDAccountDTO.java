package com.capstonegroup2.backend.dto;

import com.capstonegroup2.backend.models.CDOffering;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CDAccountDTO {

    private Long id;
    private String balance;
    private CDOffering cdOffering;
    private int term;

}
