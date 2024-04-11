package org.example.pro2projekt.views;

import ch.qos.logback.core.boolex.Matcher;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.PostConstruct;
import org.example.pro2projekt.controller.dataInput;
import org.example.pro2projekt.objects.Dispecer;
import org.example.pro2projekt.objects.Pasazer;
import org.example.pro2projekt.service.DispecerService;
import org.example.pro2projekt.service.DispecerServiceImpl;
import org.example.pro2projekt.service.PasazerService;
import org.example.pro2projekt.service.PasazerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
    private final UserDetailsService userDetailsService;
    @Autowired
    private DispecerServiceImpl dispecerService;
    @Autowired
    private PasazerServiceImpl pasazerService;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public login(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;

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
        Pasazer pasazer = pasazerService.findByEmail(username);
        Dispecer dispecer = dispecerService.findByEmail(username);

        if (dispecer != null || pasazer != null) {
            // Uživatel nalezen, zjistíme, jestli je to dispečer nebo pasažér
            UserDetails userDetails;
            String role;
            if (dispecer != null) {
                userDetails = dispecer;
                role = "DISPECER";
            } else {
                userDetails = pasazer;
                role = "PASAZER";
            }
            System.out.println(role);
            boolean passwordMatch = bCryptPasswordEncoder.matches(password, userDetails.getPassword());
            if (passwordMatch) {
                // Přihlášení úspěšné
                // Zde můžete přidat roli do userDetails, pokud je to potřeba
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(role));
                userDetails = new User(userDetails.getUsername(), userDetails.getPassword(), authorities);
                if(dispecer!=null){
                    getUI().ifPresent(ui -> ui.navigate("/admin"));
                }else{
                    getUI().ifPresent(ui -> ui.navigate("/client"));
                }
            } else {
                // Nesprávné heslo
                Notification.show("Neplatné přihlašovací údaje", 3000, Notification.Position.TOP_CENTER);

            }
        } else {
            // Uživatel nenalezen
            Notification.show("Neplatné přihlašovací údaje", 3000, Notification.Position.TOP_CENTER);

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