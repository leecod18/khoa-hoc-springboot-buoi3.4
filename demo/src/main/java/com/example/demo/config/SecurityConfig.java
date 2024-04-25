package com.example.demo.config;

import com.example.demo.oauth2.converter.CustomJwtGranteAuthoritiesConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    protected final String clientId;

    public SecurityConfig(@Value("${app.oauth2.client-id}") String clientId) {
        this.clientId = clientId;
    }


    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{

        return  httpSecurity
                .authorizeHttpRequests(
                        reg -> reg.requestMatchers(HttpMethod.GET, "/products/*").permitAll().
                                requestMatchers(HttpMethod.POST, "/products").authenticated()
                                .anyRequest().authenticated() 
                )
                .oauth2ResourceServer(conf -> conf.jwt(Customizer.withDefaults()))
                .build();
    }
    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter(){
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(new CustomJwtGranteAuthoritiesConverter(clientId));
        return converter;
    }

}
