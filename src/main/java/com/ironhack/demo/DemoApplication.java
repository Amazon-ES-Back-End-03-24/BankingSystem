package com.ironhack.demo;

import com.ironhack.demo.model.Role;
import com.ironhack.demo.model.User;
import com.ironhack.demo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {

			/*Role role = new Role();
			role.setName("ROLE_ADMIN");

			userService.saveRole(role);

			User user = new User();
			user.setUsername("Moha");
			user.setPassword("password");
			user.setRoles(List.of(role));


			userService.saveUser(user);*/
		};
	}

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}
}