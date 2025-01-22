package in.ironvalleyagro.Agronomy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
@ConfigurationPropertiesScan
public class AgronomyApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgronomyApplication.class, args);
	}

}
