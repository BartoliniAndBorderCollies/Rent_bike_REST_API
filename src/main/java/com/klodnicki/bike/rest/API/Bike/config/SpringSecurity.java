package com.klodnicki.bike.rest.API.Bike.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

@Bean
public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {

return httpSecurity.authorizeHttpRequests(request -> request
        .requestMatchers("/api/admin/**").authenticated()
        .requestMatchers("/api/user/**").authenticated()
        .anyRequest()
        .permitAll())

        .build();

}

}