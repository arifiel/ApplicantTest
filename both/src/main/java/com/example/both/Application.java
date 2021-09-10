package com.example.both;

import com.example.first.client.TimeClient;
import com.example.second.TimeController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.example.first.controller")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public TimeClient timeClient() {
        return new TimeClientImpl(new TimeController());
    }

    static class TimeClientImpl implements TimeClient {
        private TimeController timeController;

        TimeClientImpl(TimeController controller) {
            this.timeController = controller;
        }

        @Override
        public String time() {
            return timeController.time();
        }
    }

}
