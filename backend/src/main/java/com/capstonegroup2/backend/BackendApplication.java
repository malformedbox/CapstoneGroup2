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
										  IraRothRepository iraRothRepository,
										  PersonalCheckingRepository personalCheckingRepository,
										  SavingsAccountRepository savingsAccountRepository,
										  TransactionRepository transactionRepository) {
		return (args) -> {

			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			Set<Role> roles = new HashSet<>();
			Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
			roles.add(userRole);


			UserCredentials user1 = new UserCredentials
					("Damateau00", passwordEncoder.encode("password00"));
			AccountHolder accountHolder1 = new AccountHolder
					("David", "Michael", "Amateau", "123456789",
							user1);

			PersonalChecking personalChecking1 = new PersonalChecking("15000");
			IraRegular iraRegular1 = new IraRegular("40000");
			IraRoth iraRoth1 = new IraRoth("60000");
			SavingsAccount savingsAccount1 = new SavingsAccount("35000");

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
			PersonalChecking personalChecking2 = new PersonalChecking("20000");
			IraRegular iraRegular2 = new IraRegular("75000");
			IraRoth iraRoth2 = new IraRoth("100000");


			user1.setRoles(roles);
			user1.setAccountHolder(accountHolder1);

			accountHolder1.setPersonalChecking(personalChecking1);
			accountHolder1.setIraRegular(iraRegular1);
			accountHolder1.setIraRoth(iraRoth1);
			accountHolder1.setSavingsAccount(savingsAccount1);

			personalChecking1.setAccountHolder(accountHolder1);
			iraRegular1.setAccountHolder(accountHolder1);
			iraRoth1.setAccountHolder(accountHolder1);
			savingsAccount1.setAccountHolder(accountHolder1);

			userRepository.save(user1);
			accountHolderRepository.save(accountHolder1);
			personalCheckingRepository.save(personalChecking1);
			iraRothRepository.save(iraRoth1);
			iraRegularRepository.save(iraRegular1);
			savingsAccountRepository.save(savingsAccount1);

			Transaction closeAccount1 = accountHolder1.closeAccount(personalChecking1);

			transactionRepository.save(transaction1);
			transactionRepository.save(transaction2);
			transactionRepository.save(transaction3);
			transactionRepository.save(transaction4);
			transactionRepository.save(transaction5);
			transactionRepository.save(closeAccount1);

			user2.setRoles(roles);
			user2.setAccountHolder(accountHolder2);
			accountHolder2.setPersonalChecking(personalChecking2);
			accountHolder2.setIraRegular(iraRegular2);
			accountHolder2.setIraRoth(iraRoth2);
			personalChecking2.setAccountHolder(accountHolder2);
			iraRegular2.setAccountHolder(accountHolder2);
			iraRoth2.setAccountHolder(accountHolder2);


			userRepository.save(user2);
			accountHolderRepository.save(accountHolder2);
			personalCheckingRepository.save(personalChecking2);
			iraRegularRepository.save(iraRegular2);
			iraRothRepository.save(iraRoth2);
		};
	}
}
