package com.programacion.proyectofinal.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    /**
     * Permite el acceso público al endpoint "/api/saludo" sin autenticación.
     * Requiere autenticación para los endpoints "/api/productos" y "/api/productos/**".
     * Habilita autenticación básica para proteger los recursos.
     */
    @Bean
    public SecurityWebFilterChain defineSecurityRules(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/api/saludo").permitAll()
                        .pathMatchers("/api/productos").authenticated()
                        .pathMatchers("/api/productos/**").authenticated()
                        .anyExchange().authenticated())
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("mistfist")
                .password(passwordEncoder().encode("ContraseñaSegura"))
                .roles("USER")
                .build();

        return new MapReactiveUserDetailsService(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Secure password encoding
    }
}

