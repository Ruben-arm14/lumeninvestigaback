package org.lumeninvestiga.backend.repositorio.tpi.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
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
import org.springframework.web.filter.CorsFilter;

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
        config.setAllowedHeaders(Arrays.asList(HEADER_AUTHORIZATION, CONTENT_TYPE));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> corsBean =
                new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource()));
        corsBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return corsBean;
    }

    private String[] getPublicGetEndpoints() {
        return new String[]{
                "/api/v1/users",
                "/api/v1/users/{user_id}",
                "/api/v1/reviews",
                "/api/v1/reviews",
                "/api/v1/reviews/{article_id}",
                "/api/v1/articles",
                "/api/v1/articles/search",
                "/api/v1/articles/{article_id}"
        };
    }

    private String[] getPublicPostEndpoints() {
        return new String[]{
                "/api/v1/users/register",
                "/api/v1/users/login",
                "/api/v1/articles/upload",
                "/api/v1/articles/{article_id}/reviews/{user_id}"
        };
    }

    private String[] getPublicPutEndpoints() {
        return new String[]{
                "/api/v1/users/{user_id}",
                "/api/v1/articles/{article_id}",
                "/api/v1/articles/{article_id}/{review_id}"
        };
    }

    private String[] getPublicDeleteEndpoints() {
        return new String[]{
                "/api/v1/users/{user_id}",
                "/api/v1/reviews/{review_id}",
                "/api/v1/articles/{article_id}"
        };
    }
}
