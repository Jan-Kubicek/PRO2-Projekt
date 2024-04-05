package org.example.pro2projekt.views;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.PostConstruct;
import org.example.pro2projekt.objects.Dispecer;
import org.example.pro2projekt.objects.Pasazer;
import org.example.pro2projekt.service.DispecerService;
import org.example.pro2projekt.service.PasazerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.util.List;

@PageTitle("adminClients")
@Route("/admin/clients")
public class AdminClientsView extends VerticalLayout {
    @Autowired
    private PasazerService pasazerService;
    @Autowired
    private DispecerService dispecerService;
    private Grid<Pasazer> pasazerGrid = new Grid<>(Pasazer.class,false);
    private Grid<Dispecer> dispecrGrid = new Grid<>(Dispecer.class,false);
    private List<Pasazer> pasazerList;
    private List<Dispecer> dispecerList;
    public AdminClientsView(){
        FlexLayout row0 = new FlexLayout();
        Div div = new Div();
        Button btnBack = new Button();
        btnBack.addClickListener(event ->{ getUI().ifPresent(ui -> ui.navigate(admin.class));});
        btnBack.setText("Zpět na hlavní stránku");
        div.add(btnBack);
        div.getStyle().set("margin-left","80%");
        row0.add(div);
        div.getStyle().set("margin-left","40%");
        add(row0);
        pasazerGrid.addColumn(Pasazer::getPasazerID).setHeader("ID");
        pasazerGrid.addColumn(Pasazer::getJmeno).setHeader("Jmeno");
        pasazerGrid.addColumn(Pasazer::getPrijmeni).setHeader("Prijmeni");
        pasazerGrid.addColumn(Pasazer::getEmail).setHeader("Email");
        pasazerGrid.addColumn(Pasazer::getRodne_cislo).setHeader("Rodné číslo");
        pasazerGrid.addColumn(Pasazer::getTelefoni_cislo).setHeader("Telefonní číslo");
        pasazerGrid.addColumn(new ComponentRenderer<>(pasazer -> {
            int pasazerId = pasazer.getPasazerID();
            Button editButton = new Button("Úprava");
            editButton.addClickListener(event -> {
                Dialog dialog = new Dialog();

                FlexLayout row1 = new FlexLayout();
                    Div row1div1 = new Div();
                        Text jmeno = new Text("Jméno");
                        TextArea jmenoField = new TextArea();
                        jmenoField.setValue(pasazer.getJmeno());
                    row1div1.add(jmeno,jmenoField);
                    row1div1.getStyle().set("padding-left","10%");
                    Div row1div2 = new Div();
                        Text prijmeni = new Text("Přijmení");
                        TextArea prijmeniField = new TextArea();
                        prijmeniField.setValue(pasazer.getPrijmeni());
                    row1div2.add(prijmeni,prijmeniField);
                    row1div2.getStyle().set("padding-left","10%");
                row1.add(row1div1,row1div2);
                dialog.add(row1);


                FlexLayout row2 = new FlexLayout();
                Div row2div1 = new Div();
                Text email = new Text("Email");
                TextArea emailField = new TextArea();
                emailField.setValue(pasazer.getEmail());
                row2div1.add(email,emailField);
                row2div1.getStyle().set("padding-left","10%");
                Div row2div2 = new Div();
                Text rodneCislo = new Text("Rodné číslo");
                TextArea rodneCisloField = new TextArea();
                rodneCisloField.setValue(pasazer.getRodne_cislo());
                row2div2.add(rodneCislo,rodneCisloField);
                row2div2.getStyle().set("padding-left","10%");
                row2.add(row2div1,row2div2);
                dialog.add(row2);

                FlexLayout row3 = new FlexLayout();
                Div last = new Div();
                Text tel = new Text("Telefoní číslo");
                TextArea telField = new TextArea();
                telField.setValue(pasazer.getTelefoni_cislo());
                last.add(tel,telField);
                last.getStyle().set("padding-left","25%");
                row3.add(last);
                dialog.add(row3);

                FlexLayout rowLast = new FlexLayout();
                Button uploadButton = new Button("Uprav", event3 ->{
                    pasazerService.findByIdAndUpdate(pasazer.getPasazerID(),jmenoField.getValue(),prijmeniField.getValue(),emailField.getValue(),rodneCisloField.getValue(),telField.getValue());
                    dialog.close();
                    UI.getCurrent().getPage().reload();
                });
                uploadButton.getStyle().set("margin-left","40%");
                Button closeButton = new Button("Zavřít", event2 -> dialog.close());
                closeButton.getStyle().set("margin-left","10%");
                rowLast.add(closeButton,uploadButton);
                dialog.add(rowLast);
                dialog.open();
            });

            Button deleteButton = new Button("Smazání");
            deleteButton.addClickListener(event -> {
                pasazerService.findByIdAndDelete(pasazerId);
                UI.getCurrent().getPage().reload();
            });

            HorizontalLayout buttonLayout = new HorizontalLayout(editButton, deleteButton);
            return buttonLayout;
        })).setHeader("Akce");
        pasazerGrid.getStyle().set("border", "2px solid lightblue")
                .set("border-radius", "10px")
                .set("padding", "10px")
                .set("margin-bottom","20px")
                .set("box-shadow","5px 5px 5px grey");

        dispecrGrid.addColumn(Dispecer::getDispecerID).setHeader("ID");
        dispecrGrid.addColumn(Dispecer::getJmeno).setHeader("Jmeno");
        dispecrGrid.addColumn(Dispecer::getPrijmeni).setHeader("Prijmeni");
        dispecrGrid.addColumn(Dispecer::getEmail).setHeader("Email");
        dispecrGrid.addColumn(Dispecer::getRodne_cislo).setHeader("Rodné číslo");
        dispecrGrid.addColumn(Dispecer::getTelefoni_cislo).setHeader("Telefonní číslo");
        dispecrGrid.addColumn(new ComponentRenderer<>(dispecer -> {
            int dispecerId = dispecer.getDispecerID();
            Button editButton = new Button("Úprava");
            editButton.addClickListener(event -> {
                Dialog dialog = new Dialog();

                FlexLayout row1 = new FlexLayout();
                Div row1div1 = new Div();
                Text jmeno = new Text("Jméno");
                TextArea jmenoField = new TextArea();
                jmenoField.setValue(dispecer.getJmeno());
                row1div1.add(jmeno,jmenoField);
                row1div1.getStyle().set("padding-left","10%");
                Div row1div2 = new Div();
                Text prijmeni = new Text("Přijmení");
                TextArea prijmeniField = new TextArea();
                prijmeniField.setValue(dispecer.getPrijmeni());
                row1div2.add(prijmeni,prijmeniField);
                row1div2.getStyle().set("padding-left","10%");
                row1.add(row1div1,row1div2);
                dialog.add(row1);


                FlexLayout row2 = new FlexLayout();
                Div row2div1 = new Div();
                Text email = new Text("Email");
                TextArea emailField = new TextArea();
                emailField.setValue(dispecer.getEmail());
                row2div1.add(email,emailField);
                row2div1.getStyle().set("padding-left","10%");
                Div row2div2 = new Div();
                Text rodneCislo = new Text("Rodné číslo");
                TextArea rodneCisloField = new TextArea();
                rodneCisloField.setValue(dispecer.getRodne_cislo());
                row2div2.add(rodneCislo,rodneCisloField);
                row2div2.getStyle().set("padding-left","10%");
                row2.add(row2div1,row2div2);
                dialog.add(row2);

                FlexLayout row3 = new FlexLayout();
                Div last = new Div();
                Text tel = new Text("Telefoní číslo");
                TextArea telField = new TextArea();
                telField.setValue(dispecer.getTelefoni_cislo());
                last.add(tel,telField);
                last.getStyle().set("padding-left","25%");
                row3.add(last);
                dialog.add(row3);

                FlexLayout rowLast = new FlexLayout();
                Button uploadButton = new Button("Uprav", event3 ->{
                    dispecerService.findByIdAndUpdate(dispecer.getDispecerID(),jmenoField.getValue(),prijmeniField.getValue(),emailField.getValue(),rodneCisloField.getValue(),telField.getValue());
                    dialog.close();
                    UI.getCurrent().getPage().reload();
                });
                uploadButton.getStyle().set("margin-left","40%");
                Button closeButton = new Button("Zavřít", event2 -> dialog.close());
                closeButton.getStyle().set("margin-left","10%");
                rowLast.add(closeButton,uploadButton);
                dialog.add(rowLast);
                dialog.open();
            });

            Button deleteButton = new Button("Smazání");
            deleteButton.addClickListener(event -> {
                dispecerService.findByIdAndDelete(dispecerId);
                UI.getCurrent().getPage().reload();
            });

            HorizontalLayout buttonLayout = new HorizontalLayout(editButton, deleteButton);
            return buttonLayout;
        })).setHeader("Akce");
        dispecrGrid.getStyle().set("border", "2px solid lightblue")
                .set("border-radius", "10px")
                .set("padding", "10px")
                .set("margin-bottom","20px")
                .set("box-shadow","5px 5px 5px grey");


        add(pasazerGrid);
        add(dispecrGrid);
    }

    @PostConstruct
    private void init(){
        pasazerList = pasazerService.findAll();
        pasazerGrid.setItems(pasazerList);
        dispecerList = dispecerService.findAll();
        dispecrGrid.setItems(dispecerList);
    }
}
