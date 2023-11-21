package com.cclucky.metabubble.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EntityScan(basePackages = {"com.cclucky.metabubble.server.pojo.entity"})
@EnableJpaRepositories(basePackages = {"com.cclucky.metabubble.server.repository"})
@EnableScheduling
public class MetabubbleServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetabubbleServerApplication.class, args);
    }
//    public static void main(String[] args) {
//        String pass = "1234";
//        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
//        String hashPass = bcryptPasswordEncoder.encode(pass);
//        System.out.println(hashPass);
//
//        boolean f = bcryptPasswordEncoder.matches("1234",hashPass);
//        System.out.println(f);
//    }

}
