package com.ironhack.demo.service;

import com.ironhack.demo.model.Role;
import com.ironhack.demo.model.User;
import com.ironhack.demo.repository.RoleRepository;
import com.ironhack.demo.repository.UserRepository;
import com.ironhack.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private RoleRepository roleRepository;

	@Mock
	private PasswordEncoder passwordEncoder;

	@InjectMocks
	private UserService userService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testLoadUserByUsername() {
		// Given
		String username = "testuser";
		User user = new User();
		user.setUsername(username);
		user.setPassword("password");
		Role role = new Role();
		role.setName("ROLE_USER");
		user.setRoles(Collections.singleton(role));

		when(userRepository.findByUsername(username)).thenReturn(user);

		// When
		UserDetails userDetails = userService.loadUserByUsername(username);

		// Then
		assertNotNull(userDetails);
		assertEquals(username, userDetails.getUsername());
		assertTrue(userDetails.getAuthorities().stream()
				.anyMatch(a -> a.getAuthority().equals("ROLE_USER")));
	}

	@Test
	void testLoadUserByUsername_UserNotFound() {
		// Given
		String username = "nonexistinguser";
		when(userRepository.findByUsername(username)).thenReturn(null);

		// When/Then
		assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(username));
	}

	@Test
	void testSaveUser() {
		// Given
		User user = new User();
		user.setUsername("newuser");
		user.setPassword("password");
		when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
		when(userRepository.save(user)).thenReturn(user);

		// When
		User savedUser = userService.saveUser(user);

		// Then
		assertNotNull(savedUser);
		assertEquals("newuser", savedUser.getUsername());
		assertEquals("encodedPassword", savedUser.getPassword());
	}

	@Test
	void testGetUser() {
		// Given
		String username = "existinguser";
		User user = new User();
		user.setUsername(username);
		when(userRepository.findByUsername(username)).thenReturn(user);

		// When
		User fetchedUser = userService.getUser(username);

		// Then
		assertNotNull(fetchedUser);
		assertEquals(username, fetchedUser.getUsername());
	}

	@Test
	void testGetUsers() {
		// Given
		List<User> userList = Arrays.asList(new User(), new User());
		when(userRepository.findAll()).thenReturn(userList);

		// When
		List<User> fetchedUsers = userService.getUsers();

		// Then
		assertNotNull(fetchedUsers);
		assertEquals(2, fetchedUsers.size());
	}

	@Test
	void testAddRoleToUser() {
		// Given
		String username = "existinguser";
		String roleName = "ROLE_ADMIN";
		User user = new User();
		user.setUsername(username);
		Role role = new Role();
		role.setName(roleName);

		when(userRepository.findByUsername(username)).thenReturn(user);
		when(roleRepository.findByName(roleName)).thenReturn(role);

		// When
		userService.addRoleToUser(username, roleName);

		// Then
		assertTrue(user.getRoles().contains(role));
		verify(userRepository, times(1)).save(user);
	}
}
