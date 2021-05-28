package com.capstonegroup2.backend.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ira_roth")
@Data
@NoArgsConstructor
public class IRARoth extends BankAccount{


}
