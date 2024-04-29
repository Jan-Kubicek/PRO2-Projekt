package org.example.pro2projekt.views;

import ch.qos.logback.core.boolex.Matcher;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.spring.security.AuthenticationContext;
import jakarta.annotation.PostConstruct;
import org.example.pro2projekt.controller.dataInput;
import org.example.pro2projekt.objects.Dispecer;
import org.example.pro2projekt.objects.Pasazer;
import org.example.pro2projekt.service.DispecerService;
import org.example.pro2projekt.service.DispecerServiceImpl;
import org.example.pro2projekt.service.PasazerService;
import org.example.pro2projekt.service.PasazerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@PageTitle("login")
@Route("/login")
@AnonymousAllowed
public class login extends VerticalLayout implements BeforeEnterObserver {

    private final LoginForm loginForm = new LoginForm();
    @Autowired
    private PasazerServiceImpl pasazerService;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private UserDetails user;
    private AuthenticationContext authContext;
    public login(AuthenticationContext authContext) {
        this.authContext = authContext;
        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        add(new H1("JKLetenky Login"), loginForm);

        Button btnZpet = new Button("Zpět na hlavní stránku", event -> {
            getUI().ifPresent(ui -> ui.navigate("/"));
        });
        loginForm.setAction("login");
        add(btnZpet);

        loginForm.addLoginListener(e -> {
            authenticate(e.getUsername(), e.getPassword());
        });
    }

    private void authenticate(String username, String password) {
        // Načtení uživatelských údajů na základě uživatelského jména
        UserDetails userDetails = loadUserByUsername(username);
        if (userDetails != null) {
            // Uživatel nalezen
            boolean passwordMatch = bCryptPasswordEncoder.matches(password, userDetails.getPassword());
            if (passwordMatch) {
                // Přihlášení úspěšné
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                user = userDetails;
                navigateToNextPage();
            } else {
                // Nesprávné heslo
                Notification.show("Neplatné přihlašovací údaje", 3000, Notification.Position.TOP_CENTER);
            }
        } else {
            // Uživatel nenalezen
            Notification.show("Neplatné přihlašovací údaje", 3000, Notification.Position.TOP_CENTER);
        }
    }

    private UserDetails loadUserByUsername(String username) {
        Pasazer pasazer = null;
        try{
            pasazer =  pasazerService.findByEmail(username);
        }catch (Exception e){
            pasazer = null;
        }
        if(pasazer != null && (pasazer.getTyp_pasazeraID() != 6)){
            return new User(pasazer.getEmail(), pasazer.getPassword(), getAuthorities("PASAZER"));
        } else if (pasazer != null && pasazer.getTyp_pasazeraID() == 6) {
            return new User(pasazer.getEmail(), pasazer.getPassword(), getAuthorities("DISPECER"));
        }
        return null;
    }

    private List<GrantedAuthority> getAuthorities(String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return authorities;
    }

    private void navigateToNextPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_DISPECER"))) {
                VaadinSession vaadinSession = VaadinSession.getCurrent();
                vaadinSession.setAttribute("loggedInUser",user);
                getUI().ifPresent(ui -> ui.navigate("/admin"));
            } else {
                VaadinSession vaadinSession = VaadinSession.getCurrent();
                vaadinSession.setAttribute("loggedInUser",user);
                getUI().ifPresent(ui -> ui.navigate("/client"));
            }
        }
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if(beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            loginForm.setError(true);
        }
    }
}