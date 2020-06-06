    package tr.com.heartsapiens.tedis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Locale;


	@SpringBootApplication
public class TedisApplication {

	public static void main(String[] args) {

		Locale.setDefault(Locale.ENGLISH);

		SpringApplication.run(TedisApplication.class, args);
	}

}
