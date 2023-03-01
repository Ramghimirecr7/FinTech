package com.peerlender.lendingengine;

import com.peerlender.lendingengine.domain.repository.UserRepository;
import com.peerlender.lendingengine.domain.model.User;
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
		userRepository.save(new User("John", "John", "Brick",27, "Software developer"));
		userRepository.save(new User("Nick", "Nick", "Cena",23, "Software Engineer"));
		userRepository.save(new User("Jasmine", "Jasmine", "D oe",24, "Student"));
	}
}
