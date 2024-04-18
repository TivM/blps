package com.blps.demo.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.blps.demo.user.Role.*;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers("/auth/**")
                                .permitAll()

                                .requestMatchers("/carts").hasRole(CLIENT.name())
                                .requestMatchers("/carts/**").hasRole(CLIENT.name())
                                .requestMatchers("/items").hasRole(SELLER.name())
                                .requestMatchers("/items/filter").hasAnyRole(SELLER.name(), CLIENT.name())
                                .requestMatchers("/orders/payment").hasRole(CLIENT.name())
                                .requestMatchers("/orders/payment/**").hasRole(PICKUP_POINT_EMPLOYEE.name())
                                .requestMatchers("/orders").hasRole(CLIENT.name())
                                .requestMatchers(POST, "/orders/**").hasAnyRole(PICKUP_POINT_EMPLOYEE.name(), SELLER.name())
                                .requestMatchers(GET, "/orders/**").hasAnyRole(CLIENT.name(),PICKUP_POINT_EMPLOYEE.name())

                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
