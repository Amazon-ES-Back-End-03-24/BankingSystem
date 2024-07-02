package com.ironhack.demo.model;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChekingAccount extends Account {
    private Double overdraftLimit;
}
