package org.example.pro2projekt.controller;

import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.example.pro2projekt.service.UserDetailsServiceImpl;
import org.example.pro2projekt.views.login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.crypto.SecretKey;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends VaadinWebSecurity {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/").permitAll();
                    auth.requestMatchers("/admin/**").hasRole("DISPECER");
                    auth.requestMatchers("/client/**").hasRole("PASAZER");
                }).logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // Nastavení cesty pro odhlášení
                .logoutSuccessUrl("/") // Cesta, kam se uživatel přesměruje po odhlášení
                .invalidateHttpSession(true) // Invalidace HTTP relace po odhlášení
                .deleteCookies("JSESSIONID")).formLogin((form) -> form.loginPage("/login").loginProcessingUrl("/login").permitAll());
        //setLoginView(http, login.class);
        super.configure(http);
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

}
