package com.example.projekt;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjektApplication {

    public static void main(String[] args) {
//        SpringApplication.run(ProjektApplication.class, args);
        Application.launch(FXApplication.class, args);

    }

}
