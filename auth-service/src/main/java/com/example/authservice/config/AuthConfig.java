package com.example.authservice.config;

import com.example.authservice.repository.UserrRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AuthConfig{

    private final UserrRepository userrRepository;

    public AuthConfig(UserrRepository userrRepository) {
        this.userrRepository = userrRepository;
    }

    public UserDetailsService userDetailsService(){
        return new CustomUserDetailsService(userrRepository);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/auth/register", "/auth/token", "/auth/validate").permitAll()
                .and()
                .build();
    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return web -> web.debug(false)
//                .ignoring()
//                .requestMatchers(
//                        "/api/v3/api-docs/swagger-config",
//                        "/v3/api-docs/**",
//                        "/api/v3/api-docs",
//                        "/v3/api-docs",
//                        "/configuration/ui",
//                        "/swagger-resources/**",
//                        "/configuration/security",
//                        "/swagger-ui.html",
//                        "/swagger-ui/**",
//                        "/swagger-config",
//                        "/swagger-ui/index.html",
//                        "/api/docs/**",
//                        "/api/swagger-ui",
//                        "/api/swagger-ui/**",
//                        "/api/management",
//                        "/webjars/**",
//                        "/h2-console/**",
//                        "/api/versions/auth/login",
//                        "/api/versions/1/auth/validate",
//                        "/auth/token"
//                );
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
