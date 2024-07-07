package com.ironhack.demo.repository;

import com.ironhack.demo.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

	@Mock
	private UserRepository userRepositoryMock;

	@Test
	public void testFindByUsername() {
		// Datos de prueba
		String username = "john_doe";
		User user = new User();
		user.setId(1L);
		user.setUsername(username);
		user.setPassword("password");

		// Configurar el mock del repositorio
		when(userRepositoryMock.findByUsername(username)).thenReturn(user);

		// Llamar al método del repositorio simulado
		User foundUser = userRepositoryMock.findByUsername(username);

		// Verificar
		assertThat(foundUser).isNotNull();
		assertThat(foundUser.getUsername()).isEqualTo(username);
	}
}
