package com.example.licentademo.Config;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final CustomCorsFilter customCorsFilter;

    @Primary
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .csrf(AbstractHttpConfigurer::disable)
                .addFilterAfter(customCorsFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(req ->
                        req


                                .requestMatchers("/review")
                                .authenticated()
                                .requestMatchers("/auth/**")
                                .permitAll()
                                .requestMatchers("/update")
                                .permitAll()
                                .anyRequest()
                                .permitAll()

                )
                .logout((logout) ->
                        logout.logoutSuccessHandler(
                                        (httpServletRequest, httpServletResponse, authentication) -> {
                                            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
                                            System.out.println(httpServletRequest.getHeaderNames());
                                            httpServletResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
                                        })
                                .permitAll()


                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider);

        return http.build();
    }
}
