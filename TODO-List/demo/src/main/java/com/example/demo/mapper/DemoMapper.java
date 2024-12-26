package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface DemoMapper {
    Map<String, String> getUser(String userId);
}
