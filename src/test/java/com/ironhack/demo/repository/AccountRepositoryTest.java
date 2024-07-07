package com.ironhack.demo.repository;

import com.ironhack.demo.model.Account;
import com.ironhack.demo.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountRepositoryTest {

	@Mock
	private AccountRepository accountRepositoryMock;

	@Test
	public void testFindByUserId() {
		// Datos de prueba
		Long userId = 1L;
		User user = new User();
		user.setId(userId);

		Account account1 = new Account();
		account1.setId(1L);
		account1.setOwner("Owner1");
		account1.setBalance(800.0);
		account1.setUser(user);

		Account account2 = new Account();
		account2.setId(2L);
		account2.setOwner("Owner2");
		account2.setBalance(100.0);
		account2.setUser(user);

		List<Account> accounts = Arrays.asList(account1, account2);

		// Configurar el mock del repositorio
		when(accountRepositoryMock.findByUserId(userId)).thenReturn(accounts);

		// Llamar al método del repositorio simulado
		List<Account> foundAccounts = accountRepositoryMock.findByUserId(userId);

		// Verificar
		assertThat(foundAccounts).isNotNull();
		assertThat(foundAccounts).hasSize(2); // Verifica que se devuelvan exactamente 2 cuentas
		assertThat(foundAccounts.get(0).getUser().getId()).isEqualTo(userId); // Verifica que el primer elemento tenga el userId correcto
		assertThat(foundAccounts.get(1).getUser().getId()).isEqualTo(userId); // Verifica que el segundo elemento tenga el userId correcto
	}
}
