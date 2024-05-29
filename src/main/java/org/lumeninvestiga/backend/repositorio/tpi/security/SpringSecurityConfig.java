package org.lumeninvestiga.backend.repositorio.tpi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.Arrays;
import java.util.List;

import static org.lumeninvestiga.backend.repositorio.tpi.security.TokenJwtConfig.CONTENT_TYPE;
import static org.lumeninvestiga.backend.repositorio.tpi.security.TokenJwtConfig.HEADER_AUTHORIZATION;

@Configuration
public class SpringSecurityConfig {
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity.authorizeHttpRequests((authz) -> authz
//                        .requestMatchers(HttpMethod.GET, "/users").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/users/{id}").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/users").permitAll()
//                        .requestMatchers(HttpMethod.PUT, "/users").permitAll()
//                        .requestMatchers(HttpMethod.PUT, "/users/{id}").permitAll()
//                        .requestMatchers(HttpMethod.DELETE, "/users").permitAll()
//                        .requestMatchers(HttpMethod.DELETE, "/users/{id}").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/reviews").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/reviews/{id}").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/reviews").permitAll()
//                        .requestMatchers(HttpMethod.PUT, "/reviews").permitAll()
//                        .requestMatchers(HttpMethod.PUT, "/reviews/{id}").permitAll()
//                        .requestMatchers(HttpMethod.DELETE, "/reviews").permitAll()
//                        .requestMatchers(HttpMethod.DELETE, "/reviews/{id}").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/files").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/files/{id}").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/files").permitAll()
//                        .requestMatchers(HttpMethod.PUT, "/files/{id}").permitAll()
//                        .requestMatchers(HttpMethod.PUT, "/files").permitAll()
//                        .requestMatchers(HttpMethod.DELETE, "/files").permitAll()
//                        .requestMatchers(HttpMethod.DELETE, "/files/{id}").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/folders").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/folders/{id}").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/folders").permitAll()
//                        .requestMatchers(HttpMethod.PUT, "/folders").permitAll()
//                        .requestMatchers(HttpMethod.PUT, "/folders/{id}").permitAll()
//                        .requestMatchers(HttpMethod.DELETE, "/folders").permitAll()
//                        .requestMatchers(HttpMethod.DELETE, "/folders/{id}").permitAll()
//                        .anyRequest()
//                        .authenticated()
//                )
//                .csrf(AbstractHttpConfigurer::disable)
//                //configuracion de frontend
//                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .build();
//    }
//
//    //Frontend
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration config = new CorsConfiguration();
//
//        config.setAllowedOriginPatterns(List.of("*"));
//        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
//        config.setAllowedHeaders(Arrays.asList(HEADER_AUTHORIZATION, CONTENT_TYPE));
//        config.setAllowCredentials(true);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config);
//        return source;
//    }
}
