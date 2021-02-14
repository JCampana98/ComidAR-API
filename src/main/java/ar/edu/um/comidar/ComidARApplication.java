package ar.edu.um.comidar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import com.dropbox.core.DbxException;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ComidARApplication {

	public static void main(String[] args) throws DbxException {
		SpringApplication.run(ComidARApplication.class, args);
	}

}
