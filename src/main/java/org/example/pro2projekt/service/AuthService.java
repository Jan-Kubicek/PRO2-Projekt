package org.example.pro2projekt.service;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.server.VaadinSession;
import org.example.pro2projekt.views.*;
import org.springframework.context.annotation.Role;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {

    public record AuthorizedRoute(String route, String name, Class<? extends Component> view) {

    }

    public class AuthException extends Exception {

    }

    private UserDetailsService userRepository ;

    public AuthService() {
        this.userRepository = userRepository;
    }


    public List<AuthorizedRoute> getAuthorizedRoutes(String role) {
        var routes = new ArrayList<AuthorizedRoute>();

        if (role.equals("ROLE_DISPECER")) {
            routes.add(new AuthorizedRoute("/admin", "admin", admin.class));
            routes.add(new AuthorizedRoute("/admin/clients", "adminClients", AdminClientsView.class));
            routes.add(new AuthorizedRoute("/admin/letadla", "adminLetadla", AdminLetadlaView.class));
            routes.add(new AuthorizedRoute("/admin/letiste", "adminLetiste", AdminLetisteView.class));
            routes.add(new AuthorizedRoute("/admin/spolecnost", "adminSpolecnost", AdminSpolecnostView.class));

        } else if (role.equals("ROLE_PASAZER")) {
            routes.add(new AuthorizedRoute("/client/", "client", client.class));
        }
        return routes;
    }

}