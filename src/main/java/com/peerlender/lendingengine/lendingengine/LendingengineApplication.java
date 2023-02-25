package com.peerlender.lendingengine.lendingengine;

import com.peerlender.lendingengine.lendingengine.domain.model.User;
import com.peerlender.lendingengine.lendingengine.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LendingengineApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	public static void main(String[] args) {
		SpringApplication.run(LendingengineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userRepository.save(new User(1, "John", "B",27, "Software developer"));
		userRepository.save(new User(2, "Nick", "Cena",23, "Software Engineer"));
		userRepository.save(new User(3, "John", "doe",24, "Student"));
	}
}
