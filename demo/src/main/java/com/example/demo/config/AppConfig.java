package com.example.demo.config;

import com.example.demo.web.function.Arthmetic;
import com.example.demo.web.function.Bracket;
import com.example.demo.web.function.Check;
import com.example.demo.web.function.Error;
import com.example.demo.web.function.Power;
import com.example.demo.web.function.Root;

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

    @Bean
    public Root root() {
        return new Root();
    }

    @Bean
    public Power power() {
        return new Power();
    }
}
