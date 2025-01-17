package com.ironhack.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    private String owner;
    private Double balance;
    public void deposit (double amount) {
        this.balance += amount;
    }
    public void withdraw (Double amount) {
        if(amount<=this.balance) {
            this.balance -= amount;
        } else{throw new IllegalArgumentException("Insufficient balance");}
    }
}
