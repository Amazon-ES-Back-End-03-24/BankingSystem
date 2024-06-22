package com.ironhack.demo.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavingAccount extends Account {
    private Double interestRate;
    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }
    public Double getInterestRate() {
        return interestRate;
    }
    public void addInterest(){
        this.setBalance(this.getBalance()*(1+interestRate));
    }
}
