package org.example.pro2projekt.views;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.router.*;
import jakarta.annotation.PostConstruct;
import org.example.pro2projekt.objects.Letenka;
import org.example.pro2projekt.objects.Pasazer;
import org.example.pro2projekt.objects.Zavazadlo;
import org.example.pro2projekt.service.LetenkaService;
import org.example.pro2projekt.service.PasazerService;
import org.example.pro2projekt.service.ZavazadloService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@PageTitle("client")
@Route("/client/")
public class client extends VerticalLayout implements HasUrlParameter<String> {
    @Autowired
    private ZavazadloService zavazadloService;
    @Autowired
    private PasazerService pasazerService;
    @Autowired
    private LetenkaService letenkaService;
    private Pasazer pasazer;
    private Grid<Letenka> historieLetenek = new Grid<>(Letenka.class,false);
    private Grid<Letenka> registraceLetenek = new Grid<>(Letenka.class,false);
    private Grid<Zavazadlo> zavazadloGrid = new Grid<>(Zavazadlo.class,false);
    private List<Letenka> historieList;
    private List<Letenka> registerList;
    private List<Zavazadlo> zavazadloList;
    private int pasazerId;
    Button btnLogout;
    TabSheet tabSheet;
    Div divZavazadla, divProfil, divHistorie, divRegistrace;
    public client(){
        //componenty
        init();
        //header
        FlexLayout row1 = new FlexLayout();
        Div row1div1 = new Div();
        H1 text = new H1("Client page");
        row1div1.add(text);
        row1div1.setWidth("50%");
        Div row1div2 = new Div();
        Icon iconLogOut = new Icon(VaadinIcon.POWER_OFF);
        btnLogout = new Button("Odhlášení");
        btnLogout.addClickListener(event -> getUI().ifPresent(ui -> ui.navigate(index.class)));
        btnLogout.setIcon(iconLogOut);
        row1div2.add(btnLogout);
        row1div2.setWidth("50%");
        row1div2.getStyle().set("padding-left","60%").set("padding-top","1%");
        row1.add(row1div1,row1div2);
        row1.setWidth("90%");
        add(row1);
        //components
        divZavazadla = new Div();

        divProfil = new Div();
            FlexLayout profilRow1 = new FlexLayout();
                Div div1Prow1 = new Div();
                    Text jmeno = new Text("Jméno");
                    Text jmenoValue = new Text(pasazer.getJmeno());
                div1Prow1.add(jmeno,jmenoValue);
            profilRow1.add(div1Prow1);
        divProfil.add(profilRow1);
        divHistorie = new Div();

        divRegistrace = new Div();


        //nav
        tabSheet = new TabSheet();
        tabSheet.add("Můj profil", divProfil);
        tabSheet.add("Historie",divHistorie);
        tabSheet.add("Registrace Letenek",divRegistrace);
        tabSheet.add("Moje zavazadlo",divZavazadla);
        add(tabSheet);
        tabSheet.setWidth("90%");

        //
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
        if (parameter != null) {
            try {
                pasazerId = Integer.parseInt(parameter);
                List<Pasazer> pasazers = pasazerService.findByID(pasazerId);
                pasazer = pasazers.isEmpty() ? null : pasazers.get(0);
                if (pasazer == null) {
                    showError("Passenger not found");
                }
            } catch (NumberFormatException e) {
                showError("Invalid parameter: " + parameter);
            }
        } else {
            showError("Parameter not provided");
        }
    }
    private void showError(String message) {
        Notification.show(message, 3000, Notification.Position.MIDDLE);
    }
    @PostConstruct
    private void init() {
        if (pasazerId >= 1 && pasazer == null) {
            List<Pasazer> pasazers = pasazerService.findByID(pasazerId);
            pasazer = pasazers.isEmpty() ? null : pasazers.get(0);
            if (pasazer == null) {
                // Redirect to another page as passenger not found
                getUI().ifPresent(ui -> ui.navigate(index.class));
            }
        }
    }
}
