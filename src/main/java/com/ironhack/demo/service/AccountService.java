package com.ironhack.demo.service;

import com.ironhack.demo.dto.AccountDTO;
import com.ironhack.demo.model.Account;
import com.ironhack.demo.model.User;
import com.ironhack.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    public List<AccountDTO> getAllAccounts() {

        List<Account> accounts = accountRepository.findAll();
        List<AccountDTO> accountsDTO = new ArrayList<>();

        for (Account account : accounts){
            accountsDTO.add(convertToDTO(account));
        }

        return accountsDTO;
    }
    public AccountDTO getAccountById(Long id) {

        Account account = accountRepository.findById(id).orElse(null);
        return convertToDTO(account);
    }

    public AccountDTO createAccount(AccountDTO account) {
        Account accountBbdd = accountRepository.save(convertToEntity(account));
        return convertToDTO(accountBbdd);
    }

    public AccountDTO updateAccount(Long id, AccountDTO accountDetails) {
       Account account = accountRepository.findById(id).orElse(null);

       if (account != null) {
           account.setOwner(accountDetails.getOwner());
           account.setBalance(accountDetails.getBalance());

           User user = new User();
           user.setId(accountDetails.getId());
           account.setUser(user);
           return convertToDTO(accountRepository.save(account));

       }
       return null;
    }
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    public AccountDTO convertToDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setOwner(account.getOwner());
        accountDTO.setBalance(account.getBalance());

        if (account.getUser() != null){
            accountDTO.setUserId(account.getUser().getId());

        }

        return accountDTO;
    }


    public Account convertToEntity(AccountDTO accountDTO) {
        Account account = new Account();

        User user = new User();
        user.setId(accountDTO.getUserId());

        account.setUser(user);

        account.setOwner(accountDTO.getOwner());
        account.setBalance(accountDTO.getBalance());
        return account;
    }
}
