package com.example.restServiceHospital;

import com.example.restServiceHospital.api.model.Hospital;
import com.example.restServiceHospital.api.repository.HospitalRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootApplication
public class SpringApiApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringApiApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(SpringApiApplication.class, args);
	}


	@Bean
	public CommandLineRunner demo2(HospitalRepository repository) {
		return (args) -> {

//			repository.save(new Driver("Ahmed","123 ss","+201011575147","6 minutes",7.0f,9.0f));
//			repository.insertData("Omar","259 bas","+201115483248",120.0f,125.0f);
//			repository.insertData("Shehab","259 bas","+201115483248",120.0f,125.0f);

			log.info("Hospitals found with findAll():");
			log.info("-------------------------------");
			for (Hospital hospital : repository.findAvailableHospitals()) {
				log.info(hospital.toString());
			}
			log.info("");
		};
	}
}
