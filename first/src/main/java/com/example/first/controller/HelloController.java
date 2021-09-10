package com.example.first.controller;

import com.example.first.client.TimeClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final TimeClient timeClient;

    public HelloController(TimeClient timeClient) {
        this.timeClient = timeClient;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        return String.format("Hello %s, current time: %s", name, timeClient.time());
    }
}
