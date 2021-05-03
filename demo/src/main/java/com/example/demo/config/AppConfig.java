package com.example.demo.config;

import com.example.demo.function.Arthmetic;
import com.example.demo.function.Bracket;
import com.example.demo.function.Check;
import com.example.demo.function.Error;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    
    @Bean
    public Arthmetic arthmetic() {
        return new Arthmetic();
    }
    
    @Bean
    public Bracket bracket() {
        return new Bracket();
    }

    @Bean
    public Check check() {
        return new Check();
    }

    @Bean
    public Error error() {
        return new Error();
    }
}
