package com.peerlending.profile;

import com.peerlending.profile.domain.model.User;
import com.peerlending.profile.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class ProfileApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	public static void main(String[] args) {
		SpringApplication.run(ProfileApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userRepository.save(
				new User("John","John", "Doe", 22,"Fighter", LocalDate.now()));
	}
}
