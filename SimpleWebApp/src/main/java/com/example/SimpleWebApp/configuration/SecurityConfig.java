package com.example.SimpleWebApp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

// db code.
//    @Bean
//    public UserDetailsManager dbUserDetailsManager(DataSource dataSource){
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        jdbcUserDetailsManager.setUsersByUsernameQuery("select username, password, enabled from users" +
//                " where username=?");
//        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select username, authorities from users" +
//                " where username=?");
//        return jdbcUserDetailsManager;
//    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configure -> configure
                        .requestMatchers("/signup").permitAll()
                        .requestMatchers("/processRegistrationForm").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form.loginPage("/loginPage")
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll()
                )
                .logout(logout -> logout.permitAll());
        return http.build();
    }

}