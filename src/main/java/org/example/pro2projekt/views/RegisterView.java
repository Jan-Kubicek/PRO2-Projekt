package org.example.pro2projekt.views;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.example.pro2projekt.service.PasazerService;
import org.example.pro2projekt.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("register")
@Route("/register")
@AnonymousAllowed
public class RegisterView extends VerticalLayout {
    private final Validator validate = new Validator();
    private final PasazerService pasazerService;
    TextArea jmenoField, prijmeniField, emailField;
    TextArea rodnecisloField, telefonniCisloField;
    PasswordField hesloField;
    Button btnRegister, btnBack;
    RadioButtonGroup<String> pohlavi;
    String jmeno = "", prijmeni = "", rodCi = "", telCis = "", heslo = "", email = "", pohla = "";

    @Autowired
    public RegisterView(PasazerService pasazerService) {
        this.pasazerService = pasazerService;
        Div main = new Div();
        H1 h = new H1("Register");
        main.add(h);
        FlexLayout row1 = new FlexLayout();
        Div name = new Div();
        Text lblJmeno = new Text("Zadejte své jméno: ");
        jmenoField = new TextArea();
        name.add(lblJmeno, jmenoField);
        name.setWidth("50%");

        Div surename = new Div();
        Text lblPrijmeni = new Text("Zadejte své přijmení: ");
        prijmeniField = new TextArea();
        surename.add(lblPrijmeni, prijmeniField);
        surename.setWidth("50%");
        row1.add(name, surename);

        FlexLayout row2 = new FlexLayout();
        Div gender = new Div();
        Text lblPohlavi = new Text("Vyberte své pohlaví: ");
        pohlavi = new RadioButtonGroup<>();
        pohlavi.setItems("Muž", "Žena");
        pohlavi.setValue("Muž");
        gender.add(lblPohlavi, pohlavi);
        gender.getStyle().set("padding-left", "30%");
        row2.add(gender);

        FlexLayout row3 = new FlexLayout();
        Div rodC = new Div();
        Text rc = new Text("Zadejte své rodné číslo: ");
        rodnecisloField = new TextArea();
        rodC.add(rc, rodnecisloField);
        rodC.getStyle().set("padding-left", "30%");
        row3.add(rodC);

        FlexLayout row3a = new FlexLayout();
        Div tlf = new Div();
        Text flt = new Text("Zadejte své telefonní číslo: ");
        telefonniCisloField = new TextArea();
        tlf.add(flt, telefonniCisloField);
        tlf.getStyle().set("padding-left", "30%");
        row3a.add(tlf);

        FlexLayout row4 = new FlexLayout();
        Div pss = new Div();
        Text ps = new Text("Zadejte své budoucí heslo: ");
        hesloField = new PasswordField();
        pss.add(ps, hesloField);
        pss.getStyle().set("padding-left", "30%");
        row4.add(pss);

        FlexLayout row4b = new FlexLayout();
        Div eml = new Div();
        Text em = new Text("Zadejte svůj email: ");
        emailField = new TextArea();
        eml.add(em, emailField);
        eml.getStyle().set("padding-left", "30%");
        row4b.add(eml);

        FlexLayout row5 = new FlexLayout();
        Div btnF = new Div();
        btnRegister = new Button("Register");
        btnF.add(btnRegister);
        btnF.setWidth("50%");
        btnF.getStyle().set("padding-left", "20%");
        Div btnL = new Div();
        btnBack = new Button("Zpět na původní stránku");
        btnL.add(btnBack);
        btnL.setWidth("50%");
        row5.add(btnF, btnL);

        jmenoField.addValueChangeListener(event -> jmeno = event.getValue());
        prijmeniField.addValueChangeListener(event -> prijmeni = event.getValue());
        rodnecisloField.addValueChangeListener(event -> rodCi = event.getValue());
        telefonniCisloField.addValueChangeListener(event -> telCis = event.getValue());
        hesloField.addValueChangeListener(event -> heslo = event.getValue());
        pohlavi.addValueChangeListener(event -> pohla = event.getValue());
        emailField.addValueChangeListener(event -> email = event.getValue());

        btnRegister.addClickListener(event -> {
            pasazerService.createPasazer(jmeno, prijmeni, email, rodCi, telCis, heslo, pohla);
            getUI().ifPresent(ui -> ui.navigate(LoginView.class));
        });
        btnBack.addClickListener(event -> getUI().ifPresent(ui -> ui.navigate(Index.class)));

        main.add(row1, row2, row3, row3a, row4, row4b, row5);
        add(main);
        main.setWidth("80%");
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }
}
