package com.ironhack.demo.controller;


import com.ironhack.demo.dto.AccountDTO;
import com.ironhack.demo.model.Account;
import com.ironhack.demo.service.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Tutorial", description = "Tutorial management APIs")
@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<AccountDTO> getAllAccounts() {
        return accountService.getAllAccounts();
    }
    @GetMapping("/{id}")
    public AccountDTO getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }
    @PostMapping
    public AccountDTO createAccount(@RequestBody AccountDTO account) {

        return accountService.createAccount(account);
    }
    @PutMapping("/{id}")
    public AccountDTO updateAccount(@PathVariable Long id, @RequestBody AccountDTO accountDetails) {
        return accountService.updateAccount(id, accountDetails);

    }
    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }
}
