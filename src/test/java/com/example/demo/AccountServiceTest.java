package com.example.demo;


import com.ironhack.demo.model.Account;
import com.ironhack.demo.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AccountServiceTest {
    private AccountService accountService;

    /*@Test
    public void testCreateAccount() {
        Account account = new Account();
        account.setOwner("Lisa Medina");
        account.setBalance(1000.0);
        Account createdAccount = accountService.createAccount(account);
        assertNotNull(createdAccount);
        assertEquals("Lisa Medina", createdAccount.getOwner());
    }*/
}
