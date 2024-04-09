package org.example.pro2projekt.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.example.pro2projekt.service.CustomDispecerDetailsSevice;
import org.example.pro2projekt.service.CustomUserDetailsService;
import org.example.pro2projekt.service.DispecerService;
import org.example.pro2projekt.service.PasazerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService customUserDetailsService;
    private final CustomDispecerDetailsSevice  customDispecerDetailsSevice;

    @Autowired
    public SecurityConfig(CustomUserDetailsService customUserDetailsService,
                          CustomDispecerDetailsSevice customDispecerDetailsSevice) {
        this.customUserDetailsService = customUserDetailsService;
        this.customDispecerDetailsSevice = customDispecerDetailsSevice;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
        auth.userDetailsService(customDispecerDetailsSevice).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // Specifikace pravidel přístupu k jednotlivým URL
                .antMatchers("/admin/**").hasRole("DISPECER")
                .antMatchers("/admin/clients/**", "/admin/letadla/**", "/admin/letiste/**", "/admin/spolecnost/**").hasRole("DISPECER")
                .antMatchers("/client/**").hasRole("PASAZER")
                .antMatchers("/", "/login", "/register").permitAll() // Všechny mají přístup k těmto URL
                .anyRequest().authenticated() // Všechny ostatní URL vyžadují autentizaci
                .and()
                .formLogin() // Použití formulářového přihlášení
                .and()
                .logout().logoutSuccessUrl("/login").permitAll(); // Nastavení odhlášení
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
