package org.lumeninvestiga.backend.repositorio.tpi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import java.util.Arrays;
import java.util.List;

import static org.lumeninvestiga.backend.repositorio.tpi.security.TokenJwtConfig.CONTENT_TYPE;
import static org.lumeninvestiga.backend.repositorio.tpi.security.TokenJwtConfig.HEADER_AUTHORIZATION;

@Configuration
public class SpringSecurityConfig {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(HttpMethod.GET, "/users", "/users/{id}", "/reviews", "/reviews/{id}", "/files", "/files/{id}", "/folders", "/folders/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/users", "/reviews", "/files", "/folders").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/users", "/users/{id}", "/reviews", "/reviews/{id}", "/files", "/files/{id}", "/folders", "/folders/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/users", "/users/{id}", "/reviews", "/reviews/{id}", "/files", "/files/{id}", "/folders", "/folders/{id}").permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(List.of("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
