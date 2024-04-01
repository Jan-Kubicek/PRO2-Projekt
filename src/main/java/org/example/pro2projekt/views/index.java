package org.example.pro2projekt.views;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Paragraph;
import org.example.pro2projekt.controller.dataInput;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

@PageTitle("index")
@Route("/")
public class index extends VerticalLayout {
    Button btnLogin, btnRegister,btnSubmit;
    RouterLink lingLogin, likRegister;//todo
    String statOdletu = "";
    String statPriletu = "";
    dataInput input = new dataInput();
    ComboBox<String> odletField, priletField;
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
            remove(lets);
            lets = new Div();
            lets = importLety(input.getLets(statOdletu, statPriletu));
            add(lets);
        });

        odletField.addValueChangeListener(event -> {statOdletu = event.getValue(); });
        priletField.addValueChangeListener(event -> {statPriletu = event.getValue(); });

        //footer

        //
        btnLogin.addClickListener(event -> getUI().ifPresent(ui -> ui.navigate("login")));

        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }
    private void refreshLety() {
        removeAll();
        add(importZahlavi());
        add(importMain());
        add(importLety(input.getLets(statOdletu, statPriletu)));
    }
    private Div importLety(ResultSet resultSet){
    // LetId LetadloID Cas_Odletu Cas_Priletu MestoOdletu StatOdletu NazevLOdletu MestoPriletu StatPriletu NazevLPriletu
        Div div = new Div();

        try {
            while (resultSet.next()) {
                // Získání hodnot z ResultSet pro každý řádek
                int letId = resultSet.getInt("LetId");
                int letadloId = resultSet.getInt("LetadloID");
                String casOdletu = resultSet.getString("Cas_Odletu");
                String casPriletu = resultSet.getString("Cas_Priletu");
                String mestoOdletu = resultSet.getString("MestoOdletu");
                String statOdletu = resultSet.getString("StatOdletu");
                String nazevLOdletu = resultSet.getString("NazevLOdletu");
                String mestoPriletu = resultSet.getString("MestoPriletu");
                String statPriletu = resultSet.getString("StatPriletu");
                String nazevLPriletu = resultSet.getString("NazevLPriletu");

                // Vytvoření textového řetězce s daty
                String rowData = "Let ID: " + letId + ", Letadlo ID: " + letadloId + ", Čas odletu: " + casOdletu +
                        ", Čas příletu: " + casPriletu + ", Město odletu: " + mestoOdletu +
                        ", Stát odletu: " + statOdletu + ", Název letiště odletu: " + nazevLOdletu +
                        ", Město příletu: " + mestoPriletu + ", Stát příletu: " + statPriletu +
                        ", Název letiště příletu: " + nazevLPriletu;

                // Vytvoření paragraph elementu pro každý řádek dat
                Paragraph paragraph = new Paragraph(rowData);
                div.add(paragraph);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Pokud dojde k chybě při čtení z ResultSet
        }

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
        DatePicker datumODletuFiled = new DatePicker();
        datumODletuFiled.setWidthFull();

        Text datumPriletu = new Text("Datum příletu ");
        DatePicker datumPriletuField = new DatePicker();
        datumPriletuField.setWidthFull();

        Text pocetOsob = new Text("Počet osob ");
        ComboBox<Integer> pocetOsobField = new ComboBox<>();
        pocetOsobField.setPlaceholder("Zadejte počet osob");
        pocetOsobField.setItems(1,2,3,4,5,6,7);
        pocetOsobField.setWidthFull();

        Text trida = new Text("Třída: ");
        ComboBox<String> tridaFiled = new ComboBox<>();
        tridaFiled.setItems(input.getAllClasses());
        tridaFiled.setPlaceholder("Vyberte třídu");
        tridaFiled.setWidthFull();

        Div row1 = new Div(odlet, odletField,prilet,priletField);
        Div row2 = new Div(datumOdletu,datumODletuFiled);
        Div row3 = new Div(datumPriletu,datumPriletuField);
        Div row4 = new Div(pocetOsob,pocetOsobField);
        Div row5 = new Div(trida,tridaFiled);
        Div row6 = new Div(btnSubmit);
        btnSubmit.setWidthFull();

        main.add(row1,row2,row3,row4,row5,row6);

        return main;
    }
}
