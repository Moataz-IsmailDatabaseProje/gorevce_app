package com.gorevce.authentication_service.security;

import com.gorevce.authentication_service.security.filter.JwtAuthenticationFilter;
import com.gorevce.authentication_service.security.filter.RoleAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {



    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final RoleAuthorizationFilter roleAuthorizationFilter;
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public SecurityConfig(UserDetailsService UserDetailsService, JwtAuthenticationFilter jwtAuthenticationFilter, RoleAuthorizationFilter roleAuthorizationFilter) {
        this.userDetailsService = UserDetailsService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.roleAuthorizationFilter = roleAuthorizationFilter;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(


                        auth -> {
                            auth.requestMatchers("/authentication/auth/**").permitAll();
                            auth.requestMatchers("/authentication/change-credential/**").authenticated();
                            auth.requestMatchers("/authentication/**").hasAnyRole("SUPER_ADMIN");
                            auth.anyRequest().authenticated();
                        }
                );
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(roleAuthorizationFilter, JwtAuthenticationFilter.class);
        return http.build();
    }
}
