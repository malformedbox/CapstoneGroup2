package com.capstonegroup2.backend;

// Stupid Comment

import com.capstonegroup2.backend.models.*;
import com.capstonegroup2.backend.repositories.CDOfferingRepository;
import com.capstonegroup2.backend.repositories.RoleRepository;
import com.capstonegroup2.backend.repositories.UserCredentialsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendApplication {



	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner addAdmin(UserCredentialsRepository repository) {
		return (args) -> {
			repository.save(new UserCredentials("admin", "password"));
		};
	}

	@Bean
	public CommandLineRunner addRoles(RoleRepository repository) {
		return (args) -> {
			repository.save(new Role(RoleName.ROLE_USER));
			repository.save(new Role(RoleName.ROLE_ADMIN));
		};
	}

	@Bean
	public CommandLineRunner addCDOfferings(CDOfferingRepository repository) {
		return (args) -> {
			repository.save(MeritBank.CDOFFERINGS.get(0));
			repository.save(MeritBank.CDOFFERINGS.get(1));
			repository.save(MeritBank.CDOFFERINGS.get(2));
			repository.save(MeritBank.CDOFFERINGS.get(3));
		};
	}
}
