package com.example.Matches.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    @GetMapping("/healthcheck")
    public String healthCheck() {
        return "OK";
    }
}