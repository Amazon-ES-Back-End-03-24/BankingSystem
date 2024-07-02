package com.ironhack.demo.model;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BussinessAccount extends Account {
    private Double creditLimit;

}
