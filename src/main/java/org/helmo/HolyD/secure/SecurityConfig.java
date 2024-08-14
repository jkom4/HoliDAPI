package org.helmo.HolyD.secure;

import org.helmo.HolyD.secure.jwt.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private static final String[] SWAGGER_LIST = {
            "/doc",
            "/v2/**",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/webjars/**",
            "favicon.ico"};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security, AuthenticationProvider authenticationProvider, JwtFilter jwtFilter) throws Exception {
        return security
                .cors().and()
                .formLogin().disable()
                .httpBasic().disable()
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers(HttpMethod.POST,"/user/signup").permitAll()
                    .antMatchers(HttpMethod.POST, "/user/signin").permitAll()
                    .antMatchers(HttpMethod.POST, "/user/signinWithProvider").permitAll()
                    .antMatchers(HttpMethod.GET, "/user/byHoliday").permitAll()
                    .antMatchers(SWAGGER_LIST).permitAll()
                    .anyRequest().authenticated()
                .and().sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }
    @Bean
    public Argon2PasswordEncoder passwordEncoder() {
        return new Argon2PasswordEncoder(16, 32, 1, 4096, 3);
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, Argon2PasswordEncoder passwordEncoder){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return daoAuthenticationProvider;
    }
}

