package com.peerlender.lendingengine;

import com.peerlender.lendingengine.domain.model.Balance;
import com.peerlender.lendingengine.domain.model.Currency;
import com.peerlender.lendingengine.domain.model.Money;
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
		User john = new User("John", "John", "Brick",27, "Software developer", new Balance());
		User nick = new User("Nick", "Nick", "Cena",23, "Software Engineer", new Balance());
		User jasmine = new User("Jasmine", "Jasmine", "Doe",24, "Student", new Balance());
		User peter = new User("Peter", "Peter", "Doe", 33, "Manager", new Balance());
		john.topUp(new Money(100, Currency.USD));
		nick.topUp(new Money(200, Currency.USD));
		jasmine.topUp(new Money(300,Currency.USD));
		peter.topUp(new Money(200, Currency.USD));
		userRepository.save(nick);
		userRepository.save(jasmine);
		userRepository.save(john);
		userRepository.save(peter);
	}
}
