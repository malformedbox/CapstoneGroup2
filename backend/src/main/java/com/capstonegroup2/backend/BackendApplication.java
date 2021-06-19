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

	// The methods below are being used to seed the database for demonstrational purposes, if you would like
	// to start with an empty database, comment or delete them out.
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
	public CommandLineRunner seedDatabase(UserCredentialsRepository userRepository,
										  RoleRepository roleRepository,
										  AccountHolderRepository accountHolderRepository,
										  IraRegularRepository iraRegularRepository,
										  IraRothRepository iraRothRepository,
										  PersonalCheckingRepository personalCheckingRepository,
										  TransactionRepository transactionRepository) {
		return (args) -> {

			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			Set<Role> roles = new HashSet<>();
			Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
			roles.add(userRole);

			UserCredentials user1 = new UserCredentials
					("Damateau00", passwordEncoder.encode("password00"));
			PersonalChecking personalChecking1 = new PersonalChecking("15000");
			AccountHolder accountHolder1 = new AccountHolder
					("David", "Michael", "Amateau", "123456789", user1, personalChecking1);

			IraRegular iraRegular1 = new IraRegular("40000");
			IraRoth iraRoth1 = new IraRoth("60000");
			Transaction transaction1 = new Transaction("630.45", TransactionType.WITHDRAWAL, personalChecking1);
			Transaction transaction2 = new Transaction("2500", TransactionType.DEPOSIT, personalChecking1);
			Transaction transaction3 = new Transaction("400", TransactionType.TRANSFER,
					personalChecking1, iraRegular1);
			Transaction transaction4 = new Transaction("45.60", TransactionType.WITHDRAWAL, personalChecking1);
			Transaction transaction5 = new Transaction("340.56", TransactionType.WITHDRAWAL, personalChecking1);





			UserCredentials user2 = new UserCredentials
					("BobFishes01", passwordEncoder.encode("bobLikesFish"));
			AccountHolder accountHolder2 = new AccountHolder
					("Bob", "Sands", "Fisherman", "012345678", user2);


			user1.setRoles(roles);
			user1.setAccountHolder(accountHolder1);
			iraRegular1.setAccountHolder(accountHolder1);
			iraRoth1.setAccountHolder(accountHolder1);
			personalChecking1.setAccountHolder(accountHolder1);
			userRepository.save(user1);
			accountHolderRepository.save(accountHolder1);



//			iraRothRepository.save(iraRoth1);
//			iraRegularRepository.save(iraRegular1);
//			transactionRepository.save(transaction1);
//			transactionRepository.save(transaction2);
//			transactionRepository.save(transaction3);
//			transactionRepository.save(transaction4);
//			transactionRepository.save(transaction5);


			user2.setRoles(roles);
			user2.setAccountHolder(accountHolder2);
			userRepository.save(user2);
			accountHolderRepository.save(accountHolder2);
		};
	}
}
