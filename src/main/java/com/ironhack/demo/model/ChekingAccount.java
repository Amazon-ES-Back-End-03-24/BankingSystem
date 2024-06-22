package com.ironhack.demo.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChekingAccount extends Account {
    private Double overdraftLimit;

    public void setOverdraftLimit(Double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }
    public Double getOverdraftLimit() {
        return overdraftLimit;
    }

}
