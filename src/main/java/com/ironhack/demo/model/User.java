package com.ironhack.demo.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static jakarta.persistence.FetchType.EAGER;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Account> accounts;

    @ManyToMany(fetch = EAGER)
    private Collection<Role> roles = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public User() {
    }

    public User(Long id, String username, String password, List<Account> accounts, Collection<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.accounts = accounts;
        this.roles = roles;
    }
}
