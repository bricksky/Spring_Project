package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import com.example.demo.service.DemoService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/")
    public void setUser(@RequestBody UserRequest request) {
        demoService.setUser(request);
    }
}
