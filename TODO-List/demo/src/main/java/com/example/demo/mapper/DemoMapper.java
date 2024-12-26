package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Bean;

import java.util.Map;

@Mapper
public interface DemoMapper {
   Map<String, String> getUser(String userId);
}
