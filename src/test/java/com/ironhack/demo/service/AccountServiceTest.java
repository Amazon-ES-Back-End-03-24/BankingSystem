package com.ironhack.demo.service;

import com.ironhack.demo.dto.AccountDTO;
import com.ironhack.demo.model.Account;
import com.ironhack.demo.model.User;
import com.ironhack.demo.repository.AccountRepository;
import com.ironhack.demo.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

	@Mock
	private AccountRepository accountRepository;

	@InjectMocks
	private AccountService accountService;

	private Account account1;
	private Account account2;

	@BeforeEach
	void setUp() {
		account1 = new Account();
		account1.setId(1L);
		account1.setOwner("Owner1");
		account1.setBalance(1000.0);
		User user1 = new User();
		user1.setId(1L);
		account1.setUser(user1);

		account2 = new Account();
		account2.setId(2L);
		account2.setOwner("Owner2");
		account2.setBalance(2000.0);
		User user2 = new User();
		user2.setId(2L);
		account2.setUser(user2);
	}

	@Test
	void testGetAllAccounts() {
		when(accountRepository.findAll()).thenReturn(Arrays.asList(account1, account2));
		List<AccountDTO> accountsDTO = accountService.getAllAccounts();
		assertThat(accountsDTO).hasSize(2);
		assertThat(accountsDTO.get(0).getOwner()).isEqualTo("Owner1");
		assertThat(accountsDTO.get(1).getOwner()).isEqualTo("Owner2");
	}

	@Test
	void testGetAccountById() {
		when(accountRepository.findById(1L)).thenReturn(Optional.of(account1));
		AccountDTO accountDTO = accountService.getAccountById(1L);
		assertThat(accountDTO).isNotNull();
		assertThat(accountDTO.getOwner()).isEqualTo("Owner1");
	}

	@Test
	void testCreateAccount() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setOwner("Owner1");
		accountDTO.setBalance(1000.0);
		accountDTO.setUserId(1L);
		when(accountRepository.save(any(Account.class))).thenReturn(account1);
		AccountDTO createdAccount = accountService.createAccount(accountDTO);
		assertThat(createdAccount).isNotNull();
		assertThat(createdAccount.getOwner()).isEqualTo("Owner1");
	}

	@Test
	void testUpdateAccount() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setOwner("UpdatedOwner");
		accountDTO.setBalance(1500.0);
		accountDTO.setUserId(1L);
		when(accountRepository.findById(1L)).thenReturn(Optional.of(account1));
		when(accountRepository.save(any(Account.class))).thenReturn(account1);
		AccountDTO updatedAccount = accountService.updateAccount(1L, accountDTO);
		assertThat(updatedAccount).isNotNull();
		assertThat(updatedAccount.getOwner()).isEqualTo("UpdatedOwner");
		assertThat(updatedAccount.getBalance()).isEqualTo(1500.0);
	}

	@Test
	void testDeleteAccount() {
		doNothing().when(accountRepository).deleteById(1L);
		accountService.deleteAccount(1L);
		verify(accountRepository, times(1)).deleteById(1L);
	}
}
