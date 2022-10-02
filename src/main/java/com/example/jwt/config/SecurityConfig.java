package com.example.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        // Configure AuthenticationManagerBuilder
        AuthenticationManagerBuilder authenticationManagerBuilder = http
                .getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);

        // Get AuthenticationManager
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().anyRequest().permitAll();
        http.addFilter(new AuthenticationFilerConfig(authenticationManager));

        // http
        // .csrf().disable()
        // .authorizeRequests()
        // .anyRequest()
        // .permitAll()

        // // User Authentication with custom login URL path
        // // .addFilter(getAuthenticationFilter(authenticationManager))

        // // User Authorization with JWT
        // .addFilter(new AuthenticationFilerConfig(authenticationManager))
        // // .authenticationManager(authenticationManager)

        // .sessionManagement()
        // .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }

    // protected AuthenticationFilter getAuthenticationFilter(AuthenticationManager
    // authenticationManager)
    // throws Exception {
    // final AuthenticationFilter filter = new
    // AuthenticationFilter(authenticationManager);
    // filter.setFilterProcessesUrl("/users/login");
    // return filter;
    // }
}
