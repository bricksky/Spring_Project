package com.example.demo.mapper;

import com.example.demo.controller.UserRequest;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Bean;

import java.util.Map;

@Mapper
public interface DemoMapper {
   Map<String, String> getUser(String userId);

   void setUser(UserRequest request);
}

