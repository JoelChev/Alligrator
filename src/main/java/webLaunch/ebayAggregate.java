package webLaunch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author JoelChev
 * 
 * This is the SpringBoot launcher class. It simply starts the Web Servler for the API.
 *
 */
@SpringBootApplication
public class ebayAggregate {

    public static void main(String[] args) {
        SpringApplication.run(ebayAggregate.class, args);
    }
}