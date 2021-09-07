package fptu.phuochg.library;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import fptu.phuochg.library.config.StorageProperties;
import fptu.phuochg.library.service.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class PhuochgLibraryManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhuochgLibraryManagementApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args->{
			storageService.init();
		});
	}
}
