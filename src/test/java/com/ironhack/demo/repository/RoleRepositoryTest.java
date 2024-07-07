package com.ironhack.demo.repository;

import com.ironhack.demo.model.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoleRepositoryTest {

	@Mock
	private RoleRepository roleRepositoryMock;

	@Test
	public void testFindByName() {
		// Datos de prueba
		String roleName = "ROLE_ADMIN";
		Role adminRole = new Role(1L, roleName);

		// Configurar el mock del repositorio
		when(roleRepositoryMock.findByName(roleName)).thenReturn(adminRole);

		// Llamar al método del repositorio simulado
		Role foundRole = roleRepositoryMock.findByName(roleName);

		// Verificar
		assertThat(foundRole).isNotNull();
		assertThat(foundRole.getName()).isEqualTo(roleName);
	}
}
