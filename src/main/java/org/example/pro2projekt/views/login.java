package org.example.pro2projekt.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.example.pro2projekt.controller.dataInput;
import org.example.pro2projekt.service.PasazerService;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("login")
@Route("/login")
@AnonymousAllowed
public class login extends VerticalLayout implements BeforeEnterObserver {
    @Autowired
    private PasazerService pasazerService;
    private int pasazerIdFound;
    dataInput input = new dataInput();
    private final LoginForm login = new LoginForm();

    public login(){
        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        add(new H1("JKLetenky Login"), login);


        Button btnZpet = new Button("Zpět na hlavní stránku", event -> {
            getUI().ifPresent(ui -> ui.navigate("/"));
        });
        add(btnZpet);
        // Handle login form submission
        login.addLoginListener(event -> {
            String username = event.getUsername();
            String password = event.getPassword();

            if (loginDispecer(username,password)) {
                // Dispecer
                getUI().ifPresent(ui -> ui.navigate(admin.class)); // Navigate to your main application view
            }
            if (loginPasazer(username,password)) {
                // Pasazer
                int value = getPasazerIdFound(username,password);
                getUI().ifPresent(ui -> ui.navigate(client.class, Integer.toString(value))); // Navigate to your main application view
            } if(!loginPasazer(username,password) && !loginDispecer(username,password)) {
                login.setError(true);
                Notification.show("Neplatné jméno nebo heslo.");
            }
        });
    }
    private int getPasazerIdFound(String email, String password){
        return pasazerService.findByEmailAndPassword(email,password);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if(beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            login.setError(true);
        }
    }
    private boolean loginPasazer(String email, String password){
        return input.isPasazer(email, password);
    }
    private boolean loginDispecer(String email, String password){
        return input.isDispecer(email, password);
    }

}
