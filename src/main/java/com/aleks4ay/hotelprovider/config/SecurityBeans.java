package com.aleks4ay.hotelprovider.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityBeans {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/image/*/svg").hasRole("HOTEL_CHIEF")
                        .requestMatchers("/image/*").hasAnyRole("HOTEL_CHIEF", "HOTEL_MANAGER")
                        .requestMatchers(HttpMethod.POST, "/hotel").hasRole("HOTEL_CHIEF")
                        .requestMatchers(HttpMethod.GET, "/hotel").hasAnyRole("HOTEL_CHIEF", "HOTEL_MANAGER")
                        .requestMatchers("/hotel/**").hasAnyRole("HOTEL_CHIEF", "HOTEL_MANAGER", "HOTEL_CUSTOMER")
                        .requestMatchers(HttpMethod.POST, "/room").hasRole("HOTEL_CHIEF")
                        .requestMatchers(HttpMethod.GET, "/room").hasAnyRole("HOTEL_CHIEF", "HOTEL_MANAGER")
                        .requestMatchers("/room/**").hasAnyRole("HOTEL_CHIEF", "HOTEL_MANAGER", "HOTEL_CUSTOMER")
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults())
                );

        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        var converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(new KeycloakRealmRoleConverter());
        return converter;
    }
}
