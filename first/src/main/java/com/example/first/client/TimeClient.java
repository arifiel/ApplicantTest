package com.example.first.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("second")
public interface TimeClient {

    @GetMapping("/time")
    String time();
}
