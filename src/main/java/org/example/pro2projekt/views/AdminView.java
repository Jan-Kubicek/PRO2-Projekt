package org.example.pro2projekt.views;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import jakarta.annotation.security.RolesAllowed;
import org.example.pro2projekt.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("admin")
@Route("/admin")
@RolesAllowed("DISPECER")
public class AdminView extends VerticalLayout {

    Button btnLogout, btnClients, btnLetadla, btnLetiste, btnSpolecnost;
    private final SecurityService securityService;

    @Autowired
    public AdminView(SecurityService securityService) {
        this.securityService = securityService;
        //ragdol
        //componenty
        VaadinSession vaadinSession = VaadinSession.getCurrent();
        System.out.println(vaadinSession.getAttribute("loggedInUser"));
        System.out.println(vaadinSession.getAttribute("userRole"));
        if ((securityService.getAuthenticatedUser() != null) && (vaadinSession.getAttribute("userRole").equals("ROLE_DISPECER"))) {
            //header
            FlexLayout row1 = new FlexLayout();
            Div row1div1 = new Div();
            H1 text = new H1("Administrátorský účet");
            row1div1.add(text);
            row1div1.setWidth("50%");
            Div row1div2 = new Div();
            Icon iconLogOut = new Icon(VaadinIcon.POWER_OFF);
            btnLogout = new Button("Odhlášení");
            btnLogout.addClickListener(event -> getUI().ifPresent(ui -> {
                VaadinSession vaadinSession1 = VaadinSession.getCurrent();
                vaadinSession1.setAttribute("loggedInUser", null);
                vaadinSession1.setAttribute("userRole", null);
                this.securityService.logout();
                ui.getPage().executeJs("location.assign('/')");
            }));
            btnLogout.setIcon(iconLogOut);
            row1div2.add(btnLogout);
            row1div2.setWidth("50%");
            row1div2.getStyle().set("padding-left", "60%").set("padding-top", "1%");
            row1.add(row1div1, row1div2);
            row1.setWidth("90%");
            row1.getStyle().set("border-bottom", "2px solid lightblue");
            add(row1);
            //main

            FlexLayout row2 = new FlexLayout();
            Div row2div1 = new Div();
            H2 text1 = new H2("Účty");
            row2div1.add(text1);
            row2div1.setWidth("50%");
            row2div1.getStyle().set("padding-left", "3%");

            Div row2div2 = new Div();
            Icon icon2 = new Icon(VaadinIcon.ARROW_FORWARD);
            btnClients = new Button("Účty");
            btnClients.addClickListener(event -> getUI().ifPresent(ui -> ui.navigate(AdminClientsView.class)));
            btnClients.setIcon(icon2);
            row2div2.add(btnClients);
            row2div2.setWidth("50%");
            row2div2.getStyle().set("padding-left", "50%");

            row2.add(row2div1, row2div2);
            row2.setWidth("90%");
            row2.getStyle().set("border", "2px solid lightblue")
                    .set("border-radius", "10px")
                    .set("padding", "10px")
                    .set("margin-bottom", "20px")
                    .set("box-shadow", "5px 5px 5px grey");
            add(row2);

            FlexLayout row3 = new FlexLayout();
            Div row3div1 = new Div();
            H2 text2 = new H2("Letadla");
            row3div1.add(text2);
            row3div1.setWidth("50%");
            row3div1.getStyle().set("padding-left", "3%");

            Div row3div2 = new Div();
            btnLetadla = new Button("Letadla");
            btnLetadla.addClickListener(event -> getUI().ifPresent(ui -> ui.navigate(AdminLetadlaView.class)));
            Icon icon3 = new Icon(VaadinIcon.ARROW_FORWARD);
            btnLetadla.setIcon(icon3);
            row3div2.add(btnLetadla);
            row3div2.setWidth("50%");
            row3div2.getStyle().set("padding-left", "50%");

            row3.add(row3div1, row3div2);
            row3.setWidth("90%");
            row3.getStyle().set("border", "2px solid lightblue")
                    .set("border-radius", "10px")
                    .set("padding", "10px")
                    .set("margin-bottom", "20px")
                    .set("box-shadow", "5px 5px 5px grey");
            add(row3);
            FlexLayout row4 = new FlexLayout();
            Div row4div1 = new Div();
            H2 text3 = new H2("Letiště");
            row4div1.add(text3);
            row4div1.setWidth("50%");
            row4div1.getStyle().set("padding-left", "3%");

            Div row4div2 = new Div();
            btnLetiste = new Button("Letiště");
            btnLetiste.addClickListener(event -> getUI().ifPresent(ui -> ui.navigate(AdminLetisteView.class)));
            Icon icon4 = new Icon(VaadinIcon.ARROW_FORWARD);
            btnLetiste.setIcon(icon4);
            row4div2.add(btnLetiste);
            row4div2.setWidth("50%");
            row4div2.getStyle().set("padding-left", "50%");

            row4.add(row4div1, row4div2);
            row4.setWidth("90%");
            row4.getStyle().set("border", "2px solid lightblue")
                    .set("border-radius", "10px")
                    .set("padding", "10px")
                    .set("margin-bottom", "20px")
                    .set("box-shadow", "5px 5px 5px grey");
            add(row4);

            FlexLayout row5 = new FlexLayout();
            Div row5div1 = new Div();
            H2 text4 = new H2("Společnosti");
            row5div1.add(text4);
            row5div1.setWidth("50%");
            row5div1.getStyle().set("padding-left", "3%");

            Div row5div2 = new Div();
            btnSpolecnost = new Button("Společnosti");
            btnSpolecnost.addClickListener(event -> getUI().ifPresent(ui -> ui.navigate(AdminSpolecnostView.class)));
            Icon icon5 = new Icon(VaadinIcon.ARROW_FORWARD);
            btnSpolecnost.setIcon(icon5);
            row5div2.add(btnSpolecnost);
            row5div2.setWidth("50%");
            row5div2.getStyle().set("padding-left", "50%");

            row5.add(row5div1, row5div2);
            row5.setWidth("90%");
            row5.getStyle().set("border", "2px solid lightblue")
                    .set("border-radius", "10px")
                    .set("padding", "10px")
                    .set("margin-bottom", "20px")
                    .set("box-shadow", "5px 5px 5px grey");
            add(row5);
            //footer
            FlexLayout footer = new FlexLayout();
            Text text5 = new Text("@2024");
            Text text6 = new Text("Jan Kubíček");
            Div div = new Div();
            div.add(text5);
            div.getStyle().set("margin-left", "10%").set("font-size", "1.3em").set("color", "blue").set("font-weight", "bolder");
            Div div1 = new Div();
            div1.add(text6);
            div1.getStyle().set("margin-left", "70%").set("font-size", "1.3em").set("color", "blue").set("font-weight", "bolder");
            footer.getStyle().set("border-top", "2px solid lightblue").set("width", "90%");
            footer.add(div, div1);
            add(footer);
            //
            setAlignItems(Alignment.CENTER);
            setJustifyContentMode(JustifyContentMode.CENTER);
        } else {
            H2 nadpis = new H2("Nemáte dostatečná oprávnění pro tuto stránku");
            Button btnZpet = new Button("Zpět na hlavní stránku");
            add(nadpis);
            nadpis.getStyle().set("margin-left", "25%");
            add(btnZpet);
            btnZpet.getStyle().set("margin-left", "46%");
            btnZpet.addClickListener(event -> getUI().ifPresent(ui -> ui.navigate(ClientView.class)));
        }
    }
}
