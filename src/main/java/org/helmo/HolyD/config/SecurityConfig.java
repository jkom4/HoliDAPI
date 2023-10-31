package org.helmo.HolyD.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        return security.httpBasic().disable()
                .csrf().disable().authorizeRequests().anyRequest().permitAll()
                .and().formLogin().disable().build(); // Works for GET, POST, PUT, DELETE

    }
}

