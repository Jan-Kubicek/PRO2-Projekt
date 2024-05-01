package org.example.pro2projekt.views;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
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
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinSession;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.security.RolesAllowed;
import org.example.pro2projekt.objects.*;
import org.example.pro2projekt.security.SecurityService;
import org.example.pro2projekt.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@PageTitle("client")
@Route("/client")
@RolesAllowed("CLIENT")
public class ClientView extends VerticalLayout implements HasUrlParameter<String> {
    private final PasazerService pasazerService;
    private final ZavazadloService zavazadloService;
    private final LetenkaService letenkaService;
    private final LetenkaHistorieService letenkaHistorieService;
    private final LetenkaRegisterService letenkaRegisterService;
    private final SecurityService securityService;
    private Pasazer pasazer;
    private final Grid<Letenka> historieLetenek = new Grid<>(Letenka.class, false);
    private final Grid<LetenkaRegister> registraceLetenek = new Grid<>(LetenkaRegister.class, false);
    private final Grid<Zavazadlo> zavazadloGrid = new Grid<>(Zavazadlo.class, false);
    private List<Letenka> historieList;
    private List<LetenkaRegister> registerList;
    private List<Zavazadlo> zavazadloList;
    private List<LetenkaHistorie> letenkaHistories;
    private List<String> classesList, statesList;
    DatePicker datumODletuFiled;
    ComboBox<String> odletField, priletField;
    ComboBox<Integer> pocetOsobField;
    ComboBox<String> tridaFiled;
    private int pasazerId, letID = 0;
    LetenkaHistorie letenkaH;
    Button btnLogout, btnSubmit;
    TabSheet tabSheet;
    Div divZavazadla, divProfil, divHistorie, divRegistrace;

    @Autowired
    public ClientView(SecurityService securityService, PasazerService pasazerService, ZavazadloService zavazadloService, LetenkaService letenkaService, LetenkaHistorieService letenkaHistorieService, LetenkaRegisterService letenkaRegisterService) {
        this.securityService = securityService;
        this.pasazerService = pasazerService;
        this.zavazadloService = zavazadloService;
        this.letenkaService = letenkaService;
        this.letenkaRegisterService = letenkaRegisterService;
        this.letenkaHistorieService = letenkaHistorieService;
        VaadinSession vaadinSession = VaadinSession.getCurrent();
        System.out.println(vaadinSession.getAttribute("loggedInUser"));
        System.out.println(vaadinSession.getAttribute("userRole"));
        // Inicializace komponent v konstruktoru
        FlexLayout row1 = new FlexLayout();
        Div row1div1 = new Div();
        H1 text = new H1("Klientský účet");
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
        div.getStyle().set("margin-left", "10%").set("font-size", "1.3em").set("color", "blue").set("font-weight", "bolder");
        Div div1 = new Div();
        div1.add(text2);
        div1.getStyle().set("margin-left", "70%").set("font-size", "1.3em").set("color", "blue").set("font-weight", "bolder");
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
                if (pasazer != null) {
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

            FlexLayout row4 = new FlexLayout();
            Div last2 = new Div();
            Text datumNa = new Text("Datum narození");
            DatePicker datePicker = new DatePicker();
            last2.add(datumNa, datePicker);
            last2.getStyle().set("padding-left", "25%");
            row4.add(last2);
            dialog.add(row4);

            FlexLayout rowLast = new FlexLayout();
            Button uploadButton = new Button("Uprav", event3 -> {
                pasazerService.findByIdAndUpdate(pasazer.getPasazerID(), jmenoField.getValue(), prijmeniField.getValue(), emailField.getValue(), rodneCisloField.getValue(), telField.getValue(), datePicker.getValue().toString());
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
            String value;
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
                for (LetenkaHistorie historie : letenkaHistories) {
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

            FlexLayout l = new FlexLayout();
            l.add(showDetails);
            Date current = new Date();
            letID = letenka.getLetID();
            for (LetenkaHistorie historie : letenkaHistories) {
                if (historie.getCas_Odletu().after(current) && letID == historie.getLetId()) {
                    Button del = new Button("Zrušení letenky");
                    Icon icon = new Icon(VaadinIcon.CLOSE);
                    del.setIcon(icon);
                    del.addClickListener(event -> {
                        letenkaService.deleteLetenka(letenka.getLetenkaID());
                        UI.getCurrent().getPage().reload();
                    });
                    l.add(del);
                    break;
                }
            }
            return l;
        }));
        divHistorie.add(historieLetenek);
    }

    private void updateRegister() {
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
        registraceLetenek.addColumn(new ComponentRenderer<>(letenkaRegister -> {
            Button register = new Button("Registruj");
            register.addClickListener(event -> {
                Dialog dialog = new Dialog();

                FlexLayout rowLast = new FlexLayout();
                Button back = new Button("Zpět", event2 -> dialog.close());
                Icon icon2 = new Icon(VaadinIcon.CLOSE);
                back.setIcon(icon2);
                Button set = new Button("Potvrd", event3 -> {
                    int jeSkupinova = 0;
                    if (pocetOsobField.getValue() > 1) {
                        jeSkupinova = 1;
                    }
                    letenkaService.createNewLetenka(letenkaRegister.getLetId(), pasazerId, jeSkupinova, pocetOsobField.getValue(), tridaFiled.getValue());
                    dialog.close();
                    UI.getCurrent().getPage().reload();
                });
                Icon icon1 = new Icon(VaadinIcon.CHECK);
                set.setIcon(icon1);
                rowLast.add(back, set);
                dialog.add(rowLast);
                dialog.open();
            });
            return register;
        })).setHeader("Registrace");
        divRegistrace.add(registraceLetenek);
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
            String value;
            if (zavazadlo.getZavazadloID() == 1) {
                value = "Příruční";
            } else {
                value = "Odbavované";
            }
            return new Span(value);
        })).setHeader("Typ zavazadla");
        zavazadloGrid.addColumn(Zavazadlo::getSirka).setHeader("Šířka [cm]");
        zavazadloGrid.addColumn(Zavazadlo::getVyska).setHeader("Výška [cm]");
        zavazadloGrid.addColumn(Zavazadlo::getHloubka).setHeader("Hloubka [cm]");
        zavazadloGrid.addColumn(Zavazadlo::getVaha).setHeader("Váha [Kg]");
        zavazadloGrid.addColumn(new ComponentRenderer<>(zavazadlo -> {
            String value;
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
                Text sirka = new Text("Šířka [cm]");
                NumberField sirkaField = new NumberField();
                sirkaField.setValue(Double.valueOf(String.valueOf(zavazadlo.getSirka())));
                row1div1.add(sirka, sirkaField);
                row1div1.getStyle().set("padding-left", "10%");

                Div row1div2 = new Div();
                Text vyska = new Text("Výška [cm]");
                NumberField vyskaField = new NumberField();
                vyskaField.setValue((double) zavazadlo.getVyska());
                row1div2.add(vyska, vyskaField);
                row1div2.getStyle().set("padding-left", "10%");
                row1.add(row1div1, row1div2);
                dialog.add(row1);

                FlexLayout row2 = new FlexLayout();
                Div row2div1 = new Div();
                Text vaha = new Text("Váha [Kg]");
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

                FlexLayout rowW = new FlexLayout();
                Div diva = new Div();
                Text type = new Text("Hloubka [cm]");
                NumberField hloubkaField = new NumberField();
                hloubkaField.setValue((double) zavazadlo.getHloubka());
                diva.add(type, hloubkaField);
                rowW.add(diva);
                dialog.add(rowW);

                FlexLayout rowLast = new FlexLayout();
                Button uploadButton = new Button("Uprav", event3 -> {
                    zavazadloService.findByIdAndUpdate(zavazadlo.getZavazadloID(), sirkaField.getValue().intValue(), vyskaField.getValue().intValue(), vahaField.getValue().intValue(), krehkeField.getValue().intValue(), typField.getValue().intValue(), hloubkaField.getValue().intValue());
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
        })).setHeader("Akce").setWidth("25%");
        divZavazadla.add(zavazadloGrid);
        FlexLayout row = new FlexLayout();
        Div div = new Div();
        Button btnNew = new Button();
        btnNew.addClickListener(event -> {
            Dialog dialog = new Dialog();

            FlexLayout row1 = new FlexLayout();
            Div row1div1 = new Div();
            Text sirka = new Text("Šířka [cm]");
            NumberField sirkaField = new NumberField();
            row1div1.add(sirka, sirkaField);
            row1div1.getStyle().set("padding-left", "10%");

            Div row1div2 = new Div();
            Text vyska = new Text("Výška [cm]");
            NumberField vyskaField = new NumberField();
            row1div2.add(vyska, vyskaField);
            row1div2.getStyle().set("padding-left", "10%");
            row1.add(row1div1, row1div2);
            dialog.add(row1);

            FlexLayout row2 = new FlexLayout();
            Div row2div1 = new Div();
            Text vaha = new Text("Váha [Kg]");
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

            FlexLayout row5 = new FlexLayout();
            Div div3 = new Div();
            Text typP = new Text("Hloubka [cm]");
            NumberField hloubkaField = new NumberField();
            div3.add(typP, hloubkaField);
            row5.add(div3);
            dialog.add(row5);

            FlexLayout rowLast = new FlexLayout();
            Button uploadButton = new Button("Nový", event3 -> {
                zavazadloService.createZavazadlo(pasazerId, sirkaField.getValue().intValue(), vyskaField.getValue().intValue(), vahaField.getValue().intValue(), krehkeField.getValue().intValue(), typField.getValue().intValue(), hloubkaField.getValue().intValue());
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
        registerList = letenkaRegisterService.findAll();
        statesList = letenkaService.getAllStates();
        classesList = letenkaService.getAllClasses();
        if (pasazer != null) {
            updateProfile(); // Aktualizace profilu
            updateRegister();
            updateHistory();
            updateZavazadla();
        }
    }
}