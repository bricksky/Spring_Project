package com.example.demo.service;

import com.example.demo.mapper.DemoMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DemoService {
    private final DemoMapper demoMapper;

    public DemoService(DemoMapper demoMapper) {
        this.demoMapper = demoMapper;
    }

    public Map<String, String> getUser(String userId) {
        return demoMapper.getUser(userId);
    }
}
