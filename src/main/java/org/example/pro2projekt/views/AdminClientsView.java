package org.example.pro2projekt.views;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.security.RolesAllowed;
import org.example.pro2projekt.objects.Pasazer;
import org.example.pro2projekt.objects.PasazerStats;
import org.example.pro2projekt.service.PasazerService;
import org.example.pro2projekt.service.PasazerStatsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@PageTitle("adminClients")
@Route("/admin/clients")
@RolesAllowed("DISPECER")
public class AdminClientsView extends VerticalLayout {
    @Autowired
    private PasazerService pasazerService;
    @Autowired
    private PasazerStatsService pasazerStatsService;
    private Grid<PasazerStats> pasazerStatsGrid = new Grid<>(PasazerStats.class, false);
    private Grid<Pasazer> pasazerGrid = new Grid<>(Pasazer.class, false);
    private Grid<Pasazer> dispecrGrid = new Grid<>(Pasazer.class, false);
    private List<Pasazer> pasazerList;
    private List<PasazerStats> pasazerStatsList;
    private List<Pasazer> dispecerList;

    public AdminClientsView() {
        FlexLayout row0 = new FlexLayout();
        Div div = new Div();
        Button btnBack = new Button();
        btnBack.addClickListener(event -> {
            getUI().ifPresent(ui -> ui.navigate(Admin.class));
        });
        btnBack.setText("Zpět na hlavní stránku");
        Icon icon1 = new Icon(VaadinIcon.ARROW_BACKWARD);
        btnBack.setIcon(icon1);
        div.add(btnBack);
        Div div2 = new Div();
        Button btnNew = new Button();
        btnNew.addClickListener(event -> {
            Dialog dialog = new Dialog();

            FlexLayout row1 = new FlexLayout();
            Div row1div1 = new Div();
            Text jmeno = new Text("Jméno");
            TextArea jmenoField = new TextArea();
            row1div1.add(jmeno, jmenoField);
            row1div1.getStyle().set("padding-left", "10%");
            Div row1div2 = new Div();
            Text prijmeni = new Text("Přijmení");
            TextArea prijmeniField = new TextArea();
            row1div2.add(prijmeni, prijmeniField);
            row1div2.getStyle().set("padding-left", "10%");
            row1.add(row1div1, row1div2);
            dialog.add(row1);


            FlexLayout row2 = new FlexLayout();
            Div row2div1 = new Div();
            Text email = new Text("Email");
            TextArea emailField = new TextArea();
            row2div1.add(email, emailField);
            row2div1.getStyle().set("padding-left", "10%");
            Div row2div2 = new Div();
            Text rodneCislo = new Text("Rodné číslo");
            TextArea rodneCisloField = new TextArea();
            row2div2.add(rodneCislo, rodneCisloField);
            row2div2.getStyle().set("padding-left", "10%");
            row2.add(row2div1, row2div2);
            dialog.add(row2);

            FlexLayout row3 = new FlexLayout();
            Div last = new Div();
            Text tel = new Text("Telefoní číslo");
            TextArea telField = new TextArea();
            last.add(tel, telField);
            last.getStyle().set("padding-left", "10%");
            Div lastt = new Div();
            Text hes = new Text("Heslo");
            TextArea hesloField = new TextArea();
            lastt.add(hes, hesloField);
            lastt.getStyle().set("padding-left", "10%");
            row3.add(last, lastt);
            dialog.add(row3);

            FlexLayout rowLast = new FlexLayout();
            Button uploadButton = new Button("Vytvoř", event3 -> {
                pasazerService.createDispecer(emailField.getValue(), hesloField.getValue(), jmenoField.getValue(), prijmeniField.getValue(), rodneCisloField.getValue(), telField.getValue());
                dialog.close();
                UI.getCurrent().getPage().reload();
            });
            uploadButton.getStyle().set("margin-left", "40%");
            Icon icon10 = new Icon(VaadinIcon.CHECK);
            uploadButton.setIcon(icon10);

            Button closeButton = new Button("Zavřít", event2 -> dialog.close());
            closeButton.getStyle().set("margin-left", "10%");
            Icon icon11 = new Icon(VaadinIcon.CLOSE);
            closeButton.setIcon(icon11);
            rowLast.add(closeButton, uploadButton);
            dialog.add(rowLast);
            dialog.open();
        });
        btnNew.setText("Nový účet dispečera");
        btnNew.getStyle().set("margin-left", "40%");
        Icon icon2 = new Icon(VaadinIcon.PLUS_CIRCLE);
        btnNew.setIcon(icon2);
        div2.add(btnNew);
        div2.getStyle().set("margin-left", "40%");
        row0.add(div, div2);
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
                row1div1.add(jmeno, jmenoField);
                row1div1.getStyle().set("padding-left", "10%");
                Div row1div2 = new Div();
                Text prijmeni = new Text("Přijmení");
                TextArea prijmeniField = new TextArea();
                prijmeniField.setValue(pasazer.getPrijmeni());
                row1div2.add(prijmeni, prijmeniField);
                row1div2.getStyle().set("padding-left", "10%");
                row1.add(row1div1, row1div2);
                dialog.add(row1);


                FlexLayout row2 = new FlexLayout();
                Div row2div1 = new Div();
                Text email = new Text("Email");
                TextArea emailField = new TextArea();
                emailField.setValue(pasazer.getEmail());
                row2div1.add(email, emailField);
                row2div1.getStyle().set("padding-left", "10%");
                Div row2div2 = new Div();
                Text rodneCislo = new Text("Rodné číslo");
                TextArea rodneCisloField = new TextArea();
                rodneCisloField.setValue(pasazer.getRodne_cislo());
                row2div2.add(rodneCislo, rodneCisloField);
                row2div2.getStyle().set("padding-left", "10%");
                row2.add(row2div1, row2div2);
                dialog.add(row2);

                FlexLayout row3 = new FlexLayout();
                Div last = new Div();
                Text tel = new Text("Telefoní číslo");
                TextArea telField = new TextArea();
                telField.setValue(pasazer.getTelefoni_cislo());
                last.add(tel, telField);
                last.getStyle().set("padding-left", "25%");
                row3.add(last);
                dialog.add(row3);

                FlexLayout rowLast = new FlexLayout();
                Button uploadButton = new Button("Uprav", event3 -> {
                    pasazerService.findByIdAndUpdate(pasazer.getPasazerID(), jmenoField.getValue(), prijmeniField.getValue(), emailField.getValue(), rodneCisloField.getValue(), telField.getValue());
                    dialog.close();
                    UI.getCurrent().getPage().reload();
                });
                Icon icon10 = new Icon(VaadinIcon.CHECK);
                uploadButton.setIcon(icon10);
                uploadButton.getStyle().set("margin-left", "40%");
                Button closeButton = new Button("Zavřít", event2 -> dialog.close());
                closeButton.getStyle().set("margin-left", "10%");
                Icon icon11 = new Icon(VaadinIcon.CLOSE);
                closeButton.setIcon(icon11);
                rowLast.add(closeButton, uploadButton);
                dialog.add(rowLast);
                dialog.open();
            });
            Icon icon3 = new Icon(VaadinIcon.ARROW_CIRCLE_UP);
            editButton.setIcon(icon3);
            Button deleteButton = new Button("Smazání");
            deleteButton.addClickListener(event -> {
                pasazerService.findByIdAndDelete(pasazerId);
                UI.getCurrent().getPage().reload();
            });
            Icon icon4 = new Icon(VaadinIcon.CLOSE_CIRCLE);
            deleteButton.setIcon(icon4);

            HorizontalLayout buttonLayout = new HorizontalLayout(editButton, deleteButton);
            return buttonLayout;
        })).setHeader("Akce");
        pasazerGrid.getStyle().set("border", "2px solid lightblue")
                .set("border-radius", "10px")
                .set("padding", "10px")
                .set("margin-bottom", "20px")
                .set("box-shadow", "5px 5px 5px grey");

        pasazerStatsGrid.addColumn(PasazerStats::getTyp).setHeader("Typ Pasažéra");
        pasazerStatsGrid.addColumn(PasazerStats::getPocet).setHeader("Počet");
        pasazerStatsGrid.addColumn(PasazerStats::getPopis).setHeader("Popis");
        pasazerStatsGrid.addColumn(new ComponentRenderer<>(pasazerStats -> {
            double percent = ((double) pasazerStats.getPocet() / pasazerStats.getAllPasazers()) * 100;
            return new Span(String.format("%.2f %%", percent));
        })).setHeader("% z celku");
        pasazerStatsGrid.getStyle().set("border", "2px solid lightblue")
                .set("border-radius", "10px")
                .set("padding", "10px")
                .set("margin-bottom", "20px")
                .set("box-shadow", "5px 5px 5px grey");

        dispecrGrid.addColumn(Pasazer::getPasazerID).setHeader("ID");
        dispecrGrid.addColumn(Pasazer::getJmeno).setHeader("Jmeno");
        dispecrGrid.addColumn(Pasazer::getPrijmeni).setHeader("Prijmeni");
        dispecrGrid.addColumn(Pasazer::getEmail).setHeader("Email");
        dispecrGrid.addColumn(Pasazer::getRodne_cislo).setHeader("Rodné číslo");
        dispecrGrid.addColumn(Pasazer::getTelefoni_cislo).setHeader("Telefonní číslo");
        dispecrGrid.addColumn(new ComponentRenderer<>(dispecer -> {
            int dispecerId = dispecer.getPasazerID();
            Button editButton = new Button("Úprava");
            editButton.addClickListener(event -> {
                Dialog dialog = new Dialog();

                FlexLayout row1 = new FlexLayout();
                Div row1div1 = new Div();
                Text jmeno = new Text("Jméno");
                TextArea jmenoField = new TextArea();
                jmenoField.setValue(dispecer.getJmeno());
                row1div1.add(jmeno, jmenoField);
                row1div1.getStyle().set("padding-left", "10%");
                Div row1div2 = new Div();
                Text prijmeni = new Text("Přijmení");
                TextArea prijmeniField = new TextArea();
                prijmeniField.setValue(dispecer.getPrijmeni());
                row1div2.add(prijmeni, prijmeniField);
                row1div2.getStyle().set("padding-left", "10%");
                row1.add(row1div1, row1div2);
                dialog.add(row1);


                FlexLayout row2 = new FlexLayout();
                Div row2div1 = new Div();
                Text email = new Text("Email");
                TextArea emailField = new TextArea();
                emailField.setValue(dispecer.getEmail());
                row2div1.add(email, emailField);
                row2div1.getStyle().set("padding-left", "10%");
                Div row2div2 = new Div();
                Text rodneCislo = new Text("Rodné číslo");
                TextArea rodneCisloField = new TextArea();
                rodneCisloField.setValue(dispecer.getRodne_cislo());
                row2div2.add(rodneCislo, rodneCisloField);
                row2div2.getStyle().set("padding-left", "10%");
                row2.add(row2div1, row2div2);
                dialog.add(row2);

                FlexLayout row3 = new FlexLayout();
                Div last = new Div();
                Text tel = new Text("Telefoní číslo");
                TextArea telField = new TextArea();
                telField.setValue(dispecer.getTelefoni_cislo());
                last.add(tel, telField);
                last.getStyle().set("padding-left", "25%");
                row3.add(last);
                dialog.add(row3);

                FlexLayout rowLast = new FlexLayout();
                Button uploadButton = new Button("Uprav", event3 -> {
                    pasazerService.findByIdAndUpdateDispecer(dispecer.getPasazerID(), jmenoField.getValue(), prijmeniField.getValue(), emailField.getValue(), rodneCisloField.getValue(), telField.getValue());
                    dialog.close();
                    UI.getCurrent().getPage().reload();
                });
                Icon icon10 = new Icon(VaadinIcon.CHECK);
                uploadButton.setIcon(icon10);
                uploadButton.getStyle().set("margin-left", "40%");

                Button closeButton = new Button("Zavřít", event2 -> dialog.close());
                closeButton.getStyle().set("margin-left", "10%");
                Icon icon11 = new Icon(VaadinIcon.CLOSE);
                closeButton.setIcon(icon11);
                rowLast.add(closeButton, uploadButton);
                dialog.add(rowLast);
                dialog.open();
            });
            Icon icon3 = new Icon(VaadinIcon.ARROW_CIRCLE_UP);
            editButton.setIcon(icon3);
            Button deleteButton = new Button("Smazání");
            deleteButton.addClickListener(event -> {
                pasazerService.findByIdAndDeleteDispecer(dispecerId);
                UI.getCurrent().getPage().reload();
            });
            Icon icon4 = new Icon(VaadinIcon.CLOSE_CIRCLE);
            deleteButton.setIcon(icon4);

            HorizontalLayout buttonLayout = new HorizontalLayout(editButton, deleteButton);
            return buttonLayout;
        })).setHeader("Akce");
        dispecrGrid.getStyle().set("border", "2px solid lightblue")
                .set("border-radius", "10px")
                .set("padding", "10px")
                .set("margin-bottom", "20px")
                .set("box-shadow", "5px 5px 5px grey");


        add(pasazerGrid);
        add(pasazerStatsGrid);
        add(dispecrGrid);
        FlexLayout footer = new FlexLayout();
        Text text1 = new Text("@2024");
        Text text2 = new Text("Jan Kubíček");
        Div div1 = new Div();
        div1.add(text1);
        div1.getStyle().set("margin-left", "10%").set("font-size", "1.3em").set("color", "blue").set("font-weight", "bolder");
        Div div3 = new Div();
        div3.add(text2);
        div3.getStyle().set("margin-left", "70%").set("font-size", "1.3em").set("color", "blue").set("font-weight", "bolder");
        footer.getStyle().set("border-top", "2px solid lightblue").set("width", "100%");
        footer.add(div1, div3);
        add(footer);
    }

    @PostConstruct
    private void init() {
        pasazerStatsList = pasazerStatsService.groupByTyp();
        pasazerStatsGrid.setItems(pasazerStatsList);
        pasazerList = pasazerService.findAll();
        pasazerGrid.setItems(pasazerList);
        dispecerList = pasazerService.findAllDispecers();
        dispecrGrid.setItems(dispecerList);
    }
}
