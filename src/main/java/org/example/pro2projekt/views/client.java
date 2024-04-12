package org.example.pro2projekt.views;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.pro2projekt.objects.Letenka;
import org.example.pro2projekt.objects.LetenkaHistorie;
import org.example.pro2projekt.objects.Pasazer;
import org.example.pro2projekt.objects.Zavazadlo;
import org.example.pro2projekt.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@PageTitle("client")
@Route("/client/")
@RolesAllowed("ROLE_PASAZER")
public class client extends VerticalLayout implements HasUrlParameter<String> {
    @Autowired
    private PasazerService pasazerService;
    @Autowired
    private ZavazadloService zavazadloService;
    @Autowired
    private LetenkaService letenkaService;
    @Autowired
    private LetenkaHistorieService letenkaHistorieService;
    private Pasazer pasazer;
    private Grid<Letenka> historieLetenek = new Grid<>(Letenka.class, false);
    private Grid<Letenka> registraceLetenek = new Grid<>(Letenka.class, false);
    private Grid<Zavazadlo> zavazadloGrid = new Grid<>(Zavazadlo.class, false);
    private List<Letenka> historieList;
    private List<Letenka> registerList;
    private List<Zavazadlo> zavazadloList;
    private List<LetenkaHistorie> letenkaHistories;
    private int pasazerId, letID = 0;
    LetenkaHistorie letenkaH;
    Button btnLogout;
    TabSheet tabSheet;
    Div divZavazadla, divProfil, divHistorie, divRegistrace;

    public client() {
        VaadinSession vaadinSession = VaadinSession.getCurrent();
        System.out.println(vaadinSession.getAttribute("loggedInUser"));
        System.out.println(vaadinSession.getAttribute("userRole"));
        // Inicializace komponent v konstruktoru
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
        row1div2.getStyle().set("padding-left", "60%").set("padding-top", "1%");
        row1.add(row1div1, row1div2);
        row1.setWidth("90%");
        row1.getStyle().set("border-bottom", "2px solid lightblue");
        add(row1);

        divZavazadla = new Div();
        divProfil = new Div();
        divHistorie = new Div();
        divRegistrace = new Div();
        tabSheet = new TabSheet();
        tabSheet.add("Můj profil", divProfil);
        tabSheet.add("Historie", divHistorie);
        tabSheet.add("Registrace Letenek", divRegistrace);
        tabSheet.add("Moje zavazadlo", divZavazadla);
        add(tabSheet);
        tabSheet.setWidth("90%");
        FlexLayout footer = new FlexLayout();
            Text text1 = new Text("@2024");
            Text text2 = new Text("Jan Kubíček");
            Div div = new Div();
            div.add(text1);
            div.getStyle().set("margin-left","10%").set("font-size","1.3em").set("color","blue").set("font-weight","bolder");
            Div div1 = new Div();
            div1.add(text2);
            div1.getStyle().set("margin-left","70%").set("font-size","1.3em").set("color","blue").set("font-weight","bolder");
        footer.getStyle().set("border-top", "2px solid lightblue").set("width", "90%");
        footer.add(div, div1);
        add(footer);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
        if (parameter != null) {
            try {
                pasazerId = Integer.parseInt(parameter);
                List<Pasazer> pasazers = pasazerService.findByID(pasazerId);
                pasazer = pasazers.isEmpty() ? null : pasazers.getFirst();
                zavazadloList = zavazadloService.findByPasazerId(pasazerId);
                zavazadloGrid.setItems(zavazadloList);
                historieList = letenkaService.findByPasazer(pasazerId);
                historieLetenek.setItems(historieList);
                if (pasazer == null) {
                } else {
                    // Po načtení pasažéra aktualizujeme profil
                    updateProfile();
                    updateRegister();
                    updateHistory();
                    updateZavazadla();
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

    private void updateProfile() {
        if (divProfil == null) {
            divProfil = new Div(); // inicializace divProfil, pokud je null
        } else {
            divProfil.removeAll(); // Odstraní všechny existující komponenty z divu profil
        }

        // Vytvoříme a přidáme komponenty pro zobrazení profilu pasažéra
        FlexLayout profilRow1 = new FlexLayout();
        Div div1Prow1 = new Div();
        Text jmeno = new Text("Jméno: ");
        Text jmenoValue = new Text(pasazer.getJmeno());
        div1Prow1.add(jmeno, jmenoValue);
        div1Prow1.getStyle().set("margin-left", "10%");
        Div div2Prow1 = new Div();
        Text prijemni = new Text("Přijmení: ");
        Text prijmeniValue = new Text(pasazer.getPrijmeni());
        div2Prow1.add(prijemni, prijmeniValue);
        div2Prow1.getStyle().set("margin-left", "5%");
        Div div3Prow1 = new Div();
        Text datumNaroz = new Text("Datum narození: ");
        Text datumNarozValue = new Text(pasazer.getDatum_narozeni().toString());
        div3Prow1.add(datumNaroz, datumNarozValue);
        div3Prow1.getStyle().set("margin-left", "10%");
        profilRow1.add(div1Prow1, div2Prow1, div3Prow1);
        profilRow1.getStyle().set("margin-botton", "5%").set("border-bottom", "1px solid lightblue");
        FlexLayout profilRow2 = new FlexLayout();
        Div div1Prow2 = new Div();
        Text email = new Text("Email: ");
        Text emailValue = new Text(pasazer.getEmail());
        div1Prow2.add(email, emailValue);
        div1Prow2.getStyle().set("margin-left", "10%");
        Div div2Prow2 = new Div();
        Text tel = new Text("Telefoní číslo: ");
        Text telValue = new Text(pasazer.getTelefoni_cislo());
        div2Prow2.add(tel, telValue);
        div2Prow2.getStyle().set("margin-left", "5%");
        Div div3Prow2 = new Div();
        Text rodC = new Text("Rodné číslo");
        Text rodneValue = new Text(pasazer.getRodne_cislo());
        div3Prow2.add(rodC, rodneValue);
        div3Prow2.getStyle().set("margin-left", "10%");
        profilRow2.add(div1Prow2, div2Prow2, div3Prow2);
        profilRow2.getStyle().set("margin-botton", "5%").set("border-bottom", "1px solid lightblue");
        FlexLayout profilRow3 = new FlexLayout();
        Div btnDiv = new Div();
        Button update = new Button();
        update.addClickListener(event -> {
            Dialog dialog = new Dialog();
            FlexLayout row1 = new FlexLayout();
            Div row1div1 = new Div();
            Text jmenoA = new Text("Jméno");
            TextArea jmenoField = new TextArea();
            jmenoField.setValue(pasazer.getJmeno());
            row1div1.add(jmenoA, jmenoField);
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
            Text email2 = new Text("Email");
            TextArea emailField = new TextArea();
            emailField.setValue(pasazer.getEmail());
            row2div1.add(email2, emailField);
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
            Text tel2 = new Text("Telefoní číslo");
            TextArea telField = new TextArea();
            telField.setValue(pasazer.getTelefoni_cislo());
            last.add(tel2, telField);
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
            Button closeButton = new Button("Zavřít", event3 -> dialog.close());
            closeButton.getStyle().set("margin-left", "10%");
            Icon icon11 = new Icon(VaadinIcon.CLOSE);
            closeButton.setIcon(icon11);
            rowLast.add(closeButton, uploadButton);
            dialog.add(rowLast);
            dialog.open();
        });
        update.setText("Úprava profilu");
        update.setIcon(new Icon(VaadinIcon.ARROW_CIRCLE_UP));
        btnDiv.add(update);
        btnDiv.getStyle().set("margin-left", "45%");
        profilRow3.add(btnDiv);
        profilRow3.getStyle().set("margin-botton", "5%");
        divProfil.add(profilRow1, profilRow2, profilRow3);
    }

    private void updateHistory() {
        if (divHistorie == null) {
            divHistorie = new Div();
        } else {
            divHistorie.removeAll();
        }
        historieLetenek.addColumn(Letenka::getLetenkaID).setHeader("ID");
        historieLetenek.addColumn(new ComponentRenderer<>(letenka -> {
            String value = "";
            if (letenka.getJeSkupinova() == 1) {
                value = "Skupinová";
            } else {
                value = "Individuální";
            }
            return new Span(value);
        })).setHeader("Skupinová");
        historieLetenek.addColumn(Letenka::getPocet_pasazeru).setHeader("Počet pasažerů");
        historieLetenek.addColumn(Letenka::getTrida).setHeader("Třída");
        historieLetenek.addColumn(new ComponentRenderer<>(letenka -> {
            Button showDetails = new Button();
            showDetails.setText("Detaily");
            showDetails.setIcon(new Icon(VaadinIcon.SEARCH));
            showDetails.addClickListener(event -> {
                Dialog dialog = new Dialog();

                letID = letenka.getLetID();
                System.out.println("size of ltHs" + letenkaHistories.size());
                for (int i = 0; i < letenkaHistories.size(); i++) {
                    LetenkaHistorie historie = letenkaHistories.get(i);
                    if (historie != null && historie.getLetId() == letID) {
                        letenkaH = historie;
                        break; // Pokud nalezena shoda, ukončíme cyklus
                    }
                }
                FlexLayout row = new FlexLayout();
                Div div = new Div();
                Text text1 = new Text("Letadlo ID: ");
                Text text2 = new Text(String.valueOf(letenkaH.getLetadloID()));
                div.add(text1, text2);
                div.getStyle().set("margin-left", "10%");
                Div div1 = new Div();
                Text text3 = new Text("Čas odletu: ");
                Text text4 = new Text(String.valueOf(letenkaH.getCas_Odletu().toString()));
                div1.add(text3, text4);
                div1.getStyle().set("margin-left", "30%");
                row.add(div, div1);
                dialog.add(row);
                FlexLayout row2 = new FlexLayout();
                Div div2 = new Div();
                Text text5 = new Text("Odlet  Město: ");
                Text text6 = new Text(letenkaH.getMestoOdletu());
                div2.add(text5, text6);
                div2.getStyle().set("margin-left", "2%");
                Div div3 = new Div();
                Text text7 = new Text("Název: ");
                Text text8 = new Text(letenkaH.getNazevLOdletu());
                div3.add(text7, text8);
                div3.getStyle().set("margin-left", "2%");
                Div div4 = new Div();
                Text text9 = new Text("Stát: ");
                Text text10 = new Text(letenkaH.getStatOdletu());
                div4.add(text9, text10);
                div4.getStyle().set("margin-left", "2%");
                row2.add(div2, div3, div4);
                dialog.add(row2);
                FlexLayout row3 = new FlexLayout();
                Div div5 = new Div();
                Text text11 = new Text("Přílet  Město: ");
                Text text12 = new Text(letenkaH.getMestoPriletu());
                div5.add(text11, text12);
                div5.getStyle().set("margin-left", "2%");
                Div div6 = new Div();
                Text text13 = new Text("Název: ");
                Text text14 = new Text(letenkaH.getNazevLPriletu());
                div6.add(text13, text14);
                div6.getStyle().set("margin-left", "2%");
                Div div7 = new Div();
                Text text15 = new Text("Stát: ");
                Text text16 = new Text(letenkaH.getStatPriletu());
                div7.add(text15, text16);
                div7.getStyle().set("margin-left", "2%");
                row3.add(div5, div6, div7);
                dialog.add(row3);
                FlexLayout rowLast = new FlexLayout();
                Button closeButton = new Button("Zavřít", event2 -> dialog.close());
                closeButton.getStyle().set("margin-left", "10%");
                Icon icon11 = new Icon(VaadinIcon.CLOSE);
                closeButton.setIcon(icon11);
                rowLast.add(closeButton);
                dialog.add(rowLast);
                dialog.open();
            });
            return showDetails;
        }));
        divHistorie.add(historieLetenek);
    }

    private void updateRegister() {
        if (divRegistrace == null) {
            divRegistrace = new Div();
        } else {
            divRegistrace.removeAll();
        }
        //here
        Text text = new Text("Registrace ");
        divRegistrace.add(text);
    }

    private void updateZavazadla() {
        if (divZavazadla == null) {
            divZavazadla = new Div();
        } else {
            divZavazadla.removeAll();
        }
        //here
        Text text = new Text("Má zavazadla ");
        divZavazadla.add(text);

        zavazadloGrid.addColumn(new ComponentRenderer<>(zavazadlo -> {
            String value = "";
            if (zavazadlo.getZavazadloID() == 1) {
                value = "Příruční";
            } else {
                value = "Odbavované";
            }
            return new Span(value);
        })).setHeader("Typ zavazadla");
        zavazadloGrid.addColumn(Zavazadlo::getSirka).setHeader("Šířka");
        zavazadloGrid.addColumn(Zavazadlo::getVyska).setHeader("Výška");
        zavazadloGrid.addColumn(Zavazadlo::getVaha).setHeader("Váha");
        zavazadloGrid.addColumn(new ComponentRenderer<>(zavazadlo -> {
            String value = "";
            if (zavazadlo.getKrehke() == 1) {
                value = "Křehké";
            } else {
                value = "Odolné";
            }
            return new Span(value);
        })).setHeader("Křehkost");
        zavazadloGrid.addColumn(new ComponentRenderer<>(zavazadlo -> {
            Button editButton = new Button("Úprava");
            editButton.addClickListener(event -> {
                Dialog dialog = new Dialog();

                FlexLayout row1 = new FlexLayout();
                Div row1div1 = new Div();
                Text sirka = new Text("Šířka");
                NumberField sirkaField = new NumberField();
                sirkaField.setValue(Double.valueOf(String.valueOf(zavazadlo.getSirka())));
                row1div1.add(sirka, sirkaField);
                row1div1.getStyle().set("padding-left", "10%");

                Div row1div2 = new Div();
                Text vyska = new Text("Výška");
                NumberField vyskaField = new NumberField();
                vyskaField.setValue((double) zavazadlo.getVyska());
                row1div2.add(vyska, vyskaField);
                row1div2.getStyle().set("padding-left", "10%");
                row1.add(row1div1, row1div2);
                dialog.add(row1);

                FlexLayout row2 = new FlexLayout();
                Div row2div1 = new Div();
                Text vaha = new Text("Váha");
                NumberField vahaField = new NumberField();
                vahaField.setValue((double) zavazadlo.getVaha());
                row2div1.add(vaha, vahaField);
                row2div1.getStyle().set("padding-left", "10%");

                Div row2div2 = new Div();
                Text krehke = new Text("Křehké -> 1 ");
                NumberField krehkeField = new NumberField();
                krehkeField.setValue((double) zavazadlo.getKrehke());
                row2div2.add(krehke, krehkeField);
                row2div2.getStyle().set("padding-left", "10%");
                row2.add(row2div1, row2div2);
                dialog.add(row2);

                FlexLayout row = new FlexLayout();
                Div div = new Div();
                Text typ = new Text("Typ 1 - Příruční 2 - Odbavované");
                NumberField typField = new NumberField();
                typField.setValue((double) zavazadlo.getTyp_zavazadlaID());
                div.add(typ, typField);
                row.add(div);
                dialog.add(row);

                FlexLayout rowLast = new FlexLayout();
                Button uploadButton = new Button("Uprav", event3 -> {
                    zavazadloService.findByIdAndUpdate(zavazadlo.getZavazadloID(), sirkaField.getValue().intValue(), vyskaField.getValue().intValue(), vahaField.getValue().intValue(), krehkeField.getValue().intValue(), typField.getValue().intValue());
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
                zavazadloService.findByIdAndDelete(zavazadlo.getZavazadloID());
                UI.getCurrent().getPage().reload();
            });
            Icon icon4 = new Icon(VaadinIcon.CLOSE_CIRCLE);
            deleteButton.setIcon(icon4);
            HorizontalLayout buttonLayout = new HorizontalLayout(editButton, deleteButton);
            return buttonLayout;
        })).setHeader("Akce");
        divZavazadla.add(zavazadloGrid);
        FlexLayout row = new FlexLayout();
        Div div = new Div();
        Button btnNew = new Button();
        btnNew.addClickListener(event -> {
            Dialog dialog = new Dialog();

            FlexLayout row1 = new FlexLayout();
            Div row1div1 = new Div();
            Text sirka = new Text("Šířka");
            NumberField sirkaField = new NumberField();
            row1div1.add(sirka, sirkaField);
            row1div1.getStyle().set("padding-left", "10%");

            Div row1div2 = new Div();
            Text vyska = new Text("Výška");
            NumberField vyskaField = new NumberField();
            row1div2.add(vyska, vyskaField);
            row1div2.getStyle().set("padding-left", "10%");
            row1.add(row1div1, row1div2);
            dialog.add(row1);

            FlexLayout row2 = new FlexLayout();
            Div row2div1 = new Div();
            Text vaha = new Text("Váha");
            NumberField vahaField = new NumberField();
            row2div1.add(vaha, vahaField);
            row2div1.getStyle().set("padding-left", "10%");

            Div row2div2 = new Div();
            Text krehke = new Text("Křehké -> 1 ");
            NumberField krehkeField = new NumberField();
            row2div2.add(krehke, krehkeField);
            row2div2.getStyle().set("padding-left", "10%");
            row2.add(row2div1, row2div2);
            dialog.add(row2);

            FlexLayout row4 = new FlexLayout();
            Div div2 = new Div();
            Text typ = new Text("Typ 1 - Příruční 2 - Odbavované");
            NumberField typField = new NumberField();
            div2.add(typ, typField);
            row4.add(div2);
            dialog.add(row4);

            FlexLayout rowLast = new FlexLayout();
            Button uploadButton = new Button("Nový", event3 -> {
                zavazadloService.createZavazadlo(pasazerId, sirkaField.getValue().intValue(), vyskaField.getValue().intValue(), vahaField.getValue().intValue(), krehkeField.getValue().intValue(), typField.getValue().intValue());
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
        btnNew.setText("Nové zavazadlo");
        div.add(btnNew);
        div.getStyle().set("margin-left", "50%");
        row.add(div);
        divZavazadla.add(row);
    }

    @PostConstruct
    private void init() {
        // Po inicializaci komponent vytvoříme profil na základě ID předaného v URL
        List<Pasazer> pasazers = pasazerService.findByID(pasazerId);
        pasazer = pasazers.isEmpty() ? null : pasazers.getFirst();
        zavazadloList = zavazadloService.findByPasazerId(pasazerId);
        zavazadloGrid.setItems(zavazadloList);
        historieList = letenkaService.findByPasazer(pasazerId);
        historieLetenek.setItems(historieList);
        letenkaHistories = letenkaHistorieService.findAll();
        if (pasazer == null) {
        } else {
            updateProfile(); // Aktualizace profilu
            updateRegister();
            updateHistory();
            updateZavazadla();
        }
    }
}