package ar.edu.um.comidar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.users.FullAccount;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ComidARApplication {

    private static final String ACCESS_TOKEN = "Mt5grMVKopAAAAAAAAAAVTnhikFaKMyvzJRsxm8mzOTbXVI5iY9jYhHAeLGmbhrn";
    
	public static void main(String[] args) throws DbxException {
		SpringApplication.run(ComidARApplication.class, args);
		// Create Dropbox client
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
	}

}
