package com.ironhack.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.demo.controller.AccountController;
import com.ironhack.demo.dto.AccountDTO;
import com.ironhack.demo.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AccountControllerTest {

	private MockMvc mockMvc;

	@Mock
	private AccountService accountService;

	@InjectMocks
	private AccountController accountController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
	}

	@Test
	void testGetAllAccounts() throws Exception {
		// Given
		AccountDTO account1 = new AccountDTO();
		account1.setId(1L);
		account1.setOwner("John Doe");
		account1.setBalance(1000.0);

		AccountDTO account2 = new AccountDTO();
		account2.setId(2L);
		account2.setOwner("Jane Smith");
		account2.setBalance(2000.0);

		List<AccountDTO> accountList = Arrays.asList(account1, account2);

		when(accountService.getAllAccounts()).thenReturn(accountList);

		// When/Then
		mockMvc.perform(get("/accounts"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[0].owner").value("John Doe"))
				.andExpect(jsonPath("$[0].balance").value(1000.0))
				.andExpect(jsonPath("$[1].id").value(2))
				.andExpect(jsonPath("$[1].owner").value("Jane Smith"))
				.andExpect(jsonPath("$[1].balance").value(2000.0));

		verify(accountService, times(1)).getAllAccounts();
	}

	@Test
	void testGetAccountById() throws Exception {
		// Given
		Long accountId = 1L;
		AccountDTO account = new AccountDTO();
		account.setId(accountId);
		account.setOwner("John Doe");
		account.setBalance(1000.0);

		when(accountService.getAccountById(accountId)).thenReturn(account);

		// When/Then
		mockMvc.perform(get("/accounts/{id}", accountId))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(accountId))
				.andExpect(jsonPath("$.owner").value("John Doe"))
				.andExpect(jsonPath("$.balance").value(1000.0));

		verify(accountService, times(1)).getAccountById(accountId);
	}

	@Test
	void testCreateAccount() throws Exception {
		// Given
		AccountDTO newAccount = new AccountDTO();
		newAccount.setOwner("John Doe");
		newAccount.setBalance(1500.0);

		when(accountService.createAccount(any(AccountDTO.class))).thenReturn(newAccount);

		// When/Then
		mockMvc.perform(post("/accounts")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(newAccount)))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.owner").value("John Doe"))
				.andExpect(jsonPath("$.balance").value(1500.0));

		verify(accountService, times(1)).createAccount(any(AccountDTO.class));
	}

	@Test
	void testUpdateAccount() throws Exception {
		// Given
		Long accountId = 1L;
		AccountDTO updatedAccount = new AccountDTO();
		updatedAccount.setId(accountId);
		updatedAccount.setOwner("Jane Smith");
		updatedAccount.setBalance(2500.0);

		when(accountService.updateAccount(eq(accountId), any(AccountDTO.class))).thenReturn(updatedAccount);

		// When/Then
		mockMvc.perform(put("/accounts/{id}", accountId)
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(updatedAccount)))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(accountId))
				.andExpect(jsonPath("$.owner").value("Jane Smith"))
				.andExpect(jsonPath("$.balance").value(2500.0));

		verify(accountService, times(1)).updateAccount(eq(accountId), any(AccountDTO.class));
	}

	@Test
	void testDeleteAccount() throws Exception {
		// Given
		Long accountId = 1L;

		// When/Then
		mockMvc.perform(delete("/accounts/{id}", accountId))
				.andExpect(status().isOk());

		verify(accountService, times(1)).deleteAccount(accountId);
	}
}
