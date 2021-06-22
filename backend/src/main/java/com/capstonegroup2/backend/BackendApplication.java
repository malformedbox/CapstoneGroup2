package com.capstonegroup2.backend;

import com.capstonegroup2.backend.enums.TransactionType;
import com.capstonegroup2.backend.models.*;
import com.capstonegroup2.backend.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
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

	@Bean
	@Transactional
	public CommandLineRunner seedDemoDatabase(UserCredentialsRepository userRepository,
										  RoleRepository roleRepository,
										  AccountHolderRepository accountHolderRepository,
										  IraRegularRepository iraRegularRepository,
										  PersonalCheckingRepository personalCheckingRepository,
										  TransactionRepository transactionRepository) {
		return (args) -> {

			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			Set<Role> roles = new HashSet<>();
			Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
			roles.add(userRole);


			UserCredentials user1 = new UserCredentials
					("DemoUser00", passwordEncoder.encode("password00"));
			AccountHolder accountHolder1 = new AccountHolder
					("David", "Michael", "Amateau", "123456789",
							user1);

			PersonalChecking personalChecking1 = new PersonalChecking("15000");
			IraRegular iraRegular1 = new IraRegular("40000");

			Transaction transaction1 = new Transaction("630.45", TransactionType.WITHDRAWAL, personalChecking1);
			Transaction transaction2 = new Transaction("2500", TransactionType.DEPOSIT, personalChecking1);
			Transaction transaction3 = new Transaction("400.65", TransactionType.TRANSFER,
					personalChecking1, iraRegular1);
			Transaction transaction4 = new Transaction("45.61", TransactionType.WITHDRAWAL, personalChecking1);
			Transaction transaction5 = new Transaction("340.56", TransactionType.WITHDRAWAL, personalChecking1);
			Transaction transaction6 = new Transaction("25000", TransactionType.DEPOSIT, iraRegular1);

			user1.setRoles(roles);
			user1.setAccountHolder(accountHolder1);
			accountHolder1.setPersonalChecking(personalChecking1);
			accountHolder1.setIraRegular(iraRegular1);
			personalChecking1.setAccountHolder(accountHolder1);
			iraRegular1.setAccountHolder(accountHolder1);
			personalChecking1.setBalance(personalChecking1.getBalance().subtract(transaction1.getAmount()));
			personalChecking1.setBalance(personalChecking1.getBalance().add(transaction2.getAmount()));
			personalChecking1.setBalance(personalChecking1.getBalance().subtract(transaction3.getAmount()));
			iraRegular1.setBalance(iraRegular1.getBalance().add(transaction3.getAmount()));
			personalChecking1.setBalance(personalChecking1.getBalance().subtract(transaction4.getAmount()));
			personalChecking1.setBalance(personalChecking1.getBalance().subtract(transaction5.getAmount()));
			iraRegular1.setBalance(iraRegular1.getBalance().add(transaction6.getAmount()));


			userRepository.save(user1);
			accountHolderRepository.save(accountHolder1);
			personalCheckingRepository.save(personalChecking1);
			iraRegularRepository.save(iraRegular1);

			transactionRepository.save(transaction1);
			transactionRepository.save(transaction2);
			transactionRepository.save(transaction3);
			transactionRepository.save(transaction4);
			transactionRepository.save(transaction5);
			transactionRepository.save(transaction6);
		};
	}
}
