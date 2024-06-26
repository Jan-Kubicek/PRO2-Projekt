package org.example.pro2projekt.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.example.pro2projekt.objects.Pasazer;
import org.example.pro2projekt.service.PasazerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@PageTitle("login")
@Route("/login")
@AnonymousAllowed
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private final LoginForm loginForm = new LoginForm();
    private final PasazerServiceImpl pasazerService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private UserDetails user;

    @Autowired
    public LoginView(PasazerServiceImpl pasazerService) {
        this.pasazerService = pasazerService;
        addClassName("login-view");
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

        Button btnZpet = new Button("Zpět na hlavní stránku", event -> getUI().ifPresent(ui -> ui.navigate("/")));
        add(btnZpet);

        loginForm.setAction("login");
        add(new H1("JKLetenky Přihlášení"), loginForm);
        loginForm.addLoginListener(e -> authenticate(e.getUsername(), e.getPassword()));
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
        Pasazer pasazer;
        try {
            pasazer = pasazerService.findByEmail(username);
        } catch (Exception e) {
            pasazer = null;
        }
        if (pasazer != null && (pasazer.getTyp_pasazeraID() != 6)) {
            return new User(pasazer.getEmail(), pasazer.getPassword(), getAuthorities("CLIENT"));
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
                vaadinSession.setAttribute("loggedInUser", user);
                vaadinSession.setAttribute("userRole", "ROLE_DISPECER");
                vaadinSession.setAttribute("name", user.getUsername());
                getUI().ifPresent(ui -> ui.navigate(Index.class));
            } else {
                VaadinSession vaadinSession = VaadinSession.getCurrent();
                vaadinSession.setAttribute("loggedInUser", user);
                vaadinSession.setAttribute("userRole", "ROLE_CLIENT");
                vaadinSession.setAttribute("name", user.getUsername());
                getUI().ifPresent(ui -> ui.navigate(Index.class));
            }
        }
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            loginForm.setError(true);
        }
    }
}