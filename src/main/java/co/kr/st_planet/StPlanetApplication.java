package co.kr.st_planet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StPlanetApplication {
	private static final Logger logger = LoggerFactory.getLogger(StPlanetApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(StPlanetApplication.class, args);
	}
}
