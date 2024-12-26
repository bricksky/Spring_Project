package com.example.demo.controller;

import com.example.demo.service.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DemoController {

    // demoService 호출하는 부분
    private final DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/{userId}")
    public Map<String, String> getUser(@PathVariable("userId") String userId) {
        return demoService.getUser(userId);
    }
}
