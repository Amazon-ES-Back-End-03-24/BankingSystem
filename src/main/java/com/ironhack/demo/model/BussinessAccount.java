package com.ironhack.demo.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BussinessAccount extends Account {
    private Double creditLimit;
    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
    }
    public Double getCreditLimit() {
        return creditLimit;
    }

}
