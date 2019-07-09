package dev.ione.dob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement

public class DobApplication {

    public static void main(String[] args) {
        SpringApplication.run(DobApplication.class, args);
    }

}
