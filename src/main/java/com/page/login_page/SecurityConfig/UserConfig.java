//package com.page.login_page.SecurityConfig;
//
//import com.page.login_page.reposetory.UserRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.SecurityProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class UserConfig {
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService(){
//
//
//
//        UserDetails adminUser = User
//                .withUsername("Gaurish")
//                .password(passwordEncoder().encode("Atal"))
//                .roles("ADMIN").build();
//
//
//        UserDetails normalUser = User
//                .withUsername("Ambesh")
//                .password(passwordEncoder().encode("Kuvar"))
//                .roles("NORMAL").build();
//
//        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(adminUser, normalUser);
//        return inMemoryUserDetailsManager;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//           http
//                   .authorizeHttpRequests()
//                   .anyRequest()
//                   .authenticated()
//                   .and()
//                   .httpBasic();
//
//           return http.build();
//    }
//}