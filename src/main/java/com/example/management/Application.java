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


	public void init(AppRepository appRepository){
		System.out.println("saving init ... ");
		appRepository.save(new AppUser("yj6ley", "Radhi"));
		appRepository.save(new AppUser("YJ6LEY", "radhi"));
	}
}
@Component
class DataInitializer implements CommandLineRunner {

	private final AppRepository appRepository;

	public DataInitializer(AppRepository userRepository) {
		this.appRepository = userRepository;
	}

	@Override
	public void run(String... args) {
		System.out.println("saving init ... ");
		appRepository.save(new AppUser("yj6ley", "Radhi"));
		appRepository.save(new AppUser("YJ6LEY", "radhi"));
	}
}

