package org.example.pro2projekt.views;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.example.pro2projekt.controller.dataInput;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.sql.ResultSet;
import java.sql.SQLException;

@PageTitle("index")
@Route("/")
@AnonymousAllowed
public class index extends VerticalLayout implements BeforeEnterObserver {
    Button btnLogin, btnRegister,btnSubmit;
    DatePicker datumODletuFiled, datumPriletuField;
    String statOdletu = "";
    String statPriletu = "";
    String datumOdletu = "", trida = "";
    dataInput input = new dataInput();
    ComboBox<String> odletField, priletField;
    ComboBox<Integer> pocetOsobField;
    ComboBox<String> tridaFiled;
    Div lets;
    public index(){
        //componenty
        btnLogin = new Button("Login");
        btnRegister = new Button("Register");
        btnSubmit = new Button("Submit");

        //header
        add(importZahlavi());
        //nav

        //main
        add(importMain());

        btnSubmit.addClickListener(event -> {
            if(lets!= null)
                remove(lets);
            lets = importLety(input.getLets(statOdletu, statPriletu,datumOdletu,trida));
            add(lets);
        });

        odletField.addValueChangeListener(event -> {statOdletu = event.getValue(); });
        priletField.addValueChangeListener(event -> {statPriletu = event.getValue(); });
        datumODletuFiled.addValueChangeListener(event ->{datumOdletu = event.getValue().toString();}) ;
        tridaFiled.addValueChangeListener(event -> {trida = event.getValue();});

        //footer
        FlexLayout footer = new FlexLayout();
        Text text1 = new Text("@2024");
        Text text2 = new Text("Jan Kubíček");
        Div div = new Div();
        div.add(text1);
        div.getStyle().set("margin-left","10%").set("font-size","1.3em").set("color","blue").set("font-weight","bolder");
        Div div1 = new Div();
        div1.add(text2);
        div1.getStyle().set("margin-left","70%").set("font-size","1.3em").set("color","blue").set("font-weight","bolder");
        footer.getStyle().set("border-top", "2px solid lightblue").set("width", "70%");
        footer.add(div, div1);
        add(footer);
        //
        btnLogin.addClickListener(event -> getUI().ifPresent(ui -> ui.navigate("login")));
        btnRegister.addClickListener(event -> getUI().ifPresent(ui -> ui.navigate(register.class)));
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }
    private Div importLety(ResultSet resultSet){
    // LetId LetadloID Cas_Odletu Cas_Priletu MestoOdletu StatOdletu NazevLOdletu MestoPriletu StatPriletu NazevLPriletu
        Div div = new Div();

        try {
            while (resultSet.next()) {
                // Získání hodnot z ResultSet pro každý řádek
                Div let = new Div();
                FlexLayout row1 = new FlexLayout();
                Div row1Left = new Div();
                int letId = resultSet.getInt("LetId");
                Text letIdText = new Text(String.valueOf("Id letu: "+letId));
                row1Left.add(letIdText);
                Div row1Right = new Div();
                int letadloId = resultSet.getInt("LetadloID");
                Text letadloIdText = new Text(String.valueOf("Id letadla: "+letadloId));
                row1Right.add(letadloIdText);
                row1Left.setWidth("20%");
                row1Right.setWidth("80%");
                row1.setWidthFull();
                row1.add(row1Left,row1Right);

                row1.getStyle().set("border", "1px solid lightblue").set("background","lightblue").set("border-radius", "6px");

                FlexLayout  row2 = new FlexLayout ();
                Div row2Left = new Div();
                String casOdletu = resultSet.getString("Cas_Odletu");
                Text casOdletuText = new Text(String.valueOf("Čas Odletu: "+casOdletu));
                row2Left.add(casOdletuText);
                row2Left.setWidth("20%");


                FlexLayout row2Right = new FlexLayout();
                Div rw2a = new Div();
                String mestoOdletu = resultSet.getString("MestoOdletu");
                Text textMestoOdletu =new Text(String.valueOf("Město : "+mestoOdletu));
                rw2a.add(textMestoOdletu);
                rw2a.setWidth("33%");

                Div rw2b = new Div();
                String statOdletu = resultSet.getString("StatOdletu");
                Text textStatOdletu = new Text(String.valueOf("Stát : "+statOdletu));
                rw2b.add(textStatOdletu);
                rw2b.setWidth("33%");

                Div rw2c = new Div();
                String nazevLOdletu = resultSet.getString("NazevLOdletu");
                Text textLOdletu = new Text(String.valueOf("Název : "+nazevLOdletu));
                rw2c.add(textLOdletu);
                rw2c.setWidth("33%");
                row2Right.setWidth("80%");
                row2Right.add(rw2a,rw2b,rw2c);

                row2.setWidthFull();
                row2.add(row2Left,row2Right);
                row2.getStyle().set("border", "1px solid lightblue").set("background","white").set("border-radius", "6px");

                FlexLayout  row3 = new FlexLayout ();
                Div row3Left = new Div();
                String casPriletu = resultSet.getString("Cas_Priletu");
                Text casPriletuText = new Text(String.valueOf("Čas : "+casPriletu));
                row3Left.add(casPriletuText);
                row3Left.setWidth("20%");

                FlexLayout row3Right = new FlexLayout();
                Div rw3a = new Div();
                String mestoPriletu = resultSet.getString("MestoPriletu");
                Text mestoPriletuText = new Text(String.valueOf("Město : "+mestoPriletu));
                rw3a.add(mestoPriletuText);
                rw3a.setWidth("33%");

                Div rw3b = new Div();
                String statPriletu = resultSet.getString("StatPriletu");
                Text statPriletuText = new Text(String.valueOf("Stát : "+statPriletu));
                rw3b.add(statPriletuText);
                rw3b.setWidth("33%");

                Div rw3c = new Div();
                String nazevLPriletu = resultSet.getString("NazevLPriletu");
                Text nazevLPriletuText = new Text(String.valueOf("Název : "+nazevLPriletu));
                rw3c.add(nazevLPriletuText);
                rw3c.setWidth("33%");

                row3Right.setWidth("80%");
                row3Right.add(rw3a,rw3b,rw3c);

                row3.setWidthFull();
                row3.add(row3Left,row3Right);
                row3.getStyle().set("border", "1px solid lightblue").set("background","lightblue").set("border-radius", "6px");

                let.add(row1,row2,row3);
                let.getStyle().set("border", "2px solid lightblue")
                        .set("border-radius", "10px")
                        .set("padding", "10px")
                        .set("margin-bottom","20px")
                        .set("box-shadow","5px 5px 5px grey");
                div.add(let);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Pokud dojde k chybě při čtení z ResultSet
        }
        div.setWidth("80%");

        return div;
    }


    private Div importZahlavi(){
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
        zahlaviBtn.add(btnLogin, btnRegister);

        zahlaviDiv.add(zahlaviH1);
        zahlaviDiv.add(zahlaviBtn);
        zahlaviDiv.getStyle().set("border-bottom", "2px solid lightblue");
        return zahlaviDiv;
    }

    private Div importMain(){
        Div main = new Div();
        main.setMaxWidth("80%");

        Text odlet = new Text("Stát odeltu: ");
        odletField = new ComboBox<>();
        odletField.setItems(input.getAllStates());
        odletField.setPlaceholder("Vyberte stát odletu");

        Text prilet = new Text("Stát příletu: ");
        priletField = new ComboBox<>();
        priletField.setItems(input.getAllStates());
        priletField.setPlaceholder("Vyberte stát příletu");

        Text datumOdletu = new Text("Datum odletu: ");
        datumODletuFiled = new DatePicker();
        datumODletuFiled.setWidthFull();

        Text pocetOsob = new Text("Počet osob ");
        pocetOsobField = new ComboBox<>();
        pocetOsobField.setPlaceholder("Zadejte počet osob");
        pocetOsobField.setItems(1,2,3,4,5,6,7);
        pocetOsobField.setWidthFull();

        Text trida = new Text("Třída: ");
        tridaFiled = new ComboBox<>();
        tridaFiled.setItems(input.getAllClasses());
        tridaFiled.setPlaceholder("Vyberte třídu");
        tridaFiled.setWidthFull();

        Div row1 = new Div(odlet, odletField,prilet,priletField);
        Div row2 = new Div(datumOdletu,datumODletuFiled);
        Div row4 = new Div(pocetOsob,pocetOsobField);
        Div row5 = new Div(trida,tridaFiled);
        Div row6 = new Div(btnSubmit);
        btnSubmit.setWidthFull();

        main.add(row1,row2,row4,row5,row6);

        return main;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        try {
            VaadinSession vaadinSession = VaadinSession.getCurrent();
            if (vaadinSession != null) {
                Object userRoleObj = vaadinSession.getAttribute("userRole");
                if (userRoleObj != null) {
                    String userRole = userRoleObj.toString();
                    if ("ROLE_DISPECER".equals(userRole)) {
                        getUI().ifPresent(ui -> ui.navigate("/admin"));
                    } if ("ROLE_PASAZER".equals(userRole)) {
                        getUI().ifPresent(ui -> ui.navigate(client.class,"9"));
                    }
                }
            }
        } catch (Exception ignored) {
        }
    }
}
