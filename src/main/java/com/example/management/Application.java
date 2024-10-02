package com.example.management;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	public void init(UserRepository userRepository){
		System.out.println("saving init ... ");
		userRepository.save(new AppUser("yj6ley", "Radhi"));
		userRepository.save(new AppUser("YJ6LEY", "radhi"));
	}
}
@Component
class DataInitializer implements CommandLineRunner {

	private final UserRepository userRepository;

	public DataInitializer(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void run(String... args) {
		System.out.println("saving init ... ");
		userRepository.save(new AppUser("yj6ley", "Radhi"));
		userRepository.save(new AppUser("YJ6LEY", "radhi"));
	}
}

