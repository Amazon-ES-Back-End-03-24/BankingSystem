package com.ironhack.demo.model;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SavingAccount extends Account {

    private Double interestRate;

    public void addInterest(){
        this.setBalance(this.getBalance()*(1+interestRate));
    }
}
