package com.ironhack.demo.service;

import com.ironhack.demo.model.Account;
import com.ironhack.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }
    public Account updateAccount(Long id, Account accountDetails) {
       Account account = accountRepository.findById(id).orElse(null);
       if (account != null) {
           account.setOwner(accountDetails.getOwner());
           account.setBalance(accountDetails.getBalance());
           return accountRepository.save(account);

       }
       return null;
    }
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

}
