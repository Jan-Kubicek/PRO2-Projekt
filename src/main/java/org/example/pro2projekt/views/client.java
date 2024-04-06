package org.example.pro2projekt.views;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
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
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.*;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.annotation.PostConstruct;
import org.example.pro2projekt.objects.Letenka;
import org.example.pro2projekt.objects.Pasazer;
import org.example.pro2projekt.objects.Zavazadlo;
import org.example.pro2projekt.service.LetenkaService;
import org.example.pro2projekt.service.PasazerService;
import org.example.pro2projekt.service.PasazerServiceImpl;
import org.example.pro2projekt.service.ZavazadloService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@PageTitle("client")
@Route("/client/")
public class client extends VerticalLayout implements HasUrlParameter<String> {
    @Autowired
    private PasazerService pasazerService;
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
        row1div2.getStyle().set("padding-left","60%").set("padding-top","1%");
        row1.add(row1div1,row1div2);
        row1.setWidth("90%");
        add(row1);

        divZavazadla = new Div();
        divProfil = new Div();
        divHistorie = new Div();
        divRegistrace = new Div();
        tabSheet = new TabSheet();
        tabSheet.add("Můj profil", divProfil);
        tabSheet.add("Historie",divHistorie);
        tabSheet.add("Registrace Letenek",divRegistrace);
        tabSheet.add("Moje zavazadlo",divZavazadla);
        add(tabSheet);
        tabSheet.setWidth("90%");
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
            div1Prow1.getStyle().set("margin-left","10%");
            Div div2Prow1 = new Div();
                Text prijemni = new Text("Přijmení: ");
                Text prijmeniValue = new Text(pasazer.getPrijmeni());
            div2Prow1.add(prijemni,prijmeniValue);
            div2Prow1.getStyle().set("margin-left","5%");
        Div div3Prow1 = new Div();
            Text datumNaroz = new Text("Datum narození: ");
            Text datumNarozValue = new Text(pasazer.getDatum_narozeni().toString());
        div3Prow1.add(datumNaroz,datumNarozValue);
        div3Prow1.getStyle().set("margin-left","10%");
        profilRow1.add(div1Prow1,div2Prow1,div3Prow1);
        profilRow1.getStyle().set("margin-botton","5%").set("border-bottom","1px solid lightblue");
        FlexLayout profilRow2 = new FlexLayout();
            Div div1Prow2 = new Div();
                Text email = new Text("Email: ");
                Text emailValue = new Text(pasazer.getEmail());
            div1Prow2.add(email,emailValue);
            div1Prow2.getStyle().set("margin-left","10%");
            Div div2Prow2 = new Div();
                Text tel = new Text("Telefoní číslo: ");
                Text telValue = new Text(pasazer.getTelefoni_cislo());
            div2Prow2.add(tel,telValue);
            div2Prow2.getStyle().set("margin-left","5%");
            Div div3Prow2 = new Div();
                Text rodC = new Text("Rodné číslo");
                Text rodneValue = new Text(pasazer.getRodne_cislo());
            div3Prow2.add(rodC,rodneValue);
            div3Prow2.getStyle().set("margin-left","10%");
        profilRow2.add(div1Prow2,div2Prow2,div3Prow2);
        profilRow2.getStyle().set("margin-botton","5%").set("border-bottom","1px solid lightblue");
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
                        row1div1.add(jmenoA,jmenoField);
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
                        Text email2 = new Text("Email");
                        TextArea emailField = new TextArea();
                        emailField.setValue(pasazer.getEmail());
                        row2div1.add(email2,emailField);
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
                        Text tel2 = new Text("Telefoní číslo");
                        TextArea telField = new TextArea();
                        telField.setValue(pasazer.getTelefoni_cislo());
                        last.add(tel2,telField);
                        last.getStyle().set("padding-left","25%");
                        row3.add(last);
                        dialog.add(row3);

                        FlexLayout rowLast = new FlexLayout();
                        Button uploadButton = new Button("Uprav", event3 ->{
                            pasazerService.findByIdAndUpdate(pasazer.getPasazerID(),jmenoField.getValue(),prijmeniField.getValue(),emailField.getValue(),rodneCisloField.getValue(),telField.getValue());
                            dialog.close();
                            UI.getCurrent().getPage().reload();
                        });
                        Icon icon10 = new Icon(VaadinIcon.CHECK);
                        uploadButton.setIcon(icon10);
                        uploadButton.getStyle().set("margin-left","40%");
                        Button closeButton = new Button("Zavřít", event3 -> dialog.close());
                        closeButton.getStyle().set("margin-left","10%");
                        Icon icon11 = new Icon(VaadinIcon.CLOSE);
                        closeButton.setIcon(icon11);
                        rowLast.add(closeButton,uploadButton);
                        dialog.add(rowLast);
                        dialog.open();
                    });
                update.setText("Úprava profilu");
                update.setIcon(new Icon (VaadinIcon.ARROW_CIRCLE_UP));
                btnDiv.add(update);
            btnDiv.getStyle().set("margin-left","45%");
        profilRow3.add(btnDiv);
        profilRow3.getStyle().set("margin-botton","5%");
        divProfil.add(profilRow1,profilRow2,profilRow3);
    }

    private void updateHistory() {
        if (divHistorie == null) {
            divHistorie = new Div();
        } else {
            divHistorie.removeAll();
        }
        //here
        Text text = new Text("historie ");
        divHistorie.add(text);
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
        Text text = new Text("Zavazadla ");
        divZavazadla.add(text);
    }

    @PostConstruct
    private void init() {
        // Po inicializaci komponent vytvoříme profil na základě ID předaného v URL
        List<Pasazer> pasazers = pasazerService.findByID(pasazerId);
        pasazer = pasazers.isEmpty() ? null : pasazers.getFirst();
        if (pasazer == null) {
        } else {
            updateProfile(); // Aktualizace profilu
            updateRegister();
            updateHistory();
            updateZavazadla();
        }
    }
}