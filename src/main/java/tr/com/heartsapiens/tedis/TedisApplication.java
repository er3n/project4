    package tr.com.heartsapiens.tedis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Locale;


@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class TedisApplication {

	public static void main(String[] args) {

		Locale.setDefault(Locale.ENGLISH);

		SpringApplication.run(TedisApplication.class, args);
	}

}
