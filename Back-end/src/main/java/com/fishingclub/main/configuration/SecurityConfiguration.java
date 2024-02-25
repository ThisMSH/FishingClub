package com.fishingclub.main.configuration;

import com.fishingclub.main.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final UserDetailsService memberService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(request -> request
                        .requestMatchers(
                                new AntPathRequestMatcher("/api/members/login", HttpMethod.POST.name()),
                                new AntPathRequestMatcher("/api/members/register", HttpMethod.POST.name())
                        ) // set the path of auth request or auth controller
                        .permitAll() // allowing the access to the above path without authentication
                )
                .authorizeHttpRequests(request -> request
                        .requestMatchers(
                                new AntPathRequestMatcher("/api/members/update", HttpMethod.PUT.name()),
                                new AntPathRequestMatcher("/api/members/delete/{code}", HttpMethod.DELETE.name())
                        )
                        .hasAnyAuthority(UserRole.MANAGER.name())
                )
                .authorizeHttpRequests(request -> request
                        .requestMatchers(
                                new AntPathRequestMatcher("/api/competitions/add", HttpMethod.POST.name()),
                                new AntPathRequestMatcher("/api/competitions/update", HttpMethod.PUT.name()),
                                new AntPathRequestMatcher("/api/competitions/delete/{code}", HttpMethod.DELETE.name()),
                                new AntPathRequestMatcher("/api/ranking/**"),
                                new AntPathRequestMatcher("/api/hunting/**")
                        )
                        .hasAnyAuthority(UserRole.MANAGER.name(), UserRole.JURY.name())
                )
                .authorizeHttpRequests(request -> request
                        .requestMatchers(
                                new AntPathRequestMatcher("/api/ranking/{code}", HttpMethod.GET.name())
                        )
                        .hasAnyAuthority(UserRole.MANAGER.name(), UserRole.JURY.name(), UserRole.MEMBER.name())
                )
                .authorizeHttpRequests(request -> request
                        .anyRequest()
                        .authenticated()
                )
                .sessionManagement(manager -> manager
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(this.authProvider()).addFilterBefore(
                        this.jwtAuthFilter, UsernamePasswordAuthenticationFilter.class
                )
                .build();
    }

    @Bean
    public AuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProv = new DaoAuthenticationProvider();
        authProv.setUserDetailsService(this.memberService);
        authProv.setPasswordEncoder(this.passwordEncoder());

        return authProv;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
