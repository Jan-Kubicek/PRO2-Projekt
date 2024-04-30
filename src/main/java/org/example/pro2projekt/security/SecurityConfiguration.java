package org.example.pro2projekt.security;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.example.pro2projekt.views.Login;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends VaadinWebSecurity {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers(new AntPathRequestMatcher("/")).permitAll();
            auth.requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("DISPECER");
            auth.requestMatchers(new AntPathRequestMatcher("/client/**")).hasRole("DISPECER");
        });

        super.configure(http);

        setLoginView(http, Login.class, "/");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}