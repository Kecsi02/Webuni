package logistics.mate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import logistics.mate.service.InitdbService;
import logistics.mate.config.LogisticsConfigProperties;

import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class LogisticsApplication implements CommandLineRunner{
	@Override
	public void run(String... args) throws Exception {
		if (!config.isTest()) {
			initdbService.initTP();
		}
	}
	@Autowired
	LogisticsConfigProperties config;
	@Autowired
	InitdbService initdbService;
	public static void main(String[] args) {
		SpringApplication.run(LogisticsApplication.class, args);
	}
}
