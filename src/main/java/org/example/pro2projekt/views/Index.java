package org.example.pro2projekt.views;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.PostConstruct;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.example.pro2projekt.objects.LetenkaRegister;
import org.example.pro2projekt.service.LetenkaRegisterService;
import org.example.pro2projekt.service.LetenkaService;
import org.example.pro2projekt.service.PasazerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@PageTitle("index")
@Route("/")
@AnonymousAllowed
public class Index extends VerticalLayout {
    private final LetenkaRegisterService letenkaRegisterService;
    private final LetenkaService letenkaService;
    private final Grid<LetenkaRegister> registraceLetenek = new Grid<>(LetenkaRegister.class, false);
    private List<LetenkaRegister> registerList;
    private List<String> classesList, statesList;
    private final PasazerService pasazerService;
    Button btnLogin, btnRegister, btnSubmit, btnClient, btnAdmin;
    DatePicker datumODletuFiled;
    ComboBox<String> odletField, priletField;
    ComboBox<Integer> pocetOsobField;
    ComboBox<String> tridaFiled;
    int pasazerId;
    Div divRegistrace;

    @Autowired
    public Index(PasazerService pasazerService, LetenkaRegisterService letenkaRegisterService, LetenkaService letenkaService) {
        this.pasazerService = pasazerService;
        this.letenkaRegisterService = letenkaRegisterService;
        this.letenkaService = letenkaService;
        //componenty
        btnLogin = new Button("Přihlášení");
        btnRegister = new Button("Registrace");
        btnClient = new Button("Můj profil");
        btnAdmin = new Button("Správa Systému");
        //heade
        //nav
        add(importZahlavi());
        //main
        divRegistrace = new Div();
        //
        btnLogin.addClickListener(event -> getUI().ifPresent(ui -> ui.navigate("login")));
        btnRegister.addClickListener(event -> getUI().ifPresent(ui -> ui.navigate(RegisterView.class)));
        btnAdmin.addClickListener(event -> {
            try {
                VaadinSession vaadinSession = VaadinSession.getCurrent();
                if (vaadinSession != null) {
                    Object userRoleObj = vaadinSession.getAttribute("userRole");
                    if (userRoleObj != null) {
                        String userRole = userRoleObj.toString();
                        if ("ROLE_DISPECER".equals(userRole)) {
                            getUI().ifPresent(ui -> ui.navigate("/admin"));
                        }
                    }
                }
            } catch (Exception ignored) {
            }
        });
        btnClient.addClickListener(event -> {
            try {
                VaadinSession vaadinSession = VaadinSession.getCurrent();
                if (vaadinSession != null) {
                    Object userRoleObj = vaadinSession.getAttribute("userRole");
                    if (userRoleObj != null) {
                        String userRole = userRoleObj.toString();
                        if ("ROLE_CLIENT".equals(userRole)) {
                            getUI().ifPresent(ui -> ui.navigate(ClientView.class, String.valueOf(pasazerId)));
                        }
                    }
                }
            } catch (Exception ignored) {
            }
        });
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }

    private void importLety() {
        if (divRegistrace == null) {
            divRegistrace = new Div();
        } else {
            divRegistrace.removeAll();
        }
        Div form = new Div();
        //here
        btnSubmit = new Button("Hledat");
        Text odlet = new Text("Stát odeltu: ");
        odletField = new ComboBox<>();
        odletField.setItems(statesList);
        odletField.setPlaceholder("Vyberte stát odletu");

        Text prilet = new Text("Stát příletu: ");
        priletField = new ComboBox<>();
        priletField.setItems(statesList);
        priletField.setPlaceholder("Vyberte stát příletu");

        Text datumOdletu = new Text("Datum odletu: ");
        datumODletuFiled = new DatePicker();
        datumODletuFiled.setWidthFull();

        Text pocetOsob = new Text("Počet osob ");
        pocetOsobField = new ComboBox<>();
        pocetOsobField.setPlaceholder("Zadejte počet osob");
        pocetOsobField.setItems(1, 2, 3, 4, 5, 6, 7);
        pocetOsobField.setWidthFull();

        Text trida = new Text("Třída: ");
        tridaFiled = new ComboBox<>();
        tridaFiled.setItems(classesList);
        tridaFiled.setPlaceholder("Vyberte třídu");
        tridaFiled.setWidthFull();

        Div row1 = new Div(odlet, odletField, prilet, priletField);
        Div row2 = new Div(datumOdletu, datumODletuFiled);
        Div row4 = new Div(pocetOsob, pocetOsobField);
        Div row5 = new Div(trida, tridaFiled);
        Div row6 = new Div(btnSubmit);
        form.add(row1, row2, row4, row5, row6);
        btnSubmit.setWidthFull();
        Icon icon = new Icon(VaadinIcon.ARROW_CIRCLE_UP);
        btnSubmit.setIcon(icon);
        form.add(btnSubmit);
        divRegistrace.add(form);
        List<LetenkaRegister> Selected = new ArrayList<>();
        btnSubmit.addClickListener(event -> {
            Selected.clear();
            String statOdletu = odletField.getValue();
            String statPriletu = priletField.getValue();
            LocalDate datum = datumODletuFiled.getValue();
            String tridaSelected = tridaFiled.getValue();

            System.out.println("first " + tridaSelected);
            for (LetenkaRegister letenkaRegister : registerList) {
                System.out.println(letenkaRegister.getTrida());
                if (letenkaRegister.getStatOdletu().equals(statOdletu) && letenkaRegister.getStatPriletu().equals(statPriletu)
                        && letenkaRegister.getTrida().equals(tridaSelected) && (datum.isBefore(letenkaRegister.getCas_Odletu().toLocalDate()))
                ) {
                    System.out.println("second " + letenkaRegister.getTrida());
                    Selected.add(letenkaRegister);
                }
            }
            System.out.println(Selected.size());
            if (!Selected.isEmpty()) {
                registraceLetenek.setItems(Selected);
            }
        });
        System.out.println(Selected.size());
        registraceLetenek.addColumn(LetenkaRegister::getCas_Odletu).setHeader("Datum Odletu");
        registraceLetenek.addColumn(LetenkaRegister::getMestoOdletu).setHeader("Město Odletu");
        registraceLetenek.addColumn(LetenkaRegister::getMestoPriletu).setHeader("Město Příletu");
        divRegistrace.add(registraceLetenek);
        add(divRegistrace);
        //footer
        FlexLayout footer = new FlexLayout();
        Text text1 = new Text("@2024");
        Text text2 = new Text("Jan Kubíček");
        Div div = new Div();
        div.add(text1);
        div.getStyle().set("margin-left", "10%").set("font-size", "1.3em").set("color", "blue").set("font-weight", "bolder");
        Div div1 = new Div();
        div1.add(text2);
        div1.getStyle().set("margin-left", "70%").set("font-size", "1.3em").set("color", "blue").set("font-weight", "bolder");
        footer.getStyle().set("border-top", "2px solid lightblue").set("width", "70%");
        footer.add(div, div1);
        add(footer);
    }


    private Div importZahlavi() {
        Div zahlaviDiv = new Div();
        H1 logo = new H1("JKLetenky");

        HorizontalLayout zahlaviH1 = new HorizontalLayout();
        zahlaviH1.setPadding(true);
        zahlaviH1.setSpacing(true);
        zahlaviH1.setDefaultVerticalComponentAlignment(Alignment.START);
        zahlaviH1.add(logo);

        HorizontalLayout zahlaviBtn = new HorizontalLayout();
        zahlaviBtn.setPadding(true);
        zahlaviBtn.setSpacing(true);
        zahlaviBtn.setDefaultVerticalComponentAlignment(Alignment.END);
        VaadinSession vaadinSession = VaadinSession.getCurrent();
        if (vaadinSession != null) {
            String s = (String) vaadinSession.getAttribute("userRole");
            try {
                if (s.equals("ROLE_DISPECER")) {
                    zahlaviBtn.add(btnLogin, btnRegister, btnAdmin);
                }
                if (s.equals("ROLE_CLIENT")) {
                    zahlaviBtn.add(btnLogin, btnRegister, btnClient);
                }
            } catch (Exception e) {
                zahlaviBtn.add(btnLogin, btnRegister);
            }
        } else {
            zahlaviBtn.add(btnLogin, btnRegister);
        }

        zahlaviDiv.add(zahlaviH1);
        zahlaviDiv.add(zahlaviBtn);
        zahlaviDiv.getStyle().set("border-bottom", "2px solid lightblue");
        return zahlaviDiv;
    }

    @PostConstruct
    private void init() {
        registerList = letenkaRegisterService.findAll();
        statesList = letenkaService.getAllStates();
        classesList = letenkaService.getAllClasses();
        importLety();
        try {
            VaadinSession vaadinSession = VaadinSession.getCurrent();
            String email = vaadinSession.getAttribute("name").toString();
            System.out.print(email);
            pasazerId = pasazerService.findIdByEmail(email);
        } catch (Exception ignored) {

        }
    }
}
