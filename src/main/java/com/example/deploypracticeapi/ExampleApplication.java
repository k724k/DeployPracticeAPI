package com.example.deploypracticeapi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ExampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }

    @RestController
    class ExampleController {

        @GetMapping("/")
        public String helloWorld() {
            return "Hello World!";
        }

        @GetMapping("/health")
        public ResponseEntity<String> healthCheck() {
            return ResponseEntity.ok("Success Health Check");
        }

    }
}
