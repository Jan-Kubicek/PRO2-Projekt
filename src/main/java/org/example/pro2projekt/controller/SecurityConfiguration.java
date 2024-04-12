package org.example.pro2projekt.controller;

import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.VaadinSessionState;
import com.vaadin.flow.spring.scopes.VaadinSessionScope;
import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.example.pro2projekt.service.DispecerService;
import org.example.pro2projekt.service.PasazerService;
import org.example.pro2projekt.service.UserDetailsServiceImpl;
import org.example.pro2projekt.views.login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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

    private static final String LOGIN_PROCESSING_URL = "/login";
    private static final String LOGIN_FAILURE_URL = "/login?error";
    private static final String LOGIN_URL = "/login";
    private static final String LOGOUT_SUCCESS_URL = "/login";

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
                .deleteCookies("JSESSIONID"));

        super.configure(http);
        setLoginView(http, login.class);
    }
    /*
    Button logoutButton = new Button("Odhlásit se");
    logoutButton.addClickListener(event -> {
    // Po kliknutí na tlačítko se provede odhlášení
    UI.getCurrent().getPage().executeJs("window.location.href = '/logout';");
    });
     */
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