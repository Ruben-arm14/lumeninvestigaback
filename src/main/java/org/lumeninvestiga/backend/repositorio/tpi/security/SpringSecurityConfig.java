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
                        .requestMatchers(HttpMethod.GET, getPublicGetEndpoints()).permitAll()
                        .requestMatchers(HttpMethod.POST, getPublicPostEndpoints()).permitAll()
                        .requestMatchers(HttpMethod.PUT, getPublicPutEndpoints()).permitAll()
                        .requestMatchers(HttpMethod.DELETE, getPublicDeleteEndpoints()).permitAll()
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

    private String[] getPublicGetEndpoints() {
        return new String[]{
                "/api/users", "/api/users/{id}",
                "/api/reviews", "/api/reviews/{id}",
                "/api/files", "/api/files/{id}",
                "/api/folders", "/api/folders/{id}",

                "/api/articles", "/api/articles/{name}",
                "/api/articles/{article_id}", "/api/articles/search/{name}"

        };
    }

    private String[] getPublicPostEndpoints() {
        return new String[]{
                "/api/users", "/api/reviews", "/api/files", "/api/folders",
                "/api/users/login",

                "/api/articles/upload"
        };
    }

    private String[] getPublicPutEndpoints() {
        return new String[]{
                "/api/users", "/api/users/{id}",
                "/api/reviews", "/api/reviews/{id}",
                "/api/files", "/api/files/{id}",
                "/api/folders", "/api/folders/{id}",

                "/api/articles/{article_id}"

        };
    }

    private String[] getPublicDeleteEndpoints() {
        return new String[]{
                "/api/users", "/api/users/{id}",
                "/api/reviews", "/api/reviews/{id}",
                "/api/files", "/api/files/{id}",
                "/api/folders", "/api/folders/{id}",

                "/api/articles/{article_id}"
        };
    }
}
